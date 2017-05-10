package hello;
 
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class HelloImpl extends UnicastRemoteObject implements Hello {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelloImpl() throws RemoteException {
    }
 
    public String greeting() throws RemoteException {
        return "greeting";
    }
 
}