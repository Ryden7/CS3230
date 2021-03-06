package StudentChat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Group 
{
	//Represents class of students
	private ArrayList<Student> ClassofStudentsSorted;
	private Class classroom = new Class();
	
	//the conversations LIST

	private ArrayList<String> conversations;

	
	//Read as Group --> Students
	private HashMap<String, HashSet> Group2students;
	private HashMap<Student, String> Student2Group;
	private HashMap<String, ArrayList> Group2messages;
	
	//Group size
	private int size;
	
	public Group()
	{
		ClassofStudentsSorted = new ArrayList<Student>();
		Group2students = new HashMap<String, HashSet>();
		Student2Group = new HashMap<Student, String>();
		Group2messages = new HashMap<String, ArrayList>();
		conversations = new ArrayList<String>();
		

		size = 0;
		
	}
	
	/**
	 * Adds 2 Students in a group
	 * @param Student1
	 * @param Student2
	 */
	public void addStudenttoGroup(Student Student1, Student Student2)
	{
		HashSet<Student> Groupof2Students = new HashSet<Student>();

		Groupof2Students.add(Student1);
		Groupof2Students.add(Student2);
		size++;
				
		Group2students.put("Group"+size, Groupof2Students);
		Student2Group.put(Student1, "Group"+size);
		Student2Group.put(Student2, "Group"+size);

		
	}
	
	/**
	 * Creates the class of students;
	 * @return
	 */
	public List<Student> CreateStudents()
	{
		Collections.sort(classroom.listofStudents);
		for(String s: classroom.listofStudents)
		{
			String[] parts = s.split(" ");
			
			Student asdf = new Student(parts[0], parts[1], 0);
			
			ClassofStudentsSorted.add(asdf);

		}
		
		return ClassofStudentsSorted;
	}
	
	/**
	 * Generates the groups for the students in the class
	 * @param allStudents
	 * @return
	 */
	public int GenerateGroups(List<Student> allStudents)
	{
		int count = 0;
		for (int i = 0; i < allStudents.size() / 2; i++)
		{
			addStudenttoGroup(allStudents.get(count), allStudents.get(count+1));
			count = count + 2;
		}
		
		return 0;

	}
	
	/**
	 * Returns a Student object based on the Students first name
	 * @param first
	 * @return
	 */
	public Student findStudent(String first)
	{
		
		for (Student item: ClassofStudentsSorted)
		{
			if (item.firstName.equals(first))
			{
				return item;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * private helper
	 * @param name
	 * @return
	 */
	private String findStudentGroup(String name)
	{
		Student s = findStudent(name);
		String group = Student2Group.get(s);
		return group;
	}
	
	
	/**
	 * Main chat method between 2 Students
	 * @param s
	 * @param message
	 */
	public void Chat(Student s, String message)
	{
		String group = findStudentGroup(s.firstName);
		
		if (s.thingsStudentHasSaid.contains(s.firstName + " " + s.lastName + " : " + message))
			return;
		else
		{
			conversations.add(s.firstName + " " + s.lastName + " : " + message);
			Group2messages.put(group, conversations);
			s.thingsStudentHasSaid.add(s.firstName + " " + s.lastName + " : " + message);
		}

		
		
	}
	
	/**
	 * Displays the conversation between a student and his partner
	 * @param firstName
	 * @return
	 */
	public ArrayList<String> FindConversationsofStudent(String firstName)
	{
		String group = findStudentGroup(firstName);
		
		ArrayList<String> conversation = Group2messages.get(group);
		
		return conversation;

	}
	
}
