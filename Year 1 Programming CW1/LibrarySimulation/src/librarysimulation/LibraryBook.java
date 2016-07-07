package librarysimulation;
/**
 * LibraryBook class for LibrarySimulation
 * @author James Vine - 100022010
 */
public class LibraryBook
{
    private String author;
    private String title;
    private int pages;
    String classification;
    int borrowed;
    BookStatus status;
    int reservations;
    
    public enum BookStatus
    {
        REFERENCE_ONLY, ON_LOAN, AVAILABLE_FOR_LENDING
    }
    
     /**
     * Constructor with arguments for LibraryBook’s author(s), title
     * and number of pages
     * @param bookAuthor    the names of author(s) of this LibraryBook
     * @param bookTitle     the title of this LibraryBook
     * @param bookPages     the number of pages of this LibraryBook
     */
     public LibraryBook(String bookAuthor, String bookTitle, 
                                           int bookPages)
     {
         author = bookAuthor;
         title = bookTitle;
         pages = bookPages;
         
//       variables below already set to these by default
         classification = null;
         borrowed = 0;
         status = BookStatus.REFERENCE_ONLY;
         reservations = 0;
     }
    
    String getAuthor()
    {
        return author;
    }
     
    String getTitle()
    {
        return title;
    }
    
    int getPages()
    {
        return pages;
    }
    
    String getClassification()
    {
        return classification;
    }
     /**
     * A  method to reset Library classification of this LibraryBook
    ￼* @param bookClass
     * @return the proposed new classification
                            true,   if the proposed new classification
                                    has at least 3 characters to which 
                                    the Library classification 
                                    is reset.
                            false,  otherwise. 
     */
    public boolean setClassification(String bookClass)
    {
        classification = bookClass;
        if (classification.length() >3)
        {
            return false;
        }
        return true;
    }
     
    public boolean isAvailable(BookStatus status)
    {
        if(status == BookStatus.REFERENCE_ONLY)
        {
            return false;
        }
        else if(status == BookStatus.ON_LOAN)
        {
            return false;
        }
        return true;
    } 
    
    void setAsReferenceOnly()
    {
        status = BookStatus.REFERENCE_ONLY;
    }
    
    void setAsForLending()
    {
        status = BookStatus.AVAILABLE_FOR_LENDING;
    }
            
    BookStatus getStatus()
    {
        return status;
    }
    
    void setReservations(int numReservations)
    {
        if (numReservations > 3)
        {
            throw new Error("Number of reservations greater than 3");
        }
        reservations = numReservations;
    }

    int getReservations()
    {
        return reservations;
    }
    
    /**
    * If possible, reserves this LibraryBook.
    * This is only possible if this LibraryBook is currently on loan
    * and less than 3 reservations have been placed since this 
    * went on loan.
    * @return      true,   if new reservation has been made for this.
    *              false,  otherwise
    */
    public boolean reserveBook()
    {
        if(status == BookStatus.ON_LOAN && reservations < 3)
        {
            reservations ++;
            return true;
        }
        return false;
    }

    public boolean borrowBook()
    {
        if(status == BookStatus.AVAILABLE_FOR_LENDING)
        {
            status = BookStatus.ON_LOAN;
            return true;
        }
        return false;
    }
    
    public boolean returnBook()
    {
        if(status == BookStatus.ON_LOAN)
        {
            status = BookStatus.AVAILABLE_FOR_LENDING;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        String returnVal = "Title: " + title + "\n";
        returnVal = returnVal + "Author: " + author + "\n";
        returnVal = returnVal + "Pages: " + pages + "\n";
        returnVal = returnVal + "Classification: " + classification 
                                                   + "\n";
        returnVal = returnVal + "Book Status: " + status + "\n";
        return returnVal;
    }
}