package Algos;

import Graph.Edge;
import Graph.Transport;
import Graph.Vertex;

import java.util.*;

public class FordBellman extends ShortestPath{
    public void FordBellman(ArrayList<Vertex> vertices, ArrayList<Edge> edges, Vertex st, Vertex to, String time, Transport tr, Map<Vertex, Double> dist, Map<Vertex, Edge> parent) {

        if (!TimeValidate(tr.getLifeTime(), time)) {
            return;
        }

        dist.put(st, 0.0);
        parent.put(st, null);

        for (int step = 1; step < vertices.size(); step++) {
            for (Edge edge : edges) {
                Vertex v1 = edge.getV1();
                Vertex v2 = edge.getV2();

                boolean ok = false;
                for (Transport t : edge.getTr()) {
                    if (Objects.equals(t.getId(), tr.getId())) {
                        ok = true;
                    }
                }
                if (!ok) {
                    continue;
                }
                double speed = speedCalc(tr, edge.getDeadTime(), time);
                double len = edge.getLen() / speed;

                if (!dist.containsKey(v1) && !dist.containsKey(v2)) {
                    continue;
                }
                else if (!dist.containsKey(v1)) {
                    dist.put(v1, dist.get(v2) + len);
                    parent.put(v1, edge);
                }
                else if (!dist.containsKey(v2)) {
                    dist.put(v2, dist.get(v1) + len);
                    parent.put(v2, edge);
                }
                else if (dist.get(v2) > dist.get(v1) + len) {
                    dist.put(v2, dist.get(v1) + len);
                    parent.put(v2, edge);
                }
                else if (dist.get(v1) > dist.get(v2) + len) {
                    dist.put(v1, dist.get(v2) + len);
                    parent.put(v1, edge);
                }
            }
        }
    }
    public void Table1(ArrayList<Vertex> vertices, ArrayList<Edge> edges, Vertex st, Vertex to, String time, Transport tr) {
        Map<Vertex, Double> dist = new HashMap<>();
        Map<Vertex, Edge> parent = new HashMap<>();

        FordBellman(vertices, edges, st, to, time, tr, dist, parent);

        int congestion = getTrafficCongestion(parent, to, time);

        if (!dist.containsKey(to)) {
            System.out.println("FordBellman: There is no road to" + " " + to.getName() + " from " + st.getName() + " by " + tr.getName());
            return;
        }

        System.out.println("FordBellman:" + " " + doubleToTime(dist.get(to)) + " " + tr.getName() + " " + congestion);
    }

    public void Table2(ArrayList<Vertex> vertices, ArrayList<Edge> edges, Vertex st, Vertex to, String time, Transport tr) {
        Map<Vertex, Double> dist = new HashMap<>();
        Map<Vertex, Edge> parent = new HashMap<>();

        FordBellman(vertices, edges, st, to, time, tr, dist, parent);

        if (!dist.containsKey(to)) {
            System.out.println("FordBellman: There is no road to" + " " + to.getName() + " from " + st.getName() + " by " + tr.getName());
            return;
        }

        ArrayList<Vertex> path = getPath(parent, to);

        System.out.println("FordBellman:" + " " + st.getName() + " " + doubleToTime(dist.get(to)) + " by " + tr.getName()  + " path:");
        System.out.println(toStringPath(path));
    }


}
