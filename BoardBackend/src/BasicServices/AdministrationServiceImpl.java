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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
/*      boards.length in der 2.Schleife ist soll die Anzahl der aktiven Namen
        im NameService sein
        equals(boards), boards soll hier ein aktiver Name im NameService sein
*/
//        BoardServiceImpl bsi = new BoardServiceImpl();
//        User user = new User("admin");
//        for (int i = 0; i < boards.length; i++) {
//            for (int j = i + 1; j <= boards.length; j++) {
//                if (boards[i].equals(boards)) {
//                    try {
//                        bsi.sendMessage(user, message, boards[i]);
//                    } catch (UnknownUser ex) {
//                        System.out.println("User existiert nicht!");
//                    }
//                }
//            }
//        }
    }

    @Override
    public void createUser(User newuser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
