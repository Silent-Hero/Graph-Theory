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

public class WeightGraph {
    private Vertex[] vertices;//making a array to store the number of vertces
    private Edge[] edgelist;// making array to store the number of edges 
    private int vIndex;//determine where in the array to store the next vertex 
    private int edgeIndex;//determine where in the array to store the next edge
    private boolean isCircuit;//to see if is a circuit 

  /**
  * a constructor to have a max size of how many number of vertices and edges
  * @param maxSize
  */
    public WeightGraph (int vertexSize, int edgeSize) {
        this.vertices = new Vertex[vertexSize];
        this.edgelist = new Edge[edgeSize];
        this.vIndex = 0;
        this.edgeIndex = 0;
        this.isCircuit = false;
    }

    /**
     * add vertex to the weigh graphs
     * @param name
     */
    public Vertex addVertex(int name) {
        Vertex v = new Vertex(name);
        vertices[vIndex] = v;
        vIndex++;
        return v;
    }
    /**
     * add edge to the weight graphs
     * @param src
     * @param dest
     * @param weight
     */
    public void addEdge(Vertex src, Vertex dest, int weight) {
        edgelist[edgeIndex] = new Edge(src, dest, weight);
        edgeIndex++;
    }


/**
 * reset temp circuit after every cycle 
 */
    public void resetTempCircuit() {
        for (Edge edge : edgelist) {
            edge.setTempCircuit(false);
        }
    }
//reset final circuit to run both methods at the same time 
    public void resetFinalCircuit() {
        for (Edge edge : edgelist) {
            edge.setFinalCircuit(false);
        }
    }
// prints all the circuits to the screen
    public void prettyPrintFinalCircuit() {
        for (Edge edge : edgelist) {
            if (edge.isFinalCircuit()) {
                System.out.println(edge.getSrc().getName() + " --" + 
                        edge.getWeight() + "-- " + edge.getDesti().getName()
                        + " ");
            }
        }
    }
// get total weight of the graph 
    public int getTotalWeight() {
        int totalWeight = 0;

        for (Edge edge : edgelist) {
            if (edge.isFinalCircuit()) {
                totalWeight = totalWeight + edge.getWeight();
            }
        }

        return totalWeight;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Edge[] getEdgelist() {
        return edgelist;
    }

    public void setEdgelist(Edge[] edgelist) {
        this.edgelist = edgelist;
    }

    public int getvIndex() {
        return vIndex;
    }

    public void setvIndex(int vIndex) {
        this.vIndex = vIndex;
    }

    public int getEdgeIndex() {
        return edgeIndex;
    }

    public void setEdgeIndex(int edgeIndex) {
        this.edgeIndex = edgeIndex;
    }

    public boolean isCircuit() {
        return isCircuit;
    }

    public void setCircuit(boolean circuit) {
        isCircuit = circuit;
    }
}



