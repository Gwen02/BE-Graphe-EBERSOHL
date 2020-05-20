package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label  implements Comparable<Label> {
	protected Node sommet_courant;
	protected boolean marque;
	protected double cout;
	protected Arc pere;
	
	public Label(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
		marque = false;
		cout = Double.POSITIVE_INFINITY;
		pere = null;
	}
	
	public double getCost() {
		return cout;
	}
	
	public double getTotalCost() {
		return this.getCost();
	}
	
	public Node getNode() {
		return sommet_courant;
	}
	
	public Arc getFather() {
		return pere;
	}
	
	public boolean isMarked() {
		return marque;
	}
	
	public void setFather(Arc pere) {
		
		this.pere = pere;
	}
	
	public void mark() {
		marque = true;
	}
	
	public void setCost(double cout) {
		this.cout = cout;
	}
	
	@Override public int compareTo(Label other) {
		if (this.getTotalCost() < other.getTotalCost()) {
			return -1;
		}
		else if (this.getTotalCost() == other.getTotalCost()) {
			if (this.getCost() < other.getCost()) {
				return -1;
			}
			else if (this.getCost() > other.getCost()) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			return 1;
		}
	}
}


