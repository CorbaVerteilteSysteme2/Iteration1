/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import BoardModules.Message;
import java.util.ArrayList;

/**
 * Diese Klasse soll dazu dienen, auf den BoardDataStorage zuzugreifen!
 * 
 * @author Tobias
 */
public class MessageStorage {
    
    private static final MessageStorage instance = new MessageStorage();
    private static final Object lockInstanceObj = new Object();
    public static MessageStorage getInstance() {
        return instance;
    }
    
    private MessageStorage() {
        // hier wird der Service / das Objekt des BoardDataStorage aufgerufen
        this.messageList = new ArrayList<>();
    }
    
    private final ArrayList<Message> messageList;
    
    public void push() {
        synchronized(lockInstanceObj) {
             // lese die Daten neu aus dem BoardDataStorage
        }
    }
    
    public ArrayList<Message> getMessages() {
        ArrayList<Message> messages = null;
        synchronized(lockInstanceObj) {
            messages = new ArrayList<>(messageList);
        }
        return messages;
    }
    
    public void addMessage(Message message) {
        synchronized(lockInstanceObj) {
            // TODO: hier muss eigentlich auf den BoardDataStorage zugegriffen werden,
            // dannach ein push(), um die Daten zu aktualisieren
            this.messageList.add(message);
        }
    }
}
