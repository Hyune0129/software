import java.util.ArrayList;
import java.util.Scanner;

public class GalleryManager extends Member{
	Gallery ownGallery;
	GalleryManager(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = false;
	}
	public GalleryManager(String ID,String password,String phoneNumber,String email,Gallery ownGallery) {
		this(ID,password,phoneNumber,email);
		this.ownGallery = ownGallery;
	}
	boolean hasGallery(){
		GalleryHelper gh = new GalleryHelper();
		if(ownGallery==null)
			return false;
		if(gh.hasGalleryName(ownGallery.getname()))
			return true;
		else
			return false;
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
	}
	void printinfo(){	//register accept���� ������ print�ϱ� ���� �޼ҵ�
		System.out.println("====================================");
		System.out.println("ID : "+this.ID);
		System.out.println("Password : "+this.password);
		System.out.println("Phone Number : "+this.phoneNumber);
		System.out.println("Email : "+this.email);
		System.out.println("====================================");
	}
	@Override
	public void printMainpage() {
		int select;
		while(true)
		{
			Scanner input = new Scanner(System.in);
			SystemMain.clear();
			System.out.println("***���ð� ���� �ý���***");
			System.out.println("ȯ���մϴ� " + ID + "��!");
			System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
			System.out.println("=================================");
			System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���ð� ��� | 4. ���ù� �߰�");
			System.out.println("=================================");
			System.out.print("�Է�>>");
			select = Integer.parseInt(input.nextLine());
			if (select == 1) // logout
			{
				System.out.print("������ �α׾ƿ��Ͻðڽ��ϱ�?(Y/N)");
				String temp = input.next();
				if (temp.equals("Y") || temp.equals("y")) {
					return;
				}
			}
			mainSelect(select);
		}
	}
	@Override
	public void mainSelect(int select) {
		GalleryHelper gh = new GalleryHelper();
		ExhibitHelper eh = new ExhibitHelper();
		DBManager db = new DBManager();
		Scanner input = new Scanner(System.in);
		String temp;
		switch(select)
		{
		case 2:	// ���ð� ����
			if(!hasGallery())
			{
				System.out.println("������ �ִ� ���ð��� �����ϴ�.");
				System.out.println("���� �Է½� ���� ȭ������ �̵��մϴ�.");
				input.nextLine();
				return;
			}
			System.out.println("[ ���ð� ���� ]");
			System.out.print("������ ���ð��� �̸��� �ۼ����ּ���. >>");
			temp = input.nextLine();
			if(!temp.equals(ownGallery.getname()))
			{
				System.out.println("�̸��� Ʋ���ų�, ������ ����� ���ð��� �ƴմϴ�.");
				System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
				input.nextLine();
				return;
			}
			
			
			Gallery newgallery = gh.manageGallery(ownGallery);
			if(newgallery == null)	//���� ��� �� null ��ȯ 
				return;
			
			setOwnGallery(newgallery);
			break;
			
		case 3:	//add gallery
			if(hasGallery())
			{
				System.out.println("��ϵ� ���ð��� �̹� �ֽ��ϴ�.");
				System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
				input.nextLine();
				return;
			}
			Gallery mygallery = gh.addGallery();
			db.deleteMemberData(new GalleryManager(getID(), getPassword(), getPhoneNumber(), getEmail()));
			db.writeMemberData(new GalleryManager(getID(), getPassword(), getPhoneNumber(), getEmail(),mygallery));
			setOwnGallery(mygallery);
			
			
			System.out.println("����� �Ϸ�Ǿ����ϴ�!");
			System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
			input.nextLine();
			break;
		case 4:	//add exhibit
			if(!hasGallery())
			{
				System.out.println("���ð� ��� �� ������ �۾��Դϴ�.");
				System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
				input.nextLine();
				return;
			}
			
			eh.addExhibit(ownGallery);
			System.out.println("����� �Ϸ�Ǿ����ϴ�!");
			System.out.println("���� �Է½� ����ȭ������ �̵��մϴ�.");
			input.nextLine();
			break;
		default:
				
		}
	}
}
