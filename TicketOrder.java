/**
 * Basic object representing a student waiting for tickets.
 * 
 * This class contains no mutator methods, only accessors.
 */
public class TicketOrder {

  private static int idGenerator = 1001;  // generator of unique order ID numbers
  
  private String studentName;                // name of the student who made this Order
  private int numTickets;                   // number of tickets in this Order
  private final int ORDER_ID;             // unique order ID number
  
  /**
   * Constructor, initializes student name and number of tickets. Also sets this order's unique
   * identifier.
   * @param studentName the name of the student who made this Order
   * @param numTickets the number of tickets in this Order
   */
  public TicketOrder(String studentName, int numTickets) {
    if (numTickets < 1) throw new IllegalArgumentException("Invalid number of tickets");
    this.ORDER_ID = idGenerator++;
    this.studentName = studentName;
    this.numTickets = numTickets;
  }
  
  /**
   * Returns the name of the student who made this Order
   * 
   * @return the name of the student who made this Order
   */
  public String getStudentName() {
    return this.studentName;
  }
  
  /**
   * Returns the approximate number of tickets in this Order
   * 
   * @return the approximate number of tickets in this Order
   */
  public int getNumTickets() {
    return this.numTickets;
  }
  
  /**
   * Returns the unique ID number of this Order
   * 
   * @return the unique ID number of this Order
   */
  public int getID() {
    return this.ORDER_ID;
  }
  
  /**
   * Returns a String representation of this Order in the format "ID: studentname (numTickets)"
   * 
   * @return a String representation of this Order
   */
  @Override
  public String toString() {
    return this.ORDER_ID+": "+this.studentName+" ("+this.numTickets+")";
  }
  
  /**
   * This method resets the idGenerator to 1001. 
   * This method must be used in your tester methods only.
   */
  public static void resetIDGenerator() {
    idGenerator = 1001;
  }
  
}
