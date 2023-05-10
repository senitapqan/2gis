package Algos;


import Graph.Edge;
import Graph.Transport;
import Graph.Vertex;

import java.util.*;

public class Djikstra extends ShortestPath{

    public void djikstra(Map<Vertex, ArrayList<Edge>> adj, Vertex st, Vertex to, String time, Transport tr, Map<Vertex, Double> dist, Map<Vertex, Edge> parent) {

        if (!TimeValidate(tr.getLifeTime(), time)) {
            return;
        }

        dist.put(st, 0.0);
        parent.put(st, null);

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.dist < p2.dist) {
                    return -1;
                }
                if (p1.dist > p2.dist) {
                    return 1;
                }
                return 0;
            }
        });


        pq.add(new Pair(0, st));


        while (!pq.isEmpty()) {

            Pair from = pq.poll();

            if (!adj.containsKey(from.v)) {
                continue;
            }
            if (adj.get(from.v).isEmpty()) {
                continue;
            }

            for (Edge edge : adj.get(from.v)) {
                Vertex v1 = edge.getV1();
                Vertex v2 = edge.getV2();

                if (v2 == from.v) {
                    Vertex temp = v1;
                    v1 = v2;
                    v2 = temp;
                }

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

                if (!dist.containsKey(v2)) {
                    dist.put(v2, dist.get(v1) + len);
                    pq.add(new Pair(dist.get(v2), v2));
                    parent.put(v2, edge);
                } else if (dist.get(v2) > from.dist + len) {
                    dist.put(v2, dist.get(v1) + len);
                    pq.add(new Pair(dist.get(v2), v2));
                    parent.put(v2, edge);
                }
            }
        }
    }
    public void Table1(Map<Vertex, ArrayList<Edge>> adj, Vertex st, Vertex to, String time, Transport tr) {
        Map<Vertex, Double> dist = new HashMap<>();
        Map<Vertex, Edge> parent = new HashMap<>();

        djikstra(adj, st, to, time, tr, dist, parent);

        int congestion = getTrafficCongestion(parent, to, time);

        if (!dist.containsKey(to)) {
            System.out.println("Djikstra: There is no road to" + " " + to.getName() + " from " + st.getName() + " by " + tr.getName());
            return;
        }

        System.out.println("Djikstra:" + " " + doubleToTime(dist.get(to)) + " " + tr.getName() + " " + congestion);
    }

    public void Table2(Map<Vertex, ArrayList<Edge>> adj, Vertex st, Vertex to, String time, Transport tr) {
        Map<Vertex, Double> dist = new HashMap<>();
        Map<Vertex, Edge> parent = new HashMap<>();

        djikstra(adj, st, to, time, tr, dist, parent);


        if (!dist.containsKey(to)) {
            System.out.println("Djikstra: There is no road to" + " " + to.getName() + " from " + st.getName() + " by " + tr.getName());
            return;
        }

        ArrayList<Vertex> path = getPath(parent, to);

        System.out.println("Djikstra:" + " " + st.getName() + " " + doubleToTime(dist.get(to)) + " by " + tr.getName()  + " path:");
        System.out.println(toStringPath(path));
    }


}
