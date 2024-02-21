
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is our iterator for the ticket orders.
 *
 * @author Alejandro Aguina
 */
public class OrderIterator implements Iterator<TicketOrder> {
  //setting our private instance data fields
  private LinkedOrder current;


  public OrderIterator(LinkedOrder start){
    this.current = start;

  }

  /**
   * Checks to see if our current ticket has an item.
   *
   * @return The T/F value of if the current ticket has an item.
   */
  public boolean hasNext(){
    if(current != null){
      return true;
    }
    return false;
  }

  /**
   * Grabs and returns the TicketOrder's next element if it exists.
   *
   * @return The next TicketOrder
   * @throws NoSuchElementException If no TicketOrder exists, throw exception
   */
  public TicketOrder next() throws NoSuchElementException{
    TicketOrder next = null;

    next = current.getOrder();
    current = current.getNext();

    return next;
  }



}
