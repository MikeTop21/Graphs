/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.topper.graphs;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.topper.graphs.entities.UserEdge;
import ru.topper.graphs.entities.UserVertex;

/**
 *
 * @author User
 */
public class GNetTest {
    
    private GNet gNet;
    
    public GNetTest() {
        this.gNet = new GNet();
             
        gNet.addVertex(new UserVertex(0L, "A")); 
        gNet.addVertex(new UserVertex(1L, "B"));
        gNet.addVertex(new UserVertex(2L, "C"));
        gNet.addVertex(new UserVertex(3L, "D"));
        gNet.addVertex(new UserVertex(4L, "E"));
        
        
        gNet.addEdge(new UserEdge(0L,1L, 5.0));
        gNet.addEdge(new UserEdge(0L,2L, 2.0));
        gNet.addEdge(new UserEdge(1L,2L, 2.0));
        gNet.addEdge(new UserEdge(1L,4L, 7.0));
        gNet.addEdge(new UserEdge(2L,3L, 6.0));
        gNet.addEdge(new UserEdge(3L,4L, 1.0));
               
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of addVertex method, of class GNet.
     */
    @Test
    public void testAddVertex() {
        System.out.println("addVertex");
      
        assertEquals( "A", ((UserVertex)gNet.getVertexes().get(0L)).getName());               
        assertEquals( "B", ((UserVertex)gNet.getVertexes().get(1L)).getName());       
        assertEquals( "C", ((UserVertex)gNet.getVertexes().get(2L)).getName());       
        assertEquals( "D", ((UserVertex)gNet.getVertexes().get(3L)).getName());       
        assertEquals( "E", ((UserVertex)gNet.getVertexes().get(4L)).getName());
    }

    /**
     * Test of addEdge method, of class GNet.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
       
        UserVertex uv = (UserVertex) gNet.getVertexes().get(0L);
        assertEquals( (Long)1L, ((UserEdge)uv.allEdges().get(1L)).getFinish());
        assertEquals( (Double)5.0, ((UserEdge)uv.allEdges().get(1L)).getWeight());
        
        uv = (UserVertex) gNet.getVertexes().get(1L);
        assertEquals( (Long)0L, ((UserEdge)uv.allEdges().get(0L)).getFinish());
        assertEquals( (Double)5.0, ((UserEdge)uv.allEdges().get(0L)).getWeight());
        
           
        uv = (UserVertex) gNet.getVertexes().get(3L);
        assertEquals( (Long)2L, ((UserEdge)uv.allEdges().get(2L)).getFinish());
        assertEquals( (Double)6.0, ((UserEdge)uv.allEdges().get(2L)).getWeight());
        assertEquals( (Long)4L, ((UserEdge)uv.allEdges().get(4L)).getFinish());
        assertEquals( (Double)1.0, ((UserEdge)uv.allEdges().get(4L)).getWeight());
        
    }

    /**
     * Test of getVertexes method, of class GNet.
     */
    @Test
    public void testGetVertexes() {
        System.out.println("getVertexes");
        assertEquals(5, gNet.getVertexes().size());
    }

    /**
     * Test of getPath method, of class GNet.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Long start = 0L;
        Long finish = 4L;
        Long[] expResult = new Long[]{0L, 2L, 3L, 4L};
        List<Long> result = gNet.getPath(start, finish);
        for(int i=0; i< result.size(); i++){
            assertEquals(expResult[i], result.get(i));
        }    
    }

    @Test
    public void testGetPathLenght() {
        System.out.println("getPathLenght");
        List<Long> expTest = new ArrayList();
        expTest.add(0L);
        expTest.add(2L);
        expTest.add(3L);
        expTest.add(4L);
        
        Double result = gNet.getPathLenght(expTest);
        assertEquals((Double)9.0, result);
    }    
    
    /**
     * Test of depthTraverse method, of class GNet.
     */
    @Test
    public void testDepthTraverse_Long() {
        System.out.println("depthTraverse");
        Long start = 0L;
        Long[] expResult = new Long[]{0L,1L, 2L, 3L, 4L};
        List<Long> result = gNet.depthTraverse(start);
        for(int i=0; i< result.size(); i++){
            assertEquals(expResult[i], result.get(i));
        }    
    }

    /**
     * Test of depthTraverse method, of class GNet.
     */
    @Test
    public void testDepthTraverse_0args() {
        System.out.println("depthTraverse");
        Long[] expResult = new Long[]{0L,1L, 2L, 3L, 4L};
        List<Long> result = gNet.depthTraverse();
        for(int i=0; i< result.size(); i++){
            assertEquals(expResult[i], result.get(i));
        }    
    }

    /**
     * Test of breadthTraverse method, of class GNet.
     */
    @Test
    public void testBreadthTraverse() {
        System.out.println("breadthTraverse");
        Long start = 0L;
        Long[] expResult = new Long[]{0L,1L, 2L, 4L, 3L};
        List<Long> result = gNet.breadthTraverse(start);
        for(int i=0; i< result.size(); i++){
            assertEquals(expResult[i], result.get(i));
        }    
    }
    
}
