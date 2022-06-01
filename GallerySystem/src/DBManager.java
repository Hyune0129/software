import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DBManager {
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
		return exhibitList;
	}
	
	
	ArrayList<Gallery> getGalleryData() {
		//name;info1|info2|info3|...; 순
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
		return galleryList;
	}
	ArrayList<Member> getMemberInfoData(){
		//ID;password;phonenumber;email;admin or owngallery; 
		String temp;
		String[] info = new String[5];
		StringTokenizer st;
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			Scanner fileinput = new Scanner(memberInfo,"UTF-8");
			fileinput.nextLine();
			while(fileinput.hasNextLine())
			{
				temp = fileinput.nextLine();
				st = new StringTokenizer(temp,";");
				for(int i =0; i<5; i++)
				{
					info[i] = st.nextToken();
				}
				if(info[4].equals("admin"))
				{//admin일때
					memberList.add(new Administrator(info[0], info[1], info[2], info[3]));
				}
				else
				{//GalleryManager일때
					memberList.add(new GalleryManager(info[0], info[1], info[2], info[3]));
				}
			}
			fileinput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return memberList;
		
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
	void deleteExhibitData(Exhibit data){
		
	}
	void deleteGalleryData(Gallery data){
		
	}
	void deleteMemberData(Member data){
		
	}
}
