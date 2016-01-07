/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardModules.AdvancedServices.*;
import BoardModules.MessageListHolder;
import BoardModules.User;
import BoardModules.UserListHolder;

/**
 *
 * @author Tobias
 */
public class VirtualGroupServiceImpl extends VirtualGroupServicePOA {

    @Override
    public void addMember(String boardname, User[] users) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMember(String boardname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createBackupOfVirtualGroup(UserListHolder users, MessageListHolder messages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
