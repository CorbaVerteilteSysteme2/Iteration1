/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import AdvancedServices.VirtualGroupCore;
import BoardCore.ORBAccessControl;
import BoardModules.BasicServices.AdministrationServicePOA;
import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
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
        } catch (NotFound ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBound ex) {
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
	    
	    
	    int batchSize = 100;
	    BindingListHolder bList = new BindingListHolder();
	    BindingIteratorHolder bIterator = new BindingIteratorHolder();
	    
	    ORBAccessControl.getInstance().getNameService().list(batchSize, bList, bIterator);
	    /*      boards.length in der 2.Schleife ist soll die Anzahl der aktiven Namen
	    im NameService sein
	    equals(boards), boards soll hier ein aktiver Name im NameService sein
	    */
            ArrayList<String> strListBoards = new ArrayList<>();
            for (int i = 0; i < boards.length; i++) {
                strListBoards.add(boards[i]);
            }
            for (int i = 0; i < bList.value.length; i++) {
                String boardname = bList.value[i].binding_name[0].id;
                if (strListBoards.contains(boardname)) {
                    System.out.println(bList.value[i].binding_name[0].id);
                    BoardService boardServiceObj = (BoardService) BoardServiceHelper.narrow(ORBAccessControl.getInstance().getNameService().resolve_str(boardname + "/BoardService"));
                    boardServiceObj.sendMessage(new User("."), message, "");
                } else {
                    System.err.println("Tafel existiert nicht: " + boardname + "!");
                }
            }
//	
	} catch (Exception ex) {
	    Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void createUser(User newuser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
