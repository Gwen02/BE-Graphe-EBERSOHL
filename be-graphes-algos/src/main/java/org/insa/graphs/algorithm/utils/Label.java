package org.insa.graphs.algorithm.utils;

import org.insa.graphs.model.Node;

public class Label{
	private int cost; 
	private boolean mark; 
	private Node father;
	private Node sommet; 
	
	public Label(Node somm) {
		this.mark = false; 
		this.father = null; 
		this.sommet = somm;
		this.cost = 1_000_000_000; 
	}
	
	public Node getSom() {
		return this.sommet;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public boolean getMark() {
		return this.mark;
	}
	
	public void setMark() {
		this.mark = true;
	}
	
	public Node getFather() {
		return this.father;
	}
}