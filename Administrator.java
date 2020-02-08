//Administrator is the class for running the simulation
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Administrator {
	
	public School school; //the school of the administrator
	public Scanner x; //scanner for reading from the file
	
	//constructor for Administrator which takes a school as an input
	public Administrator(School school) {
		this.school=school;
		//school is the school of the administrator
	}
	//creating the randomGenerator for getting random numbers
	Random randomGenerator=new Random();
	
	//the run method that runs the simulation
	public void run() {
		//RANDOM ADDING TO THE SCHOOL
	/*
	 * every day up to 2 students will join the school(randomly)
	 * every random student with have the following chances of having the specific programming skills:
	 * ->C:30%
	 * ->B:20%
	 * ->A:10%
	 */
		int randomNumber=randomGenerator.nextInt(3); //getting a random value from 0(inclusive) to 2(inclusive)
		if(randomNumber==0) //if the random value is 0, no student will be added to the school
			System.out.println("No students joined the school!");
		else
			while(randomNumber!=0) { //if the random value is non-zero, new students will be added to the school
				System.out.println("A random student has been added!");
				Student randomStu=new Student("RandomStu", 'M',18);
				
				int random=randomGenerator.nextInt(100)+1; //for each student created we should get a new probability

				if(random<=30)
					randomStu.updatePrerequisites("Programming", "C");
				else if(random<=50)
					randomStu.updatePrerequisites("Programming", "B");
				else if(random<=60)
					randomStu.updatePrerequisites("Programming", "A");
				
				school.add(randomStu); //adding a new student to the school.
				randomNumber--;
			}
		
		randomNumber=randomGenerator.nextInt(100)+1; //getting a new random value from 1(inclusive) to 100(inclusive)
		//every day a new instructor will join the school with the following probabilities:
		//Teacher= 20%
		//Demonstrator=10%
		//OOTrainer=5%
		//GUITrainer=5%
		if(randomNumber<=20) { //if the random value is from 1(inclusive) to 20(inclusive), a new Teacher(prob of 20%) should be added to the school
			school.add(new Teacher("RandomTeacher", 'M',30));
			System.out.println("A teacher has joined the school!");
		}
		else if(randomNumber<=30) { //if the random value is from 21(inclusive) to 30(inclusive), a new Demonstrator(prob of 10%) should be added to the school
			school.add(new Demonstrator("RandomDemostrator", 'M',35));
			System.out.println("A Demostrator has joined the school!");
		}
		else if(randomNumber<=35) { //if the random value is from 31(inclusive) to 35(inclusive), a new OOTrainer(prob of 5%) should be added the school
			school.add(new OOTrainer("RandomOOTrainer", 'M',45));
			System.out.println("A OOTrainer has joined the school!");
		}
		else if(randomNumber<=40) { //if the random value is from 36(inclusive) to 40(inclusive), a new GUITrainer(prob of 5%) should be added the school
			school.add(new GUITrainer("RandomGUITrainer", 'M',50));
			System.out.println("A GUITrainer has joined the school!");
		}
		//making a day pass for the school
		school.aDayAtSchool(); 
		
		
		//PROB FOR LEAVING THE SCHOOL
		ArrayList<Instructor> instructors =school.getInsructors(); //getting the instructors of the school
		ArrayList<Student> students =school.getStudents(); //getting the students of the school

		int index=0; //index used to identify who got removed
		Iterator<Instructor> it=instructors.iterator();//iterator for the instructors ArrayList
		//each instructor if he/she does not have a course assigned, has a 20% chanche of leaving the school
		while(it.hasNext()) {
			randomNumber=randomGenerator.nextInt(100)+1; //generating a new random number for each instructor
			Instructor instructor=it.next();
			if(instructor.getAssignedCourse()==null && randomNumber<=20) { //checking whether the instructor has a course assigned 
				it.remove(); //removing the instructor
				System.out.println("The instructor with the index of "+index +" was removed!");
			}
			index++;
		}
		index=0; //re-initiating the index back to 0 so we can identify who gets removed!
		Iterator<Student> its=students.iterator();//iterator for the students ArrayList
		
		//each student who has all the certificates needs to be removed from the school
		//each student who does not have a course assigned has a 5% chance of leaving the school
		while(its.hasNext()) {
			randomNumber=randomGenerator.nextInt(100)+1; //getting a new random number for each student
			Student student=its.next();
			ArrayList<Integer> certificates=student.getCertificates();
			
			boolean hasAllCertificates=false; //variable for telling whether the student has all certificates or not
			
			if(certificates.size()==school.getSubjects().size()) 
				hasAllCertificates=true;
				
			if(hasAllCertificates==true) { //if the student has all certificates, he/she gets removed!
				System.out.println("The student "+student.getName()+" has all certificates! so it gets removed!");
				its.remove();
			}

			if(hasAllCertificates==false) { //if the student does not have all certificates, we need to check whether he/she is in a course
				if(student.getSubject()==null && randomNumber<=5) { //if he/she is not and is in the 5%, he/she leaves the school
					System.out.println("The student " + student.getName() + " was removed! (has no course and is in the 5%)");
					its.remove();
				}
			}
		}
	}
	
	//overloading the run method
	public void run(int numbOfDays) {	
		int currday=0;//variable used for displaying the current day
		while(numbOfDays!=0) {
			try{
			System.out.println("	THE EVENTS FOR THE DAY	"+currday); 
			run();//running the run()
			System.out.println(school.toString()); //printing out the status of the school
			Thread.sleep(10); //pausing for 1/2 sec 
			numbOfDays--;
			currday++;
			
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	//method that opens the file to be read
	public void openFile(File file) {
		try{
			x=new Scanner(file);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//method that reads the file to be read
	public void readFile(File file) {
		while(x.hasNext()) {
			School school=null; //the name of the school from the file
			String a=x.nextLine(); //reading line by line
			String[] tokens=a.split(":"); //splitting the string so that we can determine which kind of object to create
			
			//if the first word is "school", a new School should be created
			if(tokens[0].equals("school")) {
				String nameOfSchool=tokens[1];
				school=new School(nameOfSchool); //creating a new school
				this.school=school; //setting the new school to the administrator
			}
			//if the first word is "subject", a new Subject should be created and added to the school
			if(tokens[0].equals("subject")) {				
				String tokens2[]=tokens[1].split(","); //splitting the string so that we can determine the description,id,specialism and duration

				String name=tokens2[0];	//the description of the subject			
				int ID=Integer.parseInt(tokens2[1]); //the id of the subject
				int specID=Integer.parseInt(tokens2[2]); //the specialism of the subject
				int dur=Integer.parseInt(tokens2[3]); //the duration of the subject
				
				Subject subject =new Subject(ID,specID,dur); //creating a new subject
				subject.setDescription(name); //setting the description for that subject
				this.school.add(subject); //adding the subject to the school of the administrator
			}
			//if the first word is "student", a new Student should be created and added to the school
			if(tokens[0].equals("student")) {
				String tokens2[]=tokens[1].split(","); //splitting the string so we can determine the name,gender and age of the student

				String name=tokens2[0]; //the name of the student
				char gender[]=tokens2[1].toCharArray(); //the gender of the student
				int age=Integer.parseInt(tokens2[2]); //the age of the student
				
				Student student=new Student(name,gender[0],age); //creating a new student
				
				if(tokens2.length==4) //if the student has a field for programming, the 
					student.updatePrerequisites("Programming", tokens2[3]);
				
				
				this.school.add(student); //adding the student to the school
			}
			//if the first word is "Teacher", a new Teacher should be created and added to the school
			if(tokens[0].equals("Teacher")) {
				String tokens2[]=tokens[1].split(","); //splitting the string so we can determine the name,gender and age of the teacher

				String name=tokens2[0]; //the name of the teacher
				char gender[]=tokens2[1].toCharArray(); //the gender of the teacher
				int age=Integer.parseInt(tokens2[2]); //the age of the teacher
				
				Teacher teacher =new Teacher(name,gender[0],age); //creating a new teacher
				this.school.add(teacher); //adding the teacher to the school
			}
			//if the first word is "Demonstrator", a new Demonstrator should be created and added to the school
			if(tokens[0].equals("Demonstrator")) {
				String tokens2[]=tokens[1].split(","); //splitting the string so we can determine the name,gender and age of the demonstrator

				String name=tokens2[0]; //the name of the demonstrator
				char gender[]=tokens2[1].toCharArray();//the gender of the demonstrator
				int age=Integer.parseInt(tokens2[2]); //the age of the demonstrator
				
				Demonstrator demonstrator =new Demonstrator(name,gender[0],age); //creating a new demonstrator
				this.school.add(demonstrator); //adding the new administrator to the school
			}
			//if the first word is "OOTrainer", a new OOTrainer should be created and added to the school
			if(tokens[0].equals("OOTrainer")) {
				String tokens2[]=tokens[1].split(","); //splitting the array so we can determine the name,gender and age of the ootrainer

				String name=tokens2[0]; //the name of the ootrainer
				char gender[]=tokens2[1].toCharArray(); //the gender of the ootrainer
				int age=Integer.parseInt(tokens2[2]); //the age of the ootrainer
				
				OOTrainer ooTrainer =new OOTrainer(name,gender[0],age); //creating a new ootrainer
				this.school.add(ooTrainer); //adding the new ootrainer to the school
			}
			//if the first word is "GUITrainer", a new GUITrainer should be created and added to the school
			if(tokens[0].equals("GUITrainer")) {
				String tokens2[]=tokens[1].split(","); //splitting the array so we can determine the name,gender and age of the guitrainer
				
				String name=tokens2[0];	//the name of the guitrainer
				char gender[]=tokens2[1].toCharArray();	//the gender of the guitrainer
				int age=Integer.parseInt(tokens2[2]);	//the age of the guitrainer
				
				GUITrainer guiTrainer =new GUITrainer(name,gender[0],age);	//creating a new guitrainer
				this.school.add(guiTrainer);	//adding the new guitrainer to the school
			}
		}
	}
	
	//method that closes the file that was read
	public void closeFile(File file) {
		x.close();
	}
}
