package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
	
	private double cout_estime;
	
	public double getCoutEstime() {
		return this.cout_estime;
		
	}
	
	public double getTotalCost() {
		return this.getCost() + this.getCoutEstime();
	}
	
}


