import java.util.ArrayList;

public class ExhibitHelper {
	ArrayList<Exhibit> exhibitList;
	ExhibitHelper(DBManager db){
		this.exhibitList = db.getExhibitData();
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
	void addExhibit(){
		
	}
}
