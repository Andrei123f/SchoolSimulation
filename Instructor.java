//Instructor is the abstract super-class for Teacher,Demonstrator,OOTrainer,GUITrainer
//Instructor is an abstract class that is a sub-class of the Person class
public abstract class Instructor extends Person {
	public Course assignedCourse;//the Course assigned to the instructor
	
	//constructor for the Instructor which calls the super-class Person constructor
	public Instructor(String name, char gender, int age) {
		super(name, gender, age); //name,gender,age are the name, gender and age of the instructor
	}
	
	//method for assigning the course to the instructor
	public void assignCourse(Course course) {
		this.assignedCourse=course;
	}
	
	//method for unassigning the course to the instructor
	public void unassignCourse() {
		this.assignedCourse=null;
	}
	
	//getter method that returns the assigned course of the instructor
	public Course getAssignedCourse() {
		return this.assignedCourse;
	}
	/*
	 * abstract method that needs to be overridden by the subclasses
	 * returns true if the instructor can teach the subject and false if not
	 * the instructor can teach the subject based on his type:
	 * ->Teachers can teach subjects with the id specialism of 1 or 2
	 * ->Demonstrators can teach subjects only with the id of 2
	 * ->GUITrainers can teach subjects with the id of 1, 2, 4
	 * ->OOTrainers can teach subjects with the id of 1, 2, 3
	 */
	public abstract boolean canTeach(Subject subject);
}
