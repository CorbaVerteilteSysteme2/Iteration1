package BoardModules.BasicServices;

/**
* BoardModules/BasicServices/BoardServiceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idls/BoardModules.idl
* Donnerstag, 14. Januar 2016 14:02 Uhr MEZ
*/

public final class BoardServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public BoardModules.BasicServices.BoardService value = null;

  public BoardServiceHolder ()
  {
  }

  public BoardServiceHolder (BoardModules.BasicServices.BoardService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BoardModules.BasicServices.BoardServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BoardModules.BasicServices.BoardServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BoardModules.BasicServices.BoardServiceHelper.type ();
  }

}
