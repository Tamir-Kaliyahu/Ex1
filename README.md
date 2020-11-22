## Weighted Graph
This file contains instructions and info about how to use Weighted Graph object, and all the sub-Classes variables and functions.

### Node-Info Class: implements node_info (interface):
this class represents the Node Structure - part of a bigger project that
will help me build the WeightedGraph structure, using several fields and functions:

#### Variables
 *  (int) key - used to easily access every node in the Hashmap.
 *  (int) keygen - used to generate new keys ('ID') for every new node constructed.
 *  (double) tag - this variable is used to hold data for finding shortest path.
 *  (String) info - this String holds data in order to find shortest path inside a graph.
 
 #### Functions
 *  Node Data constructor 1: used to construct new NodeData object.
 *  Node Data constructor 2: used to construct a NodeData with pre-chosen key.
 *  getKey func - returns the key of the current node.
 *  getInfo func - function to get a node's (String) info.
 *  setInfo func - function to set a node's (String) info.
 *  getTag func - function to get a node's (int) tag.
 *  setTag func - function to set a node's (int) tag.

### WGraph_DS Class: implements weighted_graph(interface):
this class represents the WeightedGraph Structure using NodeInfo.
Each graph holds several variables, using some functions to build or edit the graph in various ways.

#### Variables
 *  HashMap<Integer,node_info> MapG - a useful data structure to hold graph nodes.
 *  HashMap<Integer,HashMap<Integer,Double>> EdgeWeight- hashmap inside a hashmap, in order to follow every node, with edge list+ (double) weight.
 *  int Mcount - holds the number of moves done on the graph.
 *  int numofV - holds number of Vertex of each graph.
 *  int numofE - holds number of Edges of each graph.
 
 #### Functions
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
 
### WGraph_Algo Class: implements weighted_graph_algorithms interface:
every WGraphAlgo holds a WGraphDS, offers different functions to understand facts about the Graph.


#### Variables
* weighted_graph gAlgo - current Wgraph pointer to inspect and run algorithms.
 
#### Functions
 *  WGraph_Algo constructor: used to construct a new WGraph_Algo object, with empty WGraphDS.
 *  init func (graph) : initiate a specific graph to work on.
 *  getGraph func - is used for getting the currently stored graph.
 *  copy func - a function for doing a deep copy of a graph.
 *  isConnected func: boolean func to know if the graph is a 'connected graph'.
 *  shortestPathDist func: function to know the distance(int) between a node to the given node.
 *  shortestPath func: returns a List (if possible) of the nodes which is the shortest way to the given node.
 *  save func: saves the current Graph inside a file containing all data.
 *  load func: reads the data of a graph from file to our program.
