package StudentChat;

import java.util.HashSet;

public class Student 
{
	//Student values
	public String firstName;
	public String lastName;
	private int score;
	
	//Super easy way to keep track of repeated messages
	public HashSet<String> thingsStudentHasSaid;
	
	/**
	 * Student object
	 * @param first
	 * @param last
	 * @param _score
	 */
	public Student(String first, String last, int _score)
	{
		firstName = first;
		lastName = last;
		score = _score;
		thingsStudentHasSaid = new HashSet<String>();
		
		
	}
	
}
