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
    
    public VirtualGroupServiceImpl(VirtualGroupCore core) {
        this.core = core;
    }

    @Override
    public void addMember(String boardname, User[] users) {
        if (!this.core.getMembernames().contains(boardname)) {
            ArrayList<User> userList = new ArrayList<>();

            for (User user : users) {
                user.name = boardname + "." + user.name;
                System.out.println(user.name);
                userList.add(user);
            }

            this.core.addMember(boardname, userList);
        } else {
            System.out.println(boardname + " ist bereits Mitglied von " + this.core.getIdentifier());
        }
    }

    @Override
    public void removeMember(String boardname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO
    @Override
    public void createBackupOfVirtualGroup(StringListHolder membernames, UserListHolder users, MessageListHolder messages) {
        core.getMembernames().toArray(membernames.value);
        users.value = core.getAllUsers();
        messages.value = core.getAllMessages();
    }

    @Override
    public void heartbeat() {
        // leere Methode zum Prüfen, ob Virtuelle Gruppe noch aktiv ist
    }
}
