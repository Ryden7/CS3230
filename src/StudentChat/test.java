package StudentChat;

import java.util.HashSet;
import java.util.List;

public class test {

	/**
	 * Tests the Student, Group and Class objects
	 * @param args
	 */
	public static void main(String[] args)
	{
		Group GroupofStudents = new Group();
		List<Student> temp = GroupofStudents.CreateStudents();
		GroupofStudents.GenerateGroups(temp);
		
		Student s = GroupofStudents.findStudent("Rizwan");
		GroupofStudents.Chat(s, "Hello");
		HashSet<String> a = GroupofStudents.Chat(s, "asdf");
		
		
		
		
		for(String item: a)
		{
			System.out.println(item);
		}
		
		
		
		
	}

}
