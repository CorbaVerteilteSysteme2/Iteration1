package BoardModules;


/**
* BoardModules/UnknownUser.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 15. Dezember 2015 18:00 Uhr MEZ
*/

public final class UnknownUser extends org.omg.CORBA.UserException
{
  public String username = null;

  public UnknownUser ()
  {
    super(UnknownUserHelper.id());
  } // ctor

  public UnknownUser (String _username)
  {
    super(UnknownUserHelper.id());
    username = _username;
  } // ctor


  public UnknownUser (String $reason, String _username)
  {
    super(UnknownUserHelper.id() + "  " + $reason);
    username = _username;
  } // ctor

} // class UnknownUser
