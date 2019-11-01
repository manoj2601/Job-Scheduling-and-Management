MANOJ KUMAR
2018CS50411
ASSIGNMENT4

In this assignment, we have to implement three data structures namely Trie, Red-Black Tree and Maximum Heap 
with three seperate packages. 

In the implementation of data structure Trie, we created class Trie(whose object is a data structure Trie). 
This class has an array (of type TrieNode) so that each element of that array also contains another array.
Every node of an array(i.e. levels) has a boolean variable which is true only when that level is the 
termination of a string.
This way we insert a string in the Trie data structure.
For deleting, we reach at that termination node's array and set the boolean variable to be false. 
Similarly, other functions such as Search, etc. have been implemented.

M=Length of the string

Average Case:
INSERT: O(M)
DELETE: O(M)
SEARCH: O(M)
STARTSWITH: O(M)
PRINTTRIE: O(M)
PRINT: O(M)
PRINTLEVEL: O(M)

Worst Case:
INSERT: O(M)
DELETE: O(M)
SEARCH: O(M)
STARTSWITH: O(M)
PRINTTRIE: O(M)
PRINT: O(M)
PRINTLEVEL: O(M)

For the implementation of data structure Red-Black Tree, we inserted elements by first inserting in it just 
as binary search tree and then apply recolouring and restructure to satisfy the properties of a Red-Black 
Tree. 
N=Number of total nodes in tree
Average Case:
INSERT: O(logN)
SEARCH: O(logN)

Worst Case:
INSERT: O(logN)
SEARCH: O(logN)

For the implementation of data structure Maximum Heap, we consider each maximum heap to be an array list.
We start filling from index 0. 
For insert function, we start by placing at that element at the last of the array list(which has index i). 
Its parent is at i/2 if i/2 is not an integer and (i-1)/2. Swap parent and the inserted node until parent
is greater than the child.
Similarly, for extractmax the required node and place the last element of the array representation to the 
created  blank node.

Average Case:
INSERT: O(logN)
DELETE: O(logN)

Worst Case:
INSERT: O(logN)
DELETE: O(logN)


Job Scheduler
we need to combine all the previous components of the assignment, Trie, Red-Black Tree and Priority Queue to implement a Job scheduler (Project management). 
ach Job will belong to a Project and created by an User. The name of the Jobs will be unique (this is guaranteed in the test cases). All the jobs have a running time, i.e. the time required to run this job. The priority of a job is same as of that its project and a job can only be executed if its running time is less than the current budget of the Project. Successfully running a Job, will reduce the budget of that project by running time of the project.
All the projects will be stored in a Trie, using the project name as the key. Project names will be unique. All the Jobs will be stored in a Priority Queue, specifically a Max-Heap, using their priorities as the key.

1.
USER: Create the user with given user name.
2.
PROJECT: Create a project. NAME PRIORITY BUDGET
3.
JOB: Create a job. NAME PROJECT USER RUNTIME
4.
QUERY: Return the status of the Job queried.
5.
ADD: Increase the budget of the project. PROJECT BUDGET
6.
EMPTY_LINE: Let the scheduler execute a single JOB.

The scheduler will execute a single job whenever it will encounter an empty line in the input specifications. After the end of the INP (input file) file, scheduler will continue to execute jobs till there are jobs left that can be executed.
Each time the scheduler wants to execute a job, it will do the following:

1.
It selects the job with the highest priority from the MAX HEAP.
2.
It first check the running time of the Job, say t.
3.
It will then fetch the project from the RB-Tree and check its budget, say B.
4.
If B = t then it executes the job. Executing a job means:
Set the status of the job to complete.
Increase the global time by job time.
Set the completed time of the job as the current global time.
Decrease the budget of the project by run-time of the job. i.e. ˆB = B - t, where ˆB is the new budget of the project.
5.
If: B < t, then select the next job from the max-heap (where jobs are stored) and try to execute this.
6.
A scheduler will return in following cases:
It successfully executed a single job.
There are no jobs to be executed.
None of the jobs can be executed because of the budget issue.
7.
After the execution returns, process the next batch of commands (all the commands till next EMPTY_LINE or EOF).
8.
If there are no more commands in the INP (input file) file, then let the scheduler execute jobs till there are no jobs left, or no jobs can be executed because of budget issues. This marks the END of the execution.


ASSIGNMENT 5

This assignment is an extension to the Project Management (Scheduler) part from assignment 4. You are required to perform more complex queries to the scheduler.

In the assignment 5, we were asked to implement three data structures namely priority queue, Red-Black Tree.
There are 3 packages for each of them and 1 seperate package for PROJECT MANAGEMENT.


PACKAGE PROJECT MANAGEMENT
Class Job: An object of this class represents a particular job. It has several information about the job such 
as name of job, project, user, arrival time, completion time, etc. It contains various functions such as
compareTo(), getRunTime(), getPriority(), getJobName(), etc.It implements JobReport_ .

Class Project: An object of this class represents a particular project. It contains information 
about the project such as project name, budget, priority. 

Class User: An object of this class represents a particular user. It contains information about the user such
as name, its latest job and the time consumed by the jobs of this user.It implements UserReport_ .

Class Scheduler_Driver: It reads a file and takes input from it. Further it answers the queries as mentioned
below: 
handle_empty_line(): A cycle is executed till a job is successfully executed. It searches jobs from the jobs
(MaxHeap) and if it has sufficient budget then runs that job else keeps in a seperate array list.
handles_query(): Returns whether the job is finished or not.
handle_add(): It searches the project in projects(Trie) and then increases its budget by the given amount.
If there was any job which cannot get succesfully executed due to insufficient budget, this function also 
takes cares of these jobs.
run_to_completion(): Executes all the remaining jobs based on their budgets. Similarly, adds the job of unsufficient
budget to a seperate list and completed jobs to another array list.
print_stats(): Prints all the jobs in the database whether they were unfinished or finished with their 
information(statistics).
timed_handle_user(): prints the time in which a user object is created.
timed_handle_job(): prints the time in which a job object is created.
timed_handle_project(): prints the time in which a project object is created.
timed_run_to_completion(): prints the time taken in the running of run_to_completion() function. 

ArrayList<JobReport_> timed_report: Returns the list of all jobs with the given project and having arrival time in between given 
intervals/ jobs with the given user and having arrival time in between given intervals/ jobs with the given project and given user
having arrival time in between given intervals/ jobs having enough budgets and priorities greater than or 
equal to the given integer.

ArrayList<UserReport_>timed_top_consumer: Returns the list of top users arranged from higher to lower 
consuming times.

timed_flush(): Prioritise the longer waiting jobs to execute them irrespective of the empty line command.
Temporarily increase the priority of that job to be highest and extract it from the heap if it has 
sufficient budget.Execute it if they have waiting time greater than or equal to the given time
and increase the GLOBAL TIME by the runtime of the executed job.