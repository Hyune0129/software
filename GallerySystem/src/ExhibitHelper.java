import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ExhibitHelper {
	private static ArrayList<Exhibit> exhibitList;
	void init (DBManager db){
		this.exhibitList.addAll(db.getExhibitData());
	}
	Exhibit getExhibit(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.name.equals(name))
				return temp;
		}
		return null;
	}
	boolean hasExhibitName(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.name.equals(name))
				return true;
		}
		return false;
	}
	void appendExhibitData(Exhibit data){
		exhibitList.add(data);
		DBManager db = new DBManager();
		db.writeExhibitData(data);
	}
	void deleteExhibitData(Exhibit data){
		exhibitList.remove(data);
		DBManager db = new DBManager();
		db.deleteExhibitData(data);
	}
	ArrayList<Exhibit> getLocalExhibitList(String location){
		Exhibit temp;
		ArrayList<Exhibit> localList = new ArrayList<Exhibit>();
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.location.equals(location))
				localList.add(temp);
		}
		return localList;
	}
	void printLocalExhibitList(String location){
		ArrayList<Exhibit> localList;
		localList = getLocalExhibitList(location);
		for(int i=0; i<localList.size(); i++)
		{
			System.out.println("["+ i+1 + "] "+localList.get(i));
		}
		
	}
	void addExhibit(Gallery ownGallery){
		String name,temp=null;
		ArrayList<String> info = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		do
		{
			System.out.println("[ 전시물 추가 ]");
			System.out.println("양식에 맞게 작성하여 제출해 주시길 바랍니다.");
			System.out.println("'입력종료'를 작성하면 작성이 완료됩니다.");
			System.out.println("===============================");
			System.out.print("전시물 이름 : ");
			name = input.nextLine();
			if (hasExhibitName(name)) {
				System.out.println("이미 해당 전시관에서 등록된 전시물입니다.");
				continue;
			}
			System.out.print("전시물 소개 : ");
			while(true)
			{
				temp = input.nextLine();
				if(temp.equals("입력종료"))
					break;
				info.add(temp);
			}
			System.out.println("===============================");
			System.out.println("===============================");
			System.out.println("전시물 이름 : "+name);
			System.out.print("전시물 소개 : ");
			for(int i=0; i<info.size(); i++)
				System.out.println(info.get(i));
			System.out.println("===============================");
			System.out.print("해당 정보로 등록하시겠습니까? (예/아니오)>> ");
			if(input.next().equals("예"))
				break;
		}while(true);
		Exhibit exhibit = new Exhibit(name, info, ownGallery.getname());
		appendExhibitData(exhibit);
		
		
	}
}
