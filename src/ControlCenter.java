import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Nate
 *
 */
public class ControlCenter{
	
	private static final int MAX_THREADS = 6;
	private LinkedList<Operator> freeOperators;
	private List<Operator> operatorList;
	private LinkedList<Customer> waitList;
	private static ControlCenter instance = new ControlCenter();
	private ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS); 

	/**
	 *  Initial Constructor
	 *  Creates Lists to be used
	 */
	public ControlCenter(){
		operatorList = new ArrayList<>();
		waitList = new LinkedList<>();
		freeOperators =  new LinkedList<>();
		

	}
	/**
	 * Adds a customer to the wait list
	 * @param customer an object containing customer information
	 * @return returns a string stating the customer with an id has 
	 * been added to the wait list
	 */
	public String addCall(Customer customer) {
		
		waitList.addLast(customer);
		return "Added Customer "+ customer.id+"\n";
		
		
	}
	/*
	 * Gets the wait list 
	 */
	public List<Customer> getWaitList() {
		return waitList;
	}
	
	/*
	 * Transfers a customer to a freeOperator.
	 * Operator is then added to a thread pool until call is completed
	 */
	public void transferCustomer() {
		
		freeOperators.peek().startCall(waitList.poll());
		
		pool.execute(freeOperators.poll());
	}
	
	/*
	 * Gets the Singleton instance of this class
	 */
	public static ControlCenter getInstance() {
		
		return instance;
	}
	
	/*
	 * Creates new Operator Objects based on the int given
	 * @param an int indicating how many operator objects to create
	 */
	public void addOperators(int operatorCount) {
		
		for(int i = 0; i<= operatorCount; i++) {
			
			Operator operator = new Operator(instance, i);
			operatorList.add(operator);
			freeOperators.add(operator);
			
		}
		
		
	}

	/*
	 * Updates the freeOperator List
	 * @param the operator that has recently finished a call
	 */
	public void updateFree(Operator operator) {
		freeOperators.add(operator);
		
		
	}
	/**
	 * Checks to see if any operators are available to take a call
	 */
	public void checkAvaliblity() {
		
		//Sleep to minimize breaking of program
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(freeOperators.size()>=1 && waitList.size() > 0) {
				transferCustomer();
			}
		
	}
	
		

}
