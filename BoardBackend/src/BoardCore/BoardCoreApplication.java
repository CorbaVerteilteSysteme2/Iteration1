/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

/**
 * 
 * 
 * @author Tobias
 */
public class BoardCoreApplication {
    
    public static void main(String[] args) throws RuntimeException, CannotProceed, InvalidName, org.omg.CORBA.ORBPackage.InvalidName, AdapterInactive {
        BoardCore core = null;
        
        if (args.length == 2) {
            ORBAccessControl.getInstance().setORB("1050", args[0]);
            core = new BoardCore(args[1]);
        } else {
            // Testausführung!
            ORBAccessControl.getInstance().setORB("1050", "localhost");
            core = new BoardCore("Test-Tafel");
        }
        ORBAccessControl.getInstance().run();
        //core.run();
    }
}
