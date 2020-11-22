package ex1.src;

import java.util.Objects;

/**
 *   this Class is for NodeInfo, implements given node_info (interface):
 *  this class represents the Node Structure - part of a bigger project that
 *   will help me build the WeightedGraph structure, using several fields and functions:
 *
 *  (int) key - used to easily access every node in the Hashmap.
 *  (int) keygen - used to generate new keys ('ID') for every new node constructed.
 *  (double) tag - this variable is used to hold data for finding shortest path.
 *  (String) info - this String holds data in order to find shortest path.
 *
 *
 *  functions:
 *  Node Data constructor 1: used to construct new NodeData object.
 *  Node Data constructor 2: used to construct a NodeData with pre-chosen key.
 *  getKey func - returns the key of the current node.
 *  getInfo func - function to get a node's (String) info.
 *  setInfo func - function to set a node's (String) info.
 *  getTag func - function to get a node's (int) tag.
 *  setTag func - function to set a node's (int) tag.
 */
public class NodeInfo implements node_info {
    private int key;
    private static int keyGen;
    private String info;
    private double tag;

    /**
     * empty node constructor:
     * generates an empty new node.
     * sets info to "".
     * sets tag to 0.
     * increase keygen by 1 every time a node is being constructed.
     */
    public NodeInfo () {
        this.key=keyGen;
        this.info="";
        this.tag=0;
        keyGen++;
    }

    /**
     * node constructor:
     * generates a new node.
     * sets key as (int) Nkey.
     * sets info to "".
     * sets tag to 0.
     */
    public NodeInfo (int Nkey) {
        this.key=Nkey;
        this.info="";
        this.tag=0;
    }

    /**
     * getKey func:
     * return the key (int) of the current node.
     * note: keys work just like 'id' of each node.
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * getInfo func:
     * use this func to get the Info value of the Node.
     * return the current node Info (String)
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * setInfo func:
     * use this func to set info (String) into the current node.
     * get a String and set it as a new value for 'info'.
     * void func - does not return.
     */
    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    /**
     * getTag func:
     * use this func to get a node's tag data (double)
     * return the current node tag (double)
     */
    @Override
    public double getTag() {
        return this.tag;
    }

    /**
     * setTag func:
     * use this func to set a tag (double) for a node.
     * gets double, set the tag to that new value.
     * void func - does not return.
     */
    @Override
    public void setTag(double t) {
        this.tag=t;
    }

    /**
     * equals boolean func:
     * replaces java default equals func, with a handmade equals,
     * which compares between 2 nodes values.
     * return true if they are identical.
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof node_info)||other == null)
            return false;
        NodeInfo OtherN = (NodeInfo) other;
        return this.key == OtherN.key &&
                Double.compare(OtherN.tag, tag) == 0 &&
                info.equals(OtherN.info);
    }

    //unused func. auto generated.
    @Override
    public int hashCode() {
        return Objects.hash(key, info, tag);
    }

}
