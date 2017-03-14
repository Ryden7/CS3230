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
	
	public ServerHandler() throws IOException
	{
		/*
		ServerSocket ss = new ServerSocket(8091);
		socket = ss.accept();
		*/
		ss = new ServerSocket(8091);


		//test();
		
	}


	@Override
	public void run() {
		
		while(true)
		{
			try {
				socket = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new MultiServerHandler(socket)).start();
		}

			
	}
	

}
