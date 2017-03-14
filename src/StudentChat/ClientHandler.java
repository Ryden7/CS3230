package StudentChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
		Socket myClient = null;
        BufferedReader in = null;

	
		 try {
			 
			 String fromServer;
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
					e1.printStackTrace();
				}
				try {
					in = new BufferedReader(
					    new InputStreamReader(myClient.getInputStream()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			    
				 fromUser = studentName;
				    if (fromUser != null) {
				        System.out.println("Client: " + fromUser);
				        out.println(fromUser);
				    }
				        
			    try {
					while ((fromServer = in.readLine()) != null) 
					{
					    System.out.println("Server: " + fromServer);
					    GUI.textArea.append(fromServer + "\n");
					    fromServer = "";
					   
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			
		 	}
		 
		 		//starts a server in case one doesn't exist
				catch(Exception e) 
		 	{
					try
					{				
						new Thread(new ServerHandler()).start();
					}
					catch(Exception e2)
					{
						e2.printStackTrace();
					}
					
					finally {
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
							run();
						
					}

				
		}
		 

}
}
