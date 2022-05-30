import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterHelper {
	static ArrayList<GalleryManager> requestList;
	
	void acceptRegister(GalleryManager manager){
		DBManager db = new DBManager();
		LoginHelper lh = new LoginHelper();
		db.writeMemberData(manager);
		lh.addMember(manager);
	}
	void requestRegister(String ID, String password, String phoneNumber, String email){
		
		requestList.add(new GalleryManager(ID, password, phoneNumber, email));
		
		
		
	}
	boolean checkString(String phoneNumber, String email){
		
		if(Pattern.matches("\\d{3}-\\d{4}-\\d{4}", phoneNumber))
		{
			return false;
		}
		else if(Pattern.matches("^(.+)@(.+)$", email)) 
		{
			return false;
		}
		else
			return true;
	}
	GalleryManager getRegisterRequest(){
		GalleryManager temp;
		temp = requestList.get(0);
		requestList.remove(0);
		return temp;
	}
	boolean hasRequest(){
		return !requestList.isEmpty();
	}
}
