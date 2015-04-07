package UnitTests;

import Resources.*;
import JavaTransaction.*;
import java.rmi.*;

/**
 * Quick and dirty test class to verify that things are generally working.
 * Not at all a complete test suite.
 */
public class ResourceManagerTester {

    public ResourceManagerTester() {
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        if (args.length != 1) {
            System.out.println("Usage: UnitTests.ResourceManagerTester <wcName>");
            System.exit(-1);
        }

        String name = "//localhost/" + args[0];

        try {
	    ClientInterface wc = (ClientInterface)Naming.lookup(name);
            int xid = 0;

            xid = wc.start();
            //int customerId = rm.newCustomer(xid);
	  	    
	    boolean rc =
                (
		 wc.addFlight(xid, 1, 500, 50) &&
                 wc.addFlight(xid, 1, 250, 75) &&
		 wc.addFlight(xid,198,1000,200)&&
                 wc.addCars(xid,"Seattle",100, 10) &&
		 wc.addCars(xid,"Seattle", 10, 15)
		 
                 /*rm.deleteCars(xid, "Seattle", 25) &&
                 rm.addRooms(xid, "NY", 600, 100) &&
                 rm.addRooms(xid, "Boston", 600, 990) &&
                 
                 rm.reserveRoom(xid, customerId, "Boston") &&
		   rm.reserveRoom(xid, customerId, "Boston")
		 */
		 );
	   
	    /* System.out.println("CARS "+rm.query(xid,e));
	       System.out.println("@ PRICE "+rm.queryPrice(xid,e));
	       rm.reserve(xid, 1, f);
	       rm.reserve(xid, 1, e); 
	    */
	    System.out.println("CARS "+wc.queryCars(xid,"Seattle"));
	    System.out.println("@ PRICE "+wc.queryCarsPrice(xid,"Seattle"));
	    wc.reserveFlight(xid, 1, 198);
	    wc.reserveCar(xid, 1, "Seattle"); 
	    
            if (rc) {
                wc.commit(xid);
            }
            else {
                wc.abort(xid);
            }
	    System.err.println("TX ID "+xid);
	    System.out.println("CUSTOMER "+wc.queryCustomerInfo(xid,1));
	    /* 
            xid = rm.start();
            customerId = rm.newCustomer(xid);
            rc =
                 (rm.reserveFlight(xid, customerId, 1) &&
                  rm.reserveCar(xid, customerId, "Seattle") &&
                  rm.reserveRoom(xid, customerId, "Boston"));

            if (rc) {
                rm.commit(xid);
            }
            else {
                rm.abort(xid);
            }

	        System.out.println(rm.queryCustomerInfo(1));
	    
	    
	      xid = rm.start();
            rc =
                 (rm.deleteCustomer(xid, 1) &&
                  rm.deleteCustomer(xid, 2));

            if (rc) {
                rm.commit(xid);
            }
            else {
                rm.abort(xid);
            }
	    */
        } catch (RemoteException e) {
            e.detail.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
