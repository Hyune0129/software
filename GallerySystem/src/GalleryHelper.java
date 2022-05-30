import java.util.ArrayList;

public class GalleryHelper {
	static ArrayList<Gallery> galleryList;
	void init(DBManager db){
		this.galleryList.addAll(db.getGalleryData());
	}
	void addGallery(){
		
	}
	Gallery getGallery(String name){
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			if(name == temp.name)
				return temp;	
		}
		return null;
	}
	Gallery getGallery(int num)
	{	
		if(num < -1 || num>galleryList.size())
			return null;
		return galleryList.get(num);
	}
	boolean hasGalleryName(String name){
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			if(name == temp.name)
				return true;	
		}
		return false;
	}
	void appendGalleryList(Gallery data){
		galleryList.add(data);
	}
	void deleteGalleryList(Gallery data){	//db 수정
		Gallery temp;
		for(int i=0; i<galleryList.size(); i++)
		{
			temp = galleryList.get(i);
			if(temp.equals(data))
			{
				galleryList.remove(i);
				return;
			}
		}
	}
	void printGalleryList(){
		Gallery temp;
		System.out.println("[0] 돌아가기");
		for(int i=0; i<galleryList.size(); i++){
			temp = galleryList.get(i);
			System.out.println("["+(i+1)+"] "+temp.name);
		}
	}
}
