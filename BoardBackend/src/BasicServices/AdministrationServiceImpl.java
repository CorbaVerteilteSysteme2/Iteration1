/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardModules.BasicServices.AdministrationServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.User;

/**
 *
 * @author Tobias
 */
public class AdministrationServiceImpl extends AdministrationServicePOA {

    @Override
    public void createVirtualGroup(String vgroupname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }

    @Override
    public void createUser(User newuser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
