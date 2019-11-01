package ProjectManagement;

import PriorityQueue.MaxHeap;
import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
import Trie.Trie;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


	public Trie<User> trieuser = new Trie<User>();
	public Trie<Project> projecttree = new Trie();
	public MaxHeap<Job> heapjob = new MaxHeap<>();
	ArrayList<String> usernames = new ArrayList();
	ArrayList<Job> completedjobs = new ArrayList<Job>();
	public MaxHeap<Job> heapjob1 = new MaxHeap<>();
	int globaltime = 0;
	int globaltime2 = 0;
    public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        //System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
           print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
    	System.out.println("Top query");
    	ArrayList<UserReport_> a = new ArrayList();
    	for(int i=0; i<usernames.size(); i++)
    	{
    		String userr = usernames.get(i);
    		User u = trieuser.search(userr).p;
    		a.add(u);
    	}
    	//bubblesort
    	for(int i=0; i<a.size(); i++)
        {
        	for(int j=1; j<a.size()-i; j++)
        	{
        		if(a.get(j-1).consumed() < a.get(j).consumed())
        		{
        			User ja = new User(null);
        			ja = (User) a.get(j-1);
        			a.set(j-1, a.get(j));
        			a.set(j, ja);
        		}
        		else if(a.get(j-1).consumed() == a.get(j).consumed())
        		{
        			if(((User) a.get(j)).latestjobtime > ((User) a.get(j-1)).latestjobtime)
        			{
        				User ja = new User(null);
            			ja = (User) a.get(j-1);
            			a.set(j-1, a.get(j));
            			a.set(j, ja);
        			}
        		}
        	}
        }
    	ArrayList<UserReport_> a1 = new ArrayList();
    	
    	for(int i=0; i<top; i++)
    	{
    		
    		if(i<a.size()) a1.add(a.get(i));
    		else break;
    	}
        return  a1;
    }

    @Override
    public void timed_flush(int waittime) {
    	
    	//oldest start
    	System.out.println("Flush query");
    	ArrayList<JobReport_> pushing = new ArrayList();
    	ArrayList<Job> list2 = new ArrayList();
    	ArrayList<Job> list3 = new ArrayList();
    	Job j = heapjob.extractMax();
    	while(j!= null) {
    	int t = j.getruntime();
    	int B = j.projectname.budget;
	    	if(globaltime - j.arrival_time() >= waittime)
	    	{
	    		list2.add(j);
	    	}
	    	else list3.add(j);
    	j = heapjob.extractMax();
    }
    for(int i=0; i<list3.size(); i++)
    {
    	heapjob.insert(list3.get(i));
    }
    for(int i=0; i<list2.size(); i++)
    {
    	if(list2.get(i).projectname.budget >= list2.get(i).getruntime())
    	{
    		System.out.println(list2.get(i));
    		int b = list2.get(i).projectname.budget - list2.get(i).getruntime();
    		list2.get(i).projectname.budget = b;
    		list2.get(i).isComplete = true;
    		globaltime += list2.get(i).getruntime();
    		list2.get(i).endtime = globaltime;
    		completedjobs.add(list2.get(i));
    		User u = trieuser.search(list2.get(i).username.getName()).p;
    		u.consumed += list2.get(i).getruntime();
    		u.latestjobtime = list2.get(i).getruntime();
    	}
    	else
    	heapjob1.insert(list2.get(i));
    }
    return;
}
    

    private ArrayList<JobReport_> handle_new_priority(String s) {
    	System.out.println("Priority query");
    	ArrayList<JobReport_> a = new ArrayList();
        int p = Integer.parseInt(s);
        
        MaxHeap<Job> heap2 = new MaxHeap();
        Job j1 = heapjob.extractMax();
        while(j1 != null)
        {
        		if(j1.projectname.getPriority() >= p)
        		{
        			Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);

            		a.add(j2);
        		}
        		else break;
        	heap2.insert(j1);
        	j1 = heapjob.extractMax();
        }
        
        j1 = heap2.extractMax();
        while(j1 != null)
        {
        	Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);
        	heapjob.insert(j2);
        	j1 = heap2.extractMax();
        }
        //unsufficient wali
        j1 = heapjob1.extractMax();
        while(j1 != null)
        {
        		if(j1.projectname.getPriority() >= p)
        		{
        			Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);

            		a.add(j2);
        		}
        		else break;
        	heap2.insert(j1);
        	j1 = heapjob1.extractMax();
        }
        
        j1 = heap2.extractMax();
        while(j1 != null)
        {
        	Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);
        	heapjob1.insert(j2);
        	j1 = heap2.extractMax();
        }
        return a;
    }

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
    	System.out.println("Project User query");
    	ArrayList<JobReport_> a = new ArrayList<JobReport_>();
    	String project = cmd[1];
    	String userr = cmd[2];
    	int p1 = Integer.parseInt(cmd[3]);
        int p2 = Integer.parseInt(cmd[4]);
        for(int i=0; i<completedjobs.size(); i++)
        {
        	int p = completedjobs.get(i).arrival_time();
        	if(completedjobs.get(i).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
        	{
        		if(completedjobs.get(i).projectname.getName().compareTo(project) == 0)
        			a.add(completedjobs.get(i));
        	}
        }
        ArrayList<Job> a1 = new ArrayList<Job>();
        for(int i=0; i<heapjob.A.size(); i++)
        {
        	for(int j=0; j<heapjob.A.get(i).list.size(); j++)
        	{
        		int p = heapjob.A.get(i).list.get(j).arrival_time();
        		if(heapjob.A.get(i).list.get(j).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
        		{	if(heapjob.A.get(i).list.get(j).projectname.getName().compareTo(project) == 0)
        					a1.add(heapjob.A.get(i).list.get(j));
        		}
        	}
        	
        }
        for(int i=0; i<heapjob1.A.size(); i++)
        {
        	for(int j=0; j<heapjob1.A.get(i).list.size(); j++)
        	{
        		int p = heapjob1.A.get(i).list.get(j).arrival_time();
        		if(heapjob1.A.get(i).list.get(j).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
        		{	if(heapjob1.A.get(i).list.get(j).projectname.getName().compareTo(project) == 0)
        					a1.add(heapjob1.A.get(i).list.get(j));
        		}
        	}
        }
        
        //bubblesort
        for(int i=0; i<a1.size(); i++)
        {
        	for(int j=1; j<a1.size()-i; j++)
        	{
        		if(a1.get(j-1).globaltime2 > a1.get(j).globaltime2)
        		{
        			Job ja = new Job(userr, null, null, i, i, i);
        			ja = a1.get(j-1);
        			a1.set(j-1, a1.get(j));
        			a1.set(j, ja);
        		}
        	}
        }
        for(int i=0; i<a1.size(); i++)
        	a.add(a1.get(i));
        return a;
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
    	System.out.println("User query");
    	ArrayList<JobReport_> a = new ArrayList();
    	String userr = cmd[1];
        int p1 = Integer.parseInt(cmd[2]);
        int p2 = Integer.parseInt(cmd[3]);
        for(int i=0; i<heapjob.A.size(); i++)
        {
        	for(int j=0; j<heapjob.A.get(i).list.size(); j++)
        	{
        		int p = heapjob.A.get(i).list.get(j).arrival_time();
        		if(heapjob.A.get(i).list.get(j).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
        			a.add(heapjob.A.get(i).list.get(j));
        	}
        	
        }
        for(int i=0; i<completedjobs.size(); i++)
        {
        	int p = completedjobs.get(i).arrival_time();
        	if(completedjobs.get(i).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
    			a.add(completedjobs.get(i));
        }
        for(int i=0; i<heapjob1.A.size(); i++)
        {
        	for(int j=0; j<heapjob1.A.get(i).list.size(); j++)
        	{
        		int p = heapjob1.A.get(i).list.get(j).arrival_time();
        		if(heapjob1.A.get(i).list.get(j).username.getName().compareTo(userr) == 0 && p>= p1 && p<= p2)
        			a.add(heapjob1.A.get(i).list.get(j));
        	}
        	
        }
        return a;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
    	//d2 copied from prev file
    	System.out.println("Project query");
    	ArrayList<JobReport_> a = new ArrayList();
        String project = cmd[1];
        int p1 = Integer.parseInt(cmd[2]);
        int p2 = Integer.parseInt(cmd[3]);
        MaxHeap<Job> heap2 = new MaxHeap();
        Job j1 = heapjob.extractMax();
        while(j1 != null)
        {	
        	if((j1.projectname.getName()).compareTo(project) == 0)
        	{
        		if(j1.arrival_time() >= p1 && j1.arrival_time()<= p2){
        			Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);
            		a.add(j2);
        		}
        	}
        	heap2.insert(j1);
        	j1 = heapjob.extractMax();
        }
        j1 = heap2.extractMax();
        while(j1 != null)
        {
        	Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);
        	heapjob.insert(j2);
        	j1 = heap2.extractMax();
        }
        for(int i=0; i<completedjobs.size(); i++)
        {
        
        	int b =0;
        	Job j = completedjobs.get(i);
        	if((j.project_name().compareTo(project) == 0) && (j.arrival_time() >= p1 && j.arrival_time() <= p2))
        	{
        		a.add(j);
        	}
        }
        j1 = heapjob1.extractMax();
        while(j1 != null)
        {
        	if((j1.projectname.getName()).compareTo(project) == 0 && (j1.arrival_time() >= p1 && j1.arrival_time()<= p2) )
        	{

        		Job j2 = new Job(j1.name, j1.projectname, j1.username, j1.runtime, j1.arrivaltime, j1.globaltime2);

        		a.add(j2);
        	}
        	heap2.insert(j1);
        	j1 = heapjob1.extractMax();
        }
        j1 = heap2.extractMax();
        while(j1 != null)
        {
        	heapjob1.insert(j1);
        	j1 = heap2.extractMax();
        }
    	return a; 
    }
    
    public void run_to_completion() {
    	
    	Job j = heapjob.extractMax();
    	while(j!= null) {
    	System.out.println("Running code");
    	System.out.println("	Remaining jobs: "+ (heapjob.size()+1));
    	int t = j.getruntime();
    	int B = j.projectname.budget;

    	
    	while(true)
    	{
    		System.out.println("	Executing: "+j.getName()+" from: "+j.projectname.name);
    	if(B>=t)
    	{
    		int b = B-t;
    		j.projectname.budget = b;
    		j.isComplete = true;
    		globaltime += t;
    		j.endtime = globaltime;
    		User u = trieuser.search(j.username.getName()).p;
    		u.consumed += t;
    		u.latestjobtime = t;
    		completedjobs.add(j);
    		System.out.println("	Project: "+j.projectname.getName()+" budget remaining: "+j.projectname.budget);
    		System.out.println("System execution completed");
    		break;
    	}
    	else {
    		System.out.println("	Un-sufficient budget.");
    		heapjob1.insert(j);
    		j = heapjob.extractMax();
    		if(j == null) {
    			break;
    		}
    		else {
    			t = j.getruntime();
            	B = j.projectname.budget;
    		}
    	}
    	}
    	j = heapjob.extractMax();
    	if(j == null)
    	{
    		return;
    	}
    }
    }

    public void print_stats() {
    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+ completedjobs.size());
    	for(int i=0; i<completedjobs.size(); i++)
    	{
    		System.out.println(completedjobs.get(i));
    	}
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs: ");
    	Job j1 = heapjob1.extractMax();
    	int nikita = 0;
    	while(j1 != null)
    	{
    		nikita++;
    		System.out.println(j1);
    		j1 = heapjob1.extractMax();
    	}
    	System.out.println("Total unfinished jobs: "+nikita);
    	System.out.println("--------------STATS DONE---------------");
    }

    public void handle_add(String[] cmd) {
    	System.out.println("ADDING Budget");
    	if(projecttree.search(cmd[1]) == null) return;
    	Project p = projecttree.search(cmd[1]).p;
    	p.budget += Integer.parseInt(cmd[2]);
    	Job j1 = heapjob1.extractMax();
    	MaxHeap<Job> heapjob2 = new MaxHeap<>();
    	while(j1!= null)
    	{
    		if(j1.projectname.name.compareTo(p.name) == 0)
    		{
    			if(j1.projectname.getBudget() > j1.getruntime())
    			heapjob.insert(j1);
    		}
    		else heapjob2.insert(j1);
    		j1 = heapjob1.extractMax();
    	}
    	heapjob1 = heapjob2;
    	
    }

    public void handle_empty_line() {
       schedule();
    	
    }

    public  ArrayList<UserReport_> handle_new_top(String cmd)
    {
    	int top=Integer.parseInt(cmd);
    	System.out.println("Top query");
    	ArrayList<UserReport_> a = new ArrayList();
    	for(int i=0; i<usernames.size(); i++)
    	{
    		String userr = usernames.get(i);
    		User u = trieuser.search(userr).p;
    		a.add(u);
    	}
    	//bubblesort
    	for(int i=0; i<a.size(); i++)
        {
        	for(int j=1; j<a.size()-i; j++)
        	{
        		if(a.get(j-1).consumed() < a.get(j).consumed())
        		{
        			User ja = new User(null);
        			ja = (User) a.get(j-1);
        			a.set(j-1, a.get(j));
        			a.set(j, ja);
        		}
        		else if(a.get(j-1).consumed() == a.get(j).consumed())
        		{
        			if(((User) a.get(j)).latestjobtime > ((User) a.get(j-1)).latestjobtime)
        			{
        				User ja = new User(null);
            			ja = (User) a.get(j-1);
            			a.set(j-1, a.get(j));
            			a.set(j, ja);
        			}
        		}
        	}
        }
    	ArrayList<UserReport_> a1 = new ArrayList();
    	
    	for(int i=0; i<top; i++)
    	{
    		
    		if(i<a.size()) a1.add(a.get(i));
    		else a1.add(null);
    	}
    	System.out.println("showing array");//showing array
        return  a1;
    }
    

    public void handle_query(String key) {
    	System.out.println("Querying");
    	for(int i=0; i<completedjobs.size(); i++)
    	{
    		if(completedjobs.get(i).name.compareTo(key) == 0)
    		{
    			System.out.println(key+": COMPLETED");
    			return; 
    		}
    	}
    	for(int i=0; i<heapjob.A.size(); i++)
    	{
//    		System.out.println(i+" chutiya "+heapjob.A.get(i).list.size());
    		for(int j=0; j<heapjob.A.get(i).list.size(); j++) {
    		if(heapjob.A.get(i).list.get(j).getName().compareTo(key) == 0)
    		{
    			System.out.println(key+": NOT FINISHED");
    			return;
    		}}
    	}
    	for(int i=0; i<heapjob1.A.size(); i++)
    	{
    		for(int j=0; j<heapjob1.A.get(i).list.size(); j++) {
    		if(heapjob1.A.get(i).list.get(j).getName().compareTo(key) == 0)
    		{
    			System.out.println(key+": NOT FINISHED");
    			return;
    		}}
    	}
    	
    	System.out.println(key+": NO SUCH JOB");
    }

    public void handle_user(String name) {
    	System.out.println("Creating user");
//    	System.out.println("Ye wala hai");
    	if(trieuser.search(name) != null)
    	{
    		
    		System.out.println("user already exists.");
//    		System.out.println("Ye wala hai"+name);
    	}
    	else
    	{
    		usernames.add(name);
    		User u = new User (name);
//    		System.out.println(name);
    		trieuser.insert(name, u);
    	}
    }

    public void handle_job(String[] cmd) {
    	globaltime2++;
    	System.out.println("Creating job");
    	String name = cmd[1];
    	if( projecttree.search(cmd[2]) == null)
    	{
    		System.out.println("No such project exists. "+cmd[2]);
    		return;
    	}
    	Project p = projecttree.search(cmd[2]).p;
    	if(trieuser.search(cmd[3]) == null)
    	{
    		System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	User u = new User(null);
    	u = trieuser.search(cmd[3]).p;
    	int runtime = Integer.parseInt(cmd[4]);
    	Job j= new Job(name, p, u, runtime, globaltime, globaltime2);
    	heapjob.insert(j);
    }

    public void handle_project(String[] cmd) {
    	System.out.println("Creating project");
    	String name = cmd[1];
    	int budget = Integer.parseInt(cmd[3]);
    	int priority = Integer.parseInt(cmd[2]);
    	Project p = new Project(name, budget, priority);
    	if(projecttree.search(name) != null)
    		System.out.println("project already exists.");
    	else
    	{
    		projecttree.insert(name, p);
    	}
    }

    public void execute_a_job() {
    	System.out.println("Running code");
    	System.out.println("	Remaining jobs: "+ heapjob.size());
    	Job j = new Job(null, null, null, globaltime, globaltime, globaltime2);
    	j = heapjob.extractMax();
    	if(j == null)
    	{
    		System.out.println("No jobs have been left.");
    		return;
    	}
    	int t = j.getruntime();
    	int B = j.projectname.budget;

    	
    	while(true)
    	{
    		System.out.println("	Executing: "+j.getName()+" from: "+j.projectname.name);
    	if(B>=t)
    	{
    		int b = B-t;
    		j.projectname.budget = b;
    		j.isComplete = true;
    		globaltime += t;
    		j.endtime = globaltime;
    		User u = trieuser.search(j.username.getName()).p;
    		u.consumed += t;
    		u.latestjobtime = t;
    		j.priority = j.priority2;
    		completedjobs.add(j);
    		System.out.println("	Project: "+j.projectname.getName()+" budget remaining: "+j.projectname.budget);
    		System.out.println("Execution cycle completed");
    		break;
    	}
    	else {
    		System.out.println("	Un-sufficient budget.");
    		heapjob1.insert(j);
    		j = heapjob.extractMax();
    		if(j == null) {
    			System.out.println("None of the jobs can be executed.");
    			break;
    		}
    		else {
    			t = j.getruntime();
            	B = j.projectname.budget;
    		}
    	}
    	}
    }
    
    public void schedule() {
    	execute_a_job();
    }
    
    public void showheap(MaxHeap<Job> heapjob)
    {
    	System.out.println("showing heapjob: "+heapjob.size());
    	if(heapjob.A.size() == 0) return;
    	for(int i=0; i<heapjob.A.size(); i++)
    	{
    		for(int j = 0; j<heapjob.A.get(i).list.size(); j++)
    		{
    			System.out.println(heapjob.A.get(i).list.get(j));
    	}}
    }
}
