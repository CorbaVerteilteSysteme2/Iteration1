package BoardModules.BasicServices;

/**
* BoardModules/BasicServices/AdministrationServiceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/Tobias/Documents/NetBeansProjects/VS2_GitRepository/IDL-Definitions/src/idls/BoardModules.idl
* Donnerstag, 7. Januar 2016 20:23 Uhr MEZ
*/

public final class AdministrationServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public BoardModules.BasicServices.AdministrationService value = null;

  public AdministrationServiceHolder ()
  {
  }

  public AdministrationServiceHolder (BoardModules.BasicServices.AdministrationService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BoardModules.BasicServices.AdministrationServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BoardModules.BasicServices.AdministrationServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BoardModules.BasicServices.AdministrationServiceHelper.type ();
  }

}
