package librarysimulation;
import java.util.*;
/**
 * outputs classification, whether it's a check in or check out,
 * the classification after the check in or check out,
 *  and the status of the book.
 * Coursework 1 Year 1 Programming 2014
 * @author James Vine - 100022010
 */
public class LibrarySimulation 
{

    public static void main(String[] args) 
    {
        String[] events = runSimulation( generateBookStock(),20);
        for(int i = 0; i < events.length; i ++)
        {
            System.out.println(events[i]);
        }
    }
    
/**
* A method to generate a collection of LibraryBook objects to use as
* test data in your simulation
* @return      an array of LibraryBook objects
*
*/
public static LibraryBook [] generateBookStock()
{
   String [] authorsList = {"Lewis and Loftus",  "Mitrani",
                   "Goodrich","Lippman", "Gross", "Baase",
                   "Maclane", "Dahlquist", "Stimson", "Knuth",
                   "Hahn", "Cormen and Leiserson",
                   "Menzes", "Garey and Johnson"};
   String [] titlesList = {"Java Software Solutions", "Simulation",
                   "Data Structures", "C++ Primer", "Graph Theory",
                   "Computer Algorithms", "Algebra",
                   "Numerical Methods", "Cryptography",
                   "Semi-Numerical Algorithms",
                   "Essential MATLAB", "Introduction to Algorithms",
                   "Handbook of Applied Cryptography",
                   "Computers and Intractability"};
   int [] pagesList = {832, 185, 695, 614, 586, 685, 590, 573, 475,
                       685, 301, 1175, 820, 338};
   int n = authorsList.length;
   LibraryBook [] bookStock = new LibraryBook[n];
       for(int i = 0; i < n; i++)
       {
           bookStock[i] = new LibraryBook(authorsList[i],
                                          titlesList[i], pagesList[i]);
       }
//       set library classification for half of the LIbraryBooks
       for(int i = 0; i < n; i=i+2)
       {
           bookStock[i].setClassification("QA" + (99 - i));
       }
//        set approx. two thirds of LIbraryBooks in test data as
//        lending runList
       for(int i = 0; i < 2*n/3; i++)
           bookStock[i].setAsForLending();
//        set approx. one third of LibraryBooks in test data as
//        reference-only
       for(int i = 2*n/3; i < n; i++)
           bookStock[i].setAsReferenceOnly();
       return bookStock;
}

/**
* A method to derive the type of Event depending on the books
* different variables.
* @return      the type of book classification
*
*/
public static String deriveEvent(LibraryBook book, boolean checkIn, 
                                 String classification)
{
    System.out.println(book.classification + " " + checkIn 
                       + " " + classification + " " + book.status);
    if(book.classification==null)
    {
        book.setClassification(classification);
        return "BOOK IS CLASSIFIED";
    }
    else if(book.status==LibraryBook.BookStatus.REFERENCE_ONLY)
    {
        return "REFERENCE ONLY BOOK";
    }
    else if(book.status==LibraryBook.BookStatus.AVAILABLE_FOR_LENDING &&
            !checkIn)
    {
        return "BOOK IS LOANED OUT";
    }
    else if(book.status==LibraryBook.BookStatus.ON_LOAN &&
            !checkIn)
    {
        if(book.reserveBook())
        {
          return "RESERVATION PLACED FOR ON LOAN BOOK";
        }
        else
        {
            return "BOOK IS ON LOAN BUT CANNOT BE RESERVED";
        }
    }
    else if(checkIn)
    {
//        coursework sheet didn't mention
//        what happens if book is available and returned?
        return "BOOK IS RETURNED";
    }
    throw new Error("Unable to determine event type");
}
 
/**
* @param bookStock
* @param numberOfEvents
* @return

the stock of LibraryBooks in the library
the size of the events table to be generated
table of events generated during the simulation
*/
 
public static String[] runSimulation(LibraryBook[] bookStock,
                                                   int numberOfEvents)
{
    String[] runList = new String[numberOfEvents];

    for(int i = 0; i < numberOfEvents; i ++)
    {
       Random eventChoice = new Random();
//       0 = check in, 1 = check out
       int randomNum = eventChoice.nextInt(2);
       Random bookChoice = new Random();
       int randomBookNum = bookChoice.nextInt(bookStock.length-1);
       String eventString = deriveEvent(bookStock[randomBookNum], 
                                        randomNum == 0, "QA" 
                                        + randomBookNum);
       runList[i] = Integer.toString(i) + " " 
                    + Integer.toString(randomBookNum) + " "
                    + bookStock[randomBookNum].classification + " "
                    + eventString;  
       System.out.println(bookStock[randomBookNum]);
    }
    return runList; 
}
 
}
