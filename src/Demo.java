import java.io.BufferedReader;
import java.io.FileReader;

public class Demo {
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("Lab2Data.txt"));
			String str = in.readLine();
			System.out.println(str);

			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
}
