import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Member implements SystemMain{
	@Override
	public void printMainpage() {
		Scanner input = new Scanner(System.in);
		int select;
		while (true) {
			System.out.println("***전시관 관리 시스템***");
			System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
			System.out.println("===============================================");
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 전시관 목록 | 4. 전시물 찾기");
			System.out.println("===============================================");
			System.out.print("입력>> ");
			select = input.nextInt();
			mainSelect(select);
		}
	}

	@Override
	public void mainSelect(int select) {
		Scanner input = new Scanner(System.in);
		LoginHelper lh = new LoginHelper();
		switch (select) {
		case 1: // login
			String ID, password;
			while(true)
			{
				System.out.print("ID :");
				ID = input.next();
				System.out.print("Password :");
				password = input.next();
				if (lh.loginCheck(ID, password)) {
					Member user;
					user = lh.getMember(ID);
					user.printMainpage();
					break;	// 로그아웃
				}
				else
				{
					System.out.println("잘못된 아이디나 비밀번호 입력입니다.");
					continue;
				}
			}
			break;
		case 2: // register
			String[] list = new String[4]; // ID, Password, phoneNumber, Email
			RegisterHelper rh = new RegisterHelper();
			while(true)
			{
				System.out.println("[ 회원가입 ]");
				System.out.println("양식에 따라 정보를 기재해 주시길 바랍니다.");
				System.out.println("===========================");
				System.out.print("ID : ");
				list[0] = input.next();
				System.out.print("Password : ");
				list[1] = input.next();
				System.out.print("Phone Number : ");
				list[2] = input.next();
				System.out.print("Email : ");
				list[3] = input.next();
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
					input.next();
					break;
				}
			}
			break;
		case 3: // inquire galleryList
			GalleryHelper gh = new GalleryHelper();
			while (true) { // galleryList 화면 상태
				gh.printGalleryList();
				System.out.print("원하는 입력>>");
				int num = input.nextInt();
				if (num == 0) // 돌아가기 -> mainselect
					break;
				Gallery gallery = gh.getGallery(num - 1);
				if (gallery == null) {
					System.out.println("적절하지 않은 입력");
					continue;
				}
				while (true) {
					gallery.printGallery();
					num = input.nextInt();
					if (num == 0) // 돌아가기->galleryList
						break;
					else if(num == 1)
					{	//exhibit List
						ExhibitHelper eh = new ExhibitHelper();
						eh.printLocalExhibitList(gallery.getname());
						System.out.print("원하는 입력>>");
						num = input.nextInt();
					}
					else
					{
						System.out.println("적절하지 않은 입력");
						continue;
					}

				}
			}
			break;
		case 4: // find exhibit
			String name;
			Exhibit exhibit;
			ExhibitHelper eh = new ExhibitHelper();
			System.out.print("전시물 이름?");
			name = input.next();
			exhibit = eh.getExhibit(name);
			if (exhibit == null) {
				System.out.println("해당하는 전시물 없음");
			} else {
				System.out.println("전시물 출력");
			}
			break;
		default:
			System.out.println("잘못된 입력");
		}
	}

}
