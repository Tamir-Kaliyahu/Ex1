package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTester {

    private static Random _rnd = null;

    @Test
    void copy() {
        WGraph_DS g1 = small_graph();
        WGraph_Algo W1 = new WGraph_Algo();
        W1.init(g1);
        weighted_graph g2 = W1.copy();
        assertEquals(g1,g2);
    }

    @Test
    void init() {
        WGraph_DS g1 = small_graph();
        WGraph_Algo W1 = new WGraph_Algo();
        W1.init(g1);
        weighted_graph g2 = W1.getGraph();
        assertEquals(W1.getGraph().nodeSize(),6);
        assertEquals(W1.getGraph().edgeSize(),9);
    }

    @Test
    void getGraph() {
        WGraph_DS g1 = small_graph();
        WGraph_Algo W1 = new WGraph_Algo();
        W1.init(g1);
        assertEquals(W1.getGraph(),g1);
    }

    @Test
    void isConnected() {
        WGraph_DS g1 = new WGraph_DS();
        WGraph_Algo W1 = new WGraph_Algo();
        assertTrue(W1.isConnected());

        g1=small_graph();
        W1.init(g1);
        assertTrue(W1.isConnected());

        g1.removeNode(3);
        g1.removeEdge(4,5);
        g1.removeEdge(4, 2);
        assertFalse(W1.isConnected());

        WGraph_DS g0 = new WGraph_DS();
        WGraph_Algo W0 = new WGraph_Algo();
        g0.addNode(3);
        g0.addNode(2);
        W0.init(g0);
        assertFalse(W0.isConnected());

    }

    @Test
    void shortestPathDist() {
        WGraph_DS g1 = new WGraph_DS();
        WGraph_Algo W1 = new WGraph_Algo();
        g1=small_graph();
        W1.init(g1);
        assertEquals(W1.shortestPathDist(1,5),20.0);
        assertEquals(W1.shortestPathDist(3,5),11.0);
        assertEquals(W1.shortestPathDist(4,2),15.0);
        assertEquals(W1.shortestPathDist(2,5),21.0);
    }

    @Test
    void shortestPath() {
        WGraph_DS g1 = new WGraph_DS();
        g1.addNode(1);
        g1.addNode(2);
        g1.addNode(3);
        g1.addNode(4);
        g1.addNode(5);
        g1.addNode(6);
        for (node_info n1:g1.getV()
             ) {
            System.out.println("key "+n1.getKey()+" is: "+n1);
        }
        System.out.println();

        g1.connect(1, 6, 14);
        g1.connect(1, 3, 19);
        g1.connect(1, 2, 17);
        g1.connect(3, 6, 2);
        g1.connect(3, 2, 10);
        g1.connect(3, 4, 11);
        g1.connect(4, 2, 15);
        g1.connect(4, 5, 6);
        g1.connect(5, 6, 9);

        WGraph_Algo W1 = new WGraph_Algo();
        W1.init(g1);
        System.out.println("\n"+W1.shortestPath(2,6));
        System.out.println("\n"+W1.shortestPath(4,6));
        System.out.println("\n"+W1.shortestPath(5,1));
        List<node_info> Glist1 = new ArrayList<node_info>();
        Glist1.add(g1.getNode(2));
        Glist1.add(g1.getNode(3));
        Glist1.add(g1.getNode(6));
        assertEquals(Glist1,W1.shortestPath(2,6));

        List<node_info> Glist2 = new ArrayList<node_info>();
        Glist2.add(g1.getNode(4));
        Glist2.add(g1.getNode(3));
        Glist2.add(g1.getNode(6));
        assertEquals(Glist2,W1.shortestPath(4,6));

        List<node_info> Glist3 = new ArrayList<node_info>();
        Glist3.add(g1.getNode(5));
        Glist3.add(g1.getNode(6));
        Glist3.add(g1.getNode(1));
        assertEquals(Glist3,W1.shortestPath(5,1));
    }

    @Test
    void dijkstraTag() {
        WGraph_DS g1 = small_graph();
        WGraph_Algo W1 = new WGraph_Algo();
        W1.init(g1);
        W1.DijkstraTag(4);
        for (node_info n :g1.getV()
             ) {
            System.out.println(n.getKey()+" is key, this is tag:"+n.getTag());
        }
        System.out.println();
        assertEquals(g1.getNode(1).getTag(),20.0);
        assertEquals(g1.getNode(2).getTag(),15.0);
        assertEquals(g1.getNode(3).getTag(),11.0);
        assertEquals(g1.getNode(4).getTag(),0.0);
        assertEquals(g1.getNode(5).getTag(),6.0);
        assertEquals(g1.getNode(6).getTag(),13.0);
    }

    public static weighted_graph graph_creator(int Vsize, int Esize, int seed) {
        weighted_graph g1 = new WGraph_DS();
        _rnd = new Random(seed);
        for (int i = 0; i < Vsize; i++) {
            g1.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g1);
        while (g1.edgeSize() < Esize) {
            int a = nextIntRND(0, Vsize);
        }
        return g1;
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

    @Test
    void saveAndLoad() {

    }

    private static int nextIntRND(double min, double max){
        return (int) ((_rnd.nextDouble()*(max-min)+min));
    }
//not implemented dx
            private static int[] nodes(weighted_graph g) {
                int size = g.nodeSize();
                Collection<node_info> V = g.getV();
                node_info[] nodes = new node_info[size];
                V.toArray(nodes); // O(n) operation
                int[] ans = new int[size];
                for(int i=0;i<size;i++) {
                    ans[i] = nodes[i].getKey();
                }
                Arrays.sort(ans);
                return ans;
            }
}