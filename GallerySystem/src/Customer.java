import java.util.ArrayList;
import java.util.Scanner;

public class Customer implements SystemMain{
	@Override
	public void printMainpage() {
		int select;
		while (true) {
			SystemMain.clear();
			Scanner input = new Scanner(System.in);
			System.out.println("***전시관 관리 시스템***");
			System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
			System.out.println("=======================================================");
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 전시관 목록 | 4. 전시물 찾기 | 5. 종료");
			System.out.println("=======================================================");
			System.out.print("입력>> ");
			select = Integer.parseInt(input.nextLine());
			if(select == 5 ) 
				{
					input.close();
					return;	//프로그램 종료
				}
			else{
				mainSelect(select);
			}
		}
	}

	@Override
	public void mainSelect(int select) {
		Scanner input = new Scanner(System.in);
		LoginHelper lh = new LoginHelper();
		RegisterHelper rh = new RegisterHelper();
		ExhibitHelper eh = new ExhibitHelper();
		GalleryHelper gh = new GalleryHelper();
		switch (select) {
		case 1: // login
			String ID, password;
			while(true)
			{
				System.out.print("ID :");
				ID = input.nextLine();
				System.out.print("Password :");
				password = input.nextLine();
				if (lh.loginCheck(ID, password)) {
					Member user;
					user = lh.getMember(ID);
					user.printMainpage();
					break;	// 로그아웃
				}
				else
				{
					System.out.println("잘못된 아이디나 비밀번호 입력입니다.");
					System.out.println("엔터 입력시 메인 화면으로 이동합니다.");
					input.nextLine();
					break; // printmain
				}
			}
			break;
		case 2: // register
			String[] list = new String[4]; // ID, Password, phoneNumber, Email
			
			while(true)
			{
				System.out.println("[ 회원가입 ]");
				System.out.println("양식에 따라 정보를 기재해 주시길 바랍니다.");
				System.out.println("===========================");
				System.out.print("ID : ");
				list[0] = input.nextLine();
				System.out.print("Password : ");
				list[1] = input.nextLine();
				System.out.print("Phone Number(xxx-xxxx-xxxx) : ");
				list[2] = input.nextLine();
				System.out.print("Email(xxx@xxxxx.xxx) : ");
				list[3] = input.nextLine();
				System.out.println("==========================");
				if (rh.checkString(list[2], list[3])) { //phonenumber, email이 양식에 맞지 않음
					System.out.println("양식에 맞지 않는 입력입니다.");
					continue;	//다시 양식을 입력받는다.
				}
				else if(lh.hasID(list[0]))	// 중복 ID
				{
					System.out.println("이미 등록된 정보입니다.");
					continue;	//다시 양식을 입력받는다.
				}
				else
				{
					rh.requestRegister(list[0],list[1],list[2],list[3]);
					System.out.println("입력 완료되었습니다. 관리자의 승인 후 이용이 가능합니다.");
					System.out.println("입력을 받으면 돌아갑니다.");
					input.nextLine();
					break;
					
				}
			}
			break;
		case 3: // inquire galleryList
			
			while (true) { // galleryList 화면 상태
				System.out.println("[ 전시관 목록 ]");
				System.out.println("정보를 확인할 전시관을 선택해주세요.");
				System.out.println("=====================================");
				gh.printGalleryList();
				System.out.println("=====================================");
				System.out.print("원하는 입력>>");
				int num = Integer.parseInt(input.nextLine());
				if (num == 0) // 돌아가기 -> mainselect
				{
					break;
				}
				Gallery gallery = gh.getGallery(num - 1);
				if (gallery == null) {	//입력이 이상하면 null 출력
					System.out.println("존재하지 않는 선택지입니다.");
					System.out.println("다시 선택해주시기 바랍니다.");
					continue;
				}
				while (true) {	//get gallery data
					gallery.printGallery();
					System.out.println("1. 돌아가기 | 2. 전시물 목록");
					System.out.print("입력>>");
					num = Integer.parseInt(input.nextLine());
					if (num == 1) // 돌아가기->galleryList
						break;
					else if(num == 2)
					{	//exhibit List
						while(true){
							System.out.println("[ 전시물 목록 ]");
							System.out.println("정보를 확인할 전시관을 선택해주세요.");
							System.out.println("======================================");
							System.out.println("[0] 돌아가기");
							eh.printLocalExhibitList(gallery.getname());
							System.out.println("======================================");
							System.out.print("입력>>");
							num = Integer.parseInt(input.nextLine());
							if(num == 0) break;
							/*getExhibit*/
							ArrayList<Exhibit> localexhibitList = eh.getLocalExhibitList(gallery.getname());
							if(num-1 > localexhibitList.size() || num < 0)
							{
								System.out.println("등록되지 않은 선택지입니다. 다시 선택해주십시오.");
								continue;
							}
							else
							{	//전시물 선택한 것의 정보를 확인
								Exhibit exhibit = localexhibitList.get(num-1);
								exhibit.printExhibit();
								System.out.println("입력을 받으면 전시물 목록으로 돌아갑니다.");
								input.nextLine();
								continue;
							}
						}
					}
					else
					{
						System.out.println("적절하지 않은 입력입니다.");
						continue;
					}
				}
			}
			break;
		case 4: // find exhibit
			String name;
			Exhibit exhibit;
			System.out.println("[ 전시물 찾기 ]");
			System.out.print("찾고 싶은 전시물의 이름을 입력하세요>>");
			name = input.nextLine();
			exhibit = eh.getExhibit(name);
			if (exhibit == null) {
				System.out.println("해당하는 이름의 전시물이 없습니다.");
				return;
			} 
			else {
				System.out.println("[ 전시관 : "+exhibit.getlocation()+" ]");
				exhibit.printExhibit();
			}
				System.out.print("입력을 받으면 메인화면으로 돌아갑니다.");
				input.nextLine();
			break;
		default:
			System.out.println("잘못된 입력");
		}
	}
}
