/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore.Helper;

import BoardModules.Message;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class MessageComparator implements Comparator<Message> {

    private static final SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
    
    @Override
    public int compare(Message msgA, Message msgB) {
        try {
            Date dateA = (df).parse(msgA.timestamp);
            Date dateB = (df).parse(msgB.timestamp);
            return dateA.compareTo(dateB);
        } catch (ParseException ex) {
            Logger.getLogger(MessageComparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
