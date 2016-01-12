package BoardModules.BasicServices;


/**
* BoardModules/BasicServices/_AdministrationServiceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 12. Januar 2016 10:29 Uhr MEZ
*/

public class _AdministrationServiceStub extends org.omg.CORBA.portable.ObjectImpl implements BoardModules.BasicServices.AdministrationService
{

  public void createVirtualGroup (String vgroupname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createVirtualGroup", true);
                $out.write_string (vgroupname);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createVirtualGroup (vgroupname        );
            } finally {
                _releaseReply ($in);
            }
  } // createVirtualGroup

  public void loginToVirtualGroup (String vgroupname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("loginToVirtualGroup", true);
                $out.write_string (vgroupname);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                loginToVirtualGroup (vgroupname        );
            } finally {
                _releaseReply ($in);
            }
  } // loginToVirtualGroup

  public String[] getAllVirtualGroups ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getAllVirtualGroups", true);
                $in = _invoke ($out);
                String $result[] = BoardModules.StringListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getAllVirtualGroups (        );
            } finally {
                _releaseReply ($in);
            }
  } // getAllVirtualGroups

  public void forwardMessageToBoards (String[] boards, BoardModules.Message message) throws BoardModules.DestinationUnreachable
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("forwardMessageToBoards", true);
                BoardModules.StringListHelper.write ($out, boards);
                BoardModules.MessageHelper.write ($out, message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BoardModules/DestinationUnreachable:1.0"))
                    throw BoardModules.DestinationUnreachableHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                forwardMessageToBoards (boards, message        );
            } finally {
                _releaseReply ($in);
            }
  } // forwardMessageToBoards

  public void createUser (BoardModules.User newuser)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createUser", true);
                BoardModules.UserHelper.write ($out, newuser);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createUser (newuser        );
            } finally {
                _releaseReply ($in);
            }
  } // createUser

  public String[] getAllUsers ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getAllUsers", true);
                $in = _invoke ($out);
                String $result[] = BoardModules.StringListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getAllUsers (        );
            } finally {
                _releaseReply ($in);
            }
  } // getAllUsers

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoardModules/BasicServices/AdministrationService:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _AdministrationServiceStub
