package ex1.src;

import java.util.*;

/**
 *  Class WGraph_DS implements weighted_graph(interface):
 *  this class represents the Graph Structure using NodeInfo.
 *  Each graph holds several variables, using some functions to build or edit the graph in various ways.
 *
 *  HashMap<Integer,node_info> MapG - a useful data structure to hold graph nodes.
 *  HashMap<Integer,HashMap<Integer,Double>> EdgeWeight- hashmap inside a hashmap, in order to follow every node, with edge list+ (double) weight.
 *  int Mcount - holds the number of moves done on the graph.
 *  int numofV - holds number of Vertex of each graph.
 *  int numofE - holds number of Edges of each graph.
 *
 *  functions:
 *  WGraph_DS constructor: used to construct a new Graph_DS object.
 *  getNode func : returns the (NodeInfo) vertex of the graph using a given key.
 *  boolean hasEdge func - true/false according to the graph, true if 2 nodes are neighbors of each other.
 *  Double getEdge func : returns the Weight of the edge between two given keys.
 *  addNode func : add the given node_info as a vertex to the graph.
 *  connect func : connects between 2 nodes inside the graph, as long as possible.
 *  getV() func : returns a Collection of the graph's vertex (node_info).
 *  getV(int) func : returns a Collection of all neighbors of the given key number(node_info).
 *  removeNode func : remove a complete node (also edges connected), and return it to the user(node_info).
 *  removeEdge func : removes a specific edge out of the graph.
 *  nodeSize func : returns how many vertex are inside the graph
 *  edgeSize func : returns how many edges exist in the graph.
 *  getMC func : returs how many actions taken since the graph was built.
 *  equals func: compares between 2 Weighted graphs.
 *
 */
public class WGraph_DS implements  weighted_graph {
    private HashMap<Integer,node_info> MapG;
    private HashMap<Integer,HashMap<Integer,Double>> EdgeWeight;
    private int Mcount;
    private int numofV;
    private int numofE;

    /**
     * empty weighted graph constructor:
     * generates an empty new graph with 0 values of Movecount, vertex and edges.
     * sets an empty HashMap (int,node_info) for all the vertex in the graph.
     */
    public WGraph_DS() {
        this.MapG = new HashMap<Integer, node_info>();
        this.EdgeWeight = new HashMap<>();
        this.Mcount = 0;
        this.numofV = 0;
        this.numofE = 0;
    }

    /**
     * getNode func(int key): return node_info.
     * if the graph contains the node specific key, return this current node else returns null.
     */
    @Override
    public node_info getNode(int key) {
        if(this.MapG.containsKey(key))
            return this.MapG.get(key);
        return null;
    }

    /**
     * hasEdge func: return true/false if an edge between int node1 and int node2 exist.
     * 1. check that both keys are of the graph.
     * 2. check if every each node has the other node as a neighbor inside EdgeWeight (hashmap).
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(this.MapG.containsKey(node1)&&this.MapG.containsKey(node2)) {
            if (this.EdgeWeight.get(node1).containsKey(node2) &&
                    this.EdgeWeight.get(node2).containsKey(node1))
                return true;
        }
        return false;
    }

    /**
     * getEdge func: return (Double) Weight of Edge between int node1 and int node2.
     * 1. check that edge exist.
     * 2. return the Weight of the asked Edge, through EdgeWeight hashmap.
     */
    @Override
    public double getEdge(int node1, int node2) {
        double ans =-1;
        if(this.hasEdge(node1,node2)&&node1!=node2)
        ans= this.EdgeWeight.get(node1).get(node2).doubleValue();
        return ans;
    }

    /**
     * addNode func: gets (int) key, adds a new node with this key to the graph.
     * check if it is a new node to this graph, if so, construct a node.
     * add it to the vertex hashmap, with the same key.
     * also add it to the second hashmap - in order to insert values in the future.
     * count vertex number and actions number.
     */
    @Override
    public void addNode(int key) {
        if(!this.MapG.containsKey(key)) {
            NodeInfo n = new NodeInfo(key);
            this.MapG.put(key, n);
            this.EdgeWeight.put(key, new HashMap<>());
            this.numofV++;
            this.Mcount++;
        }
        else
        {
            System.out.println("nothing is changed, A node with this key already exists");
        }
    }

    /**
     * connect func: connects between 2 given keys (int) of vertex in the graph, with given (double) w - Weight.
     * 1. first check that both vertex exist and aren't equal.
     * 2. see that they are currently not connected
     * 3. connect them by adding each to the other's list inside EdgeWeight Hashmap, with w - as Weight.
     * 4. count edges and MoveCount.
     * 5. if the edge already exists, update the Weight.
     * */
    @Override
    public void connect(int node1, int node2, double w) {
        if(node1!=node2) {
            if (this.MapG.containsKey(node1) && this.MapG.containsKey(node2)) {
                if (!this.EdgeWeight.get(node1).containsKey(node2) && !this.EdgeWeight.get(node2).containsKey(node1)) {
                    this.EdgeWeight.get(node1).put(node2, w);
                    this.EdgeWeight.get(node2).put(node1, w);
                    this.numofE++;
                    this.Mcount++;
                } else if (this.EdgeWeight.get(node1).containsKey(node2) && this.EdgeWeight.get(node2).containsKey(node1)) {
                    if (this.getEdge(node1, node2) != w) {
                        this.EdgeWeight.get(node1).replace(node2, w);
                        this.EdgeWeight.get(node2).replace(node1, w);
                        this.Mcount++;
                    }
                }
            }
        }
    }

    /**
     * getV func: returns a Collection(node_info) of the graph's vertex using values of nodes Hashmap.
     */
    @Override
    public Collection<node_info> getV() {
        return this.MapG.values();
    }

    /**
     * getV (int) func: returns a Collection(node_data) of the given key(int) neighbors list.
     * check if the node exist, if so get all neighbor keys, and add it to the Collection.
     * return the desired Collection.
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        List<node_info> Nlist = new ArrayList<>();
        if(this.MapG.containsKey(node_id)) {
            for (int smurfKey : this.EdgeWeight.get(node_id).keySet()) {
                Nlist.add(this.MapG.get(smurfKey));
            }
        }
        return Nlist;
    }

    /**
     * removeNode func: returns node_info.
     * (node_info) out - holds the removed node.
     * this function carefully checks if the node exist,
     * go through node's neighbors, remove each edge and then the node
     * at last, decrease edges count by counted edges removed.
     * also decrease vertex number of graph.
     * (count MoveCount!)
     * return out (node_info).
     */
    @Override
    public node_info removeNode(int key) {
        node_info out=null;
        if(this.MapG.containsKey(key))
        {
            //remove the node and edges.
            for (node_info smurfRemove: this.getV(key)) {
                this.removeEdge(key,smurfRemove.getKey());
                //System.out.println("smurf "+smurfRemove.getKey()+" removed from "+key);
            }
            //
            out=this.MapG.get(key);
            this.MapG.remove(key);
            this.EdgeWeight.remove(key);
            this.numofV--;
            this.Mcount++;
        }
        return  out;
    }

    /**
     * removeEdge func: this function is void
     * check if this edge exist.
     * if these are different nodes and edge exist, remove each node from other's neighbors list.
     * decrease edge count by 1
     * count MoveCount!
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(this.MapG.containsKey(node1)&&this.MapG.containsKey(node2))
        {
            if(this.EdgeWeight.get(node1).containsKey(node2)&&this.EdgeWeight.get(node1).containsKey(node2))
            {
                this.EdgeWeight.get(node1).remove(node2);
                this.EdgeWeight.get(node2).remove(node1);
                this.numofE--;
                this.Mcount++;
            }
        }
    }

    /**
     * nodeSize func:
     * (int) func, return numofV (vertex number).
     */
    @Override
    public int nodeSize() {
        return this.numofV;
    }

    /**
     * edgeSize func:
     * (int) func, returns numofE (graph's Edges number).
     */
    @Override
    public int edgeSize() {
        return this.numofE;
    }

    /**
     * getMC func:
     * this func returns Mcount - moves done since graph constructed.
     */
    @Override
    public int getMC() {
        return this.Mcount;
    }

    /**
     * equals boolean func:
     * replaces java default equals func, with a handmade equals,
     * which compares between 2 Weighted Graphs.
     * return true if they are identical.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WGraph_DS))
            return false;

        WGraph_DS Other = (WGraph_DS) other;
        if (this.edgeSize() != Other.edgeSize() || this.nodeSize() != Other.nodeSize())
            return false;
        //after making sure graphs have same amount of E,V, now going to compare between nodes.
        Iterator <node_info> ItThis= this.getV().iterator();
        Iterator <node_info> ItOther= Other.getV().iterator();

        while (ItThis.hasNext())
        {
            if(!ItThis.next().equals(ItOther.next()))
                return false;
        }
        //now after making sure every node exists and identical
        //i am going to see if every edge between every node to neighbors are the same.
        for (int nKey : this.MapG.keySet()){
            Iterator<Double> IT1 = this.EdgeWeight.get(nKey).values().iterator();
            Iterator<Double> IT2 = Other.EdgeWeight.get(nKey).values().iterator();
            while (IT1.hasNext()) {
                if (!IT1.next().equals(IT2.next()))
                    return false;
            }
        }
        return  true;
    }

    //some auto generated func. not used.
    @Override
    public int hashCode() {
        return Objects.hash(MapG, EdgeWeight, Mcount, numofV, numofE);
    }
}
