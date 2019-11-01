package RedBlack;

import Trie.Person;

public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	static RedBlackNode root;// = newRedBlackNode()
	
	public static void LLrotation(RedBlackNode b, RedBlackNode p, RedBlackNode g)
	{
//		System.out.println("LLrotation.");
		if(g!= root) {
			if(g.parent.leftChild == g)
			{
				g.parent.leftChild = p;
			}
			else g.parent.rightChild = p;
			p.parent = g.parent;
		}
		else {
			root = p;
			p.parent = null;
		}
		if(p.rightChild!= null)
		{	g.leftChild = p.rightChild;
			g.leftChild.parent = g;
		}
		g.parent = p;
		p.rightChild = g;
		p.isRed = false;
		b.isRed = true;
		g.isRed = true;
//		b.uncle = p.uncle;
//		RedBlackNode temp = p.uncle;
//		p.uncle = g.uncle;
//		g.uncle = temp;
	}
	
	public static void RRrotation(RedBlackNode b, RedBlackNode p, RedBlackNode g)
	{
		
//		System.out.println("RRrotation.");
		if(g!= root) {
			if(g.parent.rightChild == g)
			{
				g.parent.rightChild = p;
			}
			else g.parent.leftChild = p;
			p.parent = g.parent;
		}
		else {
			root = p;
			p.parent = null;
		}
		if(p.leftChild!= null)
		{	g.rightChild = p.leftChild;
			g.rightChild.parent = g;
		}
		g.parent = p;
		p.leftChild = g;
		p.isRed = false;
		b.isRed = true;
		g.isRed = true;
		
//		System.out.println("RR rotation is going on.");
//		if(g.parent.leftChild == g)
//		{
//			g.parent.leftChild = p;
//		}
//		else g.parent.rightChild = p;
//		p.parent = g.parent;
//		g.rightChild = p.leftChild;
//		g.rightChild.parent = g;
//		g.parent = p;
//		p.leftChild = g;
				
	}
	
	public static void LRrotation(RedBlackNode b, RedBlackNode p, RedBlackNode g)
	{
//		System.out.println("LRrotation");
		if(g!= root) {
//			System.out.println("g is "+g.name);
			if(g.parent.rightChild == g)
			{
				g.parent.rightChild = b;
			}
			else g.parent.leftChild = b;
			b.parent = g.parent;
		}
		else {
			root = b;
			b.parent = null;
		}
		if(b.rightChild != null) {
			g.leftChild = b.rightChild;
			g.leftChild.parent = g;
			}
		if(b.leftChild != null)
		{
			p.rightChild = b.leftChild;
			p.rightChild.parent = p;
		}
		p.parent = b;
		b.leftChild = p;
		b.rightChild = g;
		g.parent = b;
//		b.isRed = false;
//		g.isRed = true;
//		p.isRed = true;
//		System.out.println("LR rotation is going on.");
//		if(g.parent.leftChild == g)
//		{
//			g.parent.leftChild = b;
//		}
//		else g.parent.rightChild = b;
//		b.parent = g.parent;--
//		g.leftChild = b.rightChild;--
//		g.leftChild.parent = g;--
//		p.rightChild = b.leftChild;--
//		p.parent = b;--
//		b.leftChild = p;
//		b.rightChild = g;--
//		g.parent = b;
//		p.rightChild.parent = p;--		
	}
	
	public static void RLrotation(RedBlackNode b, RedBlackNode p, RedBlackNode g)
	{
//		System.out.println("RLrotation");
		if(g!= root) {
			if(g.parent.leftChild == g)
			{
				g.parent.leftChild = b;
			}
			else g.parent.rightChild = b;
			b.parent = g.parent;
		}
		else {
			root = b;
			b.parent = null;
		}
		if(b.leftChild != null) {
			g.rightChild = b.leftChild;
			g.rightChild.parent = g;
			}
		if(b.rightChild != null)
		{
			p.leftChild = b.rightChild;
			p.leftChild.parent = p;
		}
		p.parent = b;
		b.rightChild = p;
		b.leftChild = g;
		g.parent = b;
		b.isRed = false;
		g.isRed = true;
		p.isRed = true;
//		System.out.println("RL rotation is going on.");
//		if(g.parent.leftChild == g)
//		{
//			g.parent.leftChild = b;
//		}
//		else g.parent.rightChild = b;
//		b.parent = g.parent;
//		g.rightChild = b.leftChild;
//		g.rightChild.parent = g;
//		p.leftChild = b.rightChild;
//		p.parent = b;
//		b.rightChild = p;
//		b.leftChild = g;
//		g.parent = b;
//		p.rightChild.parent = p;
	}	
	
    public void setting (RedBlackNode c)
    {
//    	System.out.println("setting of "+c.name);
    	if(c == root) return;
        if(c.parent.isRed == false)
        {
        	return;
        }
        else if(c.parent.isRed == true) //restructure krna pdega
        {
            if(c.parent.leftChild == c)
            {
                if(c.parent.parent.rightChild == c.parent)
                {
                    //rotate RL
                       			RedBlackNode grandpa = new RedBlackNode(null, null);
                                RedBlackNode cleft = new RedBlackNode(null, null);
                                RedBlackNode cright = new RedBlackNode(null, null);
                                RedBlackNode var = new RedBlackNode(null, null);
                                var = c.parent;
                                cleft = c.leftChild;
                                cright = c.rightChild;
                                grandpa = var.parent;
                                  c.rightChild = var;
                                c.leftChild = grandpa;
                                if(grandpa == root)
                                {
                                    root = c;
                                    c.parent = null;
                                }else{
                                    if(grandpa.parent.rightChild == grandpa)
                                    {
                                        grandpa.parent.rightChild = c;
                                    } else grandpa.parent.leftChild = c;
                                    c.parent = grandpa.parent;
                                }
                                var.parent = c;
                                grandpa.parent = c;
                                if(cright != null){
                                    var.leftChild = cright;
                                    cright.parent = var;
                                }
                                else var.leftChild = null;
                                if(cleft != null){
                                grandpa.rightChild = cleft;
                                cleft.parent = grandpa;
                                } else grandpa.rightChild = null;
                                c.isRed = true;
                                if(c == root) c.isRed = false;
                                var.isRed = false;
                                grandpa.isRed = false;
                        //new var1 = c;
                        if(c.isRed == true) setting(c);
                }
                else if (c.parent.parent.leftChild == c.parent)
                {
//                    rotate LL
                	RedBlackNode<T,E> g = new RedBlackNode(null, null);
                	g = c.parent.parent;
                	RedBlackNode<T,E> p = new RedBlackNode(null, null);
                	p = c.parent;
                	
//                	System.out.println("LLrotation.");
            		if(g!= root) {
            			if(g.parent.leftChild == g)
            			{
            				g.parent.leftChild = p;
            			}
            			else g.parent.rightChild = p;
            			p.parent = g.parent;
            		}
            		else {
            			root = p;
            			p.parent = null;
            		}
            		if(p.rightChild!= null)
            		{	g.leftChild = p.rightChild;
            			g.leftChild.parent = g;
            		}
            		g.parent = p;
            		p.rightChild = g;
            		if(root == p) p.isRed = false;
            		else p.isRed = true;
            		g.isRed = false;
            		c.isRed = false;
            		 if(p.isRed == true) setting(p);
                }
            }
            else if(c.parent.rightChild == c)
            {
                if(c.parent.parent.rightChild == c.parent)
                {                	
//                    rotate RR
                	RedBlackNode<T,E> g = new RedBlackNode(null, null);
                	g = c.parent.parent;
                	RedBlackNode<T,E> p = new RedBlackNode(null, null);
                	p = c.parent;
                	
//                	System.out.println("RRrotation.");
            		if(g!= root) {
            			if(g.parent.leftChild == g)
            			{
            				g.parent.leftChild = p;
            			}
            			else g.parent.rightChild = p;
            			p.parent = g.parent;
            		}
            		else {
            			root = p;
            			p.parent = null;
            		}
            		if(p.leftChild!= null)
            		{	g.rightChild = p.leftChild;
            			g.rightChild.parent = g;
            		}
            		g.parent = p;
            		p.leftChild = g;
            		if(root == p) p.isRed = false;
            		else p.isRed = true;
            		g.isRed = false;
            		c.isRed = false;
            		 if(p.isRed == true) setting(p);
//                        new var1 = c.parent;
//                    setting (var1);
                }
                else if(c.parent.parent.leftChild == c.parent)
                {
//                    rotate LR
                	RedBlackNode grandpa = new RedBlackNode(null, null);
                    RedBlackNode cleft = new RedBlackNode(null, null);
                    RedBlackNode cright = new RedBlackNode(null, null);
                    RedBlackNode var = new RedBlackNode(null, null);
                    var = c.parent;
                    cleft = c.leftChild;
                    cright = c.rightChild;
                    grandpa = var.parent;
                      c.leftChild = var;
                    c.rightChild = grandpa;
                    if(grandpa == root)
                    {
                        root = c;
                        c.parent = null;
                    }else{
                        if(grandpa.parent.leftChild == grandpa)
                        {
                            grandpa.parent.leftChild = c;
                        } else grandpa.parent.rightChild = c;
                        c.parent = grandpa.parent;
                    }
                    var.parent = c;
                    grandpa.parent = c;
                    if(cleft != null){
                        var.rightChild = cleft;
                        cleft.parent = var;
                    }
                    else var.rightChild = null;
                    if(cright != null){
                    grandpa.leftChild = cright;
                    cright.parent = grandpa;
                    } else grandpa.leftChild = null;
                    c.isRed = true;
                    if(c == root) c.isRed = false;
                    var.isRed = false;
                    grandpa.isRed = false;
            //new var1 = c;
            if(c.isRed == true) setting(c);
                	
//                        new var1 = c;
//                    setting (var1);
                }
            }
        }
    }

    public void swapColor(RedBlackNode g, RedBlackNode p)
    {
        int a=0;
        if(g.isRed) a = 1;
        g.isRed = p.isRed;
        if(a == 1)
        p.isRed = true;
        else p.isRed = false;
    }

    public void insert(T key, E value)
    {
        String name = (String) key;
        if (root == null)
        {
            RedBlackNode<T,E> n = new RedBlackNode (value, null);
            n.name = name;
            root = n;
            root.isRed = false;
        }
        else {
            RedBlackNode<T,E> var = root;
            while(true)
            {
                if(var.name.compareTo(name) == 0) //list me add
                {
                    var.mobnos.add((E) value);
                    break;
                    //no recoloring no restructure
                }
                else if((var.name).compareTo(name) > 0) //going to left side
                {
                    if(var.leftChild != null)
                    {
                        var = var.leftChild;
                    }
                    else if(var.leftChild == null)
                    {
                        RedBlackNode<T,E> z = new RedBlackNode (value, var);
                        z.name = name;
                        var.leftChild = z;
                        //add to ho gyaa
                        //ab check kro ki sirf recoloring se kaam bn rha hai kya
                        if(var.isRed == false)
                        {
                        	
                            break;
                        }
                        
                            if(var.parent.leftChild == var) //left left wala case start
                            {
                       
                                RedBlackNode uncle;
                                if(var.parent.rightChild == null ||(var.parent.rightChild != null && var.parent.rightChild.isRed  == false)) //uncle is null
                                {
                                    //LLrotation
                                	RedBlackNode<T,E> g = new RedBlackNode(null, null);
                                	g = var.parent;
                                	RedBlackNode<T,E> p = new RedBlackNode(null, null);
                                	p = var;
                                	
//                                	System.out.println("LLrotation.");
                            		if(g!= root) {
                            			if(g.parent.leftChild == g)
                            			{
                            				g.parent.leftChild = p;
                            			}
                            			else g.parent.rightChild = p;
                            			p.parent = g.parent;
                            		}
                            		else {
                            			root = p;
                            			p.parent = null;
                            		}
                            		if(p.rightChild!= null)
                            		{	g.leftChild = p.rightChild;
                            			g.leftChild.parent = g;
                            		}
                            		g.parent = p;
                            		p.rightChild = g;
                                    swapColor(var.parent, var);
                                    
                                    //check and do for next violation
                                }
                                else if(var.parent.rightChild != null) //uncle is not null
                                {
                                    uncle = var.parent.rightChild;
                                    if(uncle.isRed == true) //case 2 // sirf recoloring se kaam bn gyaa
                                    {
                                        if(var.parent.isRed) //dadaji ka color change
                                        var.parent.isRed = false;
                                        else var.parent.isRed = true;
                                        if(var.isRed) // papa ka color change
                                        var.isRed = false;
                                        else var.isRed = true;
                                        uncle.isRed = false; // uncle ka color change
                                    }
                                }
                            }
                            else if(var.parent.rightChild == var)
                            {
                            	RedBlackNode uncle;
                            	if(var.parent.leftChild == null ||(var.parent.leftChild != null && var.parent.leftChild.isRed  == false)) //uncle is null
                            	{
                            		 RedBlackNode grandpa = new RedBlackNode(null ,null);
                                     RedBlackNode zleft = new RedBlackNode(null, null);
                                     RedBlackNode zright = new RedBlackNode(null, null);
                                     zleft = z.leftChild;
                                     zright = z.rightChild;
                                     grandpa = var.parent;
                                       z.rightChild = var;
                                     z.leftChild = grandpa;
                                     if(grandpa == root)
                                     {
                                         root = z;
                                         z.parent = null;
                                     }else{
                                         if(grandpa.parent.rightChild == grandpa)
                                         {
                                             grandpa.parent.rightChild = z;
                                         } else grandpa.parent.leftChild = z;
                                         z.parent = grandpa.parent;
                                     }
                                     var.parent = z;
                                     grandpa.parent = z;
                                     if(zright != null){
                                         var.leftChild = zleft;
                                         zright.parent = var;
//                                         System.out.println("yha nhii aana chahiye");
                                     }
                                     else var.leftChild = null;
                                     if(zleft != null){
                                     grandpa.rightChild = zleft;
                                     zleft.parent = grandpa;
                                     } else grandpa.rightChild = null;
                                     z.isRed = true;
                                     if(z == root) z.isRed = false;
                                     var.isRed = false;
                                     
                                     grandpa.isRed = false;
                                     var.leftChild = null;
//                                     System.out.println("Manoj kumar ka jaadu"+z.name + z.isRed);
                                     setting(z);
                                     
                                     break;
                            	}
                            }
                    }
                    
                }
                else if ((var.name).compareTo(name) < 0) //going to right side
                {
                    if(var.rightChild != null)
                    {
                        var = var.rightChild;
                        //continue with the next loop
                    }
                    else if(var.rightChild == null)
                    {
                        RedBlackNode<T,E> z = new RedBlackNode (value, var);
                        z.name = name;
                        var.rightChild = z;
                        //add to ho gyaa
                        //ab check kro ki sirf recoloring se kaam bn rha hai kya
                        if(var.isRed == false)
                        {
                            //chilll
                            //insert ho gyaa
                            break; //krke bahr nikalo
                        }
                        if(var.parent.leftChild == var)  //left right wala case //yha parent bhi red hai
                        {
                            RedBlackNode uncle;
                            if(var.parent.rightChild == null || (var.parent.rightChild != null && var.parent.rightChild.isRed == false))
                            {
//                            	System.out.println("Manoj kumar ka jaadu"+var.name+" rightchild "+var.rightChild.name);
                                //recoloring se kaam nhii chalega
                                //restructure krna pdega
                                //LRrotation
                                RedBlackNode grandpa = new RedBlackNode(null ,null);
                                RedBlackNode zleft = new RedBlackNode(null, null);
                                RedBlackNode zright = new RedBlackNode(null, null);
                                zleft = z.leftChild;
                                zright = z.rightChild;
                                grandpa = var.parent;
                                  z.leftChild = var;
                                z.rightChild = grandpa;
                                if(grandpa == root)
                                {
                                    root = z;
                                    z.parent = null;
                                }else{
                                    if(grandpa.parent.leftChild == grandpa)
                                    {
                                        grandpa.parent.leftChild = z;
                                    } else grandpa.parent.rightChild = z;
                                    z.parent = grandpa.parent;
                                }
                                var.parent = z;
                                grandpa.parent = z;
                                if(zleft != null){
                                    var.rightChild = zleft;
                                    zleft.parent = var;
//                                    System.out.println("yha nhii aana chahiye");
                                }
                                else var.rightChild = null;
                                if(zright != null){
                                grandpa.leftChild = zright;
                                zright.parent = grandpa;
                                } else grandpa.leftChild = null;
                                z.isRed = true;
                                if(z == root) z.isRed = false;
                                var.isRed = false;
                                grandpa.isRed = false;
                                var.rightChild = null;
                                setting(z);
//                                System.out.println("Manoj kumar ka jaadu"+var.name);
                                break;
                            }
                            else if(var.parent.rightChild != null)
                            {
                                uncle = var.parent.rightChild;
                                if(uncle.isRed == true)
                                {
                                    uncle.isRed = false;
                                    var.isRed = false;
                                    uncle.parent.isRed = true;
                                    setting(uncle.parent);
                                }
                            }
                            
                        }
                        else if(var.parent.rightChild == var) // right - right wala case
                        {
//                        	setting(z);
                        	
//                        	System.out.println("yha aao kabhi hum bhi dekhe aapka kaam");
                        	//wrong
                            RedBlackNode uncle;
                            if(var.parent.leftChild == null || (var.parent.leftChild != null && var.parent.leftChild.isRed == false)) //restructure krna pdega
                            {
//                            	System.out.println("yha aa gyaa");
                                //RR rotation
                                RedBlackNode grandpa = new RedBlackNode(null, null);
                                grandpa = var.parent;
                                RedBlackNode varleft = new RedBlackNode(null, null);
                                varleft = var.leftChild;
                                if(grandpa == root)
                                {
                                    root = var;
                                    var.parent = null;
                                }
                                else
                                {
                                    if(grandpa.parent.leftChild == grandpa)
                                    {
                                        grandpa.parent.leftChild = var;
                                    }else grandpa.parent.rightChild = var;
                                    var.parent = grandpa.parent;
                                }
                                grandpa.rightChild = varleft;
                                grandpa.parent = var;
                                var.leftChild = grandpa;
                                    //recoloring
                                grandpa.isRed = false;
                                var.isRed = true;
                                z.isRed = false;
                                setting(var);
                                break;
                            }
                            else if(var.parent.leftChild != null)
                            {
                            	uncle = var.parent.leftChild;
                                if(uncle.isRed == true)
                                {
                                    uncle.isRed = false;
                                    var.isRed = false;
                                    uncle.parent.isRed = true;
                                    setting(uncle.parent);
                                }
                            }
                            
                        }
                       break; 
                    }
                    
                }
            }
        }
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	String name = (String) key;
    	RedBlackNode<T,E> var = root;
    	RedBlackNode<T,E> nullnode = new RedBlackNode(null, null);
//    	if(root != null) System.out.println(root);
//    	return root;
    	if(root == null)
    	{
//    		if(root.getValue() instanceof Person)
//    		{
//    			RedBlackNode<T,E> n = new RedBlackNode(null, null);
//    			return n;
//    		}
    		return nullnode;
    	}
    		while(true)
    	{
    		if((var.name).compareTo(name) == 0)
    		{
    			return var;
    		}
    		else if((var.name).compareTo(name) > 0)// var badii hai name se
    		{
    			if(var.leftChild != null)
    			{
	    			var =  var.leftChild;
    			}
    			else
    			{
//    				if(root.getValue() instanceof Person)
//    	    		{
//    	    			RedBlackNode<T,E> n = new RedBlackNode(null, null);
//    	    			return n;
//    	    		}
    				return nullnode;
    			}
    		}
    		else if((var.name).compareTo(name) < 0)// var chhota hai name se
    		{
    			if(var.rightChild != null) {
    				var =  var.rightChild;
    			}
    			else
    			{
//    				if(root.getValue() instanceof Person)
//    	    		{
//    	    			RedBlackNode<T,E> n = new RedBlackNode(null, null);
//    	    			return n;
//    	    		}
    				return nullnode;
    			}
    		}
    	}
    }
    
    public void showMe()
    {
    	showMeHelper(root);
    }
    public void showMeHelper(RedBlackNode root)
    {
    	if(root.leftChild != null) {
    	System.out.println("left child of "+root.name+root.isRed+" is : "+root.leftChild.name+root.leftChild.isRed);
    	showMeHelper(root.leftChild);}
    	if(root.rightChild != null) {
    		System.out.println("right child of "+root.name+root.isRed+" is : "+root.rightChild.name+root.rightChild.isRed);
    		showMeHelper(root.rightChild);
    	}
    }

}