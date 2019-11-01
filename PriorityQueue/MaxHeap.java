package PriorityQueue;

import java.util.ArrayList;

import ProjectManagement.Job;
import ProjectManagement.Project;
import ProjectManagement.User;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
//	Student[] A = new Student[100];
	 public ArrayList<T> A = new ArrayList<T>();	
	 int size=0;
	 public int rightChild(ArrayList<T> A, int i)
	 {
		 if(i != 0) {
		 if(2*i+2 < A.size())
		return 2*i + 2;
		 }
		 else if( i == 0 && A.size()>2)
		 {
			 return 2;
		 }
		 return -1;
	 }
	 public int size()
	 {
		 return size;
	 }
	 public int leftChild(ArrayList<T> A, int i)
	 {
		 if(i != 0) {
		 if((2*i) + 1 < A.size())
		return (2*i) + 1;
		 }
		 else if(i == 0 && A.size() > 1) {
			 return 1;
		 }
		 return -1;
	 }
	 
	 public int parent(ArrayList<T> A, int i)
	 {
		 if(i!= 0) {
		 if(i<A.size())
			 return (i-1)/2;
		 }
		 return -1;
	 }

    @Override
    public void insert(T element) 
    {
    	size++;
    	int m=0;
    	int i=0;
    	for(i=0; i<A.size(); i++)
    	{
    		if((A.get(i)).compareTo(element) == 0)
    			{m++;
    			break;}
    	}
    	if(m!=0)
    	{
    		if (A.get(i) instanceof Student) {
    			((Student) A.get(i)).list.add((Student) element);
    		}
    		else if (A.get(i) instanceof Job) {
    			int j=0;
    			for(j=0; j<((Job) A.get(i)).list.size(); j++)
    			{
    				if(((Job)A.get(i)).list.get(j).globaltime2 > ((Job)element).globaltime2)
    					break;
    			}
//    			System.out.println(((Job)A.get(i)).globaltime2);
    			((Job) A.get(i)).list.add(j, (Job) element);
    		}

    	}else {
    	  A.add(element);
    	  int index = A.size()-1;
    	  while((index>0) && (A.get(parent(A, index))).compareTo(A.get(index)) < 0){
		      T temp;// = new Student(null, 0);
		      temp = A.get(parent(A, index));
		      A.set(parent(A, index), A.get(index));
		      A.set(index, temp);
		      index = parent(A, index);
		    }
//    	  increaseKey(A, A.size()-1, (Student) element);
    }}
    

    @Override
    public T extractMax() 
    {
//    	System.out.println("extractmax is going");
//    	System.out.println("before extracting");
//    	this.showme();
    	if(A.size() == 0)
    		return null;
    	size--;
    	T temp;// = new Student(null, 0);
    	temp = A.get(0);
//    	System.out.println(((Job) temp).getName());
    	if(temp instanceof Student) {
    	if(((Student) temp).list.size()>1)
    	{
    		Student temp2 = new Student(((Student) temp).list.get(0).getName(), ((Student)temp).list.get(0).marks);
    		((Student) A.get(0)).list.remove(0);
    		((Student) A.get(0)).name = ((Student) A.get(0)).list.get(0).name;
    		return (T) temp2;
    	}
    	}
    	else if(temp instanceof Job)
    	{
    		if(((Job) temp).list.size()>1)
        	{
//    			System.out.println("list size is : "+((Job) temp).list.size());
    			Job temp2 = new Job(((Job) temp).list.get(0).getName(), ((Job)temp).list.get(0).projectname, ((Job)temp).list.get(0).username, ((Job)temp).list.get(0).runtime, ((Job)temp).list.get(0).arrivaltime, ((Job)temp).list.get(0).globaltime2);
    			((Job) A.get(0)).list.remove(0);
        		((Job) A.get(0)).name = ((Job) A.get(0)).list.get(0).name;
        		((Job) A.get(0)).username = ((Job) A.get(0)).list.get(0).username;
        		((Job) A.get(0)).projectname = ((Job) A.get(0)).list.get(0).projectname;
        		((Job) A.get(0)).runtime = ((Job) A.get(0)).list.get(0).runtime;
        		((Job) A.get(0)).isComplete = ((Job) A.get(0)).list.get(0).isComplete;
        		((Job) A.get(0)).endtime = ((Job) A.get(0)).list.get(0).endtime;
        		((Job) A.get(0)).arrivaltime = ((Job) A.get(0)).list.get(0).arrivaltime;
        		((Job) A.get(0)).globaltime2 = ((Job) A.get(0)).list.get(0).globaltime2;
        		((Job) A.get(0)).priority2 = ((Job) A.get(0)).list.get(0).priority2;
        		((Job) A.get(0)).priority = ((Job) A.get(0)).list.get(0).priority;
        		return (T) temp2;
        	}
    	}
    	A.set(0, A.get(A.size()-1));
    	A.remove(A.size()-1);
    	int index = 0;
    	while(!(leftChild(A, index) < 0) || !(rightChild(A,  index)<0))
    	{
    		int a1=0;
    		int b1=0;
    		if(leftChild(A, index) > 0) {
    			if(A.get(leftChild(A, index)) instanceof Student)
    				a1 = ((Student)A.get(leftChild(A, index))).marks;
    			else if(A.get(leftChild(A, index)) instanceof Job)
    				a1 = ((Job) A.get(leftChild(A, index))).priority;}
    		if(rightChild(A, index)>0) { 
//    			b1 = ((Student)A.get(rightChild(A, index))).marks;
    			if(A.get(rightChild(A, index)) instanceof Student)
    				b1 = ((Student)A.get(rightChild(A, index))).marks;
    			else if(A.get(rightChild(A, index)) instanceof Job)
    				b1 = ((Job) A.get(rightChild(A, index))).priority;}

    		if(temp instanceof Student)
    		{
    		if(((Student)A.get(index)).marks < a1 || ((Student)A.get(index)).marks < b1)
    		{
    			int a;
    			int b;
    			if(A.get(leftChild(A, index)) != null)
    				a = ((Student)A.get(leftChild(A, index))).marks;
    			else a = 0;
    			if(!(rightChild(A, index)<0) && A.get(rightChild(A, index)) != null)
    				b = ((Student) A.get(rightChild(A, index))).marks;
    			else b = 0;
    			if(a > b && ((a!= 0) || (b!= 0)))
    			{
    				T temp21;// = new Student(null, 0);
	   	    		 temp21 = A.get(index);
	   	    		 A.set(index, A.get(leftChild(A, index)));
	   	    		 A.set(leftChild(A, index), temp21);
	   	    		 index = leftChild(A, index);
    			}
    			else if((a!= 0) || (b!= 0)) {
    				T temp21 = A.get(index);
   	    		 A.set(index, A.get(rightChild(A, index)));
   	    		 A.set(rightChild(A, index), temp21);
   	    		 index = rightChild(A, index);
    			}
    			else break;
    		}
   		
    		else break;
    		//for Student end
    	}
    		else if(temp instanceof Job)
    		{
    			if(((Job)A.get(index)).priority < a1 || ((Job)A.get(index)).priority < b1)
        		{
        			int a;
        			int b;
        			if(A.get(leftChild(A, index)) != null)
        				a = ((Job)A.get(leftChild(A, index))).priority;
        			else a = 0;
        			if(!(rightChild(A, index)<0) && A.get(rightChild(A, index)) != null)
        				b = ((Job) A.get(rightChild(A, index))).priority;
        			else b = 0;
        			if(a > b && ((a!= 0) || (b!= 0)))
        			{
        				T temp21;// = new Student(null, 0);
    	   	    		 temp21 = A.get(index);
    	   	    		 A.set(index, A.get(leftChild(A, index)));
    	   	    		 A.set(leftChild(A, index), temp21);
    	   	    		 index = leftChild(A, index);
        			}
        			else if((a!= 0) || (b!= 0)) {
        				T temp21 = A.get(index);
       	    		 A.set(index, A.get(rightChild(A, index)));
       	    		 A.set(rightChild(A, index), temp21);
       	    		 index = rightChild(A, index);
        			}
        			else break;
        		}
       		
        		else break;
    		}
    	}
        return (T) temp;
    }

}