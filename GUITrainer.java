//GUITrainer is a subclass of the Teacher abstract super-class
//GUITrainer is the class for the guitrainers of the school
public class GUITrainer extends Teacher {
	//constructor for GUITrainer that calls the super-class Teacher constructor
	public GUITrainer(String name, char gender, int age) {
		super(name, gender, age);
		//name,gender,age are the name,gender,age of the GUITrainer
	}
	
	//method that returns whether the GUITrainer can teach the specific subject or not
	//returns true if the GUITrainer can teach the subject, false if not
	public boolean canTeach(Subject subject) {	
		if(subject.specialism==1 || subject.specialism==2 || subject.specialism==4)
			return true;
		else
			return false;
	}
}
