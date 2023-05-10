import Algos.*;
import Graph.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Start {
    public static void main(String[] args) {
        Graph g = new Graph();  ///graph object

        Djikstra dj = new Djikstra();  ///djikstra
        FordBellman fd = new FordBellman();  ///fordBellman

        Vertex sdu = new Vertex("SDU", 1);
        Vertex kbtu = new Vertex("KBTU", 2);

        g.addVertex(sdu);
        g.addVertex(kbtu);

        g.addVertex(new Vertex("Aport", g.getVerticesId()));
        g.addVertex(new Vertex("Sairan", g.getVerticesId()));
        g.addVertex(new Vertex("MegaCenter", g.getVerticesId()));
        g.addVertex(new Vertex("MegaPark", g.getVerticesId()));
        g.addVertex(new Vertex("AltynOrda", g.getVerticesId()));
        g.addVertex(new Vertex("Vokzal", g.getVerticesId()));
        g.addVertex(new Vertex("Park Pervogo Prezidenta", g.getVerticesId()));
        g.addVertex(new Vertex("Arbat", g.getVerticesId()));
        g.addVertex(new Vertex("DostykPlaza", g.getVerticesId()));
        g.addVertex(new Vertex("ShymBulak", g.getVerticesId()));
        g.addVertex(new Vertex("Maxima", g.getVerticesId()));


        Transport bus = new Transport(1, "BUS", 50.0, "09:00-21:00");
        Transport taxi = new Transport(2, "TAXI", 70.0, "09:00-21:00");
        Transport metro = new Transport(3, "METRO", 130.0, "09:00-21:00");
        Transport walk = new Transport(4, "WALK", 6.0, "09:00-21:00");

        g.addTransport(bus);
        g.addTransport(taxi);
        g.addTransport(metro);
        g.addTransport(walk);

        ArrayList<Transport> tr1 = new ArrayList<>();
        ArrayList<Transport> tr2 = new ArrayList<>();
        ArrayList<Transport> tr3 = new ArrayList<>();
        ArrayList<Transport> tr4 = new ArrayList<>();
        ArrayList<Transport> tr5 = new ArrayList<>();

        ArrayList<ArrayList<Transport>> trs = new ArrayList<>();

        tr1.add(bus);
        tr2.add(taxi);
        tr3.add(metro);
        tr4.add(walk);

        tr5.add(bus);
        tr5.add(taxi);

        trs.add(tr1);
        trs.add(tr2);
        trs.add(tr3);
        trs.add(tr4);
        trs.add(tr5);

        for (int i = 0; i < 150; i++) {
            Vertex v1 = g.getVertices().get((int)(Math.random() * 12));
            Vertex v2 = g.getVertices().get((int)(Math.random() * 12));

            double len = Math.random() * 100;

            ArrayList<Transport> t = trs.get((int)(Math.random() * 4));

            int minute = (int)(Math.random() * 1440);

            int hours = minute / 60;
            int minutes = hours % 60;

            String hh = hours + "";
            String mm = minutes + "";

            if (hours < 10) hh = "0" + hours;
            if (minutes < 10) mm = "0" + minutes;

            String time = hh + ":" + mm;

            String name = v1.getName() + "-" + v2.getName();

            g.addEdge(new Edge(g.getEdgesId(), name, v1, v2, len, t, time));
        }

        for (Vertex v : g.getVertices()) {
            if (v == sdu) {
                continue;
            }
            for (Transport tr : g.getTransports()) {
                dj.Table2(g.getAdj(), v, sdu, "15:00", tr);
            }
        }

        for (Transport tr : g.getTransports()) {
            dj.Table1(g.getAdj(),sdu, kbtu, "13:00", tr);
        }

        System.out.println(g.getTr().FindByName("SDU"));
        System.out.println(g.getTr().FindByName("Sdu"));

        for (Transport tr : g.getTransports()) {
            fd.Table1(g.getVertices(), g.getEdges(),sdu, kbtu, "13:00", tr);
        }
    }


}
