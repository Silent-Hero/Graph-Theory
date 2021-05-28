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

/**
 *  Description edge set the weight, destination to another vertex , 
 *  weight of the edge.
 */
public class Edge {

    private Vertex src; // source of the vertex where it is located
    private Vertex desti; // vertex destinantion to another vertex
    private int weight; // weight of the edge
    private boolean isTempCircuit; // use to calculate the final circuit 
    private boolean isFinalCircuit; // checks to see if it is a final circuit 

    Edge(Vertex src, Vertex desti, int weight) {
        this.src = src;
        this.desti = desti;
        this.weight = weight;
        this.isFinalCircuit = false;
        this.isTempCircuit = false; 
    }

    public Vertex getSrc() {
        return src;
    }

    public void setSrc(Vertex src) {
        this.src = src;
    }

    public Vertex getDesti() {
        return desti;
    }

    public void setDesti(Vertex desti) {
        this.desti = desti;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFinalCircuit() {
        return isFinalCircuit;
    }

    public void setFinalCircuit(boolean finalCircuit) {
        isFinalCircuit = finalCircuit;
    }

    public boolean isTempCircuit() {
        return isTempCircuit;
    }

    public void setTempCircuit(boolean tempCircuit) {
        isTempCircuit = tempCircuit;
    }


/**
 * 
 * @param edge
 * @return 
 */
    public boolean isEqual(Edge edge) {
        if (edge.getSrc().getName() == src.getName() && 
                edge.getDesti().getName() == desti.getName()) 
        {
            return true;
        } else {
            return false;
        }
    }
}
    

