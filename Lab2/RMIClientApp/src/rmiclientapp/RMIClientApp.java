package rmiclientapp;

import interfaces.SITStudent;
import java.rmi.Naming;
public class RMIClientApp {
	public static void main(String[] args) {
		try { 
			//Look up for the object             
			SITStudent sitStudent = (SITStudent) Naming.lookup( "rmi://localhost:1099/StudentService"); 
			//Access to the greet method             
			String greetMsg = sitStudent.greet("Mr. A");
			System.out.println(greetMsg);
			//Access to the add method             
			int result = sitStudent.add(10, 20);
			System.out.println("10+20 = " + result);          
			} 
		catch (Exception ex) {
			ex.printStackTrace();
			}    
		}
	}
