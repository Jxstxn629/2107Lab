package tcpclientapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream; 
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter; 
import java.net.Socket;

public class TCPClientApp {
	public static void main(String[] args) 
	{
		String hostName = "127.0.0.1";
		int port = 12345;
		try
		{
			Socket socket = new Socket(hostName, port);
			String request = "GET TIME";
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(request);
			pw.flush();
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String header = br.readLine();
			System.out.println("Response: " + header);
		}
		catch(IOException ex)
		{
			ex.printStackTrace(); 
		}
	}
}
