
import java.util.NoSuchElementException;

public class TicketQueueTester {


  /**
   * This method tests the OrderIterator class for our queue system.
   *
   * @return T/F statement on whether our tests have passed
   */
  public static boolean orderIteratorTester(){
    boolean testResult = true;
    TicketOrder.resetIDGenerator();

    //Creating TicketOrders to ensure proper LinkedOrder creation
    TicketOrder stu1 = new TicketOrder("Foo", 1);
    TicketOrder stu2 = new TicketOrder("Fee", 2);

    //Establishing LinkedOrders to test with
    LinkedOrder order1 = new LinkedOrder(stu1);
    LinkedOrder order2 = new LinkedOrder(stu2);

    //Setting the next values
    order1.setNext(order2);

    //Test 1 - Next is filled
    {
      OrderIterator testIterator = new OrderIterator(order1);

      if(!testIterator.hasNext()){
        System.out.println("(T1): Error, iterator cannot see item");
        testResult = false;
      }
    }

    //Test 2 - Next is null
    {
      OrderIterator testIterator = new OrderIterator(order1);
      testIterator.next();

      boolean actual = testIterator.hasNext();

      if(!actual){
        System.out.println("(T2): Error, item is not null (next traversal did not occur)");
        testResult = false;
      }
    }

    //Test 3 - Grabbing the next ticket order
    {
      OrderIterator testIterator = new OrderIterator(order1);

      TicketOrder expected = stu1;
      TicketOrder actual = testIterator.next();

      if(actual != expected){
        System.out.println("(T3): Error, system did not grab the next TicketOrder object.");
        testResult = false;
      }
    }

    return testResult;
  }

  /**
   * Checks the validity of the TicketQueue's enqueue method
   *
   * @return True if our tests have passed, False if they have failed.
   */
  public static boolean enqueueTest(){
    //create the boolean to run through our tests with, and reset IDs
    boolean testResult = true;
    TicketOrder.resetIDGenerator();

    //TicketOrders
    TicketOrder stu1 = new TicketOrder("Foo", 1);
    TicketOrder stu2 = new TicketOrder("Fee", 1);
    TicketOrder stu3 = null;


    //Test 1 - enqueue to empty list
    {

      TicketQueue testList = new TicketQueue();
      String expected = stu1 + " -> END";

      testList.enqueue(stu1);

      if(!testList.toString().equals(expected)){
        System.out.println("(T1): Error, system did not properly enqueue 1 item");
        testResult = false;
      }
    }

    //Test 2 - enqueue two items
    {
      TicketQueue testList = new TicketQueue();
      String expected = stu1 + " -> " + stu2 + " -> END";

      testList.enqueue(stu1);
      testList.enqueue(stu2);

      if(!testList.toString().equals(expected)){
        System.out.println("(T2): Error, system did not properly enqueue 2 items");
        testResult = false;
      }
    }

    //Test 3 - enqueue null item
    {
      TicketQueue testList = new TicketQueue();

      testList.enqueue(stu3);

      String expected = "";
      String actual = testList.toString();

      if(!actual.equals(expected)){
        System.out.println("(T3): Error, system did not properly catch the null element.");
        testResult = false;
      }
    }

    return testResult;
  }

  /**
   * Checks the validity of the TicketQueue's dequeue method
   *
   * @return True if our tests have passed, False if they have failed
   */
  public static boolean dequeueTest(){
    //create the boolean to run through our tests with, and reset IDs
    boolean testResult = true;
    TicketOrder.resetIDGenerator();

    //TicketOrders
    TicketOrder stu1 = new TicketOrder("Foo", 1);
    TicketOrder stu2 = new TicketOrder("Fee", 1);
    TicketOrder stu3 = new TicketOrder("Fum", 1);

    //Test 1 - Dequeue from full list (3 items)
    {
      try{
        TicketQueue testList = new TicketQueue();

        testList.enqueue(stu1);
        testList.enqueue(stu2);
        testList.enqueue(stu3);

        TicketOrder expected = stu1;
        TicketOrder actual = testList.dequeue();

        if(actual != expected){
          System.out.println("(T1): Error, system did not grab the correct item");
          testResult = false;
        }
      }catch (Exception e){
        System.out.println("Exception caught.");
      }
    }

    //Test 2 - Dequeue from 1 item (check that also size updates, which means list is null)
    {
      try{
        TicketQueue testList = new TicketQueue();

        testList.enqueue(stu1);

        TicketOrder expected = stu1;
        TicketOrder actual = testList.dequeue();

        String expectedStr = "";
        String actualStr = testList.toString();

        if(actual != expected || !actualStr.equals(expectedStr)){
          System.out.println("(T2): Error, system did not grab the item, or size/list was not " +
                  "set to empty/null.");
          testResult = false;
        }
      }catch (Exception e){
        System.out.println("Exception caught.");
      }
    }

    //Test 3 - Testing exception is thrown
    {
      boolean actual = false;

      TicketQueue testList = new TicketQueue();

      try{
        testList.dequeue();
      }catch(Exception e){
        actual = true;
      }

      if(!actual){
        System.out.println("(T3): Error, exception was not handled");
        testResult = false;
      }
    }

    return testResult;
  }

  /**
   * Checks the validity of the TicketQueue's peek method
   *
   * @return True if our tests have passed, False if they have failed.
   */
  public static boolean peekTest(){
    //create the boolean to run through our tests with, and reset IDs
    boolean testResult = true;
    TicketOrder.resetIDGenerator();

    //TicketOrders
    TicketOrder stu1 = new TicketOrder("Foo", 1);
    TicketOrder stu2 = new TicketOrder("Fee", 1);
    TicketOrder stu3 = new TicketOrder("Fum", 1);

    //Test 1 - adding items, peeking top item (stu1), and ensuring it is not removed
    {
      try{
        String expected = stu1 + " -> " + stu2 + " -> " + stu3 + " -> END";
        TicketQueue testList = new TicketQueue();

        testList.enqueue(stu1);
        testList.enqueue(stu2);
        testList.enqueue(stu3);

        TicketOrder peeked = testList.peek();
        String actual = testList.toString();

        if(peeked != stu1 || !actual.equals(expected)){
          System.out.println("(T1): Error, peek did not get top object, or list has been altered.");
          testResult = false;
        }
      }catch (Exception e){
        System.out.println("Exception caught.");
      }
    }

    //Test 2 - Testing that exception is thrown
    {
      boolean actual = false;

      TicketQueue testList = new TicketQueue();

      try{
        testList.peek();
      }catch(Exception e){
        actual = true;
      }

      if(!actual){
        System.out.println("(T2): Error, exception was not caught");
        testResult = false;
      }
    }


    return testResult;
  }

  /**
   * Checks the validity of the TicketQueue reverse method
   *
   * @return True if our tests have passed, false if any have failed
   */
  public static boolean reverseTest(){
    //create the boolean to run through our tests with, and reset IDs
    boolean testResult = true;
    TicketOrder.resetIDGenerator();

    //TicketOrders
    TicketOrder stu1 = new TicketOrder("Foo", 1);
    TicketOrder stu2 = new TicketOrder("Fee", 1);
    TicketOrder stu3 = new TicketOrder("Fum", 1);

    //Test 1 - adding only two items, then comparing strings
    {
      TicketQueue testList = new TicketQueue();
      testList.enqueue(stu1);
      testList.enqueue(stu2);

      TicketQueue.reverse(testList);
      String expected = stu2 + " -> " + stu1 + " -> END";
      String actual = testList.toString();

      if(!actual.equals(expected)){
        System.out.println("(T1): Error, list was not properly reversed, or strings do not match.");
        testResult = false;
      }
    }

    return testResult;
  }

  /**
   * Runs all tests through the TicketQueueTester class
   *
   * @return The final T/F value if our tests have passed/failed
   */
  public static boolean runAllTests(){
    boolean testResult = true;

    //running all our boolean tests
    boolean test1 = orderIteratorTester();
    boolean test2 = enqueueTest();
    boolean test3 = dequeueTest();
    boolean test4 = peekTest();
    boolean test5 = reverseTest();

    //if any of our tests return false, we set result to false
    if(!test1 || !test2 || !test3 || !test4 || !test5){
      testResult = false;
    }

    return testResult;
  }

  public static void main (String[]args){
    System.out.println(runAllTests());

  }
}
