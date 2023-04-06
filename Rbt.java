import java.util.*;

import javax.swing.plaf.synth.SynthStyle;

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
        RbtRide y=null;
        RbtRide x=root;

        while(x!=null)
        {   
            y=x;
            if(x.rideNumber==rideNumber) break;
            if(x.rideNumber>rideNumber)
            {
                x=x.left;
            }
            else
            {
                x=x.right;
            }
            
        }
        if(x==null) return;

        // 
        if(x.left==null || x.right==null)
        {
            standardBstDelete(x);
            deleteColorBalance();
            return;
        }
        // 

        // IF NODE HAS TWO CHILDREN WE TAKE THE HIGHEST FROM LEFT SUBTREE

        RbtRide highest=x;
        while(highest.left!=null && highest.right!=null)
        {
            highest=highest.right;
        }
        x.rideNumber=highest.rideNumber;
        x.rideCost=highest.rideCost;
        x.tripDuration=highest.tripDuration;

        standardBstDelete(highest);

        // if(highest.left!=null && highest.right==null)
        // {
        //     if(highest.parent.left==highest) 
        //     {   
        //         highest.parent.left=highest.left;
        //     }
            
        // }

        // if(highest.left==null && highest.right==null)
        // {
        //     if(highest.parent.right==highest) highest.parent.right=null;
        //     else highest.parent.left=null;
        // }
        
    }

    private static void standardBstDelete(RbtRide node)
    {   
        RbtRide x=node;
        // IF NODE TO BE DELETED IS LEAF
        if(x.left==null && x.right==null)
        {
            if(x.parent.right==x) x.parent.right=null;
            else x.parent.left=null;
            
            if(x.color==1) return;
            deleteColorBalance();
            return;
        } 

        // IF NODE TO BE DELETED HAS ONLY ONE CHILD
        if(x.left!=null && x.right==null)
        {
            if(x.parent.left==x) x.parent.left=x.left;
            else x.parent.right=x.left;
        }
        else if(x.left==null && x.right!=null)
        {
            if(x.parent.left==x) x.parent.left=x.right;
            else x.parent.right=x.right;
            if(x.color==1) return;
            deleteColorBalance();

        }
    }

    private static void deleteColorBalance()
    {

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

    private static void insertDataHelper()
    {

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
        insertRide(25, 98, 46);
        insertRide(42,17,89);
        insertRide(53,97,22); 
        insertRide(96,28,82);
        insertRide(73,28,56);
        insertRide(20,49,59);
        insertRide(62,7,10);
        // bstDelete(73);
        printElements(root);
        // System.out.println(root.right.right.rideNumber);
        // System.out.println(root.right.rideNumber);
        
    }
    
}
