/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import AdvancedServices.VirtualGroupCore;
import BoardConfiguration.BoardConfiguration;
import BoardCore.AbstractCore;
import BoardCore.ORBAccessControl;
import BoardModules.AdvancedServices.VirtualGroupService;
import BoardModules.AdvancedServices.VirtualGroupServiceHelper;
import BoardModules.BasicServices.AdministrationServicePOA;
import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;

/**
 *
 * @author Tobias
 */
public class AdministrationServiceImpl extends AdministrationServicePOA {

    private final ArrayList<VirtualGroupCore> virtualGroups;
    private final HashMap<String, VirtualGroupService> virtualGroupRefs;
    
    private final AbstractCore core;
    
    public AdministrationServiceImpl(AbstractCore core) {
        this.core = core;
        this.virtualGroups = new ArrayList<>();
        this.virtualGroupRefs = new HashMap<>();
        
        // aktiviere Heartbeat zur virtuellen Gruppe (TODO: funktioniert noch nicht)
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                
                for (int i = 0; i < virtualGroupRefs.size(); i++) {
                    
                }
                
                for (Entry<String, VirtualGroupService> vgroup : virtualGroupRefs.entrySet()) {
                    vgroup.getValue().heartbeat();
                    System.out.println(vgroup.getKey());
                }
                
            }
        }, 10000);
    }
    
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
        try {
            System.out.println("Logge in eine Virtuelle Gruppe ein: " + vgroupname);
            VirtualGroupService virtualGroupServiceObj = (VirtualGroupService) VirtualGroupServiceHelper.narrow(ORBAccessControl.getInstance().getNameService().resolve_str(vgroupname + "/" + BoardConfiguration.VGROUP_SERVICE_NAME));
            
            virtualGroupServiceObj.addMember(core.getIdentifier(), core.getAllUsers());
            virtualGroupRefs.put(vgroupname, virtualGroupServiceObj);
            
            /*
            boolean containVGroupCore = false;
            
            for (VirtualGroupCore vgroupcore : virtualGroups) {
                if (vgroupcore.getIdentifier().equals(vgroupname)) {
                    containVGroupCore = true;
                }
            }

            if (!containVGroupCore) {
                 StringListHolder membernames = new StringListHolder();
                UserListHolder users = new UserListHolder();
                MessageListHolder messages = new MessageListHolder();
                virtualGroupServiceObj.createBackupOfVirtualGroup(membernames, users, messages);
                this.virtualGroups.add(new VirtualGroupCore(vgroupname, membernames.value, users.value, messages.value));
            }
            */
            
        } catch (NotFound ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] getAllVirtualGroups() {
        int batchSize = 100;
        ArrayList<String> vgroupList = new ArrayList<>();
        
        BindingListHolder bList = new BindingListHolder();
        BindingIteratorHolder bIterator = new BindingIteratorHolder();

        ORBAccessControl.getInstance().getNameService().list(batchSize, bList, bIterator);
        
        for (Binding value : bList.value) {
            String boardname = value.binding_name[0].id;
            
            try {
                VirtualGroupService virtualGroupServiceObj = (VirtualGroupService) VirtualGroupServiceHelper.narrow(ORBAccessControl.getInstance().getNameService().resolve_str(boardname + "/" + BoardConfiguration.VGROUP_SERVICE_NAME));
                vgroupList.add(boardname);
            } catch (NotFound ex) {
                
                
            } catch (CannotProceed ex) {
                Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String result[] = new String[vgroupList.size()];
        
        vgroupList.toArray(result);
            
        return result;
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
            strListBoards.addAll(Arrays.asList(boards));
            
            for (Binding value : bList.value) {
                String boardname = value.binding_name[0].id;
                if (strListBoards.contains(boardname)) {
                    System.out.println(value.binding_name[0].id);
                    BoardService boardServiceObj = (BoardService) BoardServiceHelper.narrow(ORBAccessControl.getInstance().getNameService().resolve_str(boardname + "/" + BoardConfiguration.BOARD_SERVICE_NAME));
                    boardServiceObj.sendMessage(new User("."), message, core.getIdentifier()); 
                } else {
                    System.err.println("Tafel existiert nicht: " + boardname + "!");
                }
            }
	} catch (Exception ex) {
	    Logger.getLogger(AdministrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void createUser(User newuser) {
        if (!core.checkUser(newuser)) {
            core.addUser(newuser);
        }
    }

    @Override
    public String[] getAllUsers() {
        User users[] = this.core.getAllUsers();
        String usernames[] = new String[users.length];
        
        for (int i = 0; i < usernames.length; i++) {
            usernames[i] = users[i].name;
        }
        
        return usernames;
    }
}
