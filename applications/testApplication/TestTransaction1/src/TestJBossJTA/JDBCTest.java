package TestJBossJTA;

import java.sql.*;
import java.util.*;

public class JDBCTest {
  static public void main(String args[]) {
    Connection dbconnection = null;

    if(args.length != 4) {
      System.out.println("Usage: java JDBCTest DRIVER URL USER PASSWORD");
      return;
    }
    try {
      Class.forName(args[0]).newInstance();  // load the Driver
    }
    catch ( Exception e ) {
      System.err.println("We died at place A");
      e.printStackTrace();
      return;
    }                          
    try {                                   // Print arguments 
      for (int i=0; i < 4; i++) {
        System.out.println("Arg["+i+"]="+args[i]);
      }
      // connect to the database
      dbconnection = DriverManager.getConnection(args[1], args[2], args[3]);
      System.out.println("Connected to "+args[1]+" as user "+args[2]);
      Statement s = null;
      ResultSet r = null;
      s = dbconnection.createStatement();    // prepare for statement
      r=s.executeQuery(                      // execute Query
               "select NAME from Customer");
      System.out.println("Results of the query:");
      System.out.println("select NAME from Customer");

      while (r.next()) {                   // retrieve members of the ResultSet
        System.out.println(r.getString(1));
      }
    }
    catch (SQLException e) {
      System.err.println("We died at place B");
      throw new RuntimeException("SQL Exception " + e.getMessage());
    }
    finally {
      if( dbconnection != null ) {  // close connection if open
        try {
          dbconnection.close();
          System.out.println("Database connection closed");
        }
        catch ( SQLException e ) {
          System.err.println("We died at place C");
          e.printStackTrace();
        }
      }
    }
  }
}