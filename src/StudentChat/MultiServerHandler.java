package StudentChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiServerHandler implements Runnable{
	protected Socket socket;
	private PrintWriter output; 
	private BufferedReader input;
	private static int j = 0;
	private static HashMap<Integer, Socket> list = new HashMap<Integer, Socket>();
	private static ArrayList<String> connectedUsers = new ArrayList<String>();

	private boolean set;


	private String studentName;
	
	public MultiServerHandler(Socket incSocket)
	{
        socket = incSocket;
        set = false;


	}

	@Override
	public void run() {
		
		try 
		{
			//input from client
			 input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//output from server
			 output = new PrintWriter(socket.getOutputStream(), true);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		
		//initializes class
		Group Groups = new Group();
		List<Student> temp = Groups.CreateStudents();
		Groups.GenerateGroups(temp);
		Student s = Groups.findStudent("Rizwan");
		Student r = Groups.findStudent("Brieanna");
		Student current = null;
		Groups.Chat(s, "Hello");
		Groups.Chat(s, "asdf");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "God damn lag!");
		
		String outputLine = null, inputLine = null;


			//server receives input from the user here
		    try {
				while ((inputLine = input.readLine()) != null) 
				{
					if (set == false)
					{
						studentName = inputLine;
						if (!connectedUsers.contains(studentName))
						{
							list.put(j, socket);
							j++;
							connectedUsers.add(studentName);
							outputLine = "ACK\n";
							if (Groups.findStudent(studentName) != null)
							{
								ArrayList<String> conversations = Groups.FindConversationsofStudent(studentName);
								current = Groups.findStudent(studentName);

								int size = conversations.size();
								
								for(int i = 0; i < size; i++)
								{
									outputLine = conversations.get(i);
									output.println(outputLine);
									output.flush();
									outputLine = "";
								}
								set = true;
								continue;
							}

							

						}
						else
						{
							outputLine = "DENY\n";
							output.println(outputLine);							
							socket.close();
							return;
							
						}
						

					}
					
					//sends the messages to all other students
					//NOTE: to join the chat room, the student must be part of the class!
					for(int i = 0; i < list.size(); i++)
					{
						Socket allSockets = list.get(i);
						PrintWriter output2 = new PrintWriter(allSockets.getOutputStream(), true);
						output2.println(current.firstName + " " + current.lastName + " : " + inputLine);
					}

					output.flush();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		    	    			
		
	}


}
