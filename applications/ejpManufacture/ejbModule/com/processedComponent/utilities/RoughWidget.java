package com.processedComponent.utilities;

import javax.annotation.Resource;

import com.entity.businesslogic.Widget;

@Resource(name = "java:/MSSQLXADS/Rough")
public class RoughWidget extends Widget {

	// public RoughWidget(){
	// }

	public RoughWidget(RawWidget rawWidget) {
		super(rawWidget.getGooList());
	}

	public RoughWidget(String name, String code, RawWidget rawWidget) {
		super(name, "Rough", "Rough" + code);
	}
}
