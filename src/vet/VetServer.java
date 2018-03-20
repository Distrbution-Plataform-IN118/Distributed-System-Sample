package vet;
import java.rmi.*;
public class VetServer
{ public static void main(String[] arg)
  { try
    { Naming.bind("Vet10", new Vet10Impl());
    }
    catch(Exception e)
    { e.printStackTrace();
    }
  }
}