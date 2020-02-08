//Public class is the super-class for Student and Instructor
public class Person {
	public  String name; //the name of the person
	public char gender;//the gender of the person(M or F)
	public int age;//the age of the person
	
	//constructor for the Person
	public Person(String name,char gender,int age) {
		this.name=name;
		this.gender=gender;
		this.age=age;
		//name,gender, age are the person name, gender,age
	}
	
	//getter method that returns the name of the person
	public String getName() {
		return this.name;
	}
	
	//getter method that returns the gender of the person
	public char getGender() {
		return this.gender;
	}
	
	//method that sets a specific age to the person
	public void setAge(int age) {
		this.age=age;
		//age is the new age of the person
	}
	
	//getter method that returns the age of the person
	public int getAge() {
		return this.age;
	}
}
