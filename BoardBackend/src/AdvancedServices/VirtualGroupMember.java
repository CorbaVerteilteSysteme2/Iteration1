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
    private ArrayList<User> users;
    
    public VirtualGroupMember(String id) {
        this.identifier = id;
        
        
    }
    
    
}
