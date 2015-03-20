
package TesterAndAspect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import PersonalHealthInformation.*;
import java.io.Serializable;

/*
 * Add bound properties and serialization to point objects
 */

aspect BoundPersonPhone 
{
 
  private PropertyChangeSupport PhoneNumber.support = new PropertyChangeSupport(this);

  
  public void PhoneNumber.addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }

  public void PhoneNumber.addPropertyChangeListener(String propertyName,
                                              PropertyChangeListener listener){

    support.addPropertyChangeListener(propertyName, listener);
  }

  public void PhoneNumber.removePropertyChangeListener(String propertyName,
                                                 PropertyChangeListener listener) {
    support.removePropertyChangeListener(propertyName, listener);
  }

  public void PhoneNumber.removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public void PhoneNumber.hasListeners(String propertyName) {
    support.hasListeners(propertyName);
  }

  declare parents: PhoneNumber implements Serializable;

 
  pointcut setter(PhoneNumber phone): call(void PhoneNumber.set*(*)) && target(phone);

  /**
   * Advice to get the property change event fired when the
   * setters are called. It's around advice because you need
   * the old value of the property.
   */
  void around(PhoneNumber phone): setter(phone) {
        String propertyName =
      thisJoinPointStaticPart.getSignature().getName().substring("set".length());
        String oldAreaCode = phone.getAreaCode();
        String oldExchange = phone.getExchange();
        String oldDetailNumber = phone.getDetailNumber();
        String oldExtension = phone.getExtension();
        
        
        proceed(phone);
        if (propertyName.equals("AreaCode"))
        {
        	firePropertyChange(phone, propertyName, oldAreaCode, phone.getAreaCode());      
        } 
        else if(propertyName.equals("Exchange"))
        {
        	firePropertyChange(phone, propertyName, oldExchange, phone.getExchange());
        }
        else if(propertyName.equals("DertailNumber"))
        {
        	firePropertyChange(phone, propertyName, oldDetailNumber, phone.getDetailNumber());
        }
        else 
        {
         firePropertyChange(phone, propertyName, oldExtension, phone.getExtension());        
        }
  }

  /*
   * Utility to fire the property change event.
   */
  void firePropertyChange(PhoneNumber phone,
          String property,
          String oldval,
          String newval) {
	  phone.support.firePropertyChange(property,
                oldval,
                 newval);
}
}
