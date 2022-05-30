import java.util.ArrayList;

public class RegisterHelper {
	static ArrayList<GalleryManager> requestList;
	void acceptRegister(GalleryManager manager){
		DBManager db = new DBManager();
		LoginHelper lh = new LoginHelper();
		db.writeMemberData(manager);
		lh.addMember(manager);
	}
	void requestRegister(String ID, String password, String phoneNumber, String email){
		
		if(checkString(ID, password, phoneNumber, email))
		{
			requestList.add(new GalleryManager(ID, password, phoneNumber, email));
		}
		else
		{
			System.out.println("양식에 맞지 않은 요청입니다");
		}
	}
	boolean checkString(String id, String password, String phonenumber, String email){
		
	}
	GalleryManager getRegisterRequest(){
		GalleryManager temp;
		temp = requestList.get(0);
		requestList.remove(0);
		return temp;
	}
	void addRequestList(){
		
	}
	boolean hasRequest(){
		return !requestList.isEmpty();
	}
}
