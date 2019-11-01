package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

import Trie.Person;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {

//	List<String> letters = new ArrayList<String>();
	public List<E> mobnos = new ArrayList<E>();
	public String name;
	public boolean isRed;
	public E p;
	RedBlackNode<T, E> leftChild;
	RedBlackNode<T, E> rightChild;
	RedBlackNode<T, E> parent;
//	RedBlackNode<T, E> uncle;
	RedBlackNode (E p, RedBlackNode<T, E> parent)//, RedBlackNode<T, E> uncle)
	{
		this.p = p;
		if(p != null)
		mobnos.add(p);
		this.name = name;
		this.parent = parent;
//		this.uncle = uncle;
		isRed = true;
	}
    @Override
    public E getValue() {
        return p;
    }

    @Override
    public List<E> getValues() {
    	if(mobnos.size() == 0)
    	{
    		return null;
    	}
        return (List<E>) mobnos;
    }
}
