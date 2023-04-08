import java.util.*;


class RbtRide
{
    int rideNumber;
    int rideCost;
    int tripDuration;
    // COLOR 0->BLACK,1->RED
    int color;
    RbtRide parent;
    RbtRide left;
    RbtRide right;

    RbtRide(){}

    RbtRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;

    }
}


public class Rbt {

    private static List<RbtRide> rbtree=new ArrayList<>();
    private static int heapSize=0;
    private static RbtRide root=null;


    private static void insertRide(int rideNumber,int rideCost,int tripDuration)
    {   
        
        bstInsert(rideNumber,rideCost,tripDuration);
        // System.out.println(0);
        root.color=0;
    }

    private static void bstInsert(int rideNumber,int rideCost,int tripDuration)
    {   
        // INSERT THE NEW NODE AS A RED NODE BY DEFAULT
        RbtRide node=new RbtRide(rideNumber,rideCost,tripDuration);
        node.left=null;
        node.right=null;
        node.parent=null;
        node.color=1;

        RbtRide y=null;
        RbtRide x=root;

        while(x!=null)
        {   
            y=x;
            if(x.rideNumber>node.rideNumber)
            {
                x=x.left;
            }
            else
            {
                x=x.right;
            }
            
        }

        node.parent=y;

        if(y==null) 
        {
            root=node;
        }
        else if(node.rideNumber>y.rideNumber)
        {
            y.right=node;
        }
        else
        {
            y.left=node;
        }

        if(node.parent==null)
        {
            node.color=0;
            return;
        }
        if(node.parent.parent==null) return;
        
        insertColorBalance(node);

        root.color=0;
        
    }

    private static void insertColorBalance(RbtRide x)
    {
        RbtRide d=null;
        while(x.parent.color==1)
        {
            if(x.parent==x.parent.parent.right)
            {   
                d=x.parent.parent.left;
                if(d==null) 
                {   
                    rotateRR(x.parent.parent);
                    x.parent.color=0;
                    x.parent.left.color=1;
                    return;
                }
                if(d.color==1)
                {   

                    x.parent.color=0;
                    d.color=0;
                    x.parent.parent.color=1;
                    x=x.parent.parent;
                }
                else
                {
                    if(x==x.parent.left)
                    {   
                        x=x.parent;
                        rotateLL(x);
                    }
                    x.parent.color=1;
                    x.parent.parent.color=1;
                    rotateRR(x.parent.parent);
                }
            }
            else
            {   
                d=x.parent.parent.right;
                if(d==null) 
                {
                    rotateLL(x.parent.parent);
                    x.parent.color=0;
                    x.parent.right.color=1;
                    return;
                }
                if(d.color==1)
                {
                    x.parent.color=0;
                    d.color=0;
                    x.parent.parent.color=1;
                    x=x.parent.parent;
                }
                else
                {
                    if(x==x.parent.right)
                    {
                        x=x.parent;
                        rotateRR(x);
                    }
                    x.parent.color=1;
                    x.parent.parent.color=1;
                    rotateLL(x.parent.parent);
                }
            }

            if(x==root) break;
        }  

    }

    private static void bstDelete(int rideNumber)
    {
        // RbtRide y=null;
        RbtRide curr=root;

        while(curr!=null)
        {   
            // y=curr;
            if(curr.rideNumber==rideNumber) break;
            if(curr.rideNumber>rideNumber)
            {
                curr=curr.left;
            }
            else
            {
                curr=curr.right;
            }
            
        }
        if(curr==null) return;

        RbtRide z=curr;
        RbtRide x,y;

        y=z;
        int yOg=y.color;
        if(z.left==null)
        {
            x=z.right;

            RbtRide u=z,v=z.right;
            switchNodes(u,v);
        }
        else if(z.right==null)
        {
            x=z.left;
            RbtRide u=z,v=z.left;
            switchNodes(u,v);

        }
        else
        {   
            RbtRide nodeReplacedBy=z.right;
            while(nodeReplacedBy.left!=null)
            {
                nodeReplacedBy=nodeReplacedBy.left;
            }
            y=nodeReplacedBy;
            yOg=y.color;
            x=y.right;
            
            if(y!=null && y.parent!=null && x!=null && x.parent!=null)
                if(y.parent==z) x.parent=y;
            else
            {
                RbtRide u=z,v=y;
                switchNodes(u,v);

                if(y.right!=null)
                {
                    y.right = z.right;
				    y.right.parent = y;
                }

            }

            switchNodes(z,y);
            y.left = z.left;
			y.left.parent = y;
			y.color = z.color;

        }

        if (yOg == 0){
			deleteColorBalance(x);
		}

        // // IF NODE HAS TWO CHILDREN WE TAKE THE HIGHEST FROM LEFT SUBTREE

        // RbtRide nodeReplacedBy=x.left;
        // while(nodeReplacedBy.left!=null && nodeReplacedBy.right!=null)
        // {
        //     nodeReplacedBy=nodeReplacedBy.right;
        // }
        // // RbtRide nodeDeleted=new RbtRide(x.rideNumber,x.rideCost,x.tripDuration);
        
        
        // x.rideNumber=nodeReplacedBy.rideNumber;
        // x.rideCost=nodeReplacedBy.rideCost;
        // x.tripDuration=nodeReplacedBy.tripDuration;
        
        // // System.out.println(1);
        
        // standardBstDelete(nodeDeleted,nodeReplacedBy);

        

        // // if(highest.left!=null && highest.right==null)
        // // {
        // //     if(highest.parent.left==highest) 
        // //     {   
        // //         highest.parent.left=highest.left;
        // //     }
            
        // // }

        // // if(highest.left==null && highest.right==null)
        // // {
        // //     if(highest.parent.right==highest) highest.parent.right=null;
        // //     else highest.parent.left=null;
        // // }
        
    }

    private static void switchNodes(RbtRide u,RbtRide v)
    {
        if (u.parent == null) {
			root = v;
		} else if (u == u.parent.left){
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
        if(v!=null && u!=null)
		    v.parent = u.parent;
    }

    // private static void standardBstDelete(RbtRide nodeDeleted,RbtRide nodeReplacedBy)
    // {   
    //     // System.out.println(nodeDeleted.rideNumber);
    //     // System.out.println(nodeReplacedBy.rideNumber);

    //     RbtRide x=nodeDeleted;
    //     // IF NODE TO BE DELETED IS LEAF
    //     if(x.left==null && x.right==null)
    //     {   
    //         // System.out.println(1);
    //         if(x.parent.right==x) x.parent.right=null;
    //         else x.parent.left=null;
    //         // System.out.println(1);
    //         if(x.color==1) return;
    //         deleteColorBalance(nodeDeleted,nodeReplacedBy);
    //         return;
    //     } 

    //     // IF NODE TO BE DELETED HAS ONLY ONE CHILD
    //     if(x.left!=null && x.right==null)
    //     {
    //         if(x.parent.left==x) x.parent.left=x.left;
    //         else x.parent.right=x.left;
    //         if(x.color==1) return;
    //         deleteColorBalance(nodeDeleted,nodeReplacedBy);
    //     }
    //     else if(x.left==null && x.right!=null)
    //     {
    //         if(x.parent.left==x) x.parent.left=x.right;
    //         else x.parent.right=x.right;
    //         if(x.color==1) return;
    //         deleteColorBalance(nodeDeleted,nodeReplacedBy);

    //     }
    // }

    private static void deleteColorBalance(RbtRide x)
    {   
        
        RbtRide s;
		while (x != root && x.color == 0) {
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rotateRR(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.right.color == 0) {
						// case 3.3
						s.left.color = 0;
						s.color = 1;
						rotateLL(s);
						s = x.parent.right;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.right.color = 0;
					rotateRR(x.parent);
					x = root;
				}
			} else {
				s = x.parent.left;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rotateLL(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.left.color == 0) {
						// case 3.3
						s.right.color = 0;
						s.color = 1;
						rotateLL(s);
						s = x.parent.left;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.left.color = 0;
					rotateLL(x.parent);
					x = root;
				}
			} 
		}
		x.color = 0;


    }

    private static void rotateRR(RbtRide y)
    {   
        RbtRide x=y.right;
        y.right=x.left;
        if(y.left!=null) x.left.parent=y;
        x.parent=y.parent;
        if(y.parent==null) root=x;
        else if(y==y.parent.left) y.parent.left=x;
        else y.parent.right=x;
        x.left=y;
        y.parent=x;
    }
    private static void rotateLL(RbtRide y)
    {
        RbtRide x=y.left;
        y.left=x.right;
        if(y.right!=null) x.right.parent=y;
        x.parent=y.parent;
        if(y.parent==null) root=x;
        else if(y==y.parent.right) y.parent.right=x;
        else y.parent.left=x;
        x.right=y;
        y.parent=x;

    }

    private static void printElements(RbtRide curr)
    {
        if(curr==null) return;
        printElements(curr.left);
        System.out.println(curr.rideNumber+" "+curr.rideCost+" "+curr.tripDuration);
        System.out.println(curr.color);
        // if(curr.parent!=null)
        //     System.out.println(curr.parent.rideNumber+" "+curr.parent.rideCost+" "+curr.parent.tripDuration);
        printElements(curr.right);
    }


    public static void main(String[] args) {

        insertRide(9,76,31);
        // insertRide(25, 98, 46);
        insertRide(42,17,89);
        insertRide(53,97,22); 
        insertRide(96,28,82);
        insertRide(73,28,56);
        insertRide(20,49,59);

        // insertRide(62,7,10);
        // bstDelete(42);
        printElements(root);
        // System.out.println(root.right.right.rideNumber);
        
    }
    
}
