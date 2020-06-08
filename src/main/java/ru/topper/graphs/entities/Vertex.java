
package ru.topper.graphs.entities;

import java.util.Map;

/** Vertex interface inplementation has to be used in the lib */
public interface Vertex {
 // Vertex ID       
    void setID(Long ID);
    Long getID ();
// Vertex degree map (edges map where key is finish vertex ID)    
    void addEdge(Edge edge, boolean reverse);
    Map<Long,Edge> allEdges();
// Check visited vertex for traverse algorithm    
    boolean isVisited();
    void setVisited(boolean vis);
}
