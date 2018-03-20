package hello;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jcrbsa
 */
import java.rmi.*;
		
		public interface Hello extends java.rmi.Remote
		{
			String sayHello() throws RemoteException;
		}