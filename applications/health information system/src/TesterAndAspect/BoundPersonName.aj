
package TesterAndAspect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import PersonalHealthInformation.*;
import java.io.Serializable;

/*
 * Add bound properties and serialization to point objects
 */

aspect BoundPersonName {
 
  private PropertyChangeSupport Name.support = new PropertyChangeSupport(this);

  
  public void Name.addPropertyChangeListener(PropertyChangeListener listener){
	
    support.addPropertyChangeListener(listener);
  }

  public void Name.addPropertyChangeListener(String propertyName,
                                              PropertyChangeListener listener){
	
    support.addPropertyChangeListener(propertyName, listener);
  }

  public void Name.removePropertyChangeListener(String propertyName,
                                                 PropertyChangeListener listener) {
	
    support.removePropertyChangeListener(propertyName, listener);
  }

  public void Name.removePropertyChangeListener(PropertyChangeListener listener) {
	
    support.removePropertyChangeListener(listener);
  }

  public void Name.hasListeners(String propertyName) {
	
    support.hasListeners(propertyName);
  }

  declare parents: Name implements Serializable;

 
  pointcut setter(Name name): call(void Name.set*(*)) && target(name);

  /**
   * Advice to get the property change event fired when the
   * setters are called. It's around advice because you need
   * the old value of the property.
   */
  void around(Name name): setter(name) {
        String propertyName =
      thisJoinPointStaticPart.getSignature().getName().substring("set".length());
        String oldFirstName = name.getFirstName();
        String oldMiddleName = name.getMiddleName();
        String oldLastName = name.getLastName();
        System.out.println(name + " around ");
        proceed(name);
        if (propertyName.equals("FirstName"))
        {
    
        	firePropertyChange(name, propertyName, oldFirstName, name.getFirstName());      
        } 
        else if(propertyName.equals("MiddleName"))
        {
        	firePropertyChange(name, propertyName, oldMiddleName, name.getMiddleName());
    
        }
        else 
        {
    
         firePropertyChange(name, propertyName, oldLastName, name.getLastName());
         
        }
  }

  /*
   * Utility to fire the property change event.
   */
  void firePropertyChange(Name name,
                          String property,
                          String oldval,
                          String newval) {
    name.support.firePropertyChange(property,
                                oldval,
                                 newval);
  }
}
