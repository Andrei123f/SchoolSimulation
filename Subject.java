//Subject is the class for the subjects of the school
import java.util.HashMap; //importing java.util.HashMap for using the HashMap

public class Subject{
  public int id; //the unique id for the Subject
  public int specialism; //the specialism id of the Subject
  public int duration; //the number of days required to cover(for any Course) the Subject
  public String description; //the description of the Subject

//constrcutor for the Subject class
    public Subject(int id, int specialism, int duration){
    this.id=id;
    this.specialism=specialism;
    this.duration=duration;
  }
    
//returning the Subject id
    public int getID(){
    return this.id;
  }
    
// returning the specialism id of the Subject
    public int getSpecialism(){
      return this.specialism;
    }
    
//returning the duration for covering the Subject
    public int getDuration(){
      return this.duration;
    }
    
//setting up the description of the Subject
    public void setDescription(String description){
      this.description=description;
    }
    
//returning the Subject description
    public String getDescription(){
      return description;
    }
/*
 * method for checking whether the student can learn the subject
 * each subject has its requirements based on its id"
 * 1: needs A in mathematics
 * 2:needs a minimum of C in programming and A in mathematics
 * 3:needs a minimum of B in programming and A in mathematics
 * 4:needs a minimum of A in programming and A in mathematics
 */
   
    public boolean canLearn(Student student) {
    	HashMap<String,String> prerequisites=student.getPrerequisites(); //getting the student prerequisites
    	String math=prerequisites.get("Mathematics"); //getting the level of Mathematics of the student
    	String programming=prerequisites.get("Programming");//getting the level of Progamming of the student
    	
    	if(specialism==1) {
    		if(math.equals("A")) 
    			return true;    		
    		else
    			return false;
    	}
    	
    	if(specialism==2) {
    		boolean progSkill=false; //variable used for checking whether the student has the minimum of programming requirements or not 
    		
    		if(programming.equals("B") || programming.equals("C") || programming.equals("A"))
    			progSkill=true;
    		
    		if(math.equals("A") && progSkill==true) 
    			return true;   		
    		else
    			return false;
    	}
    	
    	if(specialism==3) {
    		boolean progSkill=false; //variable used for checking whether the student has the minimum of programming requirements or not
    		
    		if(programming.equals("B") || programming.equals("A"))
    			progSkill=true;

    		if(math.equals("A") && progSkill==true) 
    			return true;   		
    		else
    			return false;
    	}
    	
    	if(specialism==4) {
    		if(math.equals("A") && programming.equals("A")) 
					return true;
    		
    		else
					return false;
    	}
    	
    	return false;
	
    }
    
    /*
     * method for updating  the student  prerequisites based on the courses subject id he/she's just graduated 
     * 1: gives C in programming if the student doesn't have a minimum of C
     * 2:gives B in programming if the student doesn't have B,A,A+
     * 3:gives A in programming if the student doesn't have A+
     * 4:gives A+ in programming
     */
    public void updatePrerequisites(Student student) {
    	HashMap<String,String> prerequisites=student.getPrerequisites();
    	String programming=prerequisites.get("Programming");
    	if(specialism==1) {
    		if(programming.equals(""))
    			prerequisites.replace("Programming", "C");
    	}
    	
    	if(specialism==2) {
    		if(programming.equals("C"))
    			prerequisites.replace("Programming", "B");
    	}
    	
    	if(specialism==3) {
    		if(programming.equals("B"))
    			prerequisites.replace("Programming", "A");
    	}
    	
    	if(specialism==4)
    		prerequisites.replace("Programming", "A+");  		
    }
}
