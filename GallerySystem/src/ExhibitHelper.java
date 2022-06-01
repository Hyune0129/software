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
			System.out.println("[ ���ù� �߰� ]");
			System.out.println("��Ŀ� �°� �ۼ��Ͽ� ������ �ֽñ� �ٶ��ϴ�.");
			System.out.println("'�Է�����'�� �ۼ��ϸ� �ۼ��� �Ϸ�˴ϴ�.");
			System.out.println("===============================");
			System.out.print("���ù� �̸� : ");
			name = input.nextLine();
			if (hasExhibitName(name)) {
				System.out.println("�̹� �ش� ���ð����� ��ϵ� ���ù��Դϴ�.");
				continue;
			}
			System.out.print("���ù� �Ұ� : ");
			while(true)
			{
				temp = input.nextLine();
				if(temp.equals("�Է�����"))
					break;
				info.add(temp);
			}
			System.out.println("===============================");
			System.out.println("===============================");
			System.out.println("���ù� �̸� : "+name);
			System.out.print("���ù� �Ұ� : ");
			for(int i=0; i<info.size(); i++)
				System.out.println(info.get(i));
			System.out.println("===============================");
			System.out.print("�ش� ������ ����Ͻðڽ��ϱ�? (��/�ƴϿ�)>> ");
			if(input.next().equals("��"))
				break;
		}while(true);
		Exhibit exhibit = new Exhibit(name, info, ownGallery.getname());
		appendExhibitData(exhibit);
		
		
	}
}
