import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			String str = br.readLine();
			
			if(str.equals("0")) break;
			
			int len = str.length() / 2;
			
			boolean isFlag = true;
			
			for(int i = 0; i < len; i++) {
				if(str.charAt(i) != str.charAt(str.length()-1-i)) {
					isFlag = false;
				}
			}
			
			if(!isFlag) {
				System.out.println("no");
			}else {
				System.out.println("yes");
			}
		}
		
	}
}