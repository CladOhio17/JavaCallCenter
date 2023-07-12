import java.util.Random;

/**
 * @author Nate
 *
 */
public class Main {

	public static void main(String[] args) {
		

		ControlCenter control = ControlCenter.getInstance();
		Customer customer;
		control.addOperators(6);
		int customerID = 1;
		while(true) {
			Random random = new Random();
			int waitListRNG = random.nextInt(4 - 0) + 0;
				if(control.getWaitList().size() <= waitListRNG) {
					
					int callVolume  = random.nextInt(10 - 0) + 0;
					for(int i = 0; i<= callVolume ;i++) {
						customer = new Customer(customerID);
						customerID++;
			
						System.out.print("[SYSTEM] "+control.addCall(customer));
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				control.checkAvaliblity();
			}
		

	}

}
