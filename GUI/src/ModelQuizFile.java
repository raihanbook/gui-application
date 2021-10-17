import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ModelQuizFile extends ModelQuiz {

	public ModelQuizFile() {
		// TODO Auto-generated constructor stub
	}

	public ModelQuizFile(File file) {
		// TODO Auto-generated constructor stub
		try {
			Scanner sn = new Scanner(file);
			while (sn.hasNext()) {
				String soal = sn.nextLine();
				addSoal(soal);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
