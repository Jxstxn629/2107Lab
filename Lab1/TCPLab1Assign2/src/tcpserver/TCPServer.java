package tcpserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		
		int port = 12345;
		String resp = "";
		while(true)
		{
			try {
				ServerSocket ss = new ServerSocket(port);
				System.out.println("Listening for connection on port 12345 ...."); 
				while (true){
					Socket client = ss.accept();
					InputStreamReader isr = new InputStreamReader(client.getInputStream());
					BufferedReader reader = new BufferedReader(isr);
					String request = reader.readLine();
					String[] tempArray;
			        tempArray = request.split(" ");
			        System.out.println("Request type: "+ tempArray[0]);
			        System.out.println("File requested: "+ tempArray[1].substring(1));
			        BufferedReader freader;
			        PrintWriter out = new PrintWriter(client.getOutputStream());       
			        try {
			        	freader = new BufferedReader(new FileReader(tempArray[1].substring(1)));
			        	String line;
				        while ((line = freader.readLine()) != null)
				        {
				          resp += line;
				        }
			        	out.println("HTTP/1.1 200 OK");
			            out.println("Content-type: text/html");
			            out.println("\r\n");
			            out.println(resp);
			            
			        }
			        catch(Exception e)
			        {
			        	System.out.println("File not found");
			        	out.println("HTTP/1.1 404 Not Found");
			        }
		            out.flush();
		            out.close();
		            client.close();	
				}
			}
			catch (IOException ex) 
			{ 
				ex.printStackTrace();         
			} 
		}
		
		
	}
	
}
