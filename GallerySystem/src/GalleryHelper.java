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
		DBManager db = new DBManager();
		db.writeGalleryData(gallery);
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
		galleryList.add(data);
	}
	void deleteGalleryList(Gallery data){	//db ����
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++)
		{
			temp = galleryList.get(i);
			if(temp.equals(data))
			{
				galleryList.remove(i);
				return;
			}
		}
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
