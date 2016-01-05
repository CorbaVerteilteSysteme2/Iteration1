import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
    @author Adrian Müller, Dennis Kupfer, Matthäus Piechowiak
*/

public class Message
{
    String Board_ID;
    String User_ID;
    private File message_file;
    private FileWriter filewriter;
    Message(){}
    Message(String Group_ID,String User_ID)
    {
        this.Board_ID = Group_ID;
        this.User_ID = User_ID;
    }
    //Legt eine neue Tafel an.
    public void CreateNewBoard(String Board_ID)
    {
        try
        {
        message_file = new File(Board_ID + ".txt");
        if(message_file.exists())
        {
            System.out.println("Die Gruppe " + Board_ID + " existiert schon!");
        }
        filewriter = new FileWriter(message_file ,true);
        filewriter.flush();
        filewriter.close();
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
    }
    //Schreibt in eine bestehende Tafel
    public void WriteMessageToTextfile(String Group_ID,String User_ID,String Message)
    {
        try
        {
            message_file = new File(Group_ID + ".txt");
            if(!message_file.exists())
            {
                System.out.println("Die Gruppe " + Group_ID + " existiert noch nicht!");
            }
            else
            {
                filewriter = new FileWriter(message_file ,true);
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
    }
    public static void main(String[] args) {
    Message message_instance = new Message();
    //Gibt eine Warnung aus, da die Gruppe A noch nicht existiert
//    message_instance.WriteMessageToTextfile("A","Fritz","Hoi");
//    message_instance.WriteMessageToTextfile("A","Max","Ho");
    message_instance.CreateNewBoard("A");
    message_instance.WriteMessageToTextfile("A","Fritz","Hallo");
    message_instance.WriteMessageToTextfile("A","Max","Hi");
    //Gibt eine Warnung aus, da die Gruppe A schon existiert
//    message_instance.CreateNewBoard("A");
    message_instance.WriteMessageToTextfile("A","Fritz","Ciao");
    message_instance.WriteMessageToTextfile("A","Max","Ciao");
  }
}