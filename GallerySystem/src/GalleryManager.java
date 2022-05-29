
public class GalleryManager extends Member implements SystemMain{
	Gallery ownGallery;
	GalleryManager(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = false;
	}
	boolean hasGallery(){
		
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
	}
	@Override
	public void printMainpage() {
		System.out.println("***전시관 관리 시스템***");
		System.out.println("환영합니다 "+ID+"님!");
		System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
		System.out.println("=================================");
		System.out.println("1. 로그아웃 | 2. 전시관 관리 | 3. 전시관 등록 | 4. 전시물 추가");
		System.out.println("=================================");
		
	}
	@Override
	public void mainSelect(int select) {
		// TODO Auto-generated method stub
		
	}
}
