package ru.topper.graphs.entities;

/** Edge interface inplementation has to be used in the lib */
public interface Edge {
 // From ID vertex      
    void setStart(Long startID);
    Long getStart();
// To ID vertex      
    void setFinish(Long finishID);
    Long getFinish();
// Edge weight, optional (default 1)   
    default void setWeight(Double weight){
        System.out.println("Weight is not need");
    }    
    default Double getWeight(){
        return 1.0 ;
    }
// If edge directed, optional  (default is not directed) 
    default void setDirected(boolean directed){
        System.out.println("Direction is not need");
    }
    default boolean isDirected(){
        return false;
    }
// Need to copy edge to start and finish vertexes    
    Edge clone();
    
}
