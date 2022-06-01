
public class GallerySystem {

	public static void main(String[] args) {
		
		DBManager dbmanager = new DBManager();
		LoginHelper loginmanager = new LoginHelper();
		GalleryHelper galleryhelper = new GalleryHelper();
		RegisterHelper registerhelper = new RegisterHelper();
		ExhibitHelper exhibithelper = new ExhibitHelper();
		galleryhelper.init(dbmanager);
		exhibithelper.init(dbmanager);
		loginmanager.init(dbmanager);
		registerhelper.init(dbmanager);
		Customer customer = new Customer();
		customer.printMainpage();
		System.out.println("[ 종료 ]");
		System.out.println("3초 뒤 시스템을 종료합니다.");
		try {
			Thread.sleep(3000);	//3초 sleep
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
