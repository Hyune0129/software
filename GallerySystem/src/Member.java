
public abstract class Member implements SystemMain {
	String ID;
	String password;
	String phoneNumber;
	String email;
	boolean adminator;
	boolean isAdministrator(){
		return adminator;
	}
}
