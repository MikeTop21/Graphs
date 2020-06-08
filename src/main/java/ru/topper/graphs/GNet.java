package ru.topper.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import ru.topper.graphs.entities.Edge;
import ru.topper.graphs.entities.Vertex;

/**Main class */
public class GNet {
// Vertex map    
    private final Map<Long,Vertex> vertexes;

    public GNet() {
        vertexes = new TreeMap<>();
    }

// Adding vertex thred safe    
    public void addVertex(Vertex vertex){
        
        synchronized(vertexes){
            
            vertexes.put(vertex.getID(),vertex);
        }
    }
    // Adding edge thred safe   
    public void addEdge(Edge edge){
        
        synchronized(vertexes){
            Long start = edge.getStart();
            Vertex sVertex = vertexes.get(start);
            if(sVertex != null){
                sVertex.addEdge(edge, true);
            }
// if undirected graph then adds edge to the finish vertex too            
            if(!edge.isDirected()){
                Long finish = edge.getFinish();
                 Vertex fVertex = vertexes.get(finish);
                if(fVertex != null){
                    fVertex.addEdge(edge, false);
                }
            }
        }
        
    }

    public synchronized  Map<Long,Vertex> getVertexes() {
        return vertexes;
    }
    
    // returns a list of edges between 2 vertices 
    public List<Long> getPath(Long start, Long finish){
       
        
        List<Long> res = new ArrayList<>();
  //    auxiliary list
        List<DeixtraObj> serving = new ArrayList<>();
// nearest vertexes map        
        Map<Long,DeixtraObj> optimMap = new HashMap<>();
// thread save reading        
       synchronized (vertexes){ 
            // preparation
            for (Map.Entry<Long, Vertex>  entry : vertexes.entrySet()) {
                Vertex userVertex = entry.getValue();
    // If it`s start vertex weight = 0 and the nereast vertex is itself            
                if(userVertex.getID().equals(start)){
                    serving.add(new DeixtraObj(start, 0.0, start));
                }else{
    // For other vertex weight = infinity   and the nereast vertex is unknown               
                    serving.add(new DeixtraObj(userVertex.getID(), Double.MAX_VALUE, null));
                }

            }      
    // why auxiliary is not empty        
            while(serving.size()>0 ){
    // sort auxiliary by weight        
                Collections.sort(serving);

                
    // remove shortes fom auxiliary and put in to answer            
                DeixtraObj minObj = serving.remove(0);
                optimMap.put(minObj.getID(), minObj);

                Vertex minVertex = vertexes.get(minObj.getID());

    // get all edges from nearest            
                 for (Map.Entry<Long, Edge>  entry : minVertex.allEdges().entrySet()) {
                     Long dest = entry.getKey();
                     Double wieght = entry.getValue().getWeight();
    // by all remain vertexes                 
                    for(DeixtraObj dx : serving){
                        if(dx.getID().equals(dest)){
    // if in checking vertex weight more then nearest vertex weight plus path to cheking  
    // then change in checking weight and nearest
                            if( (minObj.getWeight()+wieght) < dx.getWeight()){
                                dx.setNearest(minVertex.getID());
                                dx.setWeight((minObj.getWeight()+wieght));
                                }
                            }
                        }
                    }

            }
       }
        
        // create output list
        res.add(finish);
        Long point = finish;
        while(!point.equals(start)){
            
           point = ((DeixtraObj)optimMap.get(point)).getNearest();
           res.add(point);
        }
        
        Collections.reverse(res);
        
        
        return res;
        
    }
// find path total weight
    public Double getPathLenght (List<Long> path){
        
        Double res = 0.0;
        
        // thread save reading        
       synchronized (vertexes){ 
            for(int i=0; i<path.size()-1; i++){

                Map<Long,Edge> edges =vertexes.get(path.get(i)).allEdges();

                Edge edge = edges.get(path.get(i+1));

                if(edge != null){
                    res += edge.getWeight();
                }else{
                    return null;
                }

            }
       }
        
        return res;
    }
            
     
    
//  Depth-first traversal algorithm, if we want to start from definite vertex    
    public  List<Long> depthTraverse(Long start){
        return depthTraverseGen(start);
    }
//    Depth-first traversal algorithm, if start is no matter
    public  List<Long> depthTraverse(){
        return depthTraverseGen(null);
    }
    
    // Breadth-first traversal algorithm
    private  List<Long> depthTraverseGen(Long start){
        
        List<Long> res = new ArrayList();
        
        // thread save reading        
       synchronized (vertexes){ 
            for (Map.Entry<Long, Vertex>  entry : vertexes.entrySet()) {

                    ((Vertex)entry.getValue()).setVisited(false);

            }
            // if we want to start from definite vertex
            if(start != null){
                Vertex startVertex = vertexes.get(start);
                startVertex.setVisited(true);
                depthRecurse(start, res);
            }

            for (Map.Entry<Long, Vertex>  entry : vertexes.entrySet()) {

                if(!entry.getValue().isVisited()){
                    depthRecurse(entry.getValue().getID(), res);
                }

            }
       }
        
        return res;
    }
    
    private void depthRecurse( Long index,List<Long> res){
        
        Vertex vertex = vertexes.get(index);
        vertex.setVisited(true);
        res.add(index);
        
        
        
        Map<Long,Edge> edges = vertex.allEdges();
        
        for (Map.Entry<Long, Edge>  entry : edges.entrySet()) {
            
            Vertex checkVertex = vertexes.get(entry.getKey());
            if(! checkVertex.isVisited()){
                depthRecurse(entry.getKey(),res);
            }
        }
        
        
    }
    
 
    //Depth-first traversal algorithm
    public List<Long> breadthTraverse(Long start){
        
         
        List<Long> res = new ArrayList();
        
        Deque<Vertex> deque = new ArrayDeque<>();
        // thread save reading        
       synchronized (vertexes){ 
        
            for (Map.Entry<Long, Vertex>  entry : vertexes.entrySet()) {

                    ((Vertex)entry.getValue()).setVisited(false);

            }


            deque.add(vertexes.get(start));

            while(!deque.isEmpty()){

                Vertex vertex = (Vertex)deque.remove();

                if(!vertex.isVisited()){

                    res.add(vertex.getID());
                    vertex.setVisited(true);
                    Map<Long,Edge> edges = vertex.allEdges();    

                    for (Map.Entry<Long, Edge>  entry : edges.entrySet()) {

                        Vertex checkVertex = vertexes.get(entry.getKey());
                        if(! checkVertex.isVisited()){

                            deque.add(checkVertex);
                        }
                    }
                }            
            }
       }
        
        return res;
    }
// inner class for Deixtra path algorithm   
    class DeixtraObj implements Comparable<DeixtraObj>{
        
        private Long ID;
        private Double weight;
        private Long nearest;

        public DeixtraObj(Long ID, Double weight, Long nearest) {
            this.ID = ID;
            this.weight = weight;
            this.nearest = nearest;
        }



        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public Long getNearest() {
            return nearest;
        }

        public void setNearest(Long nearest) {
            this.nearest = nearest;
        }
        
        

        @Override
        public int compareTo(DeixtraObj o) {
            return this.getWeight().compareTo(o.getWeight());
        }
    }
    
   
}
