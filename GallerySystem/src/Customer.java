
public class Customer extends Member implements SystemMain{
	LoginHelper loginhelper;
	GalleryHelper galleryhelper;
	ExhibitHelper exhibithelper;
	RegisterHelper registerHelper;
	Customer(){
		
	}
	@Override
	public void printMainpage() {
		System.out.println("***���ð� ���� �ý���***");
		System.out.println("���Ͻô� ������ ��ȣ�� �Է��� �ֽñ� �ٶ��ϴ�!");
		System.out.println("===============================================");
		System.out.println("1. �α��� | 2. ȸ������ | 3. ���ð� ��� | 4. ���ù� ã��");
		System.out.println("===============================================");
		System.out.print("�Է�>> ");
	}

	@Override
	public void mainSelect(int select) {
		switch(select)
		{
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			
		}
		
	}
	
}
