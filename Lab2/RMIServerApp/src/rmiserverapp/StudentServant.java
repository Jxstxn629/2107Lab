package rmiserverapp;
import interfaces.SITStudent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StudentServant extends UnicastRemoteObject  implements SITStudent {
	public StudentServant() throws RemoteException {
		super();     
		}     
	@Override 
	public String greet(String name) throws RemoteException {
		return "Welcome " + name + " to RMI tutorial!";
		}     
	@Override 
	public int add(int x, int y) throws RemoteException {
		return x + y;     
		} 
	}
