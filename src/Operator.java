import java.util.Random;

/*
 * Handles Calls assigned to it by the Control Center
 */
/**
 * @author Nate
 *
 */
public class Operator implements Runnable{
	
	private int callTime;
	private Boolean freeAgent;
	private Customer customer;
	private ControlCenter control;
	private int id;
	
	/**
	 * 
	 * @param parent
	 * @param id
	 */
	Operator(ControlCenter parent, int id){
		this.control = parent;
		this.freeAgent = true;
		this.id = id;
		
	}
	/**
	 * 
	 */
	public void endCall() {
		customer = null;
		control.updateFree(this);
		this.freeAgent = true;
		
	}
	
	/**
	 * 
	 * @param customer
	 */
	public void startCall(Customer customer) {
		this.customer = customer;
		this.freeAgent = false;
		System.out.println("Operator "+id+": Call Started with Customer "+id);



	}

	/**
	 * Runs On thread, "Completes" a call with a customer by sleeping
	 * 
	 */
	public void run() {


		Random random = new Random();
	    int callTime = random.nextInt(60000 - 5000) + 5000;
	    	

		
		try {
			Thread.sleep(callTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		
		}
		System.out.println("Operator "+id + ": In call with customer "+ customer.id +" for "+ callTime/1000+"s");
		System.out.println("Operator "+id + ": Call ended");
		endCall();

				
		
	}

}
