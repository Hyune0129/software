import java.util.ArrayList;

public class ExhibitHelper {
	private static ArrayList<Exhibit> exhibitList;
	void init (DBManager db){
		this.exhibitList.addAll(db.getExhibitData());
	}
	Exhibit getExhibit(String name){
		
	}
	boolean hasExhibitName(String name){
		
	}
	void appendExhibitData(Exhibit data){
		exhibitList.add(data);
	}
	void deleteExhibitData(Exhibit data){
		exhibitList.remove(data);
	}
	ArrayList<Exhibit> getLocalExhibitList(String location){
		
	}
	void printLocalExhibitList(String location){
		
	}
	public void addExhibit(){
		
	}
}
