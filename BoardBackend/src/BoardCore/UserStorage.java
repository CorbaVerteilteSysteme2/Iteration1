/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import BoardModules.User;
import java.util.ArrayList;

/**
 * @deprecated funktionen wandern in den AbstractCore
 * @author Tobias
 */
public class UserStorage {
    private static final UserStorage instance = new UserStorage();
    private static final Object lockInstanceObj = new Object();
    public static UserStorage getInstance() {
        return instance;
    }
    
    private UserStorage() {
        // hier wird der Service / das Objekt des BoardDataStorage aufgerufen
        this.userList = new ArrayList<>();
    }
    
    private final ArrayList<User> userList;
    
    public void push() {
        synchronized(lockInstanceObj) {
             // lese die Daten neu aus dem BoardDataStorage
        }
    }
    
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = null;
        synchronized(lockInstanceObj) {
            users = new ArrayList<>(userList);
        }
        return users;
    }
    
    public void addUser(User user) {
        synchronized(lockInstanceObj) {
            // TODO: hier muss eigentlich auf den BoardDataStorage zugegriffen werden,
            // dannach ein push(), um die Daten zu aktualisieren
            this.userList.add(user);
        }
    }

    public boolean checkUser(User user) {
        synchronized(lockInstanceObj) {
            return userList.contains(user);
        }
    }
}
