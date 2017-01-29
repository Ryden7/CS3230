package StudentChat;

import java.util.ArrayList;
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
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "Hi!");
		Groups.Chat(r, "God damn lag!");

		
		
		
		ArrayList<String> a = Groups.FindConversationsofStudent("Rizwan");
		
		
		
		for(String item: a)
		{
			System.out.println(item);
		}
		
		
		
		
	}

}
