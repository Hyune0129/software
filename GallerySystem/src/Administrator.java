
public class Administrator extends Member implements SystemMain{
	Administrator(String ID,String password,String phoneNumber,String email){
		this.ID = ID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		adminator = true;
	}

	@Override
	public void printMainpage() {
		System.out.println("***���ð� ���� �ý���***");
		System.out.println("ȯ���մϴ� "+ID+"��!");
		System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
		System.out.println("=================================");
		System.out.println("1. �α׾ƿ� | 2. ���ð� ���� | 3. ���� ��û ����");
		System.out.println("=================================");
	}

	@Override
	public void mainSelect(int select) {
		
	}
}
