package StudentChat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Group 
{
	//Represents class of students
	private ArrayList<Student> GroupofAllStudents;
	private Class asdf = new Class();
	
	//the set that holds the 2 students in a group
	private HashSet<Student> Groupof2Students;
	
	//Group --> 2 Students
	private HashMap<String, HashSet> Group2students;
	//Group size
	private int size;
	
	public Group()
	{
		GroupofAllStudents = new ArrayList<Student>();
		Groupof2Students = new HashSet<Student>();
		Group2students = new HashMap<String, HashSet>();
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
		
		//GroupofAllStudents = "Group" + size;
		
		Group2students.put("Group"+size, Groupof2Students);
		
	}
	
	/**
	 * Creates the class of students;
	 * @return
	 */
	public List<Student> CreateStudents()
	{
		Collections.sort(asdf.listofStudents);
		for(String s: asdf.listofStudents)
		{
			String[] parts = s.split(" ");
			
			Student asdf = new Student(parts[0], parts[1], 0);
			
			GroupofAllStudents.add(asdf);

		}
		
		return GroupofAllStudents;
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
		for (Student item: GroupofAllStudents)
		{
			if (item.firstName.equals(first))
			{
				return item;
			}
			
		}
		
		return null;
		
	}
	
}
