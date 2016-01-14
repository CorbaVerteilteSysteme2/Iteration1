/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardConfiguration.BoardConfiguration;
import BoardCore.AbstractCore;
import BoardCore.ORBAccessControl;
import BoardModules.AdvancedServices.*;
import BoardModules.Message;
import BoardModules.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Tobias
 */
public class VirtualGroupCore extends AbstractCore {
    
    private ArrayList<VirtualGroupMember> members;
    private VirtualGroupServiceImpl _virtualGroupService;
    private boolean  isActive;
    
    public VirtualGroupCore(String groupname) throws CannotProceed, InvalidName, NotFound, AlreadyBound {
        super(groupname);
        members = new ArrayList<>();
        
        try {
            _virtualGroupService = new VirtualGroupServiceImpl(this);
            
            buildVirtualGroupService();
            
        } catch (ServantNotActive ex) {
            Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VirtualGroupCore(String vgroupname, String[] membernames, User[] users, Message[] messages) throws CannotProceed, InvalidName {
        super(vgroupname);
        _virtualGroupService = new VirtualGroupServiceImpl(this);
        this.isActive = false;
        for (Message msg : messages) {
            this.addMessage(msg);
        }
        
        for (String membername : membernames) {
            ArrayList<User> memberUsers = new ArrayList<>();
            for (User user : users) {
                if (user.name.startsWith(vgroupname)) {
                    memberUsers.add(user);
                }
            }
            this.members.add(new VirtualGroupMember(membername, memberUsers));
        }
    }
    
    private void buildVirtualGroupService() throws ServantNotActive, WrongPolicy, NotFound, AlreadyBound, CannotProceed, InvalidName {
        org.omg.CORBA.Object virtualGroupServiceRef = ORBAccessControl.getInstance().getRootPOA().servant_to_reference(_virtualGroupService);
            
            VirtualGroupService virtualGroupService = VirtualGroupServiceHelper.narrow(virtualGroupServiceRef);
            
            NameComponent boardNC = new NameComponent(_identifier, "");
            NameComponent virtualGroupServiceNC = new NameComponent(BoardConfiguration.VGROUP_SERVICE_NAME, "");
            NameComponent path1[] = { boardNC, virtualGroupServiceNC };
            
            registerObjWithNameService(ORBAccessControl.getInstance().getNameService(), path1, virtualGroupServiceRef);
            this.isActive = true;
    }
    
    public void addMember(String boardname, ArrayList<User> users) {
        this.members.add(new VirtualGroupMember(boardname, users));
    }
    
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        for (VirtualGroupMember member : members) {
            for (User user : member.getUsers()) {
                users.add(user);
            }
        }
        
        return users;
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUser(User user) {
        // scheint nicht zu funktionieren
        System.out.println("checkUser");
        for (VirtualGroupMember member : members) {
            for (User u : member.getUsers()) {
                System.out.println(user.name);
                if (user.name.equals(u.name)) {
                    
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User[] getAllUsers() {
        ArrayList<User> userList = getUsers();
        
        User users[] = new User[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            users[i] = userList.get(i);
        }
        
        return users;
    }
    
    @Override 
    public void closeCore() {
        super.closeCore();
        
        NamingContextExt nameService = ORBAccessControl.getInstance().getNameService();
        
        NameComponent boardNC = new NameComponent(_identifier, "");

        if (_virtualGroupService != null) {
            try {
                nameService.unbind(new NameComponent[] { boardNC, new NameComponent(BoardConfiguration.VGROUP_SERVICE_NAME, "")});
            } catch (NotFound ex) {
                Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotProceed ex) {
                Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public ArrayList<String> getMembernames() {
        ArrayList<String> membernames = new ArrayList<>();
        
        for (VirtualGroupMember m : members) {
            membernames.add(m.getIdentifier());
        }
        
        return membernames;
    }
    
    public void activate() throws ServantNotActive, WrongPolicy, NotFound, AlreadyBound, CannotProceed, InvalidName {
        buildVirtualGroupService();
    }
}
