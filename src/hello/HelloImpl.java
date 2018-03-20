package hello;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jcrbsa
 */
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
public class HelloImpl extends UnicastRemoteObject implements Hello 
{ 
    public HelloImpl() throws RemoteException {}

    public String sayHello() { return "Hello world!"; }

    public static void main(String args[]) 
    { 
        try 
        { 
			while(true){
            HelloImpl obj = new HelloImpl(); 
            // Bind this object instance to the name "HelloServer" 
            Naming.rebind("rmi://localhost:1099/HelloServer", obj); 
			}
        } 
        catch (Exception e) 
        { 
            System.out.println("HelloImpl err: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
}