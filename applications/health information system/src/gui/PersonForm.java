package gui;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import BusinessTier.Operations;
import PersonalHealthInformation.*;


public class PersonForm extends JFrame implements PropertyChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Operations operations = null;
	private boolean isAddMode = false;
	private boolean isNameAddMode = false;
	private boolean isAddressAddMode = false;
	private boolean isPhoneAddMode = false;
	
	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		System.out.println("Property " + e.getPropertyName() + " changed from " +
                e.getOldValue() + " to " + e.getNewValue() );
		
	}
	
	public PersonForm() 
	{
		
		initComponents();
		initialzeMiddleTier();
		//testFormPopulatePerson();
		populatePersonList();
	}

	void testFormPopulatePerson()
	{	
		Person p = new Person();
		Name name = new Name(null,null,"Anas","M.","Alsobeh","S.");
		Address address = new Address(null, "36 aggie village", "1200 N 1200 E","Logan", "UT", "84321");
		PhoneNumber phone = new PhoneNumber(null,"435", "797","6653","100");
		p.addName(name);		
		p.addAddress(address);
		p.addPhoneNumber(phone);		
		HealthInfoDB.AddPerson(p);
		
	}
	void initialzeMiddleTier()
	{
		operations = new Operations();
	}
	private void btnAddActionPerformed(ActionEvent e) 
	{
		clearForm();
		isAddMode = true;
		btnAddPerson.setEnabled(false);		
		btnDelete.setEnabled(false);
		btnSave.setEnabled(true);
		cmbPersonList.setEnabled(false);
		cmbNames.setEnabled(false);
		cmbPhones.setEnabled(false);
		cmbAddresses.setEnabled(false);
	}

	
	void populatePersonList()
	{
		cmbPersonList.removeAllItems();
		String name = "";
		for(Person person : HealthInfoDB.getPersonList())
		{
				Name name_obj = person.getPrimaryNames().get(0);			
				name = name_obj.getFormattedName();
				cmbPersonList.addItem(name);										
		}
	}

	
	void getNames(Person p)
	{		
		cmbNames.removeAllItems();
		for(Name name : p.getPrimaryNames())
			cmbNames.addItem(name.getFormattedName());
	}
	
	void getAddresses(Person p)
	{
		cmbAddresses.removeAllItems();
		for(Address address : p.getAddresses())
			cmbAddresses.addItem(address.getFormattedAddress());		
	}
	
	void getPhoneNumbers(Person p)
	{
		cmbPhones.removeAllItems();
		for(PhoneNumber phone : p.getPhones())
			cmbPhones.addItem(phone.getFormattedNumber());				
	}
	
	void clearForm()
	{
		txtFirstName.setText("");
		txtMiddleName.setText("");
		txtLastName.setText("");
		
		txtAddress1.setText("");
		txtAddress2.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZipcode.setText("");
		
		txtAreaCode.setText("");
		txtExchange.setText("");
		txtExtension.setText("");
		txtDetailNumber.setText("");
	}
	Person populatePerson()
	{
		Person p = new Person();		
		Name name = new Name(null, null, txtFirstName.getText(), txtMiddleName.getText(), txtLastName.getText(), null);
		Address address = new Address(null, txtAddress1.getText(), txtAddress2.getText(), txtCity.getText(), txtState.getText(), txtZipcode.getText());
		PhoneNumber number = new PhoneNumber(null, txtAreaCode.getText(), txtExchange.getText(),txtDetailNumber.getText(), txtExtension.getText());		
		p.addName(name);
		p.addAddress(address);
		p.addPhoneNumber(number);	
		
		return p;
		//Load the data from text fiels to Perosn
	}

	void LoadPerson(Person person)
	{
		Name firstName = person.getPrimaryNames().get(0);
		if(firstName != null)
		{
			txtFirstName.setText(firstName.getFirstName());
			txtMiddleName.setText(firstName.getMiddleName());
			txtLastName.setText(firstName.getLastName());
		}
		
		Address firstAdress = person.getAddresses().get(0);
		if(firstAdress != null)
		{
			txtAddress1.setText(firstAdress.getStreetLine1());
			txtAddress2.setText(firstAdress.getStreetLine2());
			txtCity.setText(firstAdress.getCity());
			txtState.setText(firstAdress.getState());
			txtZipcode.setText(firstAdress.getPostalCode());
		}
		
		PhoneNumber firstPhone = person.getPhones().get(0);
		if(firstPhone != null)
		{
			txtAreaCode.setText(firstPhone.getAreaCode());
			txtExchange.setText(firstPhone.getExchange());
			txtExtension.setText(firstPhone.getExtension());
			txtDetailNumber.setText(firstPhone.getDetailNumber());
		}
		
	}
	private void btnSaveActionPerformed(ActionEvent e) 
	{
		PersonalHealthInformation.Person person = populatePerson();
		boolean status = false;
		if(isAddMode)
		{
			status = operations.Save(person);
			
		}else
		{			
			status = Edit(); 
		}
		
		
		 if(status)
		 {
			JOptionPane.showMessageDialog(null, " Person Saved Successfully");
		 }
		else
		{
			JOptionPane.showMessageDialog(null, " Person can't be saved");
		}
			 
		isAddMode  = false;
		populatePersonList();
		btnAddPerson.setEnabled(true);
		
		btnDelete.setEnabled(true);
		btnSave.setEnabled(true);
		cmbPersonList.setEnabled(true);
		cmbNames.setEnabled(true);
		cmbPhones.setEnabled(true);
		cmbAddresses.setEnabled(true);
		// TODO add your code here
	}

	
	Person getPerson(String person_name)
	{
		for(Person p : HealthInfoDB.getPersonList())
		{
			if (p != null)
			{ 
				for(Name name : p.getPrimaryNames())
				{
					if(person_name.equals(name.getFormattedName()) )
					{
						return p;
					}
				}
			}
		}
		return null;
	}
	
	private void btnDeleteActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		String name =  (String)cmbPersonList.getSelectedItem();
		 Person p = getPerson(name);		 
			if(operations.Delete(p))
			{
				JOptionPane.showMessageDialog(null, " Person Deleted Successfully");
			}
			else
			{
				JOptionPane.showMessageDialog(null, " Person can't be deleted");
			}
			
			populatePersonList();
			btnAddPerson.setEnabled(true);			
			btnDelete.setEnabled(true);
			btnSave.setEnabled(true);
	}

	private void cmbPersonListItemStateChanged(ItemEvent e) {
		
		Object selectedItem = cmbPersonList.getSelectedItem();
		if(selectedItem !=null)
		{
			Person p = getPerson((String)selectedItem);		
			LoadPerson(p);
			getNames(p);
			getAddresses(p);
			getPhoneNumbers(p);
		}
	}



	private boolean Edit() {
		Object selectedItem = cmbPersonList.getSelectedItem();
		
		if(selectedItem !=null)
		{
			Person p = getPerson((String)selectedItem);
			
			if(isNameAddMode){
				setNamefromFields(new Name(), p, isNameAddMode);
				isNameAddMode = false;
			}
			else
			{				
				setNamefromFields(getCurrentName(), p, isNameAddMode);
					
			}
			
			if(isAddressAddMode){
				setAddressfromFields(new Address(), p, isAddressAddMode);
				isAddressAddMode = false;
			}
			else
			{
				setAddressfromFields(getCurrentAddress(p), p, isAddressAddMode);
				
			}
			
			if(isPhoneAddMode){
				setPhonefromFields(new PhoneNumber(),p,isPhoneAddMode);
				isPhoneAddMode = false;
			}
			else
			{
				setPhonefromFields(getCurrentPhone(p), p,isPhoneAddMode);
				
			}
			
			populatePersonList();		
			btnAddPerson.setEnabled(true);			
			btnDelete.setEnabled(true);
			btnSave.setEnabled(true);
		}
		return true;
	}

	private void setPhonefromFields(PhoneNumber phone, Person p, boolean toAdd)
	{
		phone.addPropertyChangeListener(this);
		phone.setAreaCode(txtAreaCode.getText());
		phone.setExchange(txtExchange.getText());
		phone.setDetailNumber(txtDetailNumber.getText());
		phone.setExtension(txtExtension.getText());
		if(toAdd)
			p.addPhoneNumber(phone);
		
	}

	private void setAddressfromFields(Address addres, Person p, boolean toAdd) 
	{
		addres.addPropertyChangeListener(this);
		addres.setStreetLine1(txtAddress1.getText());
		addres.setStreetLine2(txtAddress2.getText());
		addres.setCity(txtCity.getText());
		addres.setPostalCode(txtZipcode.getText());
		addres.setState(txtState.getText());
		if(toAdd)
			p.addAddress(addres);
	
	}

	private void setNamefromFields(Name name, Person p, boolean toAdd)
	{	
		
		name.addPropertyChangeListener(this);		
		name.setFirstName(txtFirstName.getText());
		name.setMiddleName(txtMiddleName.getText());
		name.setLastName(txtLastName.getText());
		
		if(toAdd)
			p.addName(name);			
	}
	
	
	
	Name getName(String nameinList)
	{
		Person currentPerson = getPerson((String)cmbPersonList.getSelectedItem());
		String currentName = (String)cmbNames.getSelectedItem();
		if(currentPerson !=null)
		{
			for(Name names : currentPerson.getPrimaryNames())
			{
				if(names.getFormattedName().equals(currentName))				
					return names;
			}
		}
		return null;
	}
	
	
	Name getCurrentName()
	{
		Person currentPerson = getPerson((String)cmbPersonList.getSelectedItem());
		String currentName = (String)cmbNames.getSelectedItem();
		if(currentPerson !=null)
		{
			for(Name names : currentPerson.getPrimaryNames())
			{
				if(names.getFormattedName().equals(currentName))				
					return names;
			}
		}
		return null;
	}
	
	PhoneNumber getCurrentPhone(Person p)
	{
		Person currentPerson = p;
		String currentPhone = (String)cmbPhones.getSelectedItem();
		if(currentPerson !=null)
		{
			for(PhoneNumber phone : currentPerson.getPhones())
			{
				if(phone.getFormattedNumber().equals(currentPhone))				
					return phone;
			}
		}
		return null;
	}

	
	Address getCurrentAddress(Person p)
	{
		Person currentPerson = p;
		String currentAddress = (String)cmbAddresses.getSelectedItem();
		if(currentPerson !=null)
		{
			for(Address address : currentPerson.getAddresses())
			{
				if(address.getFormattedAddress().equals(currentAddress))				
					return address;
			}
		}
		return null;
	}

	private void btnAddNameActionPerformed(ActionEvent e) {
		
		
		isNameAddMode = true;
		txtFirstName.setText("");
		txtMiddleName.setText("");
		txtLastName.setText("");

	}

	private void btnAddAddressActionPerformed(ActionEvent e) {
		
		
		isAddressAddMode = true;
		txtAddress1.setText("");
		txtAddress2.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZipcode.setText("");					
	}

	private void btnAddPhoneActionPerformed(ActionEvent e) {

		
		isPhoneAddMode = true;
		txtAreaCode.setText("");
		txtExchange.setText("");
		txtDetailNumber.setText("");
		txtExtension.setText("");		
	}

	
	private void cmbNamesItemStateChanged(ItemEvent e) 
	{
		Name name = getCurrentName();
		if(name!=null){
			txtFirstName.setText(name.getFirstName());
			txtMiddleName.setText(name.getMiddleName());
			txtLastName.setText(name.getLastName());
		}
	}




	private void cmbAddressesItemStateChanged(ItemEvent e) 
	{
		Address addr = getCurrentAddress(getPerson((String)cmbPersonList.getSelectedItem()));
		if(addr!=null){
			txtAddress1.setText(addr.getStreetLine1());
			txtAddress2.setText(addr.getStreetLine2());
			txtCity.setText(addr.getCity());
			txtState.setText(addr.getState());
			txtZipcode.setText(addr.getPostalCode());
		}
	}

	private void cmbPhonesItemStateChanged(ItemEvent e) {
		PhoneNumber phone = getCurrentPhone(getPerson((String)cmbPersonList.getSelectedItem()));
		if(phone!=null)
		{
			txtAreaCode.setText(phone.getAreaCode());
			txtExchange.setText(phone.getExchange());
			txtDetailNumber.setText(phone.getDetailNumber());
			txtExtension.setText(phone.getExtension());
		}
		// TODO add your code here
	}

	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Anas Alsobeh
		label1 = new JLabel();
		txtFirstName = new JTextField();
		label2 = new JLabel();
		txtMiddleName = new JTextField();
		label3 = new JLabel();
		txtLastName = new JTextField();
		label12 = new JLabel();
		label13 = new JLabel();
		txtAreaCode = new JTextField();
		label14 = new JLabel();
		txtExchange = new JTextField();
		label15 = new JLabel();
		txtDetailNumber = new JTextField();
		label16 = new JLabel();
		txtExtension = new JTextField();
		btnSave = new JButton();
		btnDelete = new JButton();
		btnAddPerson = new JButton();
		cmbPersonList = new JComboBox();
		Person = new JLabel();
		label11 = new JLabel();
		label6 = new JLabel();
		txtAddress1 = new JTextField();
		label7 = new JLabel();
		txtAddress2 = new JTextField();
		label8 = new JLabel();
		txtCity = new JTextField();
		label9 = new JLabel();
		txtState = new JTextField();
		label10 = new JLabel();
		txtZipcode = new JTextField();
		cmbPhones = new JComboBox();
		cmbAddresses = new JComboBox();
		cmbNames = new JComboBox();
		label17 = new JLabel();
		btnAddName = new JButton();
		btnAddAddress = new JButton();
		btnAddPhone = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("First Name");
		contentPane.add(label1);
		label1.setBounds(105, 105, 95, 15);
		contentPane.add(txtFirstName);
		txtFirstName.setBounds(220, 105, 182, txtFirstName.getPreferredSize().height);

		//---- label2 ----
		label2.setText("Middle Name");
		contentPane.add(label2);
		label2.setBounds(105, 130, 95, 15);
		contentPane.add(txtMiddleName);
		txtMiddleName.setBounds(220, 130, 182, txtMiddleName.getPreferredSize().height);

		//---- label3 ----
		label3.setText("Last Name");
		contentPane.add(label3);
		label3.setBounds(105, 155, 100, 20);
		contentPane.add(txtLastName);
		txtLastName.setBounds(220, 155, 182, txtLastName.getPreferredSize().height);

		//---- label12 ----
		label12.setText("List of Phones");
		contentPane.add(label12);
		label12.setBounds(105, 435, 90, 20);

		//---- label13 ----
		label13.setText("Area Code");
		contentPane.add(label13);
		label13.setBounds(105, 470, 105, 25);
		contentPane.add(txtAreaCode);
		txtAreaCode.setBounds(225, 475, 45, txtAreaCode.getPreferredSize().height);

		//---- label14 ----
		label14.setText("Exchange");
		contentPane.add(label14);
		label14.setBounds(105, 500, 85, 25);
		contentPane.add(txtExchange);
		txtExchange.setBounds(225, 505, 45, txtExchange.getPreferredSize().height);

		//---- label15 ----
		label15.setText("Detail Number");
		contentPane.add(label15);
		label15.setBounds(100, 530, 105, 25);
		contentPane.add(txtDetailNumber);
		txtDetailNumber.setBounds(225, 535, 182, txtDetailNumber.getPreferredSize().height);

		//---- label16 ----
		label16.setText("Extension");
		contentPane.add(label16);
		label16.setBounds(100, 560, 95, 25);
		contentPane.add(txtExtension);
		txtExtension.setBounds(225, 560, 45, txtExtension.getPreferredSize().height);

		//---- btnSave ----
		btnSave.setText("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		contentPane.add(btnSave);
		btnSave.setBounds(260, 615, 90, 45);

		//---- btnDelete ----
		btnDelete.setText("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		contentPane.add(btnDelete);
		btnDelete.setBounds(385, 615, 90, 45);

		//---- btnAddPerson ----
		btnAddPerson.setText("New");
		btnAddPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		contentPane.add(btnAddPerson);
		btnAddPerson.setBounds(140, 615, 90, 45);

		//---- cmbPersonList ----
		cmbPersonList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbPersonListItemStateChanged(e);
			}
		});
		contentPane.add(cmbPersonList);
		cmbPersonList.setBounds(90, 20, 305, cmbPersonList.getPreferredSize().height);

		//---- Person ----
		Person.setText("Person");
		contentPane.add(Person);
		Person.setBounds(10, 25, 55, 15);

		//---- label11 ----
		label11.setText("List of Addresses");
		contentPane.add(label11);
		label11.setBounds(105, 225, 110, 14);

		//---- label6 ----
		label6.setText("Street Line 1");
		contentPane.add(label6);
		label6.setBounds(105, 255, 110, 14);
		contentPane.add(txtAddress1);
		txtAddress1.setBounds(225, 250, 180, 20);

		//---- label7 ----
		label7.setText("Street Line 2");
		contentPane.add(label7);
		label7.setBounds(105, 275, 110, 20);
		contentPane.add(txtAddress2);
		txtAddress2.setBounds(225, 275, 180, 20);

		//---- label8 ----
		label8.setText("City");
		contentPane.add(label8);
		label8.setBounds(105, 300, 61, 14);
		contentPane.add(txtCity);
		txtCity.setBounds(225, 300, 110, 20);

		//---- label9 ----
		label9.setText("State");
		contentPane.add(label9);
		label9.setBounds(105, 325, 61, 14);
		contentPane.add(txtState);
		txtState.setBounds(225, 325, 35, 20);

		//---- label10 ----
		label10.setText("Zipcode");
		contentPane.add(label10);
		label10.setBounds(105, 350, 61, 20);
		contentPane.add(txtZipcode);
		txtZipcode.setBounds(225, 350, 61, 20);

		//---- cmbPhones ----
		cmbPhones.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbPhonesItemStateChanged(e);
			}
		});
		contentPane.add(cmbPhones);
		cmbPhones.setBounds(225, 435, 195, cmbPhones.getPreferredSize().height);

		//---- cmbAddresses ----
		cmbAddresses.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbAddressesItemStateChanged(e);
			}
		});
		contentPane.add(cmbAddresses);
		cmbAddresses.setBounds(225, 220, 185, cmbAddresses.getPreferredSize().height);

		//---- cmbNames ----
		cmbNames.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbNamesItemStateChanged(e);
			}
		});
		contentPane.add(cmbNames);
		cmbNames.setBounds(220, 75, 180, cmbNames.getPreferredSize().height);

		//---- label17 ----
		label17.setText("List of Names");
		contentPane.add(label17);
		label17.setBounds(105, 80, 105, 15);

		//---- btnAddName ----
		btnAddName.setText("Add Name");
		btnAddName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddNameActionPerformed(e);
			}
		});
		contentPane.add(btnAddName);
		btnAddName.setBounds(295, 185, 110, 25);

		//---- btnAddAddress ----
		btnAddAddress.setText("Add Address");
		btnAddAddress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddAddressActionPerformed(e);
			}
		});
		contentPane.add(btnAddAddress);
		btnAddAddress.setBounds(305, 370, 110, 25);

		//---- btnAddPhone ----
		btnAddPhone.setText("Add Phone");
		btnAddPhone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddPhoneActionPerformed(e);
			}
		});
		contentPane.add(btnAddPhone);
		btnAddPhone.setBounds(305, 580, 105, 25);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Anas Alsobeh
	private JLabel label1;
	private JTextField txtFirstName;
	private JLabel label2;
	private JTextField txtMiddleName;
	private JLabel label3;
	private JTextField txtLastName;
	private JLabel label12;
	private JLabel label13;
	private JTextField txtAreaCode;
	private JLabel label14;
	private JTextField txtExchange;
	private JLabel label15;
	private JTextField txtDetailNumber;
	private JLabel label16;
	private JTextField txtExtension;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnAddPerson;
	private JComboBox cmbPersonList;
	private JLabel Person;
	private JLabel label11;
	private JLabel label6;
	private JTextField txtAddress1;
	private JLabel label7;
	private JTextField txtAddress2;
	private JLabel label8;
	private JTextField txtCity;
	private JLabel label9;
	private JTextField txtState;
	private JLabel label10;
	private JTextField txtZipcode;
	private JComboBox cmbPhones;
	private JComboBox cmbAddresses;
	private JComboBox cmbNames;
	private JLabel label17;
	private JButton btnAddName;
	private JButton btnAddAddress;
	private JButton btnAddPhone;
	// JFormDesigner - End of variables declaration  //GEN-END:variables


	
}
