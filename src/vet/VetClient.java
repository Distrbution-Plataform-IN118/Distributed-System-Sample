package vet;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class VetClient extends UnicastRemoteObject implements Remote
{ protected VetClient() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static void main(String[] arg)
  { //System.setSecurityManager(new RMISecurityManager());
    VetInterface v;
    try
    {
      v = (VetInterface)Naming.lookup("rmi://192.168.2.27:1099/Vet10");
      v.setInt(4,5);
//      v.setInt(2,5);
//      v.setInt(3,9);
//      v.setInt(8,1);

      System.out.println("Valor pos 4= "+v.getInt(4));
      System.out.println("Valor pos 2= "+v.getInt(2));
      System.out.println("Valor pos 3= "+v.getInt(3));
      System.out.println("Valor pos 8= "+v.getInt(8));
      

    }
    catch(Exception e) { /*...*/ }
  }


}

