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
			dec = new XMLDecoder(new FileInputStream(datname));
                        msglist = (ArrayList<Message>) dec.readObject();		//Datei auslesen und in ArrayList speichern
		} catch (FileNotFoundException ex) {
                    //Wenn Datei leer oder nicht existiert --> Leere ArrayList zurückgeben
			msglist = new ArrayList<>();
		}

		return msglist;
		}
	
	
    @Override
    public void removeMessage(String identifier, Message message) {
        // mit relativer Pfad
		String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
		ArrayList<Message> msglist = null;
				
		msglist = this.loadAllMessages(identifier);
		for(Message nachricht : msglist) {
			if(nachricht.equals(message)) {
				msglist.remove(nachricht);
			}
		}
		this.storeMessageList(identifier, msglist);
    }

    @Override
    public void storeMessageList(String identifier, ArrayList<Message> messages) {
		// mit relativer Pfad
		String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier + BoardConfiguration.MESSAGE_STORAGE_PATH;
		XMLEncoder enc = null;
	
		try {
			enc = new XMLEncoder(new FileOutputStream(datname));
			enc.writeObject(messages);		//ArrayList in Datei schreiben
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

