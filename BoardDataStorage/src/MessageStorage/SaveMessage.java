package MessageStorage;

import BoardConfiguration.BoardConfiguration;
import BoardModules.Message;
import Interfaces.IMessageStorage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SaveMessage implements IMessageStorage {
	int  anzObj = 0;
	
	@Override
	public void storeMessage(String identifier, Message message) {
        // mit relativer Pfad
            String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
            XMLEncoder enc = null;

            try {
                enc = new XMLEncoder(new FileOutputStream(datname));
                ArrayList<Message> msgs = this.loadAllMessages(identifier);
                msgs.add(message);
                enc.writeObject(msgs);
            } catch (IOException e) {
                    System.out.println("Fehler: Konnte nicht gespeichert werden!");
                    e.printStackTrace();
            } finally {
                if(enc != null){
                    enc.close();
                }
            }
	}
	
	@Override
	public ArrayList<Message> loadAllMessages(String Identifier) {
            // mit relativer Pfad
            String datname = BoardConfiguration.COMMON_STORAGE_PATH + Identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
            XMLDecoder dec = null;
            ArrayList<Message> msglist = null;
            try {
                //		try {
//			dec = new XMLDecoder(new FileInputStream(datname));
//			} catch (FileNotFoundException ex) {
//				Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
//			}
//			for(int i = 0; i < anzObj; i++) {
//				try {
//					nachricht = (Message) dec.readObject();
//                } finally {
//                    if(dec != null) {
//                        dec.close();
//					}
//				}
//				msglist.add(nachricht);
//			}
//		anzObj = 0;

                dec = new XMLDecoder(new FileInputStream(datname));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            msglist = (ArrayList<Message>) dec.readObject();
            if (msglist == null) {
                return new ArrayList<Message>();
            } else {
                return msglist;
            }
	}
	
    @Override
    public void removeMessage(String identifier, Message message) {
        
    }

    @Override
    public void storeMessageList(String identifier, ArrayList<Message> messages) {
		// mit relativer Pfad
        String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
        XMLEncoder enc = null;

		try {
            enc = new XMLEncoder(new FileOutputStream(datname));
//			for(Message msg : messages) {
//				enc.writeObject(msg);
//				anzObj++;
//			}
enc.writeObject(messages);
        } catch (IOException e) {
            System.out.println("Fehler: Konnte nicht gespeichert werden!");
            e.printStackTrace();
        } finally {
            if(enc != null){
                enc.close();
            }
        }
    }
}

