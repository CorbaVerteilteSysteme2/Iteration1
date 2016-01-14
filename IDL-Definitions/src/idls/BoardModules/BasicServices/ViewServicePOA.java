package BoardModules.BasicServices;


/**
* BoardModules/BasicServices/ViewServicePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idls/BoardModules.idl
* Donnerstag, 14. Januar 2016 14:02 Uhr MEZ
*/

public abstract class ViewServicePOA extends org.omg.PortableServer.Servant
 implements BoardModules.BasicServices.ViewServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getAllMessageByDestination", new java.lang.Integer (0));
    _methods.put ("getState", new java.lang.Integer (1));
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
       case 0:  // BoardModules/BasicServices/ViewService/getAllMessageByDestination
       {
         try {
           String destination = in.read_string ();
           BoardModules.Message $result[] = null;
           $result = this.getAllMessageByDestination (destination);
           out = $rh.createReply();
           BoardModules.MessageListHelper.write (out, $result);
         } catch (BoardModules.DestinationUnreachable $ex) {
           out = $rh.createExceptionReply ();
           BoardModules.DestinationUnreachableHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // BoardModules/BasicServices/ViewService/getState
       {
         boolean $result = false;
         $result = this.getState ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoardModules/BasicServices/ViewService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ViewService _this() 
  {
    return ViewServiceHelper.narrow(
    super._this_object());
  }

  public ViewService _this(org.omg.CORBA.ORB orb) 
  {
    return ViewServiceHelper.narrow(
    super._this_object(orb));
  }


} // class ViewServicePOA
