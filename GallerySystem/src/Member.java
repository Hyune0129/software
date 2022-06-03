
public abstract class Member implements SystemMain {
	protected String ID;
	protected String password;
	protected String phoneNumber;
	protected String email;
	boolean adminator;
	boolean isAdministrator(){
		return adminator;
	}
	String getID()
	{
		return ID;
	}
	String getPassword(){
		return password;
	}
	String getPhoneNumber(){
		return phoneNumber;
	}
	String getEmail(){
		return email;
	}
}
