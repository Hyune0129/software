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
		/*list에 추가 및 db에 추가*/
		galleryList.add(data);
		DBManager db = new DBManager();
		db.writeGalleryData(data);
	}
	void deleteGalleryList(Gallery data){
		/*list와 db에 있는 data 제거*/
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
		System.out.println("전시관 관리 중 원하는 작업을 선택해주세요.");
		System.out.println("1. 전시관 수정 | 2. 전시관 삭제 | 3. 전시물 관리");
		System.out.print("입력>>");
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
			System.out.print("수정할 시작점을 선택하주세요.(취소 : 0) >>");
			num = input.nextInt();
			if(num ==0 ) 
				return null;
			else if(num>info.size())
			{
				System.out.println("유효하지 않은 입력입니다.");
			}
			break;
		}
		//삭제
		
		modifyGalleryHelper.deleteGalleryList(gallery);
		for(int i=num-1; i<info.size(); i++)
			info.remove(i);
		while(true)
		{	//info 입력 수정
			System.out.print(num++ + " ");
			temp = input.nextLine();
			if(temp.equals("입력종료"))
				break;
			info.add(temp);
		}
			
		Gallery newGallery = new Gallery(gallery.getname(), info);
		modifyGalleryHelper.appendGalleryList(newGallery);
		return newGallery;
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
