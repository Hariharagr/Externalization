import java.io.*;

class Externalizabledemo
{
	public static void main(String[] args) throws Exception
	{
		demo d1 = new demo("hari",101,102);  //assigning aruguments,calling constructor
		FileOutputStream fos = new FileOutputStream("file232.scr");
		ObjectOutputStream oos =new  ObjectOutputStream(fos);
		oos.writeObject(d1);         // calling writeExternal() of demo class implements Externalization interface


		FileInputStream fis = new FileInputStream("file232.scr");
		ObjectInputStream ois = new ObjectInputStream(fis);
		demo d2 = (demo)ois.readObject();           //calling readExternal() of demo class implements Externalization interface
		System.out.println(d2.name+"........"+d2.pin+"........"+d2.oldpin);    
		                                    //d2.pin remains default(non-serialized)
	}                      

}
class demo implements Externalizable {

	String name ;
	int pin ;
	int oldpin ;

	public demo()                             //constructor creates current object during deserialization
		{
		System.out.println("contructor for creating current object to assign deserialized value");
	}
	public demo(String s, int pin,int oldpin)       //assigning arguments to d1 object
		{
		this.name  =  s;
		this.pin  =  pin;
		this.oldpin = oldpin;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);               //writes variable name in "file232.scr" file
		out.writeInt(oldpin);                 //writes variable oldpin in "file232.scr" file
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = (String) in.readObject();        //deserializing  string from "file232.scr" file
		oldpin = in.readInt();                  //deserializing  int from "file232.scr" file
	}
}