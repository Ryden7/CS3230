package StudentChat;

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
		
		Student me = GroupofStudents.findStudent("Rizwan");
		me.Chat("hello");
		me.Chat("asdf");
		
		for(String item: me.ListofMessages)
		{
			System.out.println(item);
		}
		
		
		
		
	}

}
