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
		if(ownGallery == null)
			return false;
		else
			return true;
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
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
		switch(select)
		{
		case 2:	// 전시관 관리
			if(hasGallery()) {
				GalleryHelper manageGalleryHelper = new GalleryHelper();
				
				
				
				
				
			}
			break;
		case 3:	//add gallery
			if(hasGallery())
			{
				System.out.println("등록된 전시관이 이미 있습니다.");
				return;
			}
			GalleryHelper addGalleryHelper = new GalleryHelper();
			Gallery mygallery = addGalleryHelper.addGallery();
			setOwnGallery(mygallery);
			
			System.out.println("등록이 완료되었습니다!");
			break;
		case 4:	//add exhibit
			if(!hasGallery())
			{
				System.out.println("전시관 등록 후 가능한 작업입니다.");
				return;
			}
			ExhibitHelper addExhibitHelper = new ExhibitHelper();
			addExhibitHelper.addExhibit(ownGallery);
			System.out.println("등록이 완료되었습니다!");
			break;
		default:
				
		}
	}
}
