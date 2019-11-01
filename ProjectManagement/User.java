package ProjectManagement;

public class User implements Comparable<User> , UserReport_ {


	String name;
	int consumed = 0;
	int latestjobtime = 0;
	User(String name)
	{
		this.name = name;
	}
    public String getName()
    {
    	return name;
    }
    public int compareTo(User user) {
        if(name.compareTo(user.name)<0)
    	return -1;
        else return 1;
    }
	@Override
	public String user() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public int consumed() {
		// TODO Auto-generated method stub
		return consumed;
	}
    public int compareToconsumed(User user) {
        return (this.consumed - user.consumed);
    }
}
