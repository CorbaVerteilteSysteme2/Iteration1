package MessageStorage;

import BoardModules.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/*
    @author Adrian Müller, Dennis Kupfer, Matthäus Piechowiak
*/

public class MessageParser
{
    String Board_ID;
    String User_ID;
    private File message_file;
    private FileWriter filewriter;
    MessageParser(){}
    MessageParser(String Group_ID,String User_ID)
    {
        this.Board_ID = Group_ID;
        this.User_ID = User_ID;
    }

    public MessageParser(String identifier) {
        this.Board_ID = identifier;
    }
    //Legt eine neue Tafel an
    public void CreateNewBoard(String Board_ID)
    {
        try
        {
        message_file = new File(Board_ID + ".txt");
        if(message_file.exists())
        {
            System.out.println("CreateNewBoard: Die Gruppe " + Board_ID + " existiert schon!");
        }
        filewriter = new FileWriter(message_file ,true);
        filewriter.flush();
        filewriter.close();
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
    }
    
    //Liest aus einer bestehenden Tafel und schreibt den String in ein Array
    public ArrayList<Message> ReadMessageLogFromTextfile(String Group_ID)
    {
        Message single_message;
        ArrayList<Message> the_messages = new ArrayList<>();
        String zeile;
        String message;
        String user;
        String timestamp;
        boolean user_fertig;
        boolean timestamp_fertig;
        try
        {
            message_file = new File(Group_ID + ".txt");
            if(!message_file.exists())
            {
                System.out.println("ReadMessageFromTextfile: Die Gruppe " + Group_ID + " existiert noch nicht!");
            }
            else
            {
                FileReader filereader = new FileReader(Group_ID + ".txt");
                BufferedReader bufferedreader = new BufferedReader(filereader);
                while((zeile=bufferedreader.readLine()) != null)
                {
                    timestamp_fertig = false;
                    user_fertig = false;
                    message = "";
                    user = "";
                    timestamp = "";
                    for(int i=1;i<zeile.length();i++)
                    {
                        if(!timestamp_fertig && zeile.charAt(i)!=')')
                        {
                            timestamp+=zeile.charAt(i);
                        }
                        if(!timestamp_fertig && zeile.charAt(i)==')')
                        {
                            timestamp_fertig = true;
                            i += 2;
                        }
                        if(timestamp_fertig && !user_fertig && zeile.charAt(i)!=':')
                        {
                            user += zeile.charAt(i);
                        }
                        if(timestamp_fertig && !user_fertig && zeile.charAt(i)==':')
                        {
                            user_fertig = true;
                            i += 2;
                        }
                        if(user_fertig && zeile.charAt(i)!='\n')
                        {
                            message += zeile.charAt(i);
                        }
                    }
                    single_message = new Message(message,user,timestamp);
                    the_messages.add(single_message);
                }
                bufferedreader.close();
            }
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
        return the_messages;
    }
    
    //Schreibt in eine bestehende Tafel
    public Message WriteMessageToTextfile(String Group_ID,String User_ID,String Message)
    {
        Message return_Message = null;
        try
        {
            message_file = new File(Group_ID + ".txt");
            if(!message_file.exists())
            {
                System.out.println("WriteMessageToTextfile: Die Gruppe " + Group_ID + " existiert noch nicht!");
            }
            else
            {
                GregorianCalendar now = new GregorianCalendar();
                DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
                return_Message = new Message(Message,User_ID,dateformat.format(now.getTime()));
                filewriter = new FileWriter(message_file ,true);
                filewriter.write("(");
                filewriter.write(dateformat.format(now.getTime()));
                filewriter.write(") ");
                filewriter.write(User_ID);
                filewriter.write(": ");
                filewriter.write(Message);
                filewriter.write("\n");
                filewriter.flush();
                filewriter.close();
            }
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
        return return_Message;
    }
    
    public static void main(String[] args) {
    MessageParser message_instance = new MessageParser();
    //Gibt eine Warnung aus, da die Gruppe A noch nicht existiert
//    message_instance.WriteMessageToTextfile("A","Fritz","Hoi");
//    message_instance.WriteMessageToTextfile("A","Max","Ho");
//    message_instance.ReadMessageLogFromTextfile("A");
    
    message_instance.CreateNewBoard("A");
    message_instance.WriteMessageToTextfile("A","Fritz","Hallo");
    message_instance.WriteMessageToTextfile("A","Max","Hi");
    message_instance.ReadMessageLogFromTextfile("A");
//    Gibt eine Warnung aus, da die Gruppe A schon existiert
//    message_instance.CreateNewBoard("A");
    
    message_instance.WriteMessageToTextfile("A","Fritz","Ciao");
    message_instance.WriteMessageToTextfile("A","Max","Ciao");
  }

    public void WriteMessageToTextfile(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}