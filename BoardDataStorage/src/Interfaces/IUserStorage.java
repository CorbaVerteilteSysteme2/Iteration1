/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import BoardModules.User;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public interface IUserStorage {
    /*
        Speichere einen Benutzer, abhängig vom Identifier (Name der Virtuellen Gruppe oder Tafel)
    */
    void storeUser(String identifier, User user);
    
    /*
        Lade alle Benutzer, abhängig vom Identifier (Name der Virtuellen Gruppe oder Tafel)
    */
    ArrayList<User> loadAllUsers(String identifier);
}
