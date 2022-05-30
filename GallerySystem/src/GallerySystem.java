
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
		
		Customer customer = new Customer();
		customer.printMainpage();
	}

}
