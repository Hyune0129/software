import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DBManager {
	static File exhibit = new File("exhibitData.txt");
	static File gallery = new File("galleryData.txt");
	static File memberInfo = new File("memberData.txt");
	static File request = new File("requestData.txt");
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
		//name;info1|info2|info3|...|; 순
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
					GalleryManager member = new GalleryManager(info[0], info[1], info[2], info[3]);
					if(!info[4].equals(" "))	//등록된 전시관 존재
					{
						GalleryHelper gh = new GalleryHelper();
						Gallery ownGallery = gh.getGallery(info[4]);
						if(ownGallery != null);
							member.setOwnGallery(ownGallery);
					}
					memberList.add(member);
				}
			}
			fileinput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return memberList;
		
	}
	ArrayList<GalleryManager> getRequsetData()
	{//ID;password;phonenumber;email;
		String temp;
		String[] info = new String[4];
		StringTokenizer st;
		ArrayList<GalleryManager> requestList = new ArrayList<GalleryManager>();
		try {
			Scanner fileinput = new Scanner(request,"UTF-8");
			fileinput.nextLine();
			while(fileinput.hasNextLine())
			{
				temp = fileinput.nextLine();
				st = new StringTokenizer(temp,";");
				for(int i =0; i<4; i++)
				{
					info[i] = st.nextToken();
				}
				requestList.add(new GalleryManager(info[0], info[1], info[2], info[3]));
			}
			fileinput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return requestList;
		
	}
	void writeExhibitData(Exhibit data){
		//name;location;info1|info2|info3|...|;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exhibit,true),"UTF8"));
			bw.write(data.getname());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void writeGalleryData(Gallery data){
		//name;info1|info2|info3|...;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(gallery,true),"UTF8"));
			bw.write(data.getname()+";");
			ArrayList<String> info = data.getinfo();
			for(int i=0; i<info.size(); i++)
			{
				bw.write(info.get(i)+"|");
			}
			bw.write(";");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void writeMemberData(GalleryManager data){
		//ID;password;phonenumber;email;ownGallery; 
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(memberInfo,true),"UTF8"));
			bw.write("\r\n");
			bw.write(data.getID()+";");
			bw.write(data.getPassword()+";");
			bw.write(data.getPhoneNumber()+";");
			bw.write(data.getEmail()+";");
			Gallery ownGallery = data.getOwnGallery();
			if(ownGallery==null)
				bw.write(" "+";");
			else
				bw.write(ownGallery.getname());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void writeRequestData(GalleryManager data)
	{
		// ID;password;phonenumber;email;
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(request, true), "UTF8"));
			bw.write("\r\n");
			bw.write(data.getID()+";");
			bw.write(data.getPassword()+";");
			bw.write(data.getPhoneNumber()+";");
			bw.write(data.getEmail()+";");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void deleteExhibitData(Exhibit data) {
		//name;location;info1|info2|info3|...|;
		ArrayList<String> info;
		String infodata="";
		info = data.getinfo();
		for(int i=0; i<info.size(); i++)
		{
			infodata += info.get(i)+"|";
		}
		String delline = data.getname() + ";" + data.getlocation() + ";" + infodata + ";";
		String dummy = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(exhibit), "UTF-8"));
			String line;
			dummy += br.readLine();
			while ((line = br.readLine()) != null) {
				if (line.equals(delline))
					break;
				dummy += ("\r\n"+ line );
			}
			while ((line = br.readLine()) != null) { //삭제 이후
				dummy += ("\r\n"+line);
			}
			FileWriter fw = new FileWriter(exhibit);	//덮어쓰기
			fw.write(dummy);
			fw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deleteGalleryData(Gallery data) {
		//name;info1|info2|info3|...;
		ArrayList<String> info;
		String infodata="";
		info = data.getinfo();
		for(int i=0; i<info.size(); i++)
		{
			infodata += info.get(i)+"|";
		}
		String delline = data.getname() + ";" + infodata + ";";
		String dummy = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gallery), "UTF-8"));
			String line;
			dummy += br.readLine();
			while ((line = br.readLine()) != null) {
				if (line.equals(delline))
					break;
				dummy += ("\r\n"+line);
			}
			while ((line = br.readLine()) != null) {	//삭제 데이터 이후 저장
				dummy += ("\r\n"+line);
			}
			FileWriter fw = new FileWriter(gallery);	//덮어쓰기
			fw.write(dummy);
			// bw.close();
			fw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deleteMemberData(GalleryManager data) {
		//ID;password;phonenumber;email;ownGallery; 
		Gallery ownGallery = data.getOwnGallery();			
		String delline = data.getID() + ";" + data.getPassword() + ";" + data.getPhoneNumber() + ";"+data.getEmail()+";";
		if(data.hasGallery())
			delline += ownGallery.getname()+";";
		else
			delline += " "+";";
		String dummy = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(memberInfo), "UTF-8"));
			String line;
			dummy +=br.readLine();
			while ((line = br.readLine()) != null) {
				if (line.equals(delline))
					break;
				dummy += ("\r\n"+line);
			}
			while ((line = br.readLine()) != null) {	//제거하려는 라인 건너뛰고 읽기
				dummy += ("\r\n"+line);
			}
			FileWriter fw = new FileWriter(memberInfo); //덮어쓰기
			fw.write(dummy);
			fw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deleteRequestData(GalleryManager data) {
		// ID;password;phonenumber;email;		
		String delline = data.getID() + ";" + data.getPassword() + ";" + data.getPhoneNumber() + ";" + data.getEmail()
				+ ";";
		
	
		String dummy = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(request), "UTF-8"));
			String line;
			dummy +=br.readLine();
			while ((line = br.readLine()) != null) {
				if (line.equals(delline))
					break;
				dummy += ("\r\n"+line);
			}
			while ((line = br.readLine()) != null) { // 제거하려는 라인 건너뛰고 읽기
				dummy += ("\r\n"+line);
			}
			FileWriter fw = new FileWriter(request); // 덮어쓰기
			fw.write(dummy);
			fw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
