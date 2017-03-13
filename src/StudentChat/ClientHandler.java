package StudentChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler implements Runnable{
	
	String studentName;
	InetAddress address;
	static PrintWriter out;
	static BufferedReader stdIn = null;
	static String fromUser;
	
	
	public ClientHandler(String name, InetAddress ip)
	{
		studentName = name;
		address = ip;
	}

	@Override
	public void run() 
	{
		BufferedReader in = null;

		Socket myClient = null;
	
		 try {
	           myClient = new Socket(address, 8090);
	           out = new PrintWriter(myClient.getOutputStream(), true);
	           in = new BufferedReader(
	               new InputStreamReader(myClient.getInputStream()));
	           
	           stdIn =
	        	        new BufferedReader(
	        	            new InputStreamReader(System.in));
	           
	           String userInput;
	           while ((userInput = stdIn.readLine()) != null) 
	           {
	               out.println(userInput);
	               System.out.println("echo: " + in.readLine());
	           }
		 	}
				catch(Exception e) 
		 	{
					try
					{
						ServerSocket ss = new ServerSocket(8090);
						
						ServerHandler sh = new ServerHandler(ss, studentName);
						new Thread(sh).start();

					}
					catch(Exception e2)
					{
						e2.printStackTrace();
					}
					
					finally {
						String fromServer;
					    Socket myClient2 = null;
					    PrintWriter out2 = null;
						try {
							myClient = new Socket("localhost", 8090);
						}
						catch (IOException e1) 
						{
							e1.printStackTrace();
						}
					    try {
					    	out = new PrintWriter(myClient.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					   // BufferedReader in = null;
						try {
							in = new BufferedReader(
							    new InputStreamReader(myClient.getInputStream()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    try {
							while ((fromServer = in.readLine()) != null) 
							{
							    System.out.println("Server: " + fromServer);
							    GUI.textArea.append(fromServer + "\n");
							    fromServer = "";
							    

							    /*
							    GUI.fromUser = stdIn.readLine();
							    if (GUI.fromUser != null) {
							        System.out.println("Client: " + GUI.fromUser);
							        out2.println(GUI.fromUser);
							        
							    }
							    */
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}

				
		}
		 

}
}
