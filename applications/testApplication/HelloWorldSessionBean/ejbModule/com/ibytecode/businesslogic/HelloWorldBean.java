package com.ibytecode.businesslogic;
 
import com.ibytecode.business.HelloWorld;
import javax.ejb.Stateless;
 
@Stateless
public class HelloWorldBean implements HelloWorld {
    public HelloWorldBean() {
    }
 
    public String sayHello() {
        return "Hello World !!!";
    }
}