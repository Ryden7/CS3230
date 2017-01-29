package StudentChat;


import java.util.ArrayList;

/**
 * This class Represents the class of students
 * @author Rizwan_Laptop
 *
 */
public class Class 
{
	ArrayList<String> listofStudents;
	
	public Class()
	{
		listofStudents = new ArrayList<String>();
		addStudentstoClass();
	}
	
	/**
	 * Adds Students to the class
	 */
	private void addStudentstoClass()
	{
		listofStudents.add("Nathan Borup");
		listofStudents.add("Ethan Brown");
		listofStudents.add("Michael Cullimore");
		listofStudents.add("Trevor Marsh");
		listofStudents.add("Kendra Koester");
		listofStudents.add("Cody May");
		listofStudents.add("Brieanna Miller");
		listofStudents.add("Rizwan Mohammed");
		listofStudents.add("Lauren Ribeiro");
		listofStudents.add("Tyler Toponce");

	}
	
	
}
