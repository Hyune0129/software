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
			System.out.println("[ 전시관 등록 ]");
			System.out.println("양식에 맞게 작성하여 제출해 주시길 바랍니다.");
			System.out.println("'입력종료'를 작성하면 작성이 완료됩니다.");
			System.out.println("==============================");
			System.out.print("전시관 이름 : ");
			name = input.nextLine();
			if(hasGalleryName(name))
			{
				System.out.println("이미 등록된 전시관입니다");
				continue;
			}
			System.out.print("전시관 소개 : ");
			while (true) {
				temp = input.nextLine();
				if (temp.equals("입력종료"))
					break;
				else {
					info.add(temp);
				}
			}
			System.out.println("==============================");
			System.out.println("==============================");
			System.out.println("전시관 이름 : " + name);
			System.out.print("전시관 소개 : ");
			for (int i = 0; i < info.size(); i++)
				System.out.println(info.get(i));
			System.out.println("==============================");
			System.out.println("해당 정보로 등록하시겠습니까? (예/아니오)>> ");
			temp = input.next();
		}while(temp.equals("예"));
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
	void deleteGalleryList(Gallery data){	//db 수정
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
		System.out.println("[0] 돌아가기");
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			System.out.println("["+(i+1)+"] "+temp.name);
		}
	}
}
