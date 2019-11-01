package ProjectManagement;

import java.util.ArrayList;

import PriorityQueue.Student;

public class Job implements Comparable<Job>, JobReport_ {

    public ArrayList<Job> list = new ArrayList<Job>();
	public String name;
	public Project projectname;
	public User username;
	public int runtime;
	public boolean isComplete;
	public int endtime;
	public int arrivaltime;
	public int globaltime2;
	public int priority2;
	public int priority;
	public Job(String name, Project projectname, User username, int runtime, int arrivaltime, int globaltime2)
	{
		this.name = name;		
		this.projectname = projectname;
		this.username = username;
		this.runtime = runtime;
		this.arrivaltime = arrivaltime;
		this.globaltime2 = globaltime2;
		isComplete = false;
		list.add(this);
		name = list.get(0).name;
		username = list.get(0).username;
		runtime = list.get(0).runtime;
		arrivaltime = list.get(0).arrivaltime;
		globaltime2 = list.get(0).globaltime2;
		if(projectname != null) priority = projectname.priority;
		priority2 = priority;
	}
	
	   public String getName() {
	        return list.get(0).name;
	    }
	   public User getUser()
	   {
		   return list.get(0).username;
	   }
	   public int getruntime()
	   {
		   return list.get(0).runtime;
	   }
    @Override
    public int compareTo(Job job) {
        return (priority - job.priority);
    }
    public int endtime()
    {
    	if(!isComplete)
    	{
    		return 0;
    	}
    	else
    		return endtime;
    }
    public String toString()
    {
    	String str;
    	if(isComplete)
    	str = "Job{user='"+this.username.getName()+"', project='"+this.projectname.getName()+"', jobstatus=COMPLETED, execution_time="+this.getruntime()+", end_time="+this.endtime+", name='"+this.getName()+"'}";
    	else
    		str = "Job{user='"+this.username.getName()+"', project='"+this.projectname.getName()+"', jobstatus=REQUESTED, execution_time="+this.getruntime()+", end_time=null"+", name='"+this.getName()+"'}";
    	return str;
    }

	@Override
	public String user() {
		// TODO Auto-generated method stub
		return username.name;
	}

	@Override
	public String project_name() {
		// TODO Auto-generated method stub
		return projectname.name;
	}

	@Override
	public int budget() {
		// TODO Auto-generated method stub
		return projectname.budget;
	}

	@Override
	public int arrival_time() {
		// TODO Auto-generated method stub
		return arrivaltime;
	}

	@Override
	public int completion_time() {
		// TODO Auto-generated method stub
		return endtime;
	}
}