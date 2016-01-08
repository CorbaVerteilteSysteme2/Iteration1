/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardCore.AbstractCore;
import BoardCore.ORBAccessControl;
import BoardModules.AdvancedServices.*;
import BoardModules.Message;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NameComponent;
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
    
    public VirtualGroupCore(String groupname) throws CannotProceed, InvalidName, NotFound, AlreadyBound {
        super(groupname);
        members = new ArrayList<>();
        try {
            VirtualGroupServiceImpl _virtualGroupService = new VirtualGroupServiceImpl();
            
            org.omg.CORBA.Object virtualGroupServiceRef = ORBAccessControl.getInstance().getRootPOA().servant_to_reference(_virtualGroupService);
            
            VirtualGroupService virtualGroupService = VirtualGroupServiceHelper.narrow(virtualGroupServiceRef);
            
            NameComponent boardNC = new NameComponent(_identifier, "");
            NameComponent virtualGroupServiceNC = new NameComponent("VirtualGroupService", "");
            NameComponent path1[] = { boardNC, virtualGroupServiceNC };
            
            registerObjWithNameService(ORBAccessControl.getInstance().getNameService(), path1, virtualGroupServiceRef);
            
        } catch (ServantNotActive ex) {
            Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(VirtualGroupCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMember(String boardname) {
        this.members.add(new VirtualGroupMember(boardname));
    }
}
