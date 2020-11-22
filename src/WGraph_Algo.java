package ex1.src;

import javax.print.Doc;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * class WGraph_Algo implements weighted_graph_algorithms interface:
 *  every WGraphAlgo holds a WGraphDS, offers different functions to understand facts about the Graph.
 *  (weighted_graph) gAlgo - this is going to be the current graph which is being inspected with the functions.
 *
 * variables:
 * weighted_graph gAlgo - current Wgraph pointer.
 *
 *  functions:
 *  WGraph_Algo constructor: used to construct a new WGraph_Algo object, with empty WGraphDS.
 *  init func (graph) : initiate a specific graph to work on.
 *  getGraph func - is used for getting the currently stored graph.
 *  copy func - a function for doing a deep copy of a graph.
 *  isConnected func: boolean func to know if the graph is a 'connected graph'.
 *  shortestPathDist func: function to know the distance(double) between a node to the given node.
 *  shortestPath func: returns a List (if possible) of the nodes which is the shortest way to the given node.
 *  save func: saves the current Graph inside a file containing all data.
 *  load func: reads the data of a graph from file
 *
 */
public class WGraph_Algo implements weighted_graph_algorithms {
    weighted_graph gAlgo;

    /**
     * WGraph_Algo constructor:
     * construct a weighted graph algorithms object.
     */
    public WGraph_Algo() {
        this.gAlgo = new WGraph_DS();
    }

    /**
     * init func: initiate the graph which all the algorithms are to be activated on.
     */
    @Override
    public void init(weighted_graph g) {
        this.gAlgo = g;
    }

    /**
     * getGraph func:
     * this func returns weighted_graph - the currently initiated graph.
     */
    @Override
    public weighted_graph getGraph() {
        return this.gAlgo;
    }

    /**
     *  copy func: this returns a new graph, deep copied out of the current graph.
     * building identical graph having new nodes and the same properties (IE: edges, vertex)
     */
    @Override
    public weighted_graph copy() {
        WGraph_DS gCopy = new WGraph_DS();
        for (node_info smurf : this.gAlgo.getV()) {
            gCopy.addNode(smurf.getKey());
        }
        for (node_info N1 : gCopy.getV()) {
            for (node_info smurf : this.gAlgo.getV(N1.getKey())) {
                if (this.gAlgo.hasEdge(N1.getKey(), smurf.getKey()))
                    gCopy.connect(smurf.getKey(), N1.getKey(), this.gAlgo.getEdge(smurf.getKey(), N1.getKey()));
            }
        }
        return gCopy;
    }

    /**
     * isConnected func: boolean function
     *  returns true/false depends on whether there's
     *  a connection between every 2 different nodes inside a graph
     *  if there is only 0 or 1 vertex return true.
     *  if vertex (-1) is more than edges in a graph instantly return false
     *  isConnected func runs over all nodes and neighbors, if all are connected, then all of them must be black.
     */
    @Override
    public boolean isConnected() {
        if (this.gAlgo.edgeSize() + 1 < this.gAlgo.nodeSize())
            return false;
        if (this.gAlgo.nodeSize() == 1 || this.gAlgo.nodeSize() == 0)
            return true;
        Queue<node_info> Q1 = new LinkedList<>();
        node_info current = this.gAlgo.getV().iterator().next();
        for (node_info n : this.gAlgo.getV()) {
            n.setInfo("white");
        }
        Q1.add(current);
        while (!Q1.isEmpty()) {
            current = Q1.poll();
            for (node_info nB : this.gAlgo.getV(current.getKey())
            ) {
                if (nB.getInfo().equals("white")) {
                    nB.setInfo("black");
                    Q1.add(nB);
                }
            }
        }
        boolean isCo = true;
        for (node_info n : this.gAlgo.getV()
        ) {
            if (n.getInfo().equals("white"))
                isCo = false;
        }
        return isCo;
    }

    /**
     * shortestPathDist func:
     *  this function gets src and dest - returns (double) the shortest distance between these 2.
     *  if theres no path to dest, return -1.
     */
    @Override
    public double shortestPathDist(int src, int dest) {

        if (this.gAlgo.getNode(src) == null ||
                this.gAlgo.getNode(dest) == null)
            return -1;
        if (src == dest)
            return 0;

        this.DijkstraTag(src);
        return this.gAlgo.getNode(dest).getTag(); // if -1 its ok.
    }

    /**
     * shortestPath func: returns (node_data) List, containing the nodes of the shortest path between src,dest.
     * this function does that by using Dijkstra algorithm.
     * start from dest tag, which holds (double) the shortest way, and by subtracting weight of edges in the way back, could find the true way.
     * everytime a subtraction occurs, check if the new value fits the shortest way from last node.
     * and so on... untiil arrive src, then move all queued nodes into a list.
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        List<node_info> L1 = new LinkedList<node_info>();
        Queue<node_info> q1 = new LinkedList<node_info>();
        if (this.gAlgo.getNode(src) == null ||
                this.gAlgo.getNode(dest) == null)
            return null;
        if (src == dest) {
            L1.add(this.gAlgo.getNode(src));
            return L1;
        }
        this.DijkstraTag(dest);
        for (node_info n : this.gAlgo.getV()) {
            n.setInfo("white");
        }
        double easyWay = this.gAlgo.getNode(src).getTag();

        node_info Current = this.gAlgo.getNode(src);
        while (Current.getTag() != 0) {
            q1.add(Current);
            for (node_info n : this.gAlgo.getV(Current.getKey())) {
                if (n.getTag() + this.gAlgo.getEdge(Current.getKey(), n.getKey()) == Current.getTag() && n.getInfo().equals("white")) {
                    Current = n;
                    //q1.add(n);
                }
            }

            //if(Current.getTag()==-1) // last Addition of src node having -1 in his tag.
            //    q1.add(Current);
            Current.setInfo("black");
        }
        q1.add(Current);
        // move all recorded nodes to a list in the right order.

        while (!q1.isEmpty()) {
            L1.add(q1.poll());
        }
        return L1;
    }

    /**
     * save func: saves the data of the graph inside a file.
     * Creates a file
     * write all nodes of the graph in that file.
     * write all neighbors of each node and the weight of the edge.
     */
    @Override
    public boolean save(String file) {
        boolean created;
        try (FileWriter fw = new FileWriter("graph" + file + ".txt")) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Graph: " + file+"\n");
            bw.write("NodeStart:\n");
            for (node_info n : this.gAlgo.getV()
            ){
                bw.write(n.getKey()+"\n");
            }
            bw.write("NodeEnd:\n");
            for (node_info n : this.gAlgo.getV()
            ) {
                bw.write("node:\n"+n.getKey()+"\n");
                if(this.gAlgo.getV(n.getKey())!=null)
                {
                    bw.write("StartConnect:\n");
                    for (node_info n2:this.gAlgo.getV(n.getKey())
                         ) {
                        bw.write(n2.getKey()+"\n"+this.gAlgo.getEdge(n.getKey(),n2.getKey())+"\n");
                    }
                    bw.write("EndConnect:\n");
                }
            }
            bw.write("EndGraph");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        created = true;
        return created;
    }

    /**
     * load func: loads the data of a previous grpah inside a File.
     * read all nodes of the graph in that file - and build nodes.
     * read all edges and their Weight, and connect the right nodes.
     */
    @Override
    public boolean load(String file) {
        boolean loaded =false;
        try (FileReader fr = new FileReader("graph" + file + ".txt")) {
            BufferedReader bw = new BufferedReader(fr);
            WGraph_DS GLoad = new WGraph_DS();
            String N1 = "NodeStart:";
            String N2 = "NodeEnd:";
            String C1 = "StartConnect:";
            String C2 = "EndConnect:";
            String sLoad = bw.readLine();
            sLoad = bw.readLine();
            sLoad = bw.readLine();
            Double W = 0.0;
            int counterNode=0;

                while (!sLoad.equals(N2)) {
                    int toInt = Integer.parseInt(sLoad);
                    GLoad.addNode(toInt);
                    sLoad = bw.readLine();
                    counterNode++;
                }
            if(sLoad.equals("NodeEnd:")) {
                //System.out.println("finished adding " + counterNode + " nodes");
                sLoad=bw.readLine();
            }
            int nodeK=0;
            int FriendKey=0;
            while(!sLoad.equals("EndGraph"))
            {
                if(sLoad.equals("node:")) {
                    sLoad = bw.readLine();
                    nodeK = Integer.parseInt(sLoad);
                    sLoad=bw.readLine();

                }
                while (!sLoad.equals(C2)) {
                    if(sLoad.equals(C1)) {
                        sLoad=bw.readLine();
                    }
                    FriendKey=Integer.parseInt(sLoad);
                    sLoad=bw.readLine();
                    W=Double.parseDouble(sLoad);
                    GLoad.connect(nodeK,FriendKey,W);
                    sLoad=bw.readLine();
                }
                sLoad=bw.readLine();
            }
            loaded=true;
            this.gAlgo=GLoad;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return loaded;
    }

    //Famous Algorithm, very similar to bfs, but this algorithm is able to recognize shortest way from a src node to all other nodes
    //computing each way and keeping the shortest possible as the (double) value inside a node's tag.
    //read more: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    public void DijkstraTag(int src) {
        Queue<node_info> Q1 = new LinkedList<>();
        node_info current;
        for (node_info n : this.gAlgo.getV()) {
            n.setInfo("white");
            n.setTag(-1);
        }
        current = this.gAlgo.getNode(src);
        current.setTag(0);
        Q1.add(current);
        while (!Q1.isEmpty()) {
            current = Q1.poll();
            int KeyA = current.getKey();
            //System.out.println("now running on cur node: " + current.getKey() + "with tag: " + current.getTag() + " info :" + current.getInfo());
            for (node_info n : this.gAlgo.getV(current.getKey())
            ) {
                int KeyB = n.getKey();
                //System.out.println("now running on node: " + n.getKey() + "with tag: " + n.getTag() + "info :" + n.getInfo());
                if (current.getTag() != (0) && n.getTag() > current.getTag() + this.gAlgo.getEdge(KeyA, KeyB)) {
                    n.setTag(current.getTag() + this.gAlgo.getEdge(KeyA, KeyB));
                    Q1.add(n);
                }
                if (n.getInfo().equals("white")) {
                    if (n.getTag() == -1) {
                        n.setTag(current.getTag() + this.gAlgo.getEdge(KeyA, KeyB));
                        Q1.add(n);
                    }
                    //System.out.println("now finished for on node: " + n.getKey() + "with tag: " + n.getTag() + "info :" + n.getInfo());
                }

            }
            current.setInfo("black");
        }
    }

}