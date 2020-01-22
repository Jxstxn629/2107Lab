package rmiserverapp;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServerApp {
	public static void main(String[] args) {
		try { 
			//Create a servant object             
			StudentServant ss = new StudentServant();
			//Locate the registry
			LocateRegistry.createRegistry(1099);
			//Bind the servant object
			Naming.rebind("rmi://localhost:1099/StudentService", ss);
			System.out.println("Server is ready");
			}
		catch (Exception ex) {
			ex.printStackTrace();         
			}     
		}     
	}



