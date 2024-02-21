
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates the linked list of the ticket queue, each item is placed in order, and
 * references the items in front, and behind. As new items are added/removed, the total queue
 * size updates.
 *
 * @author Alejandro Aguina
 */
public class TicketQueue implements QueueADT<TicketOrder>, Iterable<TicketOrder>{
  //setting our private instance data fields
  private LinkedOrder front;
  private LinkedOrder back;
  private int size;

  /**
   * Creates and returns a new OrderIterator beginning with the current value of front
   *
   * @return The new OrderIterator
   */
  public Iterator<TicketOrder> iterator(){
    OrderIterator itr = new OrderIterator(front);

    return itr;
  }

  /**
   * Adds a new LinkedOrder to the back of the queue, updating the size variable and front/back
   * references appropriately.
   *
   * @param newElement the element to be added.
   */
  public void enqueue(TicketOrder newElement){
    //if the newElement is null, we return
    if (newElement == null){
      System.out.println("No element to add.");
      return;
    }
    //create new order for queue
    LinkedOrder newOrder = new LinkedOrder(newElement);

    //if the list is empty, we set the front to newOrder
    if(size == 0){
      front = newOrder;
    }
    //else, we set the currBack's next to newOrder
    else{
      back.setNext(newOrder);
    }

    //at the end we set back to newOrder, increment size
    back = newOrder;
    size++;
  }

  /**
   * Removes the next LinkedOrder from the front of the queue and returns its TicketOrder,
   * updating the size variable and front/back references appropriately.
   *
   * @return The Ticket Order of the removed item
   * @throws NoSuchElementException If queue is empty, we throw exception
   */
  public TicketOrder dequeue() throws NoSuchElementException{
    //create a ticket to remove our top item
    TicketOrder removedItem = front.getOrder();

    //if the list is bigger than one item, we set front to next item
    if(size > 1){
      front = front.getNext();
    }
    //else, we set front, back to null
    else{
      front = null;
      back = null;
    }

    //lastly, we decrement size
    size--;

    return removedItem;
  }

  /**
   * Returns the Order from the LinkedOrder at the front of the queue without removing the
   * LinkedOrder from the queue.
   *
   * @return The current front of the TicketQueue list
   * @throws NoSuchElementException If the queue is empty, we throw exception
   */
  public TicketOrder peek() throws NoSuchElementException{
    TicketOrder peekOrder = front.getOrder();

    return peekOrder;
  }

  /**
   * Checks to see if TicketQueue list is empty
   *
   * @return True if the list is empty, false if items exist
   */
  public boolean isEmpty(){
    return front == null && size < 1;
  }

  /**
   * Reverses the given TicketQueue using recursion. This method both returns the reversed queue
   * and alters the original queue in place.
   *
   * @param queue The TicketQueue to reverse
   * @return The reversed TicketQueue
   */
  public static TicketQueue reverse(TicketQueue queue){
    //Base Case - the queue is empty, return the empty queue
    if(queue.isEmpty()){
      return queue;
    }

    //The objective:
    //1 - Take the current item out of the queue (using dequeue), place into new item
    TicketOrder recurTicket = queue.dequeue();
    //2 - recursively call reverse (using the rest of the queue)
    reverse(queue);
    //3 - adding the current item to the back of the queue
    queue.enqueue(recurTicket);

    return queue;
  }

  /**
   * Creates and returns a String representation of this TicketQueue using an enhanced-for loop.
   * For example, a queue with three Orders might look like this:
   * 1001: chris (2) -> 1002: michelle (1) -> 1003: alex (3) -> END
   *
   * @return A String representation of the queue
   */
  @Override
  public String toString() {
    //Code directly copy-pasted from P05 source document.
    if (this.size == 0)
      return "";
    String qString = "";
    for (TicketOrder o : this) {
      qString += o.toString();
      qString += " -> ";
    }
    qString += "END";
    return qString;
  }
}
