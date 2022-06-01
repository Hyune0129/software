import java.util.ArrayList;
import java.util.Scanner;

public class GalleryHelper {
	static ArrayList<Gallery> galleryList;

	void init(DBManager db) {
		this.galleryList.addAll(db.getGalleryData());
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
			if(name == temp.name)
				return temp;	
		}
		return null;
	}
	Gallery getGallery(int num)
	{	
		if(num < -1 || num>galleryList.size())
			return null;
		return galleryList.get(num);
	}
	boolean hasGalleryName(String name){
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			if(name == temp.name)
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
				db.deleteGalleryData(temp.getname());
				galleryList.remove(i);
				return;
			}
		}
	}
	Gallery manageGallery(Gallery gallery){
		int num;
		String temp;
		Scanner input = new Scanner(System.in);
		System.out.println("���ð� ���� �� ���ϴ� �۾��� �������ּ���.");
		System.out.println("1. ���ð� ���� | 2. ���ð� ���� | 3. ���ù� ����");
		System.out.print("�Է�>>");
		num = input.nextInt();
		GalleryHelper modifyGalleryHelper = new GalleryHelper();
		ArrayList<String> info = gallery.getinfo();
		System.out.println("=================================");
		for(int i =0; i<info.size(); i++)
		{
			System.out.println(i+" " + info.get(i));
		}
		System.out.println("=================================");
		while(true)
		{
			System.out.print("������ �������� �������ּ���.(��� : 0) >>");
			num = input.nextInt();
			if(num ==0 ) 
				return null;
			else if(num>info.size())
			{
				System.out.println("��ȿ���� ���� �Է��Դϴ�.");
			}
			break;
		}
		//����
		
		modifyGalleryHelper.deleteGalleryList(gallery);
		for(int i=num-1; i<info.size(); i++)
			info.remove(i);
		while(true)
		{	//info �Է� ����
			System.out.print(num++ + " ");
			temp = input.nextLine();
			if(temp.equals("�Է�����"))
				break;
			info.add(temp);
		}
			
		Gallery newGallery = new Gallery(gallery.getname(), info);
		modifyGalleryHelper.appendGalleryList(newGallery);
		return newGallery;
	}
	void printGalleryList(){
		Gallery temp;
		System.out.println("[0] ���ư���");
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			System.out.println("["+(i+1)+"] "+temp.name);
		}
	}
}
