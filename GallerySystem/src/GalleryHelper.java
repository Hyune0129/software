import java.util.ArrayList;
import java.util.Scanner;

public class GalleryHelper {
	private static ArrayList<Gallery> galleryList;

	void init(DBManager db) {
		ArrayList<Gallery> temp = db.getGalleryData();
		if(temp ==null)
			temp = new ArrayList<Gallery>();
		this.galleryList = temp;
	}

	Gallery addGallery() {
		String name, temp=null;
		ArrayList<String> info = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("[ ���ð� ��� ]");
			System.out.println("��Ŀ� �°� �ۼ��Ͽ� ������ �ֽñ� �ٶ��ϴ�.");
			System.out.println("'�Է�����'�� �ۼ��ϸ� �ۼ��� �Ϸ�˴ϴ�.");
			System.out.println("==============================");
			System.out.print("���ð� �̸� : ");
			name = input.nextLine();
			if(hasGalleryName(name))
			{
				System.out.println("�̹� ��ϵ� ���ð��Դϴ�");
				continue;
			}
			System.out.print("���ð� �Ұ� : ");
			while (true) {
				temp = input.nextLine();
				if (temp.equals("�Է�����"))
					break;
				else {
					info.add(temp);
				}
			}
			System.out.println("==============================");
			System.out.println("==============================");
			System.out.println("���ð� �̸� : " + name);
			System.out.print("���ð� �Ұ� : ");
			for (int i = 0; i < info.size(); i++)
				System.out.println(info.get(i));
			System.out.println("==============================");
			System.out.println("�ش� ������ ����Ͻðڽ��ϱ�? (��/�ƴϿ�)>> ");
			temp = input.next();
		}while(temp.equals("��"));
		Gallery gallery = new Gallery(name, info);
		appendGalleryList(gallery);
		return gallery;
		
	}
	Gallery getGallery(String name){
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			if(name.equals(temp.getname()))
				return temp;	
		}
		return null;
	}
	Gallery getGallery(int num)
	{	
		if(num < -1 || num>=galleryList.size())
			return null;
		return galleryList.get(num);
	}
	boolean hasGalleryName(String name){
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			if(name.equals(temp.getname()))
				return true;	
		}
		return false;
	}
	void appendGalleryList(Gallery data){
		/*list�� �߰� �� db�� �߰�*/
		galleryList.add(data);
		DBManager db = new DBManager();
		db.writeGalleryData(data);
	}
	void deleteGalleryList(Gallery data){
		/*list�� db�� �ִ� data ����*/
		Gallery temp;
		DBManager db = new DBManager();
		for(int i=0; i<galleryList.size(); i++)
		{
			temp = galleryList.get(i);
			if(temp.equals(data))
			{
				db.deleteGalleryData(temp);
				galleryList.remove(i);
				return;
			}
		}
	}
	Gallery manageGallery(Gallery gallery){
		int num;
		String temp;
		Scanner input = new Scanner(System.in);
		ArrayList<String> info = gallery.getinfo();
		DBManager db = new DBManager();
		while(true)
		{
		System.out.println("���ð� ���� �� ���ϴ� �۾��� �������ּ���.");
		System.out.println("1. ���ð� ���� | 2. ���ð� ���� | 3. ���ù� ���� | 4. ���");
		num = Integer.parseInt(input.nextLine());
			switch (num) {
			case 1: // ���ð� ���� -> (���ð� ������ ����-> ���ð� ������ �߰�)
				System.out.println("=================================");
				for (int i = 0; i < info.size(); i++) {
					System.out.println("[" + (i + 1) + "] " + info.get(i));
				}
				System.out.println("=================================");
				while (true) {
					System.out.print("������ �������� �������ּ���.(��� : 0) >>");
					num = Integer.parseInt(input.nextLine());
					if (num == 0)
						return null;
					else if (num - 1 > info.size()) {
						System.out.println("��ȿ���� ���� �Է��Դϴ�.");
						continue;
					}
					break;
				}
				
				for (int i = info.size()-1; i >= num; i--)
					info.remove(i);
				while (true) { // info �Է� ����
					System.out.print( "["+ num++ + "] ");
					temp = input.nextLine();
					if (temp.equals("�Է�����"))
						break;
					info.add(temp);
				}
				// ����
				deleteGalleryList(gallery);
				Gallery newGallery = new Gallery(gallery.getname(), info);
				appendGalleryList(newGallery);
				System.out.println("������ �Ϸ�Ǿ����ϴ�!");
				System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
				input.nextLine();
				return newGallery;	//GalleryManager�� ownGallery�� ���� ����
			case 2:
				System.out.println("������ ���ð� [" +gallery.getname()+"]�� �����Ͻðڽ��ϱ�?");
				System.out.println("Ȯ���� ���� ������ ���ù��� �̸��� �ۼ��Ͽ� �ֽñ� �ٶ��ϴ�.");
				temp = input.nextLine();
				if(temp.equals(gallery.getname()))
				{
					deleteGalleryList(gallery);
					System.out.println("["+temp+"]�� ������ �Ϸ�Ǿ����ϴ�.");
					System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
					input.nextLine();
					return null;
				}
				else{
					System.out.println("�ش� �̸��� ���� �ʽ��ϴ�.");
					System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
					input.nextLine();
					return null;
				}
			case 3:
				ExhibitHelper eh = new ExhibitHelper();
				eh.manageExhibit(gallery);
				return null;
			case 4:
				return null;
			}
		}
	}
	void printGalleryList(){
		Gallery temp;
		System.out.println("[0] ���ư���");
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			System.out.println("["+(i+1)+"] "+temp.getname());
		}
	}
}
