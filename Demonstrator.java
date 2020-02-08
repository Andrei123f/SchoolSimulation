//Demonstrator is a sub-class of the Instructor abstract super-class
//Demonstrator is the class for the demonstrators of the school
public class Demonstrator extends Instructor {
	//constructor for the Demostrator that calls the super-class Instructor constructor
	public Demonstrator(String name, char gender, int age) {
		super(name, gender, age);
		//name,gender and age are the name,gender and age of the Demostrator
	}

	//overriden method from the super-class abstract Instructor
	//returns true if the demostrator can teach the subject and false if not
	public boolean canTeach(Subject subject) {	
		if(subject.specialism==2)
			return true;
		else
			return false;
	}
}
