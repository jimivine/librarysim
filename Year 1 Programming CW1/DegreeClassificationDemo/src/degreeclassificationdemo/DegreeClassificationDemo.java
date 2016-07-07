package degreeclassificationdemo;
import java.util.*;
/**
 * input mark, returns classification, returns the min and max for
 * the classification.
 * @author James Vine - 100022010
 */
public class DegreeClassificationDemo 
{

    public enum Classification //5 enum types
    {
        Fail,III,II2,II1,I
    }
    
    public static void main(String[] args) //main method
    {
        int mark;
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter result:                      "); 
        mark = scan.nextInt();
        Classification classification = findClassification(mark);
        System.out.println("Classification:                    " 
                           + classification ); 
        int[] minMax = markRange(classification);
        System.out.println("The minimum and maximum marks are: " 
                           + minMax[0] + " and " + minMax[1]);
    }
    
    public static Classification  findClassification (int mark)
    {
        if(mark <= 39)
        {
            return Classification.Fail;
        }
        else if(mark <= 49)
        {
            return Classification.III;
        }
        else if(mark <= 59)
        {
            return Classification.II2;
        }
        else if(mark <= 69)
        {
            return Classification.II1;
        }
        
        return Classification.I;
                
    }
    
    public static int [] markRange(Classification degreeClass)
    {
        int[] range;
        range = new int[2];
        switch (degreeClass) 
        {
            case Fail:
                range[0]=0;   //array already initialises as zero
                range[1]=39;
                break;
                
            case III:
                range[0]=40;
                range[1]=49;
                break;
            
            case II2:
                range[0]=50;
                range[1]=59;
                break;
                
            case II1:
                range[0]=60;
                range[1]=69;
                break;
                
            case I:
                range[0]=70;
                range[1]=100;
                break; 
                
            default: //throws an error but should never happen
                throw new Error("invalid classification type");
                    
         }      
                
        return range;
    }
}
