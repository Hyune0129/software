
public class GalleryManager extends Member implements SystemMain{
	Gallery ownGallery;
	GalleryManager(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = false;
	}
	boolean hasGallery(){
		
	}
	void setOwnGallery(Gallery gallery){
		this.ownGallery = gallery;
	}
	Gallery getOwnGallery(){
		return ownGallery;
	}
	@Override
	public void printMainpage() {
		System.out.println("***���ð� ���� �ý���***");
		System.out.println("ȯ���մϴ� "+ID+"��!");
		System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
		System.out.println("=================================");
		System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���ð� ��� | 4. ���ù� �߰�");
		System.out.println("=================================");
		
	}
	@Override
	public void mainSelect(int select) {
		// TODO Auto-generated method stub
		
	}
}
