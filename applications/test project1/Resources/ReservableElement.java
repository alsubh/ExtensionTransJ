package Resources;

//import RM.*;

import java.util.Vector;
import java.util.Enumeration;

/**
 * Class to define an object that can be reserved by a customer.
 * This base class hold the availability and price of the units, plus
 * operations to manipulate this data (both queries and reserve/unreserve
 * functions.)  Additionally, it holds a list of customers that have
 * reservations (and these reservations are accounted for in the
 * availability numbers.)  Note that these functions do not manipulate the
 * customer objects.  Customers and ReservableElements should be modified
 * in parallel to achieve a reservation.
 *
 * @author Based on code by: Wayne Stoppler, CSE 593, Winter 2001
 * @since Step 2.
 */
public class ReservableElement implements java.io.Serializable {
    /** Name for element */
    private String m_name;

        

    /** Number of available units for given element */
    private int m_available;

    /** Price of element */
    private int m_price;

    /** List of customers currently reserving element */
    private Vector m_customers = new Vector();

    public ReservableElement(){}
    /**
     * Constructor for ReservableElement.
     *
     * @param name      Name of the element
     * @param key       Unique key for the element
     * @param available Number of available units
     * @param price     Price of an available unit
     */
    public  ReservableElement(String name){
        m_name = name;
    }
    public  ReservableElement(int name){
	//flight number
        m_name = new Integer(name).toString();
    }

    public boolean addAvailable(int available) {
        m_available += available;
	return true;
    }
    public boolean addAvailable(int available,int price) {
        m_available += available;
        m_price = price;
        return true;
    }


    /**
     * Subtracts from the available units for the element.
     *
     * @param adjustment Element containing availability decrement (name, key,
     *        and price in this object are ignored).
     * @return true if successful, otherwise false (not enough available)
     */
    public boolean reduceAvailable(ReservableElement adjustment) {
        if (m_available < adjustment.m_available) {
            return false;
        }
        m_available -= adjustment.m_available;
        return true;
    }
    
    public boolean reduceAvailable(int amt) {
	 if (m_available < amt) {
            return false;
        }
        m_available -= amt;
	return true;
    }
    /**
     * Query the name of the element.
     *
     * @return Name of the element
     */
    public String getName() {
        return m_name;
    }

    public String getKey(){
	return m_name;
    }
    /**
     * Query the number of available units.
     *
     * @return Number of available units for this element
     */
    public int getAvailable() {
        return m_available;
    }
    
   
    /**
     * Query the price.
     *
     * @return Price for a unit for this element
     */
    public int getPrice() {
        return m_price;
    }


    /**
     * Query the number of reservations held for this element.
     *
     * @return Number of reservations held.  Note that duplicates are
     *         allowed and will be reflected in this count (if a customer
     *         has reserved 2 cars at the same location, both reservations
     *         will be reflected in this total.
     */
    public int getReservationCount() {
        return m_customers.size();
    }


    /**
     * Adds a customer to the reservation list for this object.
     * Note that this does not do anything to the customer, so additional
     * bookkeeping is needed outside of this function.
     *
     * @param customer Customer who had is requesting the reservation
     * @return true if successful, otherwise false (no availablity)
     */
    public boolean makeReservation(int customer) {
        if (m_available <= 0) {
            return false;
        }

        // Reservation legal.  Note reservation and make 1 unit unavailable.
        m_available--;
        m_customers.add(new Integer(customer));
        return true;
    }


    /**
     * Removes a customer from the reservation list for this object.
     * Note that this does not do anything to the customer, so additional
     * bookkeeping is needed outside of this function.
     *
     * @param customer Customer who had item object reserved
     * @return true if successful, otherwise false (reservation not found)
     */
    public boolean dropReservation(int customer) {
        if (m_customers.remove(new Integer(customer))) {
            m_available++;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Deep copy from one element into another.
     *
     * @param r Destination element for the copy
     */
    public void copy(ReservableElement r) {
        r.m_name = this.m_name;
	r.m_available = this.m_available;
        r.m_price = this.m_price;

        r.m_customers = new Vector();
        Enumeration e = this.m_customers.elements();
        while (e.hasMoreElements()) {
            r.m_customers.add(e.nextElement());
        }
    }


    /**
     * Create string representation of object.
     *
     * @return String encoding of object.
     */
    public String toString() {
        // List base information plus customer list
        String x = "[" + m_name + "]: avail=" + m_available +
                   ", price=" + m_price + ", customers:";
        for (int i=0; i < m_customers.size(); i++) {
            x += "[" + (Integer)m_customers.elementAt(i) + "]";
        }
        return x;
    }


}
