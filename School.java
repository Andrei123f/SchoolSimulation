import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class School {
	public String name; //the name of the school
	public ArrayList<Subject> subjects; //the subjects of the school
	public ArrayList<Student> students; //the students of the school
	public ArrayList<Course> courses; //the courses of the school
	public ArrayList<Instructor> instructors; //the instructors of the school
	public int currentDay=0; //variable used to display the current day of the school
	
	//constructor for the School
	public School(String name) {
		this.name=name;
		students=new ArrayList<Student>();
		courses=new ArrayList<Course>();
		instructors=new ArrayList<Instructor>();
		subjects=new ArrayList<Subject>();
		//name is the name of the school
	}
	
	//methods for students
	public void add(Student student) {
		students.add(student);
	}
	
	public void remove(Student student) {
	students.remove(student);
	}
	
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	//methods for subjects
	public void add(Subject subject) {
		subjects.add(subject);
	}
	
	public void remove(Subject subject) {
		subjects.remove(subject);
	}
	
	public ArrayList<Subject> getSubjects(){
		return subjects;
	}
	
	//methods for courses	
	public void add(Course course) {
		courses.add(course);
	}
	
	public void remove(Course course) {
		courses.remove(course);
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	//methods for  instructors
	public void add(Instructor instructor) {
		instructors.add(instructor);
	}
	
	public void remove(Instructor instructor) {
		instructors.remove(instructor);
	}
	
	public ArrayList<Instructor> getInsructors(){
		return instructors;
	}
	
	//methods for the simulation
	//getter method for the name of the school
	public String getName() {
		return name;
	}
	
	//overriding the toString() method
	//this returns a String with all the data from the school
	public String toString() {
		int keepIndex=0; //value for keeping the index stored
		int index=0; //value for seeing
		String finalString; //the string that will be returned
		
		String school=getName(); //getting the name of the school
		finalString="\n==============DAY "+ currentDay+" ==============";
		finalString+="\nThe school with the name: "+school+ "\n";
		
		//DISPLAYING THE COURSES OF THE SCHOOL
		finalString+="\nTHE COURSES OF THE SCHOOL:\n";
		if(courses.isEmpty()==true)
			finalString+="\nThere are no courses!\n";
		else{
		for(Course course : courses) { 
			if(course.getStatus()!=0) {
			Subject subject=course.getSubject();
			Instructor instructor=course.getInstructor();
			Student[] students = course.getStudents();
			int status=course.getStatus();
			
			finalString+="Course with the index of " + index;
			
			if(status>0)
				finalString+= " is currently running ";
			if(status<0)
				finalString+= " is not running yet, days until starts: "+course.daysUntilStarts;
			
			finalString+=" and has the subject with the id of "+ subject.getID();
			if(course.getInstructor()==null)
				finalString+=" has no intructor";
			else
			finalString+=" and the instructor named "+instructor.getName();
			
			finalString+="\nThe students for the course:\n";
			
			for(Student student : students) 
				finalString+="		"+student.getName()+"\n";
			}
		index++;
		}
		}
		finalString+=" \nTHE STUDENTS OF THE SCHOOL:";
		if(students.isEmpty()==true)
			finalString+=" \nThere are no students!";
		else{
		for(Student stu : students) {
			index=0;
			boolean hasCourse=false;
			ArrayList<Integer> certificates=stu.getCertificates();
			HashMap<String,String> prerequisites=stu.getPrerequisites();
			
			
			if(certificates.isEmpty()==true) {
				finalString+="\n "+ stu.getName() + " which has no certificates!";	
			}
			else {
			finalString+="\n "+ stu.getName() + " which has the following certificates: ";
			for(Integer certificate : certificates) {
				finalString+="\n					    "+certificate;
			}
		}
			for(Course course : courses) {
				if(course.hasStudent(stu)==true)
					hasCourse=true;
			}
			
			if(hasCourse==false)
				finalString+="\n The student with the name of "+ stu.getName() + " has no course";
			else {
				finalString+="\n The student with the name of "+stu.getName()+" is in the course with the index of ";
				for(Course course : courses) {
					if(course.hasStudent(stu)==true) {
						finalString+=index;
						break;
					}
				index++;
				}
			}
			
			finalString+="\n The student with he name of " + stu.getName() +" has the following prerequisites:\n";
			finalString+="Mathematics: " + prerequisites.get("Mathematics") + "\nProgramming:";
			if(prerequisites.get("Programming")=="")
				finalString+=" the student does not have any prerequisites in programming";
			else
				finalString+=prerequisites.get("Programming");
			
			finalString+="\n";
			
}
		}
			finalString+="\n THE INSTRUCTORS OF THE SCHOOL:\n";
			for(Instructor instructor : instructors) {
				boolean hasCourse=false;
				index=0;
				finalString+="The instructor with the name of " + instructor.getName();
				if(courses.isEmpty()==true)
					finalString+=" \nThere are no courses for the instructors!\n";
				else {
				if(instructor.getAssignedCourse()==null)
					finalString+=" has no course assigned!\n";
				else {
					for(Course course : courses) {
						if(course.getInstructor()==instructor) {
							hasCourse=true;
							keepIndex=index;
							break;
						}
						index++;
					}
					if(hasCourse==true)
						finalString+=" has the course assigned with the index of " + keepIndex+"\n";
					else
						finalString+=" has no course assigned!\n";						

				}
			}
		}
		finalString+="\n==============END OF THE DAY==============\n";
			
			currentDay++;
			return finalString;
	}
	
	public void aDayAtSchool() {
		for(Subject subject : subjects) { //searching through the subjects
			int currID=subject.getID(); //getting the unique id of each subject
			boolean hasCourse=false; //variable that should tell us if the subject has a course
			
			for(Course course : courses) { //searching though the courses
			Subject currSubject=course.getSubject(); //getting the subject for each course;
			int currSubjectID=currSubject.getID();   //getting the unique id of the current course subject
			
			if(currSubjectID==currID) { //if both ids match, the subject has a course
				hasCourse=true;
				}
			}
	
			if(hasCourse==false) {  //if the ids do not match, the subject does not have a course
				courses.add(new Course(subject,2)); //so we create a course related to that subject that starts in 2 days
			}
		}
		
		int index=0;
		
		for(Course course : courses) { //searching through the courses
			Subject subject=course.getSubject(); // getting the course subject
			
			if(course.hasInstructor()==false) { //if the course has no instructor, we need to find one
				for(Instructor instructor : instructors) { //searching through the instrcutors
					if(instructor.getAssignedCourse()==null && instructor.canTeach(subject) ) { //if we find one that is free and canteach the course subject, we assign that person
						course.setInstructor(instructor);
						instructor.assignCourse(course);
						break;
					}
				}
			}
			
			index++;
		}

		
		for(Student student : students) { //seraching through the students
			boolean isFree=true; //variable that should tell us if the student has a course assigned or not
				for(Course course : courses) { //searching through the courses
				Student	Students[] = course.getStudents(); //getting the students for each course
				int numbOfStudents = course.getSize(); //getting the number of students for each course
				
				for(int i = 0;i<numbOfStudents;i++)
					if(Students[i]==student) { //if we find the current student in the courses students it means that the student is not free
						isFree=false;
						break;
					}	
				}
				
				if(isFree == true) { //if the student it's free, we need to find a course that he/she doesn't have the certificate for and the course and is not full
					for(Course course : courses) {
						if(course.getSize()<3) {
							Subject courseSubject=course.getSubject();
							if(student.hasCertificate(courseSubject)==false) {
								course.enrolStudent(student);
								break;
							}		
						}
					}
				}
		}		
		
		for(Course course : courses) //making a day pass for each course
			course.aDayPasses();
		
		//removing any cancelled or finished courses
		index=0;
		Iterator<Course> it=courses.iterator();
		while(it.hasNext()) {
			Course course=it.next();
			if(course.getStatus()==0 || course.isCancelled()==true) {
				it.remove();
				System.out.println("The course with the index of "+ index +" was removed!");
			}
			index++;
		}

	}
}