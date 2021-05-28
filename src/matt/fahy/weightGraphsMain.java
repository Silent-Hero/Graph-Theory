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
public class weightGraphsMain 
{
   public static void main(String[] args) 
   {

        /* Let us create following weighted graph
            10
       1--------2
       |  \     |
      6|   5\   |15
       |      \ |
       3--------4
           4       */

        // creating a graph and adding vertices and edges
        WeightGraph graph = new WeightGraph(4,5);
        Vertex v1 = graph.addVertex(1);
        Vertex v2 = graph.addVertex(2);
        Vertex v3 = graph.addVertex(3);
        Vertex v4 = graph.addVertex(4);
        graph.addEdge(v1, v2, 10);
        graph.addEdge(v1, v3, 6);
        graph.addEdge(v1, v4, 5);
        graph.addEdge(v3, v4, 4);
        graph.addEdge(v2, v4, 15);
//        SortedEdges.sortEdges(graph);
//        graph.prettyPrintFinalCircuit();

       graph = new WeightGraph(5,10);
       Vertex vA = graph.addVertex(1);
       Vertex vB = graph.addVertex(2);
       Vertex vC = graph.addVertex(3);
       Vertex vD = graph.addVertex(4);
       Vertex vE = graph.addVertex(5);

       graph.addEdge(vA, vB, 18);
       graph.addEdge(vA, vC, 26);
       graph.addEdge(vA, vD, 22);
       graph.addEdge(vA, vE, 20);
       graph.addEdge(vB, vC, 24);
       graph.addEdge(vB, vD, 21);
       graph.addEdge(vB, vE, 13);
       graph.addEdge(vC, vD, 15);
       graph.addEdge(vC, vE, 29);
       graph.addEdge(vD, vE, 31);

       //Kruskals
       System.out.println("Kruskals Algorithm: ");
       Kruskal.kruskalAlgorithm(graph);
       graph.prettyPrintFinalCircuit();
       System.out.println("Total Weight: " + graph.getTotalWeight());
       graph.resetFinalCircuit();


        System.out.println("--------------");

       //Sorted Edges
       System.out.println("Sorted Edges Algorithm: ");
       SortedEdges.sortEdges(graph);
       graph.prettyPrintFinalCircuit();

       System.out.println("Total Weight: " + graph.getTotalWeight());

        if (graph.isCircuit()) {
            System.out.println("Graph is a circuit");
        }

   }
    
}
