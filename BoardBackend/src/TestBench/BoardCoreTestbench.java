/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBench;

import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.UnknownUser;
import BoardModules.User;
import java.util.Date;
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
public class BoardCoreTestbench {
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
        
        
            BoardService boardServiceObj = (BoardService) BoardServiceHelper.narrow(ncRef.resolve_str(tableID + "/BoardService"));
            
            User _user1 = new User("Username1");
            boardServiceObj.sendMessage(_user1, new Message("Hallo Test-Tafel no. 1", _user1.name, new Date().toString()), tableID);
        
        } catch (InvalidName ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DestinationUnreachable ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownUser ex) {
            Logger.getLogger(BoardCoreTestbench.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
