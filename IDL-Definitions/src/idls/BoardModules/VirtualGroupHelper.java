package BoardModules;


/**
* BoardModules/VirtualGroupHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Dienstag, 15. Dezember 2015 18:00 Uhr MEZ
*/

abstract public class VirtualGroupHelper
{
  private static String  _id = "IDL:BoardModules/VirtualGroup:1.0";

  public static void insert (org.omg.CORBA.Any a, BoardModules.VirtualGroup that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BoardModules.VirtualGroup extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "vgoupname",
            _tcOf_members0,
            null);
          _tcOf_members0 = BoardModules.BoardHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "members",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (BoardModules.VirtualGroupHelper.id (), "VirtualGroup", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BoardModules.VirtualGroup read (org.omg.CORBA.portable.InputStream istream)
  {
    BoardModules.VirtualGroup value = new BoardModules.VirtualGroup ();
    value.vgoupname = istream.read_string ();
    int _len0 = istream.read_long ();
    value.members = new BoardModules.Board[_len0];
    for (int _o1 = 0;_o1 < value.members.length; ++_o1)
      value.members[_o1] = BoardModules.BoardHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BoardModules.VirtualGroup value)
  {
    ostream.write_string (value.vgoupname);
    ostream.write_long (value.members.length);
    for (int _i0 = 0;_i0 < value.members.length; ++_i0)
      BoardModules.BoardHelper.write (ostream, value.members[_i0]);
  }

}
