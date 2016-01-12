/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import BoardModules.Message;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public interface IMessageStorage {
    /*
        Speichert eine einzelne Nachricht, abhängig vom Identifier (Name der Virtuellen Gruppe oder Tafel)
    */
    void storeMessage(String identifier, Message message);
    
    /*
        Läd alle Nachrichten, abhängig vom Identifier (Name der Virtuellen Gruppe oder Tafel)
    */
    ArrayList<Message> loadAllMessages(String identifier);
    
    /*
        Entfernt eine Nachricht
    */
    void removeMessage(String identifier, Message message);
}
