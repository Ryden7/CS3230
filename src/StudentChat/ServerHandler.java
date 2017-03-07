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
import java.util.List;

public class ServerHandler implements Runnable{
	private ServerSocket ss;
	private Socket socket;
	private PrintWriter output; 
	private BufferedReader input;


	private String studentName;
	
	public ServerHandler(ServerSocket s, String name) throws IOException
	{
		ss = s;
		studentName = name;
	}

	@Override
	public void run() {
		
		try 
		{
			socket = ss.accept();
		} catch (IOException e2) 
		{
			e2.printStackTrace();
		}
		try {
			//input = socket.getInputStream();
			 input = new BufferedReader(
			        new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			//output = socket.getOutputStream();
			 output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		String outputLine, inputLine;
		
		Group Groups = new Group();
		List<Student> temp = Groups.CreateStudents();
		Groups.GenerateGroups(temp);
		ArrayList<String> conversations = Groups.FindConversationsofStudent(studentName);
		Student current = Groups.findStudent(studentName);
		int i = 0;
		outputLine = conversations.get(i);
		try{
			while (outputLine != null)
			{
				output.println(outputLine);
				output.flush();
				i++;
				outputLine = conversations.get(i);

			}
			

		    try {
				while ((inputLine = input.readLine()) != null) {
					Groups.Chat(current, inputLine);
				    outputLine = inputLine;
				    output.println(outputLine);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				input.close();
			} catch (IOException e) {
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
		System.out.println(Thread.currentThread() + "new thread");
	}

}
