
public class GallerySystem {

	public static void main(String[] args) {
		
		Customer customer = new Customer();
		customer.printMainpage();
		DBManager dbmanager = new DBManager();
		LoginHelper loginmanager = new LoginHelper();
		GalleryHelper galleryhelper = new GalleryHelper();
		RegisterHelper registerhelper = new RegisterHelper();
		galleryhelper.init(dbmanager);
		loginmanager.init(dbmanager);
		
	}

}
