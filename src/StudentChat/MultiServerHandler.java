package StudentChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiServerHandler extends Thread{
	private ServerSocket ss;
	private Socket socket;
	private PrintWriter output; 
	private BufferedReader input;
	private HashMap<String, Socket> list;
	private boolean set;


	private String studentName;
	
	public MultiServerHandler(Socket incSocket)
	{
        super("MultiServerHandler");
        socket = incSocket;
        set = false;
        list = new HashMap<String, Socket>();


	}

	@Override
	public void run() {
		
		try {
			 input = new BufferedReader(
			        new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			 output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		
		
		
		
		String outputLine, inputLine;
		
		Group Groups = new Group();
		List<Student> temp = Groups.CreateStudents();
		Groups.GenerateGroups(temp);
		Student s = Groups.findStudent("Rizwan");
		Student r = Groups.findStudent("Brieanna");
		Groups.Chat(s, "Hello");
		Groups.Chat(s, "asdf");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "God damn lag!");
		ArrayList<String> conversations = Groups.FindConversationsofStudent(studentName);
		Student current = Groups.findStudent(studentName);
		//int size = conversations.size();
		
		

			//inputLine = studentName;
			//server receives input from the user here
		    try {
				while ((inputLine = input.readLine()) != null) 
				{
					if (set == false)
					{
						studentName = inputLine;
						list.put(inputLine, socket);
						set = true;
						break;

					}
					
					Groups.Chat(current, inputLine);
				    outputLine = inputLine;
				    output.println(current.firstName + " " + current.lastName + " : " + outputLine);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    /*
		 finally {
			try 
			{
				input.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.close();
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		    
		    //server sends stuff here
		  
		    if (list.containsKey(studentName))
		    	outputLine = "DENY\n";
		    else
		    	outputLine = "ACK\n";
		    	
			
			try{

					output.println(outputLine);
					output.flush();
					
				
			}
			finally
			{
				
			}
					
					//outputLine = conversations.get(i);

					/*
					if (i == size-1)
					{

						outputLine = conversations.get(size-1);
						output.println(outputLine);
						output.flush();
						
						break;


					}
					else
					{
						i++;

					}
					
				}
				*/
			
	
		
		
	}


}
