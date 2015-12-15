package BoardModules;


/**
* BoardModules/DestinationUnreachable.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 15. Dezember 2015 18:00 Uhr MEZ
*/

public final class DestinationUnreachable extends org.omg.CORBA.UserException
{
  public String unreachableBoard = null;

  public DestinationUnreachable ()
  {
    super(DestinationUnreachableHelper.id());
  } // ctor

  public DestinationUnreachable (String _unreachableBoard)
  {
    super(DestinationUnreachableHelper.id());
    unreachableBoard = _unreachableBoard;
  } // ctor


  public DestinationUnreachable (String $reason, String _unreachableBoard)
  {
    super(DestinationUnreachableHelper.id() + "  " + $reason);
    unreachableBoard = _unreachableBoard;
  } // ctor

} // class DestinationUnreachable
