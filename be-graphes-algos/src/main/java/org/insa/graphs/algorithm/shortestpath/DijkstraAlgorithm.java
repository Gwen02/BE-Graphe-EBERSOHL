import java.util.Iterator;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        //Initialisation du graphe
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
        int nbSommets = graph.size(); //nombre de sommets du graphe
        double[] distances = new double[nbSommets]; //Tableau de distances
        Arrays.fill(distances, Double.POSITIVE_INFINITY); //Remplissage du tableau en +infini
        distances[data.getOrigin().getId()] = 0; //Mise à 0 de l'origine du graphe
        
        notifyOriginProcessed(data.getOrigin()); //Notifie l'obsvervateur du premier élément
        
        Arc[] predecessorArcs = new Arc[nbSommets];
        ShortestPathSolution solution = null;
        
        while(solution == null) {
        	
        }
        return solution;
    }

}
