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

import java.util.ArrayList; 
public class GraphUtils {
//check to if it is a cycle 
    public static boolean isCycle(WeightGraph wg, Edge edge, boolean isSource, 
            Edge baseCaseEdge) 
    {
        ArrayList<Edge> nextEdges;
    //Used for testing whether or not circuit is complete circuit or just path
        edge.setTempCircuit(true);

        //Assign the next edge in the right 'direction'.
        if (isSource) {
            nextEdges = getEdgesFromPoint(wg, edge.getSrc().getName());
        } else {
            nextEdges = getEdgesFromPoint(wg, edge.getDesti().getName());
        }

        //Remove currently looked at edge from next edges if it exists
        pruneNextEdges(nextEdges, edge);

 //If there is no next edge is final circuit, no cycle is possible. 
 //Base case reached.
        if (nextEdges.isEmpty()) {
            return false;
        }

//Check if the next edge is the starting edge.  If so, the edge to be added 
//completes a cycle. Base case reached.
        if (nextEdges.get(0).isEqual(baseCaseEdge)) {
            return true;
        }

//Check to make sure the src node of tempEdge is different from edge parameter. 
//This is used to make sure the next iteration goes in the right 'direction'
        if (nextEdges.get(0).getSrc().getName() == edge.getSrc().getName() || 
              nextEdges.get(0).getSrc().getName() == edge.getDesti().getName())
        {
            return isCycle(wg, nextEdges.get(0), false, baseCaseEdge);
        } else {
            return isCycle(wg, nextEdges.get(0), true, baseCaseEdge);
        }
    }
/**
 * Check to see if visits all the vertices 
 * @param wg
 * @param edge
 * @param isSource
 * @param baseCaseEdge
 * @return 
 */
    public static boolean isKruskalCycle(WeightGraph wg, Edge edge, 
            boolean isSource, Edge baseCaseEdge) 
    {
        ArrayList<Edge> nextEdges;
   //Used for testing whether or not circuit is complete circuit or just path
        edge.setTempCircuit(true);

        //Assign the next edge in the right 'direction'.
        if (isSource) {
            nextEdges = getEdgesFromPoint(wg, edge.getSrc().getName());
        } else {
            nextEdges = getEdgesFromPoint(wg, edge.getDesti().getName());
        }

        //Remove currently looked at edge from next edges if it exists
        pruneNextEdges(nextEdges, edge);


//If there is no next edge is final circuit, no cycle is possible. 
//Base case reached.
        if (nextEdges.isEmpty()) {
            return false;
        }


        for (Edge e : nextEdges) {
            if (e.isEqual(baseCaseEdge)) {
                return true;
            }

//Check to make sure the src node of tempEdge is different from edge parameter.
//This is used to make sure the next iteration goes in the right 'direction'
            if (e.getSrc().getName() == edge.getSrc().getName() || 
                    e.getSrc().getName() == edge.getDesti().getName()) 
            {
                if (isKruskalCycle(wg, e, false, baseCaseEdge)) {
                    return true;
                }
            } else {
                if (isKruskalCycle(wg, e, true, baseCaseEdge)) {
                    return true;
                }
            }
        }

        return false;

    }
/**
 * get the name of the vertice and where it is located by it destination
 * @param wg
 * @param name
 * @return 
 */
    public static ArrayList<Edge> getEdgesFromPoint(WeightGraph wg, int name) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (Edge edge : wg.getEdgelist()) {
            if ((edge.getSrc().getName() == name ||
                   edge.getDesti().getName() == name) && edge.isFinalCircuit()) 
            {
                edgeList.add(edge);
            }
        }

        return edgeList;
    }

    /**
     * Removes an edge from a list of edges. 
     * Used to remove the current edge from the list of next edges
     * @param edges
     * @param pruneEdge 
     */
    public static void pruneNextEdges(ArrayList<Edge> edges, Edge pruneEdge) {
        Edge tempEdge = null;
        for (Edge edge : edges) {
            if (edge.isEqual(pruneEdge)) {
                tempEdge = edge;
            }
        }

        if (tempEdge != null) {
            edges.remove(tempEdge);
        }

    }

    /**
     * sorting all the edges in order in the array
     * @param edges
     * @param start
     * @param end
     */
    private static void quicksort(Edge[] edges, int start, int end) {
        if (start < end) {
            swap(edges, end, start + (end - start) / 2);
            int pIndex = pivot(edges, start, end);
            quicksort(edges, start, pIndex - 1);
            quicksort(edges, pIndex + 1, end);
        }
    }
    /**
     * pivot arranges smallest numbers before and largest numbers after it 
     * @param edges
     * @param start
     * @param end
     * @return integer index of partition pivot
     */
    private static int pivot(Edge[] edges, int start, int end) {
        int pIndex = start;
        Edge pivot = edges[end];
        for (int i = start; i < end; i++) {
            if (edges[i].getWeight() < pivot.getWeight()) {
                swap(edges, i, pIndex);
                pIndex++;
            }
        }
        swap(edges, end, pIndex);
        return pIndex;
    }
    /**
     *Swapping the numbers in the array to arrange them in order
     * @param edges
     * @param index1
     * @param index2
     */
    private static void swap(Edge[] edges, int index1, int index2) { 
        Edge temp = edges[index1];
        edges[index1] = edges[index2];
        edges[index2] = temp;
    }
/**
 * sorting edges in the graph
 * @param wg 
 */
    public static void startQuicksortEdges(WeightGraph wg) {
        quicksort(wg.getEdgelist(), 0, wg.getEdgelist().length - 1);
    }
}

    

