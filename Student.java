//The Student class is a sub-class of the Person super-class
//The Student class is a class for the students of the school
import java.util.*;//importing java.util.* for using ArrayList

public class Student extends Person {
	
	ArrayList<Integer> certificates; //the list of certificates of a student
	Subject subject=null; //the assigned subject of the course that the student is enrolled in
	HashMap<String, String> prerequisites; //the prerequisites of the student
	
	//constructor for the student that calls the super-class Person constructor
	public Student(String name, char gender, int age) {
		super(name, gender, age); //name, gender and age are the name, gender and age of the student
		certificates=new ArrayList<Integer>(); //creating the certificates ArrayList 	
		prerequisites=new HashMap<String,String>(); //creating the prerequisites HashMap
		prerequisites.put("Mathematics", "A"); //each student starts with 'A' in mathematics
		prerequisites.put("Programming", "");	//each student has nothing in the programming field

	}

	//method for graduating
	//if a student graduates, we need to add to his certificates ArrayList the subject unique id
	public void graduate(Subject subject) {
		certificates.add(subject.id);
	}
	
	//getter method that returns the certificates of the Student as a ArrayList
	public ArrayList<Integer> getCertificates(){
		return certificates;
	}
	
	//Checking wheter the student has the specific certificate subject or not
	//if it does, the method returns true, and if does not, it returns false
	public boolean hasCertificate(Subject subject) {
		return certificates.contains(subject.id); //true if it contains, false if it does not
	}
	
	//getter method to return the course subject that the student is enrolled in
	public Subject getSubject() {
		return subject;
	}
	
	//assigning the courses subject  to the student
	public void enrollSubject(Subject subject) {
		this.subject=subject;
	}
	
	//updating the prerequisites of the student
	public void updatePrerequisites(String field,String skill) {
		prerequisites.replace(field, skill);
		}
	
	//getter method to return the prerequisites of the student
	public HashMap<String,String> getPrerequisites(){
		return prerequisites;
	}	
}
