import java.util.ArrayList;

public class Exhibit {
	String name;
	ArrayList<String> info;
	String location;
	Exhibit(String name, ArrayList<String> info, String location){
		this.name = name;
		this.info = info;
		this.location = location;
	}
	String getname(){
		return name;
	}
	ArrayList<String> getinfo(){
		return info;
	}
	String getlocation(){
		return location;
	}
	void printExhibit(){
		System.out.println("[ "+name+" ]");
		System.out.println("============================");
		for(int i=0; i<info.size(); i++)
			System.out.println(info.get(i));
		System.out.println("============================");
	}
}
