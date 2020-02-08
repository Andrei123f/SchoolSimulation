/*Course class is the class for every course of the school
 * Each course should have a subject, an instructor based on the assigned subject, students(max 3 students for each course).
 * Each course has a number of days to cover the subject(daysToRun) and a number of days until it starts(daysUntilStarts)
 */
import java.util.*; //importing java.util.* for using ArrayList
public class Course{
  public Subject subject;//the subject associated with the Course
  public int daysUntilStarts;//the number of days until the Course starts
  public int daysToRun;//the number of days that the Course still has to run(related to the subject)
  public ArrayList<Student> students;//the collection of students
  public Instructor instructor; //the instructor for the course
  public boolean isClosed; //the variable that tells whether the Course is closed or not
  						   //the default value for boolean is false
  
  //constructor for the Course
  public Course(Subject subject, int daysUntilStarts){
    this.daysToRun=subject.getDuration();//initially, the subject needs the full days to be covered
    this.subject=subject;
    this.daysUntilStarts=daysUntilStarts;
    students=new ArrayList<Student>();
    //subject,daysUntilStarts are the Course assigned subject and the days until the course starts
  }
  
//getter method that returns the subject assigned to the Course
  public Subject getSubject(){
    return subject;
  }
  
//method that returns the status of the Course
//0 if the Course has ended, 
//the negative number of days until it starts if it still has days to start
//the number of days until it ends if it has begun
  public int getStatus() {
	  if(daysToRun==0)
		  	return 0;
	  
	  if(daysUntilStarts>0)
		  	return daysUntilStarts*-1;	  	
	  else 
		  return daysToRun;
  }
 //method that makes a day pass 
  public void aDayPasses() {
	  int holderforDTR; //variable  for checking if daysToRun is changed(if we are on the first day of the course's subject)
	  holderforDTR=daysToRun;
	  //part1
	  if(getStatus()<0)
		  daysUntilStarts--;

	  else if(daysUntilStarts==0 && daysToRun!=0) //checking if we are still in the coursetime for subject!
		  daysToRun--;
	  //end of part1
	//part3
	  if(daysUntilStarts==0 && daysToRun==holderforDTR) { //checking if we are on day 1 of the course subject( or on day 0 of daysUntilStarts)	  
		  if(hasInstructor()==false){ //if the course has no Instructor, it should close
			  isClosed=true;
			  System.out.println("Course closed due to no instructor!");
			  students.clear(); //removing the students
		  }
		  else if(students.isEmpty()==true) { //if the course has no Students, it should close
			  isClosed=true;
			  System.out.println("Course closed due to no students!");
			  this.instructor.unassignCourse(); //unassigning the course from the instructor
			  this.instructor=null; //removing the instructor if the course is cancelled
		  }
	  }
	  //end of part3
	  
	 //part2
	  if(getStatus()==0){
		  for(Student stu : students) {
			  System.out.println("Student with the name "+ stu.name+ " Graduated!");
			  stu.graduate(subject); //all the students should graduate	
			  subject.updatePrerequisites(stu);
		  }
		  this.instructor.unassignCourse(); //unassigning the course from the instructor
		  this.instructor=null; //removing the instructor because the course is finished
	  }
	  //end of part2	  	  
  }
  
  //part2
  //method for enrolling Students
  //only possible if the Course has a number <3 of students/has not begun/has not been cancelled
  //returns true if enrollment was successfull and false if not
  public boolean enrolStudent(Student student) {
	  if(students.size()==3 || daysUntilStarts==0 || isCancelled()==true)
		  return false;
	  else {
		  	if(subject.canLearn(student)==true) {
		  students.add(student);
		  student.enrollSubject(subject);
		  return true;
		  	}
		  	else
		  		return false;
	  }
  }
  
  //getter method that returns the size of the Students ArrayList
  public int getSize() {
	  return students.size();
  }
  
  //getter Student that returns the Array of Students
  public Student[] getStudents() {
	  
	  Student[] Student=students.toArray(new Student[students.size()]);
	  return Student; 
  }
  //part3
  //method that assigns the Instructor to the Course
  //returns true if it has assigned successfully
  
  public boolean setInstructor(Instructor instructor) {
	  	//checking whether the Instructor can teach the Subject of the Course
		if(instructor.canTeach(subject)) {
			this.instructor=instructor;
			return true;
		}
		else
			return false;
  }
	
  //method that checks whether we have assigned an Instructor to the Course
  //returns true if the Course has an Instructor, false if not
  public boolean hasInstructor() {
		
	  if(this.instructor==null)
			return false;
		else
			return true;
  }
  
  //method for checking whether the course has been cancelled
  //returns true if it has been cancelled, false if not
  public boolean isCancelled() {
		return isClosed;
  }
  
  //end of part 3
  //getter method for the Course Instructor
  public Instructor getInstructor() {
	  return this.instructor;
  }
  
  //method that checks if whether the course has a subject or not
  //returns true if it has, false if it does not have
  public boolean hasSubject() {
	  if(subject==null)
		  return false;
	  else
		  return true;
  }
  
  //method that checks if whether the course has students or not
  //returns true if it has, false if it does not have
  public boolean hasStudents() {
	  if(students.isEmpty()==true)
		  return false;
	  else
		  return true;
  }
  
  //method that checks if whether the course has a specific student or not
  //returns true if it has, false if it does not have
  public boolean hasStudent(Student student) {
	  for(Student stu : students)
		  if(stu==student)
			  return true;
	  return false;
  }
}