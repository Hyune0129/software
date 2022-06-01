import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Member implements SystemMain{
	@Override
	public void printMainpage() {
		Scanner input = new Scanner(System.in);
		int select;
		while (true) {
			System.out.println("***���ð� ���� �ý���***");
			System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
			System.out.println("===============================================");
			System.out.println("1. �α��� | 2. ȸ������ | 3. ���ð� ��� | 4. ���ù� ã�� | 5. ����");
			System.out.println("===============================================");
			System.out.print("�Է�>> ");
			select = input.nextInt();
			if(select == 5 ) return;	//���α׷� ����
			mainSelect(select);
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
				ID = input.next();
				System.out.print("Password :");
				password = input.next();
				if (lh.loginCheck(ID, password)) {
					Member user;
					user = lh.getMember(ID);
					user.printMainpage();
					break;	// �α׾ƿ�
				}
				else
				{
					System.out.println("�߸��� ���̵� ��й�ȣ �Է��Դϴ�.");
					break; // printmain
				}
			}
			break;
		case 2: // register
			String[] list = new String[4]; // ID, Password, phoneNumber, Email
			
			while(true)
			{
				System.out.println("[ ȸ������ ]");
				System.out.println("��Ŀ� ���� ������ ������ �ֽñ� �ٶ��ϴ�.");
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
				if (rh.checkString(list[2], list[3])) { //phonenumber, email�� ��Ŀ� ���� ����
					System.out.println("��Ŀ� ���� �ʴ� �Է��Դϴ�.");
					continue;	//�ٽ� ����� �Է¹޴´�.
				}
				else if(lh.hasID(list[0]))	// �ߺ� ID
				{
					System.out.println("�̹� ��ϵ� �����Դϴ�.");
					continue;	//�ٽ� ����� �Է¹޴´�.
				}
				else
				{
					rh.requestRegister(list[0],list[1],list[2],list[3]);
					System.out.println("�Է� �Ϸ�Ǿ����ϴ�. �������� ���� �� �̿��� �����մϴ�.");
					System.out.println("�Է��� ������ ���ư��ϴ�.");
					input.next();
					break;
				}
			}
			break;
		case 3: // inquire galleryList
			
			while (true) { // galleryList ȭ�� ����
				gh.printGalleryList();
				System.out.print("���ϴ� �Է�>>");
				int num = input.nextInt();
				if (num == 0) // ���ư��� -> mainselect
					break;
				Gallery gallery = gh.getGallery(num - 1);
				if (gallery == null) {
					System.out.println("�������� ���� �Է�");
					continue;
				}
				while (true) {	//get gallery data
					gallery.printGallery();
					System.out.println("1. ���ư��� | 2. ���ù� ���");
					System.out.print("�Է�>>");
					num = input.nextInt();
					if (num == 1) // ���ư���->galleryList
						break;
					else if(num == 2)
					{	//exhibit List
						while(true){
							System.out.println("[ ���ù� ��� ]");
							System.out.println("������ Ȯ���� ���ð��� �������ּ���.");
							System.out.println("======================================");
							System.out.println("[0] ���ư���");
							eh.printLocalExhibitList(gallery.getname());
							System.out.println("======================================");
							System.out.print("�Է�>>");
							num = input.nextInt();
							if(num == 0) break;
							ArrayList<Exhibit> localexhibitList = eh.getLocalExhibitList(gallery.getname());
							if(num-1 > localexhibitList.size() || num < 0)
							{
								System.out.println("��ϵ��� ���� �������Դϴ�. �ٽ� �������ֽʽÿ�.");
								continue;
							}
							else
							{	//���ù� ������ ���� ������ Ȯ��
								Exhibit exhibit = localexhibitList.get(num);
								exhibit.printExhibit();
								System.out.println("�Է��� ������ ���ù� ������� ���ư��ϴ�.");
								input.nextLine();
								continue;
							}
						}
					}
					else
					{
						System.out.println("�������� ���� �Է�");
						continue;
					}
					
				}
			}
			break;
		case 4: // find exhibit
			String name;
			Exhibit exhibit;
			System.out.println("[ ���ù� ã�� ]");
			System.out.print("ã�� ���� ���ù��� �̸��� �Է��ϼ���>>");
			name = input.next();
			exhibit = eh.getExhibit(name);
			if (exhibit == null) {
				System.out.println("�ش��ϴ� �̸��� ���ù��� �����ϴ�.");
				return;
			} else {
				System.out.println("[���ð� :"+exhibit.getlocation()+" ]");
				exhibit.printExhibit();
				try {
					Thread.sleep(2000); //2�� ���
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("�Է��� ������ ����ȭ������ ���ư��ϴ�.");
				input.nextLine();
			}
			break;
		default:
			System.out.println("�߸��� �Է�");
		}
	}

}
