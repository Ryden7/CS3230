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
		Group Groups = new Group();
		List<Student> temp = Groups.CreateStudents();
		Groups.GenerateGroups(temp);
		
		Student s = Groups.findStudent("Rizwan");
		Student r = Groups.findStudent("Brieanna");
		
		Groups.Chat(s, "Hello");
		Groups.Chat(s, "asdf");
		Groups.Chat(s, "Hi!");
		
		
		HashSet<String> a = Groups.FindConversationsofStudent("Rizwan");
		
		
		
		for(String item: a)
		{
			System.out.println(item);
		}
		
		
		
		
	}

}
