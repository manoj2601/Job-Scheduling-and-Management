package ProjectManagement;


public class Project {
	String name;
	int budget;
	int priority;
	
	public Project(String name, int budget, int priority)
	{
		this.name = name;
		this.budget = budget;
		this.priority = priority;
	}
	
	public String getName()
		{return name;}
	public int getBudget()
		{return budget;}
	public int getPriority()
	{
		return priority;
	}
}
