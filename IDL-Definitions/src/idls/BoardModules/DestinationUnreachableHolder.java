package BoardModules;

/**
* BoardModules/DestinationUnreachableHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 12. Januar 2016 10:29 Uhr MEZ
*/

public final class DestinationUnreachableHolder implements org.omg.CORBA.portable.Streamable
{
  public BoardModules.DestinationUnreachable value = null;

  public DestinationUnreachableHolder ()
  {
  }

  public DestinationUnreachableHolder (BoardModules.DestinationUnreachable initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BoardModules.DestinationUnreachableHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BoardModules.DestinationUnreachableHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BoardModules.DestinationUnreachableHelper.type ();
  }

}
