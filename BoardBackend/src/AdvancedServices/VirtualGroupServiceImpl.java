/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardModules.AdvancedServices.*;
import BoardModules.MessageListHolder;
import BoardModules.StringListHolder;
import BoardModules.User;
import BoardModules.UserListHolder;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class VirtualGroupServiceImpl extends VirtualGroupServicePOA {

    private final VirtualGroupCore core;
    
    VirtualGroupServiceImpl(VirtualGroupCore core) {
        this.core = core;
    }

    @Override
    public void addMember(String boardname, User[] users) {
        ArrayList<User> userList = new ArrayList<>();
        
        for (User user : users) {
            user.name = boardname + "." + user.name;
            System.out.println(user.name);
            userList.add(user);
        }
        
        this.core.addMember(boardname, userList);
    }

    @Override
    public void removeMember(String boardname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createBackupOfVirtualGroup(StringListHolder membernames, UserListHolder users, MessageListHolder messages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
