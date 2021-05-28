/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.fahy;

/**
 *
 * @author SILVER
 */
public class Kruskal {

/**
 *  applying Kruskal Algorithnm 
 * sorting all the edges to look for smallest edge in the graph on line 20
 * @param wg 
 */
    public static void kruskalAlgorithm(WeightGraph wg) {
        GraphUtils.startQuicksortEdges(wg); 
        addEdges(wg);
    }
/**
 * add edges to the graph 
 * @param wg 
 */
    private static void addEdges(WeightGraph wg) {
        for (Edge edge : wg.getEdgelist()) {
            edge.setFinalCircuit(true);
            if (GraphUtils.isKruskalCycle(wg, edge, true, edge)) {
                edge.setFinalCircuit(false);
            }
        }
    }
}

    

