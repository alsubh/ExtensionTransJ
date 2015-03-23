package com.processedComponent.utilities;

import java.util.List;

import com.entity.businesslogic.*;

public class RoughWidget extends Widget {
	
//	public RoughWidget(){
//	}
	
	
	public RoughWidget(RawWidget rawWidget)
	{
		super(rawWidget.getGooList());
	}
	
	public RoughWidget(String name, String code, RawWidget rawWidget)
	{
		super(name, "Rough", "Rough"+code);	
	}	
}
