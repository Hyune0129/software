import java.util.ArrayList;

public class LoginHelper {
	static ArrayList<Member> memberList;
	LoginHelper(){
		DBManager db = new DBManager();
		memberList = db.getMemberInfoData();
	}
	boolean loginCheck(String ID,String password){
		Member temp;
		for(int i=0; i<memberList.size(); i++){
			temp = memberList.get(i);
			if(temp.ID == ID && temp.password == password)
				return true;
		}
		return false;
	}
	Member getMember(String ID){
		Member temp;
		for(int i=0; i<memberList.size(); i++){
			temp = memberList.get(i);
			if(temp.ID == ID)
			{
				return temp;
			}
		}
		return null;
	}
	void addMember(Member member){
		memberList.add(member);
	}
	boolean hasID(String ID){
		Member temp;
		for(int i=0; i<memberList.size(); i++){
			temp = memberList.get(i);
			if(temp.ID == ID)
				return true;
		}
		return false;
	}
}
