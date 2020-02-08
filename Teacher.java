//Teacher is a sub-class of the Instructor abstract super-class
//Teacher is the class for the teachers of the school
public class Teacher extends Instructor {

	//constructor for the Teacher that calls the super-class Instructor
	public Teacher(String name, char gender, int age) {
		super(name, gender, age);
	//name,gender,age are the name,genderand age of the Teacher
	}
	//overriden method from the super-class Instructor
	//returns true if the Teacher can teach the subject and false if not
	public boolean canTeach(Subject subject) {	
		if(subject.specialism==1 || subject.specialism==2)
			return true;
		else
			return false;
		
	}
}
