import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.InterruptedIOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] arg) {

		School school=new School("Nr1");
		Administrator administrator=new Administrator(school);
		File file=new File("java Administrator mySchool.txt");
		administrator.openFile(file);
		administrator.readFile(file);
		administrator.closeFile(file);

		administrator.run(10);
	}
}
