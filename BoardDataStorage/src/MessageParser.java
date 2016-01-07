import BoardModules.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
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
    boolean first_message = true;
    boolean last_message = false;
    MessageParser(){}
    MessageParser(String Group_ID,String User_ID)
    {
        this.Board_ID = Group_ID;
        this.User_ID = User_ID;
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
    public String ReadMessageLogFromTextfile(String Group_ID)
    {
        String zeile;
        String message_return="";
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
                    message_return += zeile;
                    if(!last_message)
                    {
                        message_return += "\n";
                    }
                    if((zeile=bufferedreader.readLine()) != null)
                    {
                        message_return += zeile;
                    }
                    else
                    {
                        last_message = true;
                    }
                }
                bufferedreader.close();
            }
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
        return message_return;
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
                if(!first_message)
                {
                    filewriter.write("\n");
                }
                filewriter.write("(");
                filewriter.write(dateformat.format(now.getTime()));
                filewriter.write(") ");
                filewriter.write(User_ID);
                filewriter.write(": ");
                filewriter.write(Message);
                first_message = false;
                filewriter.flush();
                filewriter.close();
            }
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
        return return_Message;
    }
    
//    public static void main(String[] args) {
//    MessageParser message_instance = new MessageParser();
//    //Gibt eine Warnung aus, da die Gruppe A noch nicht existiert
//    message_instance.WriteMessageToTextfile("A","Fritz","Hoi");
//    message_instance.WriteMessageToTextfile("A","Max","Ho");
//    message_instance.ReadMessageLogFromTextfile("A");
//    
//    message_instance.CreateNewBoard("A");
//    message_instance.WriteMessageToTextfile("A","Fritz","Hallo");
//    message_instance.WriteMessageToTextfile("A","Max","Hi");
//    System.out.println(message_instance.ReadMessageFromTextfile("A"));
////    Gibt eine Warnung aus, da die Gruppe A schon existiert
//    message_instance.CreateNewBoard("A");
//    
//    message_instance.WriteMessageToTextfile("A","Fritz","Ciao");
//    message_instance.WriteMessageToTextfile("A","Max","Ciao");
//  }
}