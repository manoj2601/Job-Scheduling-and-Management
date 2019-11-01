package Trie;

import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
	int ch;
	boolean isEndofWord;
	TrieNode[] table;
	public T p;
	TrieNode parent;
	TrieNode[] prevtable;
	TrieNode(int ch, boolean isEndofWord, T p, TrieNode[] table, TrieNode parent, TrieNode[] prevtable)
	{
		this.ch = ch;
		this.isEndofWord = isEndofWord;
		this.p = p;
		this.table = table;
		this.parent = parent;
		this.prevtable= prevtable;
	}
	
	public TrieNode[] gettable()
	{
		return table;
	}
    @Override
    public T getValue() {
    	if(this.isEndofWord()) {
        return p;
    	}
    	else
    		return null;
    }
    
    public int getchar()
    {
    	return ch;
    }
    
    public boolean isEndofWord()
    {
    	return isEndofWord;
    }
    
    public TrieNode parent()
    {
    	return parent;
    }
    
    public String toString()
    {
    	if(this.isEndofWord()) {
        	String s = "[Name: "+((Person) p).getName()+", Phone="+((Person) p).getphone()+"]";
            return s;
        	}
        	else
        		return null;
    }

}