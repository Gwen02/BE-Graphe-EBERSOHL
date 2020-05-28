package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;

public class LabelStar extends Label {
	
	private Node node_dest;
	
	private ShortestPathData data;
	
	public LabelStar(Node sommet_courant, Node node_dest, ShortestPathData data) {
		super(sommet_courant);
		this.node_dest = node_dest;
		this.data = data;
	}
	
	public LabelStar(Node sommet_courant) {
		super(sommet_courant);
	}
	
	@Override
	public double getTotalCost() {  //Cas où on cherche le chemin le plus court
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			return this.getCost() + this.getNode().getPoint().distanceTo(this.node_dest.getPoint());
		}
		else { //Cas où on cherche le chemin le plus rapide
			return this.getCost() + this.getNode().getPoint().distanceTo(this.node_dest.getPoint())/(this.data.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
		}
	}
}

