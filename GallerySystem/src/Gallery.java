import java.util.ArrayList;

public class Gallery {
	String name;
	ArrayList<String> info;
	Gallery(String name, ArrayList<String> info){
		this.name = name;
		this.info = info;
	}
	String getname(){
		return name;
	}
	ArrayList<String> getinfo(){
		return info;
	}
	void printGallery(){
		System.out.println("[ "+name+" ]");
		System.out.println("===========================");
		for(int i=0; i<info.size();i++){
			System.out.println(info.get(i));
		}
		System.out.println("===========================");
	}
}
