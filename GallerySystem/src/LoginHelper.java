import java.util.ArrayList;

public class LoginHelper {
	static ArrayList<Member> memberList;
	void init(DBManager db){
		ArrayList<Member> temp = db.getMemberInfoData();
		if(temp ==null)
			temp = new ArrayList<Member>();
		this.memberList = temp;
	}
	boolean loginCheck(String ID,String password){
		Member temp;
		for(int i=0; i<memberList.size(); i++){
			temp = memberList.get(i);
			if(temp.getID().equals(ID) && temp.getPassword().equals(password))
				return true;
		}
		return false;
	}
	Member getMember(String ID){
		Member temp;
		for(int i=0; i<memberList.size(); i++){
			temp = memberList.get(i);
			if(temp.getID().equals(ID))
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
			if(temp.getID().equals(ID))
				return true;
		}
		return false;
	}
}
