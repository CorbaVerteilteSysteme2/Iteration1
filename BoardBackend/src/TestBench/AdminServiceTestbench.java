/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBench;

import BoardModules.BasicServices.*;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * Dies ist nur eine Testbench, jedoch kann daraus die benötigten
 * Funktionen des BoardFronted gezogen werden!
 * 
 * @author Tobias Müller
 */
public class AdminServiceTestbench {
    public static void main(String[] args) {
        try {
            String tableID = "Test-Tafel";
            ORB _orb;
            Properties props = new Properties();
            
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            
            _orb = ORB.init(new String[0], props);
            
            org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        
        
            AdministrationService adminServiceObj = (AdministrationService) AdministrationServiceHelper.narrow(ncRef.resolve_str(tableID + "/AdminService"));
        
            //adminServiceObj.createVirtualGroup("VirtualGroup1");
            String boards[] = new String[2];
            boards[0] = "Test-Tafel";
            boards[1] = "nix";
            Message message = new Message("Hier steht Text", "author", "");
            adminServiceObj.forwardMessageToBoards(boards, message);
        } catch (InvalidName ex) {
            Logger.getLogger(AdminServiceTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(AdminServiceTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(AdminServiceTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(AdminServiceTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DestinationUnreachable ex) {
            Logger.getLogger(AdminServiceTestbench.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
