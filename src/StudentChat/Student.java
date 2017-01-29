package StudentChat;

import java.util.ArrayList;
import java.util.HashMap;

public class Student 
{
	//Student values
	public String firstName;
	public String lastName;
	private int score;
	
	private HashMap<Student, ArrayList<String>> Student2message;
	public ArrayList<String> ListofMessages;
	private ArrayList<String> responses;
	private int size = 0;
	private boolean sameMessage;
	
	public Student(String first, String last, int _score)
	{
		firstName = first;
		lastName = last;
		score = _score;
		responses = new ArrayList<String>();
		ListofMessages = new ArrayList<String>();
		Student2message = new HashMap<Student, ArrayList<String>>();
		
	}
	
	public void Chat(String message)
	{
		responses.add(message);
		
		if (ListofMessages.isEmpty())
		{
			Student2message.put(this, responses);
			ListofMessages.add(message);
			size++;
		}
		else
		{
			for (String item : ListofMessages)
			{
				sameMessage = item.equals(message);
				
				if (sameMessage == true)
					return;
				
				else
				{
					Student2message.put(this, responses);
				}
				
			}
			
			ListofMessages.add(message);
			size++;

		}
	}
}
