package Graph;

import java.util.ArrayList;

public class Edge {

    int id;
    String name;

    Vertex v1;
    Vertex v2;

    double len;

    ArrayList<Transport> tr;

    String deadTime; ///hh:mm

    public Edge(int id, String name, Vertex v1, Vertex v2, double len, ArrayList<Transport> tr, String deadTime) {
        this.id = id;
        this.name = name;
        this.v1 = v1;
        this.v2 = v2;
        this.len = len;
        this.tr = tr;
        this.deadTime = deadTime;
    }

    public String getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public double getLen() {
        return len;
    }

    public void setLen(double len) {
        this.len = len;
    }

    public ArrayList<Transport> getTr() {
        return tr;
    }

    public void setTr(ArrayList<Transport> tr) {
        this.tr = tr;
    }
}
