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
		Scanner input = new Scanner(System.in);
		while(true)
		{

			System.out.println("***전시관 관리 시스템***");
			System.out.println("환영합니다 " + ID + "님!");
			System.out.println("원하시는 동작을 번호를 입력해 주시길 바랍니다!");
			System.out.println("=================================");
			System.out.println("1. 로그아웃 | 2. 전시관 관리 | 3. 가입 요청 관리");
			System.out.println("=================================");
			System.out.println("입력 >>");
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
			
			Gallery newgallery = gh.manageGallery(gh.getGallery(temp));
			if(newgallery == null)	//취소 시 null 반환 
				return;
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
					System.out.println("메인화면으로 이동됩니다.");
					return;
				}
				while(true)
				{
					requestuser.printinfo();
					System.out.println("확인한 뒤 '승인' 또는 '거부'를 입력해 주시길 바랍니다.");
					System.out.print("입력>>");
					temp = input.next();
					if (temp.equals("승인")) {
						rh.acceptRegister(requestuser);
						break;
					} else if (temp.equals("거부")) {
						System.out.println("요청을 거부하였습니다.");
						break;
					} else {
						System.out.println("잘못된 입력입니다. 다시 확인해 주시길 바랍니다.");
						continue;
					}
				}
			}
		default:
			break;
		}
	}
}
