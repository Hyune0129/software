
public interface SystemMain {
	void printMainpage();
	void mainSelect(int select);
	static void clear(){
		for(int i=0; i<30; i++)
			System.out.println();
	}
}
