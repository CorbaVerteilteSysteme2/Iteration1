/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import BoardModules.User;
import Interfaces.IUserStorage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SaveUser implements IUserStorage {
    	
	@Override
	public void storeUser(String identifier, User user) {
            
            // mit relativer Pfad
            String datname = "..\\" + identifier + "_Users.xml";
            XMLEncoder enc = null;

            try {
                enc = new XMLEncoder(new FileOutputStream(datname));
                enc.writeObject(user);
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
	public ArrayList<User> loadAllUsers(String Identifier) {
            String datname = "..\\"+Identifier+"Messages.xml";
            XMLDecoder dec = null;
            ArrayList<User> msglist = new ArrayList<>();
            User user = null;
            
            try {
                dec = new XMLDecoder(new FileInputStream(datname));
                user = (User)dec.readObject();
                } catch (IOException e) {
                        System.out.println("Fehler: Konnte nicht laden!");
                        e.printStackTrace();
                } finally {
                    if(dec != null) {
                        dec.close();
                }
            }
            msglist.add(user);
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

    //@Override
    public void removeUser(String identifier, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
