package hello;
 
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.MalformedURLException; 
 
public class RMIClient {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        Hello hello = (Hello) registry.lookup("server.Hello");
        System.out.println(hello.greeting());
    }
}