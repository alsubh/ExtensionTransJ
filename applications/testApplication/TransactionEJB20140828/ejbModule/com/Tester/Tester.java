package com.Tester;

import java.util.*;
import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.*;

import com.arjuna.ats.jta.*;
import com.arjuna.ats.jta.UserTransaction.*;
import com.arjuna.ats.*;
import com.arjuna.ats.jdbc.TransactionalDriver;

import javax.transaction.*;

import com.arjuna.ats.jta.TransactionManager;
import com.arjuna.ats.jdbc.common.jdbcPropertyManager.*;
import com.arjuna.ats.jdbc.TransactionalDriver.*;
import com.client.utilities.ClientUtility;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Tester
 */
@Stateless
public class Tester {

	/**
	 * Default constructor.
	 */
	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main (String[] args)
    {
    	Connection conn = null;
    	Connection conn2 = null;
    	Statement stmt = null;  // non-tx statement
    	Statement stmtx = null;  // will be a tx-statement
    	Properties dbProperties = new Properties(); 
    	try{
    		
    		Properties jdniproperties = new Properties();
    		jdniproperties =System.getProperties();
    		ClientUtility cUtil = new ClientUtility();
    		InitialContext cnx = (InitialContext) cUtil.getInitialContext();
    		
    		DriverManager.registerDriver(new com.arjuna.ats.jdbc.TransactionalDriver());
    		String url ="HELLO"; 
    		System.out.println("\nCreating connection to database: "+url);
    	}
    	
    }
}
