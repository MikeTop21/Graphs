package ru.topper.graphs.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** An vertex implementation, adds variable name and compares by name */
public class UserVertex implements Vertex,Comparable<UserVertex>{
    
    private Long ID;
    private String name;// adds name variable 
    private Map<Long,Edge> edges;
    private boolean visited;

    public UserVertex(Long ID, String name) {
        this.ID = ID;
        this.name = name;
        this.edges = new HashMap<>();
        this.visited = false;
    }
    
    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public Long getID() {
        return this.ID;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean isVisited() {
        return visited;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserVertex other = (UserVertex) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID=" + ID + ", name=" + name ;
    }

    @Override
    public int compareTo(UserVertex o) {
        return name.compareTo(o.getName());
    }

    //Adds an egdge, if it`s reverse edge then swaps  start and finish in the clone
    @Override
    public void addEdge(Edge edge, boolean reverse) {
        
        Edge tEdge = ((UserEdge)edge).clone();
        
        synchronized(edges){
            if(!reverse){
                Long tmp = tEdge.getStart();
                tEdge.setStart(tEdge.getFinish());
                tEdge.setFinish(tmp);
            }
// Edge key is the finish vertex ID            
             edges.put(tEdge.getFinish(), tEdge);
        }
    }

    @Override
    public Map<Long, Edge> allEdges() {
        return edges;
    }
    
}
