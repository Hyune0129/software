import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public static class DBManager {
	static File exhibit = new File("exhibitData.txt");
	static File gallery = new File("galleryData.txt");
	static File memberInfo = new File("memberData.txt");
	
	ArrayList<Exhibit> getExhibitData(){
		//name;location;info1|info2|info3|...;
		String name;
		String location;
		String temp;
		StringTokenizer st,st2;
		ArrayList<String> info;
		ArrayList<Exhibit> exhibitList = new ArrayList<Exhibit>();
		try {
			
			Scanner fileinput = new Scanner(exhibit,"UTF-8");
			
			fileinput.nextLine();
			while(fileinput.hasNextLine())
			{
				
				st = new StringTokenizer(fileinput.nextLine(),";");
				while(st.hasMoreTokens()){
					name = st.nextToken();
					location = st.nextToken();
					temp = st.nextToken();
					st2 = new StringTokenizer(temp,"|");
					info = new ArrayList<String>();
					while(st2.hasMoreTokens()){
						info.add(st2.nextToken());
					}
					exhibitList.add(new Exhibit(name,info,location));
				}
				
			}
			fileinput.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(exhibitList == null)
		{
			System.out.println("exhibitList error");
			return null;
		}
		return exhibitList;
	}
	
	
	ArrayList<Gallery> getGalleryData() {
		//name;info1|info2|info3|...; ¼ø
		String temp, name;
		ArrayList<String> info;
		ArrayList<Gallery> galleryList = new ArrayList<Gallery>();
		try {
			StringTokenizer st,st2;
			Scanner fileinput = new Scanner(gallery, "UTF-8");

			fileinput.nextLine();
			while (fileinput.hasNextLine()) {

				st = new StringTokenizer(fileinput.nextLine(), ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken();
					temp = st.nextToken();
					st2 = new StringTokenizer(temp,"|");
					info = new ArrayList<String>();
					while (st2.hasMoreTokens()) {
						info.add(st2.nextToken());
					}
					galleryList.add(new Gallery(name, info));
				}

			}
			fileinput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (galleryList == null) {
			System.out.println("exhibitList error");
			return null;
		}
		return galleryList;
	}
	ArrayList<Member> getMemberInfoData(){
		//ID;password;phonenumber;email;admin; 
		String temp;
		
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			Scanner fileinput = new Scanner(memberInfo,"UTF-8");
			fileinput.nextLine();
			fileinput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}
	void writeExhibitData(Exhibit data){
		//name;location;info1|info2|info3|...;
		try {
			FileWriter fw = new FileWriter(exhibit,true);
			fw.write(data.name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void writeGalleryData(Gallery data){
		//name;info1|info2|info3|...;
		try {
			FileWriter fw = new FileWriter(gallery,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void writeMemberData(Member data){
		//ID;password;phonenumber;email;admin; 
		try {
			FileWriter fw = new FileWriter(memberInfo,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void deleteExhibitData(String name){
		
	}
	void deleteGalleryData(String name){
		
	}
	void deleteMemberData(String name){
		
	}
}
