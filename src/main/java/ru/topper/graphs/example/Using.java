/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.topper.graphs.example;

import java.util.List;
import java.util.Map;
import ru.topper.graphs.GNet;
import ru.topper.graphs.entities.Edge;
import ru.topper.graphs.entities.UserEdge;
import ru.topper.graphs.entities.UserVertex;
import ru.topper.graphs.entities.Vertex;

/**
 *
 * @author User
 */
public class Using {
    
    private GNet gNet;
    
    public static void main(String[] args) {
        
       Using using = new Using();
       using.fillVertexes();
       using.allVertexes();
       using.fillEdges();



      
//      using.accessVertex(0L);
//      using.accessVertex(1L);
//      using.accessVertex(2L);
//      using.accessVertex(3L);
//      using.accessVertex(4L);
      
      using.traverse(0L);
      
      using.printPath(0L, 4L);
      
 
      
 
      
    }

    public Using() {
        this.gNet = new GNet();
    }
    
    private void fillVertexes(){
        
        gNet.addVertex(new UserVertex(0L, "A"));
        gNet.addVertex(new UserVertex(1L, "B"));
        gNet.addVertex(new UserVertex(2L, "C"));
        gNet.addVertex(new UserVertex(3L, "D"));
        gNet.addVertex(new UserVertex(4L, "E"));

//        gNet.addVertex(new UserVertex(1L, "1"));
//        gNet.addVertex(new UserVertex(2L, "2"));
//        gNet.addVertex(new UserVertex(3L, "3"));
//        gNet.addVertex(new UserVertex(4L, "4"));
//        gNet.addVertex(new UserVertex(5L, "5"));
//        gNet.addVertex(new UserVertex(6L, "6"));
//        gNet.addVertex(new UserVertex(7L, "7"));
//        gNet.addVertex(new UserVertex(8L, "8"));
//        gNet.addVertex(new UserVertex(9L, "9"));
    }
    
    private void fillEdges(){
         
//      using.addEdge(new UserEdge(1L,2L, 10.0, true));
//      using.addEdge(new UserEdge(1L,3L, 6.0, true));
//      using.addEdge(new UserEdge(1L,4L, 8.0, true));
//      
//      using.addEdge(new UserEdge(2L,4L, 5.0, true));
//      using.addEdge(new UserEdge(2L,7L, 11.0, true));
//      using.addEdge(new UserEdge(2L,5L, 13.0, true));
//      
//      using.addEdge(new UserEdge(3L,5L, 3.0, true));
//      
//      using.addEdge(new UserEdge(4L,3L, 2.0, true));
//      using.addEdge(new UserEdge(4L,5L, 5.0, true));
//      using.addEdge(new UserEdge(4L,6L, 7.0, true));
//      using.addEdge(new UserEdge(4L,7L, 12.0, true));   
//      
//      
//      using.addEdge(new UserEdge(5L,6L, 9.0, true));
//      using.addEdge(new UserEdge(5L,9L, 12.0, true));
//      
//      using.addEdge(new UserEdge(6L,8L, 8.0, true));
//      using.addEdge(new UserEdge(6L,9L, 10.0, true));   
//       
//      using.addEdge(new UserEdge(7L,6L, 4.0, true));
//      using.addEdge(new UserEdge(7L,8L, 6.0, true));
//      using.addEdge(new UserEdge(7L,9L, 16.0, true));
//      
//      using.addEdge(new UserEdge(8L,9L, 15.0, true));
      
      addEdge(new UserEdge(0L,1L, 5.0));
      addEdge(new UserEdge(0L,2L, 2.0));
      addEdge(new UserEdge(1L,2L, 2.0));
      addEdge(new UserEdge(1L,4L, 7.0));
      addEdge(new UserEdge(2L,3L, 6.0));
      addEdge(new UserEdge(3L,4L, 1.0));

//      using.addEdge(new UserEdge(0L,1L, 10.0, true));
//      using.addEdge(new UserEdge(0L,3L, 30.0, true));
//      using.addEdge(new UserEdge(0L,4L, 100.0, true));
//      using.addEdge(new UserEdge(1L,2L, 50.0, true));
//      using.addEdge(new UserEdge(2L,3L, 20.0, true));
//      using.addEdge(new UserEdge(2L,4L, 10.0, true));
//      using.addEdge(new UserEdge(3L,4L, 60.0, true));   
        
    }
    
    private void allVertexes(){
        
        for (Map.Entry<Long, Vertex>  entry : gNet.getVertexes().entrySet()) {
            System.out.println(((UserVertex)entry.getValue()).getName()+" "+((UserVertex)entry.getValue()).getID());
        }
    }
    
    private Vertex accessVertex (Long ID){
        
        UserVertex vertex = (UserVertex) gNet.getVertexes().get(ID);
        System.out.println("Name of "+ID+" is "+ vertex.getName());
        System.out.println("Edges:");
        for (Map.Entry<Long, Edge>  entry : vertex.allEdges().entrySet()) {
            UserEdge userEdge = (UserEdge)entry.getValue();
            System.out.println("Dest= " +userEdge.getFinish()+
                    " Weight= "+userEdge.getWeight());
        }
        
        return vertex;
    }
    
    private void addEdge (Edge edge){
        
        gNet.addEdge(edge);
    }
    
    private void printPath(Long start, Long finish){
        
       List<Long> path = gNet.getPath(start, finish);
        System.out.print(" Path ");
       for(int i=0; i<path.size(); i++){
           System.out.print(path.get(i));
           if (i < path.size()-1){
               System.out.print(" - ");
           }
       }
       
       System.out.print(" \n "+ gNet.getPathLenght(path));
    }
    
    private void traverse(Long start){
        
      List<Long> depth =  gNet.depthTraverse();
      System.out.print("\nDepth \t");
      for(Long d : depth){
          System.out.print("\t"+d);
      }
      
      
     List<Long> width =  gNet.breadthTraverse(start);
      System.out.print("\nWidth \t");
      for(Long w : width){
          System.out.print("\t"+w);
      }
      
      System.out.println();
    }
   
    
}
