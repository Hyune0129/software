
public class Customer extends Member implements SystemMain{
	LoginHelper loginhelper;
	GalleryHelper galleryhelper;
	ExhibitHelper exhibithelper;
	RegisterHelper registerHelper;
	Customer(){
		
	}
	@Override
	public void printMainpage() {
		System.out.println("***전시관 관리 시스템***");
		System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
		System.out.println("===============================================");
		System.out.println("1. 로그인 | 2. 회원가입 | 3. 전시관 목록 | 4. 전시물 찾기");
		System.out.println("===============================================");
		System.out.print("입력>> ");
	}

	@Override
	public void mainSelect(int select) {
		switch(select)
		{
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			
		}
		
	}
	
}
