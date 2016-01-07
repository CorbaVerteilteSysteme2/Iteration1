package BoardModules.AdvancedServices;


/**
* BoardModules/AdvancedServices/VirtualGroupServicePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/Tobias/Documents/NetBeansProjects/VS2_GitRepository/IDL-Definitions/src/idls/BoardModules.idl
* Donnerstag, 7. Januar 2016 20:23 Uhr MEZ
*/

public abstract class VirtualGroupServicePOA extends org.omg.PortableServer.Servant
 implements BoardModules.AdvancedServices.VirtualGroupServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addMember", new java.lang.Integer (0));
    _methods.put ("removeMember", new java.lang.Integer (1));
    _methods.put ("createBackupOfVirtualGroup", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // BoardModules/AdvancedServices/VirtualGroupService/addMember
       {
         String boardname = in.read_string ();
         BoardModules.User users[] = BoardModules.UserListHelper.read (in);
         this.addMember (boardname, users);
         out = $rh.createReply();
         break;
       }

       case 1:  // BoardModules/AdvancedServices/VirtualGroupService/removeMember
       {
         String boardname = in.read_string ();
         this.removeMember (boardname);
         out = $rh.createReply();
         break;
       }

       case 2:  // BoardModules/AdvancedServices/VirtualGroupService/createBackupOfVirtualGroup
       {
         BoardModules.UserListHolder users = new BoardModules.UserListHolder ();
         BoardModules.MessageListHolder messages = new BoardModules.MessageListHolder ();
         this.createBackupOfVirtualGroup (users, messages);
         out = $rh.createReply();
         BoardModules.UserListHelper.write (out, users.value);
         BoardModules.MessageListHelper.write (out, messages.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoardModules/AdvancedServices/VirtualGroupService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public VirtualGroupService _this() 
  {
    return VirtualGroupServiceHelper.narrow(
    super._this_object());
  }

  public VirtualGroupService _this(org.omg.CORBA.ORB orb) 
  {
    return VirtualGroupServiceHelper.narrow(
    super._this_object(orb));
  }


} // class VirtualGroupServicePOA
