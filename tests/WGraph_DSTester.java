package ex1.tests;

import ex1.src.NodeInfo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
class WGraph_DSTester {

    @Test
    void getNode() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        n1.equals(g1.getNode(1));
        assertEquals(n2, g1.getNode(2));
        assertEquals(n3, g1.getNode(3));
        assertEquals(n4, g1.getNode(4));
    }

    @Test
    void hasEdge() {
        WGraph_DS g1 = small_graph();
        assertTrue(g1.hasEdge(3,4));
        assertTrue(g1.hasEdge(2,1));
        assertFalse(g1.hasEdge(1,4));
    }

    @Test
    void getEdge() {
        WGraph_DS g1 = small_graph();
        assertEquals(g1.getEdge(1,3),9.0);
        assertEquals(g1.getEdge(2,5),-1.0);
        assertEquals(g1.getEdge(3,4),11.0);
    }

    @Test
    void addNode() {
        WGraph_DS g1 = small_graph();
        assertFalse(g1.getV().contains(g1.getNode(7)));
        g1.addNode(7);
        assertTrue(g1.getV().contains(g1.getNode(7)));
    }

    @Test
    void connect() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        assertFalse(g1.hasEdge(2,4));
        g1.connect(2,4,5);
        assertTrue(g1.hasEdge(2,4));
    }

    @Test
    void getV() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        Collection <node_info> C1 = new LinkedList <node_info>();
        C1.add(n1);
        C1.add(n2);
        C1.add(n3);
        C1.add(n4);
        assertTrue(g1.getV().contains(n1));
        assertTrue(g1.getV().contains(n2));
        assertTrue(g1.getV().contains(n3));
        assertTrue(g1.getV().contains(n4));
    }

    @Test
    void removeNode() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        g1.removeNode(2);
        assertEquals(g1.nodeSize(),3);
        assertNull(g1.removeNode(2));
    }

    @Test
    void removeEdge() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        g1.connect(1,2,4);
        g1.connect(2,3,5);
        assertEquals(g1.edgeSize(),2);
        g1.removeEdge(1,4);
        assertEquals(g1.edgeSize(),2);
        g1.removeEdge(3,2);
        assertEquals(g1.edgeSize(),1);

    }

    @Test
    void nodeSize() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        node_info n5 = new NodeInfo(5);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        assertEquals(4, g1.nodeSize());
        assertNotEquals(3, g1.nodeSize());
        g1.addNode(n5.getKey());
        assertEquals(5,g1.nodeSize());
    }

    @Test
    void edgeSize() {
        WGraph_DS g1 = new WGraph_DS();
        node_info n1 = new NodeInfo(1);
        node_info n2 = new NodeInfo(2);
        node_info n3 = new NodeInfo(3);
        node_info n4 = new NodeInfo(4);
        g1.addNode(n1.getKey());
        g1.addNode(n2.getKey());
        g1.addNode(n3.getKey());
        g1.addNode(n4.getKey());
        g1.connect(1,2,4);
        g1.connect(2,3,5);
        assertEquals(g1.edgeSize(),2);
        g1.removeEdge(1,4);
        assertEquals(g1.edgeSize(),2);
        g1.removeEdge(3,2);
        assertEquals(g1.edgeSize(),1);
        g1.connect(4,1,5.0);
        assertEquals(g1.edgeSize(),2);
    }

    private WGraph_DS small_graph() {
        WGraph_DS g1 = new WGraph_DS();
        g1.addNode(1);
        g1.addNode(2);
        g1.addNode(3);
        g1.addNode(4);
        g1.addNode(5);
        g1.addNode(6);

        g1.connect(1, 6, 14);
        g1.connect(1, 3, 9);
        g1.connect(1, 2, 7);
        g1.connect(3, 6, 2);
        g1.connect(3, 2, 10);
        g1.connect(3, 4, 11);
        g1.connect(4, 2, 15);
        g1.connect(4, 5, 6);
        g1.connect(5, 6, 9);

        return  g1;
    }
}