import java.util.ArrayList;
import java.util.Scanner;

public class GalleryManager extends Member{
	Gallery ownGallery;
	GalleryManager(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = false;
	}
	boolean hasGallery(){
		GalleryHelper gh = new GalleryHelper();
		if(gh.hasGalleryName(ownGallery.getname()))
			return true;
		else
			return false;
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
	}
	void printinfo(){	//register accept에서 정보를 print하기 위한 메소드
		System.out.println("====================================");
		System.out.println("ID : "+this.ID);
		System.out.println("Password : "+this.password);
		System.out.println("Phone Number : "+this.phoneNumber);
		System.out.println("Email : "+this.email);
		System.out.println("====================================");
	}
	@Override
	public void printMainpage() {
		int select;
		Scanner input = new Scanner(System.in);
		System.out.println("***전시관 관리 시스템***");
		System.out.println("환영합니다 "+ID+"님!");
		System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
		System.out.println("=================================");
		System.out.println("1. 로그아웃 | 2. 전시관 관리 | 3. 전시관 등록 | 4. 전시물 추가");
		System.out.println("=================================");
		select = input.nextInt();
		if(select==1)	//logout
		{
			System.out.print("정말로 로그아웃하시겠습니까?(Y/N)");
			String temp = input.next();
			if(temp.equals("Y") || temp.equals("y"))
			{
				return;
			}
		}
		mainSelect(select);
	}
	@Override
	public void mainSelect(int select) {
		GalleryHelper gh = new GalleryHelper();
		ExhibitHelper eh = new ExhibitHelper();
		Scanner input = new Scanner(System.in);
		String temp;
		int num;
		switch(select)
		{
		case 2:	// 전시관 관리
			System.out.println("[ 전시관 관리 ]");
			System.out.print("관리할 전시관의 이름을 작성해주세요. >>");
			temp = input.nextLine();
			if(!temp.equals(ownGallery.getname()))
			{
				System.out.println("이름이 틀리거나, 본인이 등록한 전시관이 아닙니다.");
				return;
			}
			
			
			Gallery newgallery = gh.manageGallery(ownGallery);
			if(newgallery == null)	//취소 시 null 반환 
				return;
			setOwnGallery(newgallery);
			break;
			
		case 3:	//add gallery
			if(hasGallery())
			{
				System.out.println("등록된 전시관이 이미 있습니다.");
				return;
			}
			Gallery mygallery = gh.addGallery();
			setOwnGallery(mygallery);
			
			System.out.println("등록이 완료되었습니다!");
			break;
		case 4:	//add exhibit
			if(!hasGallery())
			{
				System.out.println("전시관 등록 후 가능한 작업입니다.");
				return;
			}
			
			eh.addExhibit(ownGallery);
			System.out.println("등록이 완료되었습니다!");
			break;
		default:
				
		}
	}
}
