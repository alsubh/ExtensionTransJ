package com.processedComponent.utilities;

import java.util.List;

import com.entity.businesslogic.*;

public class RawWidget extends Widget {
	
	public RawWidget(){
		
	}
	
	public RawWidget(List<Goo> goolist)
	{
		super(goolist);
	}
	
	public RawWidget(String name, String code, List<Goo> gooList)
	{
		super(name, "Raw", "Raw"+code, gooList);	
	}
	
}
