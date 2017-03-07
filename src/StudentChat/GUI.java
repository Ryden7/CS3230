package StudentChat;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea messageArea; 
	Group Groups;
	Student s;
	Student r;
	JTextField field;
	JTextArea textArea;
	String name;
	
	public static void main(String[] args) throws IOException 
	{
		new GUI();
	}
	
	public GUI() throws IOException
	{
		  InetAddress address;
			BufferedReader in = null;
			PrintWriter out;
			BufferedReader stdIn;
		
		  JTextField nameField = new JTextField(5);
	      JTextField ipField = new JTextField(5);

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("Name"));
	      myPanel.add(nameField);
	      myPanel.add(new JLabel("IP address"));
	      myPanel.add(ipField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter Name and IP", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.CANCEL_OPTION) 
	      {
	    	  return;
	      }
	      
	      
			try {
				address = InetAddress.getByName(ipField.getText());
				
				 try {
			           Socket MyClient = new Socket(address, 8090);
			           out = new PrintWriter(MyClient.getOutputStream(), true);
			           in = new BufferedReader(
			               new InputStreamReader(MyClient.getInputStream()));
			           
			           stdIn =
			        	        new BufferedReader(
			        	            new InputStreamReader(System.in));
			           
			           String userInput;
			           while ((userInput = stdIn.readLine()) != null) {
			               out.println(userInput);
			               System.out.println("echo: " + in.readLine());
			           }
			} catch (Exception e1)
			{
				ServerSocket ss = new ServerSocket(8090);
				
				//Socket s = ss.accept();
				ServerHandler sh = new ServerHandler(ss, nameField.getText());
				name = nameField.getText();
				
				new Thread(sh).start();
			}
			}
			 catch (Exception e)
		    {
					ServerSocket ss = new ServerSocket(8090);
					
					//new Thread(new ServerHandler(ss);

					//Socket s = ss.accept();
					ServerHandler sh = new ServerHandler(ss, nameField.getText());
					new Thread(sh).start();
					name = nameField.getText();
				//e.printStackTrace();
		    }
		
	    
		Groups = new Group();
		List<Student> temp = Groups.CreateStudents();
		Groups.GenerateGroups(temp);		
		Student s = Groups.findStudent("Rizwan");
		Student r = Groups.findStudent("Brieanna");
		Groups.Chat(s, "Hello");
		Groups.Chat(s, "asdf");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "God damn lag!");
		

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(BoxLayout.Y_AXIS));	

		
		JButton button = new JButton();

		add(panel);
		


		//add(panel2);

		
		textArea = new JTextArea(15, 30);
		textArea.setEditable(false);
		
		messageArea = new JTextArea(1, 1);
		field = new JTextField();
		
		messageArea.setLineWrap(true);
		messageArea.setSize(500, 500);
		//field.addActionListener(new ClassListener());
		messageArea.addKeyListener(new ClassListener());


		//textArea.setAlignmentX(CENTER_ALIGNMENT);
		


		//messageArea.setSize(50, 50);	//does nothing
		
		JScrollPane scrollPane = 
			new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(100, 100);
		
		panel.add(scrollPane);
		panel.add(messageArea);
		panel.add(button);
		//panel.add(field);


		
		setSize(600, 600);
		setVisible(true);
	}
	
	
	private class ClassListener implements KeyListener {
		
	    private final HashSet<Character> pressed = new HashSet<Character>();

		String text;
		@Override
		public void keyPressed(KeyEvent arg0) 
		{
	        if (arg0.getKeyCode() == KeyEvent.VK_CONTROL)
	            pressed.add(arg0.getKeyChar());
	        
	        if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
	            pressed.add(arg0.getKeyChar());

	        /*
			if (arg0.getKeyCode() == KeyEvent.VK_SHIFT)
				messageArea.append("\n");
				*/
			
			if (pressed.size() > 1)
			{
				if (!(messageArea.getText().equals(null)))
				{
					text = messageArea.getText();
					
					//How do u know which student this is?
					Student s = Groups.findStudent(name);
					Groups.Chat(s, text);
					
					
					// + "\n"
					textArea.append(s.firstName + " " + s.lastName + " : " + text + "\n");
					
					
					messageArea.setText("");

					
				}

				
				else {
					//messageArea.setText("it's a text field");
				}		
			}

			
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
	        pressed.remove(e.getKeyChar());
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	};
	

}
