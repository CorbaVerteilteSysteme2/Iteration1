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
	
	@Override
	public void storeMessage(String identifier, Message message) {
            
            // mit relativer Pfad
            String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
            XMLEncoder enc = null;

            try {
                enc = new XMLEncoder(new FileOutputStream(datname));
                enc.writeObject(message);
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
            ArrayList<Message> msglist = new ArrayList<>();
            Message nachricht = null;   // Die Nachricht muss nicht vordeklariert werden
            
            try {
                dec = new XMLDecoder(new FileInputStream(datname));
                nachricht = (Message)dec.readObject();
                } catch (IOException e) {
                        System.out.println("Fehler: Konnte nicht laden!");
                        e.printStackTrace();
                } finally {
                    if(dec != null) {
                        dec.close();
                }
            }
            msglist.add(nachricht);
            return msglist;
	}
	
//	ArrayList<Message>msglist = null;
//
//
//	@SuppressWarnings ("unchecked")
//	@Override
//	public void storeMessage(String identifier, Message message) {
//		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		ObjectOutputStream aus = null;
//		try {
//			aus = new ObjectOutputStream(new FileOutputStream(datname));
//			aus.writeObject(msglist);
//		} catch (IOException ex) {
//			System.out.println(ex);
//		} finally {
//			try {
//				if (aus != null) {
//					aus.flush();
//					aus.close();
//				}
//			} catch (IOException e) {}
//		}
//	
//	}
//	
//	@SuppressWarnings ("unchecked")
//	@Override
//	public ArrayList<Message> loadAllMessages(String identifier) {
//		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		ObjectInputStream in = null;
//		
//		
//		try {
//			//do {
//			in = new ObjectInputStream(new FileInputStream(datname));
//			msglist = (ArrayList<Message>) in.readObject();
//			//}while(in.readObject() != null);
//		} catch (FileNotFoundException ex) {
//			System.out.println("Speicherdatei (noch) nicht vorhanden!");
//		} catch(Exception ex) {
//			System.out.println(ex);
//		} finally {
//			try {
//				if(in != null) {
//					in.close();
//				}
//			} catch(IOException ex) {
//				
//			}
//		}
//		
//		if(msglist == null) {
//			msglist = new ArrayList<>();
//		}
//		
////		try {
////			//msglist.add((Message) in.readObject());
////			Message nachricht = new Message();
////			nachricht = (Message) in.readObject();
////			msglist.add(nachricht);
////		} catch (IOException ex) {
////			Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
////		} catch (ClassNotFoundException ex) {
////			Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
////		}
//	
//	return msglist;
//	}

    @Override
    public void removeMessage(String identifier, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeMessageList(String identifier, ArrayList<Message> messages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

