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
	boolean hasGallery(){
		if(ownGallery == null)
			return false;
		else
			return true;
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
	}
	@Override
	public void printMainpage() {
		int select;
		Scanner input = new Scanner(System.in);
		System.out.println("***���ð� ���� �ý���***");
		System.out.println("ȯ���մϴ� "+ID+"��!");
		System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
		System.out.println("=================================");
		System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���ð� ��� | 4. ���ù� �߰�");
		System.out.println("=================================");
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
	@Override
	public void mainSelect(int select) {
		switch(select)
		{
		case 2:	// ���ð� ����
			if(hasGallery()) {
				GalleryHelper manageGalleryHelper = new GalleryHelper();
				
				
				
				
				
			}
			break;
		case 3:	//add gallery
			if(hasGallery())
			{
				System.out.println("��ϵ� ���ð��� �̹� �ֽ��ϴ�.");
				return;
			}
			GalleryHelper addGalleryHelper = new GalleryHelper();
			Gallery mygallery = addGalleryHelper.addGallery();
			setOwnGallery(mygallery);
			
			System.out.println("����� �Ϸ�Ǿ����ϴ�!");
			break;
		case 4:	//add exhibit
			if(!hasGallery())
			{
				System.out.println("���ð� ��� �� ������ �۾��Դϴ�.");
				return;
			}
			ExhibitHelper addExhibitHelper = new ExhibitHelper();
			addExhibitHelper.addExhibit(ownGallery);
			System.out.println("����� �Ϸ�Ǿ����ϴ�!");
			break;
		default:
				
		}
	}
}
