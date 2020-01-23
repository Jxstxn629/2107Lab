package client;

import java.rmi.Naming;
import java.util.Scanner;

import interfaces.CalculatorIn;

public class CalClient {
	public static void main(String[] args) {
		try { 
			//Look up for the object             
			CalculatorIn cal = (CalculatorIn) Naming.lookup( "rmi://localhost:1099/StudentService"); 
			//Access to the greet method   
			
			while(true)
			{
				
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a command: ");
	    	String request = input.nextLine();
	    	String[] tempArray;
	        tempArray = request.split(" ");
	        String response = "";
	        Double result = 0.0;
	        
	        
	        if(!tempArray[0].equals("add") && !tempArray[0].equals("sub") && !tempArray[0].equals("mul") && !tempArray[0].equals("div"))
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
					
					if(tempArray[0].equals("add"))
					{
						result = cal.add(arg1, arg2);
						response = "Result: " + result; 
					}
					else if(tempArray[0].equals("sub"))
					{
						result = cal.sub(arg1, arg2);
						response = "Result: " + result; 
					}
					else if(tempArray[0].equals("mul"))
					{
						result = cal.mul(arg1, arg2);
						response = "Result: " + result; 
					}
					else
					{
						if(arg2 == 0)
						{
							response = "Error: Divided by zero exception";
						}
						else
						{
							result = cal.div(arg1, arg2);
							response = "Result: " + result; 
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
	        
	        System.out.println(response);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			}    
		}
}
