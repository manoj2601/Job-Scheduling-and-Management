package Trie;

import java.util.ArrayList;

public class Trie<T> implements TrieInterface
{
	TrieNode<T>[] root = new TrieNode[126];
	ArrayList<Integer> maxheight = new ArrayList<Integer> ();
	public Trie()
	{
		for(int i=0; i<126; i++)
		root[i] = null;
	}

    @Override
    public boolean delete(String word) {
    	if(this.search(word) == null) 
    		{
    			System.out.println("ERROR DELETING");
    			return false;
    		}
    	int aa = maxheight.indexOf(word.length());
    	maxheight.remove(aa);
//    	System.out.println("deletion is going to start. ");
    	TrieNode<T>[] table = root;
    	int i=0;
    	for(i=0; i<word.length()-1; i++)
    	{
//    		System.out.print(i+" ");
    		TrieNode<T> a = table[(int) word.charAt(i)];
    		table = a.gettable();
    	}
    	//abhi table last wali hai
    	
    	int manoj = 0;
    	while(i>=0 && manoj == 0)
    	{
	    	int k=0;
	    	int j=0;
	    	for(j=0; j<126; j++)
	    	{
	    		if(table[j] != null)
	    			k++;
	    	}
	    	if(k==1)
	    	{

	    		if(table[(int) word.charAt(i)].isEndofWord() == true && i<word.length()-1)
	    		{
	    			table[(int) word.charAt(i)].parent.table[(int) word.charAt(i)].table = null;
	    			table[(int) word.charAt(i)].table = null;
	    			manoj++;
	    			break;
	    			
	    		}
	//    		TrieNode parent = table[(int) word.charAt(i)].parent;
//	    		table[(int) word.charAt(i)] = null;
	    		TrieNode<T>[] table2 = table[(int) word.charAt(i)].prevtable;
//	    		TrieNode papa = table[(int) word.charAt(i)].parent;
	    		
	    		if(i != 0)
	    		{
//	    			System.out.println("Mk idhar bhi");
	    			table[(int) word.charAt(i)].parent.table = null;
	    			table = table2;
	    		}
	    		if(i == 0)
	    			table[(int) word.charAt(i)] = null;
	    	}
	    	else
	    	{
	    		if(table[(int) word.charAt(i)].isEndofWord() == true && i<word.length()-1)
	    		{
//	    			System.out.println("Hello andar wale manoj"+word.charAt(i));
//	    			table[(int) word.charAt(i)].parent.table[(int) word.charAt(i)].table = null;
	    			table[(int) word.charAt(i)].table = null;
//	    			System.out.println((char)table[(int) word.charAt(i)].ch);
	    			manoj++;
	    			break;
	    			
	    		}
//	    		System.out.println("k != 1 hai");
	    		if(table[(int) word.charAt(i)].isEndofWord == false) {
	    			table[(int) word.charAt(i)] = null;
	    		}
	    		else
	    		{
	    			table[(int) word.charAt(i)] = null;
	    		}
	    		break;
	    	}
	    	i--;
    	}
//    	table[(int) word.charAt(i)].isEndofWord = false;
//    	deletehelper(word, root, 0);
    	System.out.println("DELETED");
        return true;
    }

    @Override
    public TrieNode<T> search(String word) {
        TrieNode<T>[] table = root;
        int i=0;
        for(i=0; i<word.length(); i++)
        {//System.out.println("we are talking about : "+word.charAt(i));
        	
        	
        	if( table[(int) word.charAt(i)] == null)
        		{return null;}
        	TrieNode<T> a = table[(int) word.charAt(i)];
        	if(a.isEndofWord() && i == word.length()-1)
        	{
        		return a;
        	}
        	else table = a.gettable();
        	if(table == null)
        		{return null;}
        }

    	return null;
    }

    @Override
    public TrieNode<T> startsWith(String prefix) {
    	TrieNode[] table = root;
    	for(int i=0; i<prefix.length()-1; i++)
    	{
    		if(table[(int) prefix.charAt(i)] != null)
    		{
    			table = table[(int) prefix.charAt(i)].table;
    		}
    		else {
//    			System.out.println("null hai bhai"+prefix.charAt(i));
    			return null;
    		}
    	}
    	//System.out.println((char) (table[ (int) prefix.charAt(prefix.length()-1)].ch));
        return table[(int) prefix.charAt(prefix.length()-1)];
    }

    @Override
    public void printTrie(TrieNode trieNode) {
    	TrieNode<T> t = trieNode;
    	if(t.isEndofWord == true)
    	{
    		T p = t.p;
    		System.out.println(p);
//    		print person of t;
    		if(t.table != null)
    		{
    			for(int i=0; i<126; i++)
    			{
        			if(t.gettable()[i] != null)
        			printTrie(t.gettable()[i]);
        		}
    		}
    	}
    	
    	if(t.isEndofWord == false)
    	{
    		for(int i=0; i<126; i++)
    		{
    			if(t.gettable()[i] != null)
    			printTrie(t.gettable()[i]);
    		}
    	}
//    	TrieNode[] table = t.table;
    	
    	
    }

    @Override
    public boolean insert(String word, Object value) {
//    	if(this.search(word)== null)
//    		return false;
    	maxheight.add(word.length());
//    	System.out.print(" insertion is going to start. ");
        TrieNode[] table = root;
        //going for the root
        TrieNode parnode = null;
        TrieNode[] prevtable = root;
        if(table[(int)word.charAt(0)] == null )
        {
        	TrieNode[] child = new TrieNode[126];
        	for(int i=0; i<126; i++)
        		child[i]=null;
        	TrieNode m = new TrieNode((int) word.charAt(0), false, null, child, null, null);
        	table[(int)word.charAt(0)] = m;
        	parnode = table[(int)word.charAt(0)];
        	prevtable = table;
        	table = table[(int)word.charAt(0)].table;
        }
        else
        {
//        	System.out.println(0+"th node is not null");
        	
        	parnode = table[(int)word.charAt(0)];
        	prevtable = table;
        	table = table[(int)word.charAt(0)].table;
        }
        
        for(int i=1; i<word.length()-1; i++)
        {
        	if(table == null) 
        		{
        		TrieNode[] child = new TrieNode[126];
             	for(int j=0; j<126; j++)
             		child[j]=null;
             	parnode.table = child;
             	table = parnode.table;
        		}
        	 if(table[(int)word.charAt(i)] == null)
             {
        		 TrieNode[] child = new TrieNode[126];
             	for(int j=0; j<126; j++)
             		child[j]=null;
             	TrieNode m = new TrieNode((int) word.charAt(i), false, null, child, parnode, prevtable);
             	table[(int)word.charAt(i)] = m;
             	parnode = table[(int)word.charAt(i)];
             	prevtable = table;
             	table = table[(int)word.charAt(i)].table;
             }
             else
             {
            	parnode = table[(int)word.charAt(i)];
            	prevtable = table;
             	table = table[(int)word.charAt(i)].table;
             }
        	 
        }
        if(table == null) 
		{
		TrieNode[] child = new TrieNode[126];
     	for(int j=0; j<126; j++)
     		child[j]=null;
     	parnode.table = child;
     	table = parnode.table;
		}
        if(table[(int) word.charAt(word.length()-1)] == null)
        {
//        	System.out.println("last node is null");
        	TrieNode m = new TrieNode((int) word.charAt(word.length()-1), true, value, null, parnode, prevtable);
        	table[(int) word.charAt(word.length()-1)] = m;
//        	parnode = table[(int) word.charAt(word.length()-1)];
        }
        else
        {
//        	System.out.println("last node is not null");
        	table[(int) word.charAt(word.length()-1)].isEndofWord = true;
        	table[(int) word.charAt(word.length()-1)].p =
        			value;
        }
        
        
        
        //old code
//        int i=0;
//    	for(i=0; i<word.length(); i++)
//    	{
////    		System.out.println("Manoj");
////    		TrieNode a = table[(int) word.charAt(i)];
//    		if(table[(int) word.charAt(i)] != null)
//	        {
//    			System.out.println(i+"th table is not null");
////	        	table = table[(int) word.charAt(i)].gettable();
//	        	if(i == word.length()-1)
//	        	{
//	        		table[(int) word.charAt(i)].isEndofWord = true;
//	        		table[(int) word.charAt(i)].p = (Person) value;
//	        	}
//	        	table = table[(int) word.charAt(i)].gettable();
//	        }
//    		else {
//    			System.out.println(i+"th table is null");
//    			TrieNode[] b = new TrieNode[126];
//    			for(int j = 0; j<126; j++)
//    				b[j] = null;
////    			b = null;
//    			if(i != word.length()-1)
//    			table[(int) word.charAt(i)] = new TrieNode((int) word.charAt(i), false, null, b);
//    			else
//    				
//    				table[(int) word.charAt(i)] = new TrieNode((int) word.charAt(i), true, (Person) value, null);
//    				table = table[(int) word.charAt(i)].gettable();
//    		}
//    	}
//        
        
    	
    	return true;
    }

    
    public void printlevelhelper(TrieNode[] table, int level, ArrayList<Character> arrlevel)
    {
    	if(level == 1) {
    		for(int i=0; i<126; i++)
        	{
        		if(table[i] != null)
        			{
        			if(table[i].ch != 32)
        			arrlevel.add((char) table[i].ch);
//        				System.out.print((char) table[i].ch+ " ");
        			}
        	}
    	}
    	else {
	    			for(int i=0; i<126; i++)
	            	{
	            		if(table[i] != null)
	            		{
	            			TrieNode[] table2 = table[i].table;
	            			if(table2 != null)
	            			printlevelhelper(table2, level-1, arrlevel);
	            		}
	            	}
    		}
    	}
    
    @Override
    public void printLevel(int level) {
    	TrieNode[] table = root;
    	ArrayList<Character> arrlevel = new ArrayList<Character> ();
    	printlevelhelper(table, level, arrlevel);
    	int n = arrlevel.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if ((int) arrlevel.get(j) > (int) arrlevel.get(j+1)) 
                {  
                    char temp = arrlevel.get(j); 
                    arrlevel.set(j, arrlevel.get(j+1)); 
                    arrlevel.set(j+1, temp); 
                } 
        System.out.print("Level "+ level+": ");
        for(int i=0; i<arrlevel.size()-1; i++)
        {
        	System.out.print(arrlevel.get(i) + ",");
        }
        System.out.println(arrlevel.get(arrlevel.size()-1));
    }

    @Override
    public void print() {
    	int max = 0;
    	for(int i=0; i<maxheight.size(); i++)
    	{
    		if(max < maxheight.get(i))
    			max = maxheight.get(i);
    	}
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	int j=0;
    	for(j=0; j<max; j++)
    	{
    		this.printLevel(j+1);
    	}
    	System.out.println("Level "+(j+1) + ": ");
    	System.out.println("-------------");
    }
}