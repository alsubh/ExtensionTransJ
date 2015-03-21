package com.client;

import java.util.ArrayList;
import java.util.List;
import com.processedComponent.utilities.*;
import com.client.transaction.T1;
import com.client.transaction.T2;
import com.entity.businesslogic.*;
import com.pile.businesslogic.*;

public class ApplicationClient {
	public static void main(String[] args) {
		
		ApplicationClient client = new ApplicationClient();
		client.FillGooPile();
//		client.t1(1,"Raw1", "011", "Rough1","0111","Polished1","01111");
//		client.t1(2,"Raw2", "012", "Rough2","0112","Polished2","01112");
//		client.t1(1,"Raw3", "013", "Rough3","0113","Polished3","01113");
//
//		client.t2("Gadget1", "G01","1111",2 );
//		client.t2("Gadget2", "G02","1111",1 );
//		client.t2("Gadget3", "G03","1111",1);
	}
	
	public void FillGooPile(){
	
		// Fill GooPile 
		Goo goo1= new Goo("Goo1", "B1", 10, "01");
		Goo goo2= new Goo("Goo2", "B2", 30, "02");
		Goo goo3= new Goo("Goo3", "B3", 10, "03");
		Goo goo4= new Goo("Goo4", "B4", 20, "04");
		
		//add new Goo to the DB
		GooPile goopile = new GooPile();
		
		goopile.add(goo1);
		goopile.add(goo2);
		goopile.add(goo3);
		goopile.add(goo4);
		
		System.out.println("Number of Goo in the GooPile : "+ goopile.count());
	}
	
	public void t1(int numberOfGoos,String nameOfRawWidget, String codeOfRawWidget, 
			String nameOfRoughWidget, String codeOfRoughWidget,String nameOfPolishedWidget, String codeOfPolishedWidget )
	{	
		T1 t1= new T1();
		List<Goo> goos= new ArrayList<Goo>();
		goos=t1.getGooFromPile(numberOfGoos);
		
		System.out.println("Number of Goo that required for starting production: "+ goos.size());
		
		RawWidget  raw = new RawWidget();
		raw= t1.getRawWidget(nameOfRawWidget, codeOfRawWidget, goos);
		
		RoughWidget rough= new RoughWidget(raw);
		rough= t1.getRoughWidget(nameOfRoughWidget,codeOfRoughWidget ,raw);
		
		PolishedWidget polished= new PolishedWidget();
		polished= t1.getPolishedWidget(nameOfPolishedWidget, codeOfPolishedWidget, rough);
		
		//t1.putInWidgetPile(polished);
	}
	
	public void t2(String gadgetName, String gadgetCode,String tag, int numberOfRequiredWidgets){
		
		T2 t2 = new T2();
		 
		List<Widget> widgets= t2.getWidget(numberOfRequiredWidgets);
		
		Gadget gadget= new Gadget();
		gadget= t2.getGadget(gadgetName,gadgetCode,widgets);
				
		t2.putTag(gadget,tag);
	}
}