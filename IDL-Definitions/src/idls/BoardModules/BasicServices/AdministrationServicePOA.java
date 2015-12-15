package BoardModules.BasicServices;


/**
* BoardModules/BasicServices/AdministrationServicePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 15. Dezember 2015 18:00 Uhr MEZ
*/

public abstract class AdministrationServicePOA extends org.omg.PortableServer.Servant
 implements BoardModules.BasicServices.AdministrationServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createVirtualGroup", new java.lang.Integer (0));
    _methods.put ("loginToVirtualGroup", new java.lang.Integer (1));
    _methods.put ("getAllVirtualGroups", new java.lang.Integer (2));
    _methods.put ("forwardMessageToBoards", new java.lang.Integer (3));
    _methods.put ("createUser", new java.lang.Integer (4));
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
       case 0:  // BoardModules/BasicServices/AdministrationService/createVirtualGroup
       {
         String vgroupname = in.read_string ();
         this.createVirtualGroup (vgroupname);
         out = $rh.createReply();
         break;
       }

       case 1:  // BoardModules/BasicServices/AdministrationService/loginToVirtualGroup
       {
         String vgroupname = in.read_string ();
         this.loginToVirtualGroup (vgroupname);
         out = $rh.createReply();
         break;
       }

       case 2:  // BoardModules/BasicServices/AdministrationService/getAllVirtualGroups
       {
         String $result[] = null;
         $result = this.getAllVirtualGroups ();
         out = $rh.createReply();
         BoardModules.StringListHelper.write (out, $result);
         break;
       }

       case 3:  // BoardModules/BasicServices/AdministrationService/forwardMessageToBoards
       {
         try {
           String boards[] = BoardModules.StringListHelper.read (in);
           BoardModules.Message message = BoardModules.MessageHelper.read (in);
           this.forwardMessageToBoards (boards, message);
           out = $rh.createReply();
         } catch (BoardModules.DestinationUnreachable $ex) {
           out = $rh.createExceptionReply ();
           BoardModules.DestinationUnreachableHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // BoardModules/BasicServices/AdministrationService/createUser
       {
         BoardModules.User newuser = BoardModules.UserHelper.read (in);
         this.createUser (newuser);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoardModules/BasicServices/AdministrationService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AdministrationService _this() 
  {
    return AdministrationServiceHelper.narrow(
    super._this_object());
  }

  public AdministrationService _this(org.omg.CORBA.ORB orb) 
  {
    return AdministrationServiceHelper.narrow(
    super._this_object(orb));
  }


} // class AdministrationServicePOA
