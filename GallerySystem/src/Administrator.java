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
			System.out.println("***���ð� ���� �ý���***");
			System.out.println("ȯ���մϴ� " + ID + "��!");
			System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
			System.out.println("=======================================");
			System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���� ��û ����");
			System.out.println("=======================================");
			System.out.print("�Է� >>");
			select = Integer.parseInt(input.nextLine());
			
			if(select==1)	//logout
			{
				System.out.print("������ �α׾ƿ��Ͻðڽ��ϱ�?(Y/N)");
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
		case 2:	//���ð� ����
			System.out.println("[ ���ð� ���� ]");
			System.out.print("������ ���ð��� �̸��� �ۼ����ּ���. >>");
			temp = input.nextLine();
			if(!gh.hasGalleryName(temp))
			{
				System.out.println("�ش��ϴ� ���ð��� �����ϴ�.");
				System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
				input.nextLine();
				break;
			}
			gh.manageGallery(gh.getGallery(temp));
			break;	
		case 3:	//���� ��û ����
			GalleryManager requestuser;
			System.out.println("[ ���� ��û ���� ]");
			while(true)
			{
				requestuser = rh.getRegisterRequest();
				if (requestuser == null) // ���Կ�û�� ���� ���
				{
					System.out.println("���� ��û�� �� �̻� �������� �ʽ��ϴ�.");
					System.out.println("���� �Է½� ����ȭ������ �̵��˴ϴ�.");
					input.nextLine();
					break;
				}
				while(true)
				{
					requestuser.printinfo();
					System.out.println("Ȯ���� �� '����' �Ǵ� '�ź�'�� �Է��� �ֽñ� �ٶ��ϴ�.");
					System.out.print("�Է�>>");
					temp = input.nextLine();
					DBManager db = new DBManager();
					if (temp.equals("����")) {
						rh.acceptRegister(requestuser);
						System.out.println("��û�� �����Ͽ����ϴ�.");
						db.deleteRequestData(requestuser);
						break;
					} else if (temp.equals("�ź�")) {
						System.out.println("��û�� �ź��Ͽ����ϴ�.");
						db.deleteRequestData(requestuser);
						break;
					} else {
						System.out.println("�߸��� �Է��Դϴ�. �ٽ� Ȯ���� �ֽñ� �ٶ��ϴ�.");
						continue;
					}
				}
			}
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�.");
			System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
			input.nextLine();
			break;
		}
	}
}
