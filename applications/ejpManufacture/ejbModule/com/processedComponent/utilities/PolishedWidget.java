package com.processedComponent.utilities;

import javax.annotation.Resource;

import com.entity.businesslogic.Widget;

@Resource(name = "java:/MSSQLXADS/Polished")
public class PolishedWidget extends Widget {

	public PolishedWidget() {

	}

	public PolishedWidget(RoughWidget roughWidget) {
		super(roughWidget.getGooList());
	}

	public PolishedWidget(String name, String code, RoughWidget roughWidget) {
		super(name, "Polished", "polished" + code);
	}
}
