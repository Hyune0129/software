import java.util.ArrayList;

public class ExhibitHelper {
	private static ArrayList<Exhibit> exhibitList;
	void init (DBManager db){
		this.exhibitList.addAll(db.getExhibitData());
	}
	Exhibit getExhibit(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.name.equals(name))
				return temp;
		}
		return null;
	}
	boolean hasExhibitName(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.name.equals(name))
				return true;
		}
		return false;
	}
	void appendExhibitData(Exhibit data){
		exhibitList.add(data);
	}
	void deleteExhibitData(Exhibit data){
		exhibitList.remove(data);
	}
	ArrayList<Exhibit> getLocalExhibitList(String location){
		Exhibit temp;
		ArrayList<Exhibit> localList = new ArrayList<Exhibit>();
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.location.equals(location))
				localList.add(temp);
		}
		return localList;
	}
	void printLocalExhibitList(String location){
		ArrayList<Exhibit> localList;
		localList = getLocalExhibitList(location);
		for(int i=0; i<localList.size(); i++)
		{
			System.out.println(localList.get(i));
		}
	}
	void addExhibit(){
		
	}
}
