/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import AdvancedServices.VirtualGroupCore;
import BoardCore.ORBAccessControl;
import BoardModules.BasicServices.AdministrationServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.User;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import BoardModules.UnknownUser;

/**
 *
 * @author Tobias
 */
public class AdministrationServiceImpl extends AdministrationServicePOA {

    private final ArrayList<VirtualGroupCore> virtualGroups = new ArrayList<>();
    
    @Override
    public void createVirtualGroup(String vgroupname) {
        System.out.println("Erstelle " + vgroupname);
        try {
            virtualGroups.add(new VirtualGroupCore(vgroupname));
        } catch (CannotProceed ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loginToVirtualGroup(String vgroupname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAllVirtualGroups() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forwardMessageToBoards(String[] boards, Message message) throws DestinationUnreachable {
	try {
	    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    ORB _orb;
	    Properties props = new Properties();
	    
	    props.put("org.omg.CORBA.ORBInitialPort", "1050");
	    props.put("org.omg.CORBA.ORBInitialHost", "localhost");
	    
	    _orb = ORB.init(new String[0], props);
	    org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	    
	    int batchSize = 100;
	    BindingListHolder bList = new BindingListHolder();
	    BindingIteratorHolder bIterator = new BindingIteratorHolder();
	    
	    ncRef.list(batchSize, bList, bIterator);
	    /*      boards.length in der 2.Schleife ist soll die Anzahl der aktiven Namen
	    im NameService sein
	    equals(boards), boards soll hier ein aktiver Name im NameService sein
	    */
	BoardServiceImpl bsi = new BoardServiceImpl();
        User user = new User("admin");
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < bList.value.length; j++) {
                if (boards[i].equals(bList.value)) {
                    try {
                        bsi.sendMessage(user, message, boards[i]);
                    } catch (UnknownUser ex) {
                        System.out.println("User existiert nicht!");
                    }
                }
            }
        }
	} catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
	    Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void createUser(User newuser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
