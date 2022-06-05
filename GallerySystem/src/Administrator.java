import java.util.Scanner;

public class Administrator extends Member{
	Administrator(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = true;
	}

	@Override
	public void printMainpage() {
		int select;
		while(true)
		{
			SystemMain.clear();
			Scanner input = new Scanner(System.in);
			System.out.println("***전시관 관리 시스템***");
			System.out.println("환영합니다 " + ID + "님!");
			System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
			System.out.println("=======================================");
			System.out.println("1. 로그아웃 | 2. 전시관 관리 | 3. 가입 요청 관리");
			System.out.println("=======================================");
			System.out.print("입력 >>");
			select = Integer.parseInt(input.nextLine());
			
			if(select==1)	//logout
			{
				System.out.print("정말로 로그아웃하시겠습니까?(Y/N)");
				String temp = input.nextLine();
				if(temp.equals("Y") || temp.equals("y"))
				{
					return;
				}
			}
			mainSelect(select);
		}
	}

	@Override
	public void mainSelect(int select) {
		Scanner input = new Scanner(System.in);
		GalleryHelper gh = new GalleryHelper();
		RegisterHelper rh = new RegisterHelper();
		String temp;
		switch(select)
		{
		case 2:	//전시관 관리
			System.out.println("[ 전시관 관리 ]");
			System.out.print("관리할 전시관의 이름을 작성해주세요. >>");
			temp = input.nextLine();
			if(!gh.hasGalleryName(temp))
			{
				System.out.println("해당하는 전시관이 없습니다.");
				System.out.println("엔터 입력시 메인화면으로 이동합니다.");
				input.nextLine();
				break;
			}
			gh.manageGallery(gh.getGallery(temp));
			break;	
		case 3:	//가입 요청 관리
			GalleryManager requestuser;
			System.out.println("[ 가입 요청 관리 ]");
			while(true)
			{
				requestuser = rh.getRegisterRequest();
				if (requestuser == null) // 가입요청이 없을 경우
				{
					System.out.println("가입 요청이 더 이상 존재하지 않습니다.");
					System.out.println("엔터 입력시 메인화면으로 이동됩니다.");
					input.nextLine();
					break;
				}
				while(true)
				{
					requestuser.printinfo();
					System.out.println("확인한 뒤 '승인' 또는 '거부'를 입력해 주시길 바랍니다.");
					System.out.print("입력>>");
					temp = input.nextLine();
					DBManager db = new DBManager();
					if (temp.equals("승인")) {
						rh.acceptRegister(requestuser);
						System.out.println("요청을 승인하였습니다.");
						db.deleteRequestData(requestuser);
						break;
					} else if (temp.equals("거부")) {
						System.out.println("요청을 거부하였습니다.");
						db.deleteRequestData(requestuser);
						break;
					} else {
						System.out.println("잘못된 입력입니다. 다시 확인해 주시길 바랍니다.");
						continue;
					}
				}
			}
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			System.out.println("엔터 입력시 메인화면으로 이동합니다.");
			input.nextLine();
			break;
		}
	}
}
