package tcpserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServer {
	public static void main(String[] args) {
		int port = 12345; 
		try 
		{             
			ServerSocket ss = new ServerSocket(port);             
			System.out.println("Server is ready to receive command!");
			
			while(true)
			{                 
				Socket socket = ss.accept(); 
				InputStream is = socket.getInputStream(); //Read data as character
				InputStreamReader isr = new InputStreamReader(is);//Read data as lines
				BufferedReader br = new BufferedReader(isr); //Read the string command from the user
				String command = br.readLine();
		        String[] tempArray;
		        tempArray = command.split(" ");
				String response = "";
				
				if(!tempArray[0].equals("add") && !tempArray[0].equals("subtract") && !tempArray[0].equals("multiply") && !tempArray[0].equals("divide"))
				{	
					response = "Error: Invalid command \"" + tempArray[0] + "\"";
				}
				else if(tempArray.length != 3)
				{
					response = "Error: Invalid number of arguments";
				}
				else
				{
					if (tempArray[1].matches("[0-9]+") && tempArray[2].matches("[0-9]+"))
					{
						int arg1 = Integer.parseInt(tempArray[1]);
						int arg2 = Integer.parseInt(tempArray[2]);
						int result = 0;
						if(tempArray[0].equals("add"))
						{
							result = arg1 + arg2;
							response = "The "+tempArray[0]+" result is " + result; 
						}
						else if(tempArray[0].equals("subtract"))
						{
							result = arg1 - arg2;
							response = "The "+tempArray[0]+" result is " + result; 
						}
						else if(tempArray[0].equals("multiply"))
						{
							result = arg1 * arg2;
							response = "The "+tempArray[0]+" result is " + result; 
						}
						else
						{
							if(arg2 == 0)
							{
								response = "Error: Divided by zero exception";
							}
							else
							{
								result = arg1 / arg2;
								response = "The "+tempArray[0]+" result is " + result; 
							}
						}
					}
					else if(!tempArray[1].matches("[0-9]+") && tempArray[2].matches("[0-9]+"))
					{
						response = "Error: \""+tempArray[1]+"\" is not a number";
					}
					else if(!tempArray[2].matches("[0-9]+") && tempArray[1].matches("[0-9]+"))
					{
						response = "Error: \""+tempArray[2]+"\" is not a number";
					}
					else
					{
						response = "Error: \""+tempArray[1]+"\" and \"" +tempArray[2]+"\" are not numbers";
					}
				}
				
				
				OutputStream os = socket.getOutputStream(); //Write lines 
				PrintWriter pw = new PrintWriter(os);  
				pw.println(response);          
				pw.flush();                 
				pw.close();  
				socket.close(); 
			}         
		}
		
		catch (IOException ex) 
		{ 
			ex.printStackTrace();         
		}   
			}
}
