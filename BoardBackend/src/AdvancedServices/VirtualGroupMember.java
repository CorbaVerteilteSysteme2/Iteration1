/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardModules.User;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class VirtualGroupMember {
    
    private final String identifier;
    private final ArrayList<User> users;
    
    VirtualGroupMember(String boardname, ArrayList<User> users) {
        this.identifier = boardname;
        this.users = users;
    }
    
    public ArrayList<User> getUsers() {
        return this.users;
    }
}
