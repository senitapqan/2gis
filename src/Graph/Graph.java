package Graph;

import Algos.Trie;

import java.util.*;

public class Graph {
    ArrayList<Vertex> vertices;
    ArrayList<Edge> edges;
    ArrayList<Transport> transports;
    Map<Vertex, ArrayList<Edge>> adj;

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }

    public void setTransports(ArrayList<Transport> transports) {
        this.transports = transports;
    }

    public Map<Vertex, ArrayList<Edge>> getAdj() {
        return adj;
    }

    public void setAdj(Map<Vertex, ArrayList<Edge>> adj) {
        this.adj = adj;
    }

    public Trie getTr() {
        return tr;
    }

    public void setTr(Trie tr) {
        this.tr = tr;
    }

    public int getVerticesId() {
        return verticesId;
    }

    public void setVerticesId(int verticesId) {
        this.verticesId = verticesId;
    }

    public int getEdgesId() {
        return edgesId;
    }

    public void setEdgesId(int edgesId) {
        this.edgesId = edgesId;
    }

    public int getTransportsId() {
        return transportsId;
    }

    public void setTransportsId(int transportsId) {
        this.transportsId = transportsId;
    }

    Trie tr;

    int verticesId;
    int edgesId;
    int transportsId;

    public Graph() {
        this.vertices = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();
        this.transports = new ArrayList<Transport>();

        this.verticesId = 0;
        this.edgesId = 0;
        this.transportsId = 0;

        this.adj = new HashMap<Vertex, ArrayList<Edge>>();

        this.tr = new Trie();
    }

    public void addVertex(Vertex v) {
        this.vertices.add(v);
        this.tr.AddName(v);
        this.verticesId++;
    }
    public void addEdge(Edge e) {
        this.edges.add(e);

        Vertex v1 = e.getV1();
        Vertex v2 = e.getV2();

        if (!adj.containsKey(v1)) {
            adj.put(v1, new ArrayList<Edge>());
        }

        if (!adj.containsKey(v2)) {
            adj.put(v2, new ArrayList<Edge>());
        }

        adj.get(v1).add(e);
        adj.get(v2).add(e);

        this.edgesId++;
    }
    public void addTransport(Transport t) {
        this.transportsId++;
        this.transports.add(t);
    }


}
