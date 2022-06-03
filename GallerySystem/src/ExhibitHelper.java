import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ExhibitHelper {
	private static ArrayList<Exhibit> exhibitList;
	void init (DBManager db){
		ArrayList<Exhibit> temp = db.getExhibitData();
		if(temp ==null)
			temp = new ArrayList<Exhibit>();
		this.exhibitList = temp;
	}
	Exhibit getExhibit(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.getname().equals(name))
				return temp;
		}
		return null;
	}
	boolean hasExhibitName(String name){
		Exhibit temp;
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.getname().equals(name))
				return true;
		}
		return false;
	}
	void appendExhibitData(Exhibit data){
		exhibitList.add(data);
		DBManager db = new DBManager();
		db.writeExhibitData(data);
	}
	void deleteExhibitData(Exhibit data){
		exhibitList.remove(data);
		DBManager db = new DBManager();
		db.deleteExhibitData(data);
	}
	ArrayList<Exhibit> getLocalExhibitList(String location){
		Exhibit temp;
		ArrayList<Exhibit> localList = new ArrayList<Exhibit>();
		for(int i=0; i<exhibitList.size(); i++)
		{
			temp = exhibitList.get(i);
			if(temp.getlocation().equals(location))
				localList.add(temp);
		}
		return localList;
	}
	void printLocalExhibitList(String location){
		ArrayList<Exhibit> localList;
		localList = getLocalExhibitList(location);
		for(int i=0; i<localList.size(); i++)
		{
			System.out.println("["+ (i+1) + "] "+localList.get(i).getname());
		}
		
	}
	void addExhibit(Gallery ownGallery){
		String name,temp=null;
		ArrayList<String> info = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		do
		{
			System.out.println("[ 전시물 추가 ]");
			System.out.println("양식에 맞게 작성하여 제출해 주시길 바랍니다.");
			System.out.println("'입력종료'를 작성하면 작성이 완료됩니다.");
			System.out.println("===============================");
			System.out.print("전시물 이름 : ");
			name = input.nextLine();
			if (hasExhibitName(name)) {
				System.out.println("이미 해당 전시관에서 등록된 전시물입니다.");
				continue;
			}
			System.out.print("전시물 소개 : ");
			while(true)
			{
				temp = input.nextLine();
				if(temp.equals("입력종료"))
					break;
				info.add(temp);
			}
			System.out.println("===============================");
			System.out.println("===============================");
			System.out.println("전시물 이름 : "+name);
			System.out.print("전시물 소개 : ");
			for(int i=0; i<info.size(); i++)
				System.out.println(info.get(i));
			System.out.println("===============================");
			System.out.print("해당 정보로 등록하시겠습니까? (예/아니오)>> ");
			if(input.nextLine().equals("아니오"))
			{
				name = "";
				info.clear();
				continue;
			}
			else
				break;
		}while(true);
		Exhibit exhibit = new Exhibit(name, info, ownGallery.getname());
		appendExhibitData(exhibit);
	}
	void manageExhibit(Gallery gallery){
		Scanner input = new Scanner(System.in);
		int num;
		String temp;
		while(true)
		{
			System.out.println("[ 전시관 관리 ]");
			System.out.println("관리할 전시물을 선택하세요.");
			System.out.println("=====================================");
			ArrayList<Exhibit> exhibitList = getLocalExhibitList(gallery.getname());
			System.out.println("[0] 돌아가기");
			for (int i = 0; i < exhibitList.size(); i++) {
				System.out.println("[" + (i + 1) + "]" + exhibitList.get(i).getname());
			}
			System.out.println("=====================================");
			System.out.print("입력>>");
			num = Integer.parseInt(input.nextLine());
			if(num == 0 ){
				return;
			}
			else if(num-1>exhibitList.size()||num<0)
			{
				System.out.println("유효하지 않은 값입니다. 다시 입력해 주시길 바랍니다.");
				continue;
			}
			Exhibit exhibit = exhibitList.get(num-1);
			while(true){
				System.out.println("[" + exhibit.getname() + "]을 어떻게 하시겠습니까?");
				System.out.println("1. 전시물 수정 | 2. 전시물 삭제");
				num = Integer.parseInt(input.nextLine());
				switch(num)
				{
				case 1:
					ArrayList<String> info = exhibit.getinfo();
					while(true){
						System.out.println("=========================================");
						for (int i = 0; i < info.size(); i++) {
							System.out.println("[" + (i + 1) + "]" + info.get(i));
						}
						System.out.println("=========================================");
						System.out.print("수정할 시작점을 선택해주세요. (취소 : 0)>>");
						num = Integer.parseInt(input.nextLine());
						if(num<0 || num-1 > info.size()){
							System.out.println("유효하지 않은 값입니다. 다시 입력해 주십시오.");
							continue;
						}
						else if(num==0){//취소
							return;
						}
						System.out.println("=========================================");
						for(int i=0; i< num-1; i++)
						{
							System.out.print("["+(i+1)+"] ");
							System.out.println(info.get(i));
						}
						for (int i = info.size()-1; i >= num; i--)
							info.remove(i);
						while(true){
							System.out.print("["+ num++ +"] ");
							temp = input.nextLine();
							if(temp.equals("입력종료"))
							{
								String name = exhibit.getname();
								String location = exhibit.getlocation();
								deleteExhibitData(exhibit);
								appendExhibitData(new Exhibit(name, info, location));
								System.out.println("수정이 완료되었습니다!");
								System.out.println("엔터 입력시 메인화면으로 이동합니다.");
								input.nextLine();
								return;
							}
							info.add(temp);
						}
					}
				case 2:
					System.out.println("정말로 ["+exhibit.getname()+"]을 삭제하시겠습니까?");
					System.out.println("확인을 위해 삭제할 전시물의 이름을 작성하여 주시길 바랍니다.");
					temp = input.nextLine();
					if(temp.equals(exhibit.getname()))
					{
						deleteExhibitData(exhibit);
						System.out.println("삭제가 완료되었습니다.");
						System.out.println("엔터 입력시 메인화면으로 이동합니다.");
						input.nextLine();
					}
					else{
						System.out.println("삭제가 취소되었습니다.");
						System.out.println("엔터 입력시 메인화면으로 이동합니다.");
						input.nextLine();
						return;
					}
				default:
					System.out.println("유효하지 않은 값입니다.");
					continue;
				}
			}
		}
	}
}
