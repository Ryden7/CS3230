package StudentChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler implements Runnable{
	private ServerSocket ss;
	private Socket socket;
	public ServerHandler() throws IOException
	{

	}

	//This class handles starting the server
	@Override
	public void run() {
		
		try {
			ss = new ServerSocket(8090);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(true)
		{
			try {
				socket = ss.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new MultiServerHandler(socket)).start();
		}

			
	}
	

}
