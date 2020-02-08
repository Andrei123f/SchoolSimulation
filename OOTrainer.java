
public class OOTrainer extends Teacher {
	//constructor for the OOTrainer that calls the super-class Teacher constructor
	public OOTrainer(String name, char gender, int age) {
		super(name, gender, age);
		//name,gender,age are the OOTrainer name,gender and age
	}
	
	//method that returns true if the OOTrainer can teach the subject and false if not
	public boolean canTeach(Subject subject) {	
		if(subject.specialism==1 || subject.specialism==2 || subject.specialism==3)
			return true;
		else
			return false;
	}
}
