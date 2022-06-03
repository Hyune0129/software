import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ExhibitHelper {
	private static ArrayList<Exhibit> exhibitList;
	void init (DBManager db){
		ArrayList<Exhibit> temp = db.getExhibitData();
		if(temp ==null)
			temp = new ArrayList<Exhibit>();
		this.exhibitList = temp;
	}
	Exhibit getExhibit(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.getname().equals(name))
				return temp;
		}
		return null;
	}
	boolean hasExhibitName(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.getname().equals(name))
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
			if(temp.getlocation().equals(location))
				localList.add(temp);
		}
		return localList;
	}
	void printLocalExhibitList(String location){
		ArrayList<Exhibit> localList;
		localList = getLocalExhibitList(location);
		for(int i=0; i<localList.size(); i++)
		{
			System.out.println("["+ (i+1) + "] "+localList.get(i).getname());
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
			if(input.nextLine().equals("�ƴϿ�"))
			{
				name = "";
				info.clear();
				continue;
			}
			else
				break;
		}while(true);
		Exhibit exhibit = new Exhibit(name, info, ownGallery.getname());
		appendExhibitData(exhibit);
	}
	void manageExhibit(Gallery gallery){
		Scanner input = new Scanner(System.in);
		int num;
		String temp;
		while(true)
		{
			System.out.println("[ ���ð� ���� ]");
			System.out.println("������ ���ù��� �����ϼ���.");
			System.out.println("=====================================");
			ArrayList<Exhibit> exhibitList = getLocalExhibitList(gallery.getname());
			System.out.println("[0] ���ư���");
			for (int i = 0; i < exhibitList.size(); i++) {
				System.out.println("[" + (i + 1) + "]" + exhibitList.get(i).getname());
			}
			System.out.println("=====================================");
			System.out.print("�Է�>>");
			num = Integer.parseInt(input.nextLine());
			if(num == 0 ){
				return;
			}
			else if(num-1>exhibitList.size()||num<0)
			{
				System.out.println("��ȿ���� ���� ���Դϴ�. �ٽ� �Է��� �ֽñ� �ٶ��ϴ�.");
				continue;
			}
			Exhibit exhibit = exhibitList.get(num-1);
			while(true){
				System.out.println("[" + exhibit.getname() + "]�� ��� �Ͻðڽ��ϱ�?");
				System.out.println("1. ���ù� ���� | 2. ���ù� ����");
				num = Integer.parseInt(input.nextLine());
				switch(num)
				{
				case 1:
					ArrayList<String> info = exhibit.getinfo();
					while(true){
						System.out.println("=========================================");
						for (int i = 0; i < info.size(); i++) {
							System.out.println("[" + (i + 1) + "]" + info.get(i));
						}
						System.out.println("=========================================");
						System.out.print("������ �������� �������ּ���. (��� : 0)>>");
						num = Integer.parseInt(input.nextLine());
						if(num<0 || num-1 > info.size()){
							System.out.println("��ȿ���� ���� ���Դϴ�. �ٽ� �Է��� �ֽʽÿ�.");
							continue;
						}
						else if(num==0){//���
							return;
						}
						System.out.println("=========================================");
						for(int i=0; i< num-1; i++)
						{
							System.out.print("["+(i+1)+"] ");
							System.out.println(info.get(i));
						}
						for (int i = info.size()-1; i >= num; i--)
							info.remove(i);
						while(true){
							System.out.print("["+ num++ +"] ");
							temp = input.nextLine();
							if(temp.equals("�Է�����"))
							{
								String name = exhibit.getname();
								String location = exhibit.getlocation();
								deleteExhibitData(exhibit);
								appendExhibitData(new Exhibit(name, info, location));
								System.out.println("������ �Ϸ�Ǿ����ϴ�!");
								System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
								input.nextLine();
								return;
							}
							info.add(temp);
						}
					}
				case 2:
					System.out.println("������ ["+exhibit.getname()+"]�� �����Ͻðڽ��ϱ�?");
					System.out.println("Ȯ���� ���� ������ ���ù��� �̸��� �ۼ��Ͽ� �ֽñ� �ٶ��ϴ�.");
					temp = input.nextLine();
					if(temp.equals(exhibit.getname()))
					{
						deleteExhibitData(exhibit);
						System.out.println("������ �Ϸ�Ǿ����ϴ�.");
						System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
						input.nextLine();
					}
					else{
						System.out.println("������ ��ҵǾ����ϴ�.");
						System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
						input.nextLine();
						return;
					}
				default:
					System.out.println("��ȿ���� ���� ���Դϴ�.");
					continue;
				}
			}
		}
	}
}
