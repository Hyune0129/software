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

			System.out.println("***���ð� ���� �ý���***");
			System.out.println("ȯ���մϴ� " + ID + "��!");
			System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
			System.out.println("=================================");
			System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���� ��û ����");
			System.out.println("=================================");
			System.out.println("�Է� >>");
			select = input.nextInt();
			if(select==1)	//logout
			{
				System.out.print("������ �α׾ƿ��Ͻðڽ��ϱ�?(Y/N)");
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
		switch(select)
		{
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
	}
}
