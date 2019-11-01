# Job-Scheduling-and-Management
http://web.iitd.ac.in/~anz188059/Assignment5.html


## 9. FAQ

### Can we give an unsorted, randomly ordered arraylist in newuser and newproject or should we sort them like we sorted newprojectuser?
Ans: Only projectuser is asked to be sorted in the specs.


### Importing maps are allowed ?
Ans: NO! You can import java.util.ArrayList, java.util.Stack, and java.util.Queue. You are also allowed to import list, and linked list! (08/10/2019)


### How the correctness of flush query be checked as it does not return anything ?
Ans: The final state of the system will decide whether it had executed correctly, as the completed jobs are printed in the order jobs were executed.


### What do we have to return in timed_user etc. ?
Ans: Return an empty list.


### What is the memory limit of eclipse? How much space are we allowed to use ?
Ans: Don't worry about this, we will ensure that it's executing for majority of the class.


### Do we need to add ID of Jobs as given in the output file in mail?
Ans: It's no required, it's totally optional.





# *Assignment 5*

# *Project Scheduler Queries*



Release date: 4th October


Due date: 16th October

## Brief description
This assignment is an extension to the Project Management (Scheduler) part from assignment 4. You are required to perform more complex queries to the scheduler. Details of the commands to be implemented are given below in commands section. (A word about the last command, FLUSH. Suppose a job has sufficient resources but due to its low priority, other jobs with high priority keeps running ahead of it. This would lead job starvation. You give a push to such jobs to run. A way to handle this could be by artificially raising such jobs’ priority.)


## Scheduler Interfaces
You are required to implement the modified scheduler interface mentioned below also attached in ProjectManagement.zip. This is designed to be backward compatible, and an implementation of the original scheduler interface (for Assignment 4) also automatically implements the new interface. Well, almost. You will still need to include the interfaces and default implementations for JobReport_ and UserReport_.


Note that new methods in the interface have the timed prefix. These methods will be timed by the driver code. (You are welcome to modify your driver code accordingly.) Please make sure that the timed methods return as soon as possible with the correct action/answer. This means avoid all extraneous statements (like print) that are not required.


You may only import java.util.ArrayList, java.util.Stack, and java.util.Queue. (Maps are not allowed for assignment 5. Maps will be allowed for assignment 4 tests only.)



## 4. Sample input file:

USER Rob
USER Harry
USER Carry
PROJECT IITD.CS.ML.ICML 10 15
PROJECT IITD.CS.OS.ASPLOS 9 100
PROJECT IITD.CS.TH.SODA 8 100
JOB DeepLearning IITD.CS.ML.ICML Rob 10
JOB ImageProcessing IITD.CS.ML.ICML Carry 10
JOB Pipeline IITD.CS.OS.ASPLOS Harry 10
JOB Kmeans IITD.CS.TH.SODA Carry 10
QUERY Kmeans
QUERY Doesnotexists
JOB DeepLearningNoProject IITD.CS.ML.ICM Rob 10
JOB DeepLearningNoUser IITD.CS.ML.ICML Rob2 10
JOB DeepLearning1 IITD.CS.ML.ICML Rob 10
JOB ImageProcessing1 IITD.CS.ML.ICML Carry 10
JOB Pipeline1 IITD.CS.OS.ASPLOS Rob 10
JOB Kmeans1 IITD.CS.TH.SODA Carry 10
JOB DeepLearning2 IITD.CS.ML.ICML Rob 10
JOB ImageProcessing2 IITD.CS.ML.ICML Carry 10
JOB Pipeline2 IITD.CS.OS.ASPLOS Harry 10
JOB Kmeans2 IITD.CS.TH.SODA Carry 10
ADD IITD.CS.ML.ICML 60
JOB DeepLearning3 IITD.CS.ML.ICML Rob 10
JOB ImageProcessing3 IITD.CS.ML.ICML Carry 10
JOB Pipeline3 IITD.CS.OS.ASPLOS Harry 10
JOB Kmeans3 IITD.CS.TH.SODA Carry 10
QUERY Kmeans
JOB DeepLearning4 IITD.CS.ML.ICML Rob 10
JOB ImageProcessing4 IITD.CS.ML.ICML Carry 10
JOB Pipeline4 IITD.CS.OS.ASPLOS Harry 10
JOB Kmeans4 IITD.CS.TH.SODA Carry 10
JOB DeepLearning5 IITD.CS.ML.ICML Rob 10
JOB ImageProcessing5 IITD.CS.ML.ICML Carry 10
JOB Pipeline5 IITD.CS.OS.ASPLOS Harry 10
JOB Kmeans5 IITD.CS.TH.SODA Carry 10
QUERY Kmeans
NEW_PROJECT IITD.CS.ML.ICML 1 100
NEW_USER Rob 1 100
NEW_PROJECTUSER IITD.CS.ML.ICML Rob 1 100
NEW_PRIORITY 5
NEW_TOP 4
NEW_FLUSH 30


## 5. Sample output file:

Creating user
Creating user
Creating user
Creating project
Creating project
Creating project
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 4
Executing: DeepLearning from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 5
Execution cycle completed
Querying
Kmeans: NOT FINISHED
Querying
Doesnotexists: NO SUCH JOB
Running code
Remaining jobs: 3
Executing: ImageProcessing from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Pipeline from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 90
Execution cycle completed
Creating job
No such project exists. IITD.CS.ML.ICM
Creating job
No such user exists: Rob2
Running code
Remaining jobs: 1
Executing: Kmeans from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 90
Execution cycle completed
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 4
Executing: DeepLearning1 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: ImageProcessing1 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Pipeline1 from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 80
Execution cycle completed
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 5
Executing: DeepLearning2 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: ImageProcessing2 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Pipeline2 from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 70
Execution cycle completed
ADDING Budget
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 11
Executing: ImageProcessing from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 55
Execution cycle completed
Querying
Kmeans: COMPLETED
Running code
Remaining jobs: 10
Executing: DeepLearning1 from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 45
Execution cycle completed
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 13
Executing: ImageProcessing1 from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 35
Execution cycle completed
Creating job
Creating job
Creating job
Creating job
Running code
Remaining jobs: 16
Executing: DeepLearning2 from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 25
Execution cycle completed
Querying
Kmeans: COMPLETED
Running code
Remaining jobs: 15
Executing: ImageProcessing2 from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 15
Execution cycle completed
Project query
Time elapsed (ns): 1313755
User query
Time elapsed (ns): 457942
Project User query
Time elapsed (ns): 508123
Priority query
Time elapsed (ns): 252904
Top query
Time elapsed (ns): 979585
Flush query
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='Kmeans1'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='Kmeans2'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='DeepLearning3'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='ImageProcessing3'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='Pipeline3'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='Kmeans3'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='DeepLearning4'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='ImageProcessing4'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=9, name='Pipeline4'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=8, name='Kmeans4'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='DeepLearning5'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='ImageProcessing5'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=9, name='Pipeline5'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=8, name='Kmeans5'}
Running code
Remaining jobs: 14
Executing: Kmeans1 from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 80
Execution cycle completed
Running code
Remaining jobs: 13
Executing: Kmeans5 from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 70
Execution cycle completed
Running code
Remaining jobs: 12
Executing: Pipeline5 from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 60
System execution completed
Running code
Remaining jobs: 11
Executing: ImageProcessing5 from: IITD.CS.ML.ICML
Project: IITD.CS.ML.ICML budget remaining: 5
System execution completed
Running code
Remaining jobs: 10
Executing: DeepLearning5 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Kmeans4 from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 60
System execution completed
Running code
Remaining jobs: 8
Executing: Pipeline4 from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 50
System execution completed
Running code
Remaining jobs: 7
Executing: ImageProcessing4 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: DeepLearning4 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Kmeans3 from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 50
System execution completed
Running code
Remaining jobs: 4
Executing: Pipeline3 from: IITD.CS.OS.ASPLOS
Project: IITD.CS.OS.ASPLOS budget remaining: 40
System execution completed
Running code
Remaining jobs: 3
Executing: ImageProcessing3 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: DeepLearning3 from: IITD.CS.ML.ICML
Un-sufficient budget.
Executing: Kmeans2 from: IITD.CS.TH.SODA
Project: IITD.CS.TH.SODA budget remaining: 40
System execution completed
--------------STATS---------------
Total jobs done: 19
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=10, priority=10, name='DeepLearning'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=20, priority=9, name='Pipeline'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=30, priority=8, name='Kmeans'}
Job{user='Rob', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=40, priority=9, name='Pipeline1'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=50, priority=9, name='Pipeline2'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=60, priority=10, name='ImageProcessing'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=70, priority=10, name='DeepLearning1'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=80, priority=10, name='ImageProcessing1'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=90, priority=10, name='DeepLearning2'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=100, priority=10, name='ImageProcessing2'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=110, priority=2147483647, name='Kmeans1'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=120, priority=8, name='Kmeans5'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=130, priority=9, name='Pipeline5'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=COMPLETED, execution_time=10, end_time=140, priority=10, name='ImageProcessing5'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=150, priority=8, name='Kmeans4'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=160, priority=9, name='Pipeline4'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=170, priority=2147483647, name='Kmeans3'}
Job{user='Harry', project='IITD.CS.OS.ASPLOS', jobstatus=COMPLETED, execution_time=10, end_time=180, priority=2147483647, name='Pipeline3'}
Job{user='Carry', project='IITD.CS.TH.SODA', jobstatus=COMPLETED, execution_time=10, end_time=190, priority=2147483647, name='Kmeans2'}
------------------------.
Unfinished jobs:
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='DeepLearning5'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='ImageProcessing4'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=10, name='DeepLearning4'}
Job{user='Carry', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='ImageProcessing3'}
Job{user='Rob', project='IITD.CS.ML.ICML', jobstatus=REQUESTED, execution_time=10, end_time=null, priority=2147483647, name='DeepLearning3'}
Total unfinished jobs: 5
--------------STATS DONE---------------


### Note again:
For the description of the queries : http://web.iitd.ac.in/~anz188059/Assignment5.html
You have to build your own driver code
Maps (e.g. HashMap) are not allowed

### Manoj Kumar


