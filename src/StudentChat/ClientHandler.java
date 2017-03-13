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
	
	public ClientHandler(String name, InetAddress ip)
	{
		studentName = name;
		address = ip;
	}

	@Override
	public void run() 
	{
		BufferedReader in = null;
		PrintWriter out;
		BufferedReader stdIn;
	
		 try {
	           Socket MyClient = new Socket(address, 8090);
	           out = new PrintWriter(MyClient.getOutputStream(), true);
	           in = new BufferedReader(
	               new InputStreamReader(MyClient.getInputStream()));
	           
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
						try {
							myClient2 = new Socket("localhost", 8090);
						}
						catch (IOException e1) 
						{
							
						}
					    try {
							PrintWriter out2 = new PrintWriter(myClient2.getOutputStream(), true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    BufferedReader in2 = null;
						try {
							in2 = new BufferedReader(
							    new InputStreamReader(myClient2.getInputStream()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    try {
							while ((fromServer = in2.readLine()) != null) 
							{
							    System.out.println("Server: " + fromServer);
							    GUI.textArea.append(fromServer + "\n");
							    fromServer = "";
							    

							    /*
							    fromUser = stdIn.readLine();
							    if (fromUser != null) {
							        System.out.println("Client: " + fromUser);
							        out.println(fromUser);
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
