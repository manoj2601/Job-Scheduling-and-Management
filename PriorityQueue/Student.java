package PriorityQueue;

import java.util.ArrayList;

public class Student implements Comparable<Student> {
	ArrayList<Student> list = new ArrayList<Student>();
	String name;// = list.get(0).getName();
    Integer marks;
    

    public Student(String trim, int parseInt) {
    	name = trim;
    	marks = parseInt;
    	list.add(this);
    	name = list.get(0).name;
    }


    @Override
    public int compareTo(Student student) {
        return (marks - student.marks);
    }

    public String getName() {
        return list.get(0).name;
    }
    
    public String toString()
    {
		return "Student{name='"+list.get(0).name+"', marks="+marks+"}";
    	
    }
}
