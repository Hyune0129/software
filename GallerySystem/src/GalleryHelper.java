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
			while(true)
			{
				System.out.println("==============================");
				System.out.println("==============================");
				System.out.println("전시관 이름 : " + name);
				System.out.print("전시관 소개 : ");
				for (int i = 0; i < info.size(); i++)
					System.out.println(info.get(i));
				System.out.println("==============================");
				System.out.print("해당 정보로 등록하시겠습니까? (예/아니오)>> ");
				temp = input.nextLine();
				if (temp.equals("아니오") || temp.equals("예")) {
					break;
				} 
				else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			}
			if (temp.equals("아니오"))
			{
				name = "";
				info.clear();
				continue;
			}
			else if(temp.equals("예"))
				break;
		}while(true);
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
				db.deleteGalleryData(temp);
				galleryList.remove(i);
				return;
			}
		}
	}
	void manageGallery(Gallery gallery){
		int num;
		String temp;
		Scanner input = new Scanner(System.in);
		DBManager db = new DBManager();
		while(true)
		{
		System.out.println("전시관 관리 중 원하는 작업을 선택해주세요.");
		System.out.println("===============================================");
		System.out.println("1. 전시관 수정 | 2. 전시관 삭제 | 3. 전시물 관리 | 4. 취소");
		System.out.println("===============================================");
		System.out.print("입력>>");
		num = Integer.parseInt(input.nextLine());
			switch (num) {
			case 1: // 전시관 수정 -> (전시관 데이터 삭제-> 전시관 데이터 추가)
				ArrayList<String> info = gallery.getinfo();
				System.out.println("=================================");
				for (int i = 0; i < info.size(); i++) {
					System.out.println("[" + (i + 1) + "] " + info.get(i));
				}
				System.out.println("=================================");
				while (true) {
					System.out.print("수정할 시작점을 선택해주세요.(취소 : 0) >>");
					num = Integer.parseInt(input.nextLine());
					if (num == 0)
						return;
					else if (num > info.size()) {
						System.out.println("유효하지 않은 입력입니다.");
						continue;
					}
					break;
				}
				deleteGalleryList(gallery);// DB 수정을 위한 DB의 data삭제
				for (int i = info.size(); i >= num; i--)
					info.remove(i-1);
				while (true) { // info 입력 수정
					System.out.print( "["+ num++ + "] ");
					temp = input.nextLine();
					if (temp.equals("입력종료"))
						break;
					info.add(temp);
				}
				appendGalleryList(gallery);
				System.out.println("수정이 완료되었습니다!");
				System.out.println("엔터 입력시 메인화면으로 이동합니다.");
				input.nextLine();
				return;
			case 2:
				System.out.println("정말로 전시관 [" +gallery.getname()+"]를 삭제하시겠습니까?");
				System.out.println("확인을 위해 삭제할 전시물의 이름을 작성하여 주시길 바랍니다.");
				temp = input.nextLine();
				if(temp.equals(gallery.getname()))
				{
					deleteGalleryList(gallery);
					ExhibitHelper eh = new ExhibitHelper();
					ArrayList<Exhibit> exhibitList = eh.getLocalExhibitList(gallery.getname());
					for(int i=0; i<exhibitList.size(); i++)// 전시관 안에 있던 전시물 정보도 삭제
					{
						Exhibit exhibit = exhibitList.get(i);
						eh.deleteExhibitList(exhibit);
						exhibit = null;	//삭제
					}
					gallery = null;	//삭제
					System.out.println("["+temp+"]의 삭제가 완료되었습니다.");
					System.out.println("엔터 입력시 메인화면으로 이동합니다.");
					input.nextLine();
					return;
				}
				else{
					System.out.println("해당 이름과 맞지 않습니다.");
					System.out.println("엔터 입력시 메인화면으로 이동합니다.");
					input.nextLine();
					return;
				}
			case 3:
				ExhibitHelper eh = new ExhibitHelper();
				eh.manageExhibit(gallery);
				return;
				
			case 4:
				return;
			}
		}
	}
	void printGalleryList(){
		Gallery temp;
		System.out.println("[0] 돌아가기");
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			System.out.println("["+(i+1)+"] "+temp.getname());
		}
	}
}
