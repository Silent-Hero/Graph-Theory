/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.fahy;

import java.util.ArrayList;

/**
 *
 * @author SILVER
 */
//1) Sort the edges from lowest cost to highest cost
//2) Add edges to your circuit one at a time in order of increasing cost
//3) Skip edges that would cause you to have 3 edges at a single vertex or 
//create circuit that does not include all vertices
//4) Repeat until graph includes all vertices.

public class SortedEdges {
// Main entry point to start the sorted edges class 
    public static void sortEdges(WeightGraph wg) {
        startQuicksortEdges(wg);
        addEdges(wg);
    }
// Sorting edges in the graph 
    private static void startQuicksortEdges(WeightGraph wg) {
        quicksort(wg.getEdgelist(), 0, wg.getEdgelist().length - 1);
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
     * Swapping the numbers in the array to arrange them in order
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
     * add edges to final circuit to see if there applicable
     * @param wg 
     */
    public static void addEdges(WeightGraph wg) {
        for (Edge edge : wg.getEdgelist()) {
            //Check if either end has 2 vertices
            if (getEdgesFromPoint(wg, edge.getSrc().getName()).size() 
                 < 2 && getEdgesFromPoint(wg, edge.getDesti().getName()).size() 
                    < 2) 
            {
                //Check if creates cycle
                edge.setFinalCircuit(true);
                if (isCycle(wg, edge, true, edge)) {
                    if (isCompleteCircuit(wg)) {
                        //Complete Hamiltonion Circuit
                        wg.setCircuit(true);
                        break;
                    }
                    //Remove temporary addition to final circuit
                    edge.setFinalCircuit(false);

                }
                //Resets the temporary flags set by isCycle()
                wg.resetTempCircuit();
            }
        }
    }

    /**
     * Return all edges from the given vertex 
     * @param wg
     * @param name
     * @return 
     */
    private static ArrayList<Edge> getEdgesFromPoint(WeightGraph wg, int name) 
    {
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
     * checks if is a cycle
     * @param wg
     * @param edge
     * @param isSource
     * @param baseCaseEdge
     * @return 
     */
    private static boolean isCycle(WeightGraph wg, Edge edge,
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
//Check if the next edge is the starting edge.  If so, the edge to be added 
//completes a cycle. Base case reached.
        if (nextEdges.get(0).isEqual(baseCaseEdge)) {
            return true;
        }
 //Check to make sure the src node of tempEdge is different from edge parameter. 
// This is used to make sure the next iteration goes in the right 'direction'
        if (nextEdges.get(0).getSrc().getName() == edge.getSrc().getName() || 
              nextEdges.get(0).getSrc().getName() == edge.getDesti().getName())
        {
            return isCycle(wg, nextEdges.get(0), false, baseCaseEdge);
        } else {
            return isCycle(wg, nextEdges.get(0), true, baseCaseEdge);
        }
    }

    /**
     * Removes an edge from a list of edges.  Used to remove the current edge 
     * from the list of next edges
     * @param edges
     * @param pruneEdge 
     */
    private static void pruneNextEdges(ArrayList<Edge> edges, Edge pruneEdge) {
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
 * Checks if the cycle is complete 
 * @param wg
 * @return 
 */
    private static boolean isCompleteCircuit(WeightGraph wg) {
        int count = 0;
        for (Edge edge : wg.getEdgelist()) {
            if (edge.isTempCircuit()) {
                count++;
            }
        }
        if (count == wg.getVertices().length) {
            return true;
        } else {
            return false;
        }
    }
}
