
package TesterAndAspect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import PersonalHealthInformation.*;
import java.io.Serializable;

/*
 * Add bound properties and serialization to point objects
 */

aspect BoundPersonAddress 
{
 
  private PropertyChangeSupport Address.support = new PropertyChangeSupport(this);

  
  public void Address.addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }

  public void Address.addPropertyChangeListener(String propertyName,
                                              PropertyChangeListener listener){

    support.addPropertyChangeListener(propertyName, listener);
  }

  public void Address.removePropertyChangeListener(String propertyName,
                                                 PropertyChangeListener listener) {
    support.removePropertyChangeListener(propertyName, listener);
  }

  public void Address.removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public void Address.hasListeners(String propertyName) {
    support.hasListeners(propertyName);
  }

  declare parents: Address implements Serializable;

 
  pointcut setter(Address address): call(void Address.set*(*)) && target(address);


  void around(Address address): setter(address) {
        String propertyName =
      thisJoinPointStaticPart.getSignature().getName().substring("set".length());
        String oldStreet1 = address.getStreetLine1();
        String oldStreet2 = address.getStreetLine2();
        String oldCity = address.getCity();
        String oldState = address.getState();
        String oldPostalCode = address.getPostalCode();
        
        proceed(address);
        if (propertyName.equals("StreetLine1"))
        {
        	firePropertyChange(address, propertyName, oldStreet1, address.getStreetLine1());      
        } 
        else if(propertyName.equals("StreetLine2"))
        {
        	firePropertyChange(address, propertyName, oldStreet2, address.getStreetLine2());
        }
        else if(propertyName.equals("City"))
        {
        	firePropertyChange(address, propertyName, oldCity, address.getCity());
        }
        else if(propertyName.equals("State"))
        {
        	firePropertyChange(address, propertyName, oldState, address.getState());
        }
        else 
        {
         firePropertyChange(address, propertyName, oldPostalCode, address.getPostalCode());        
        }
  }

  /*
   * Utility to fire the property change event.
   */
  void firePropertyChange(Address address,
          String property,
          String oldval,
          String newval) {
address.support.firePropertyChange(property,
                oldval,
                 newval);
}
}
