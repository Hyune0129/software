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
			System.out.println("1. �α��� | 2. ȸ������ | 3. ���ð� ��� | 4. ���ù� ã��");
			System.out.println("===============================================");
			System.out.print("�Է�>> ");
			select = input.nextInt();
			mainSelect(select);
		}
	}

	@Override
	public void mainSelect(int select) {
		Scanner input = new Scanner(System.in);
		switch (select) {
		case 1: // login
			LoginHelper lh = new LoginHelper();
			String ID, password;

			System.out.print("ID :");
			ID = input.next();
			System.out.print("Password :");
			password = input.next();
			if (lh.loginCheck(ID, password)) {
				Member user;
				user = lh.getMember(ID);
				user.printMainpage();
			}
			break;
		case 2: // register
			RegisterHelper rh = new RegisterHelper();
			rh.addRequestList();
			break;
		case 3: // inquire galleryList
			GalleryHelper gh = new GalleryHelper();
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
				while (true) {
					gallery.printGallery();
					num = input.nextInt();
					if (num == 0) // ���ư���->galleryList
						break;
					else if(num == 1)
					{	//exhibit List
						ExhibitHelper eh = new ExhibitHelper();
						eh.printLocalExhibitList(gallery.getname());
						System.out.print("���ϴ� �Է�>>");
						num = input.nextInt();
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
			ExhibitHelper eh = new ExhibitHelper();
			System.out.print("���ù� �̸�?");
			name = input.next();
			exhibit = eh.getExhibit(name);
			if (exhibit == null) {
				System.out.println("�ش��ϴ� ���ù� ����");
			} else {
				System.out.println("���ù� ���");
			}
			break;
		default:
			System.out.println("�߸��� �Է�");
		}
	}

}
