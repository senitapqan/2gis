package Algos;

import Graph.Transport;
import Graph.Vertex;
import Graph.Edge;

import java.util.*;

public class ShortestPath {
    public int TimeToInt(String time) {
        int h = (time.charAt(0) - 48) * 10 + (time.charAt(1) - 48);
        int m = (time.charAt(3) - 48) * 10 + (time.charAt(4) - 48);
        return h * 60 + m;
    }
    public boolean TimeValidate(String lifeTime, String time) {

        int curT = TimeToInt(time);

        int timeL = TimeToInt(lifeTime.substring(0, 5));
        int timeR = TimeToInt(lifeTime.substring(6, 11));

        if (timeL <= curT && curT <= timeR) {
            return true;
        }

        return false;
    }

    public double RatioOnEdge(String deadTime, String time) {
        int curT = TimeToInt(time);
        int deadT = TimeToInt(deadTime);
        int dist = Math.abs(curT - deadT);

        return 10 - Math.min(dist / 15, 10);
    }
    public double speedCalc(Transport t, String deadTime, String time) {
        int curT = TimeToInt(time);
        int deadT = TimeToInt(deadTime);
        int dist = Math.abs(curT - deadT);

        int proc = Math.max(80 - dist / 3, 0);

        return t.getSpeed() - (t.getSpeed() * proc) / 100;
    }

    public int getTrafficCongestion(Map<Vertex, Edge> parent, Vertex from, String time) {
        Vertex cur = from;
        int res = 0;
        int cnt = 0;
        while (cur != null) {
            Edge edge = parent.get(cur);
            if (edge == null) {
                break;
            }
            cnt++;
            res += RatioOnEdge(edge.getDeadTime(), time);
            if (edge.getV1() == cur) {
                cur = edge.getV2();
            } else {
                cur = edge.getV1();
            }
        }
        if (cnt == 0) {
            return -1;
        }
        return (res + cnt - 1)/ cnt;
    }

    public ArrayList<Vertex> getPath(Map<Vertex, Edge> parent, Vertex from) {
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex cur = from;
        while (cur != null) {
            path.add(cur);
            Edge edge = parent.get(cur);
            if (edge == null) {
                break;
            }
            if (edge.getV1() == cur) {
                cur = edge.getV2();
            } else {
                cur = edge.getV1();
            }
        }
        Collections.reverse(path);
        return path;
    }

    public String toStringPath(ArrayList<Vertex> path) {
        String res = "";
        for (int i = 0; i + 1 < path.size(); i++) {
            res += path.get(i).getName() + " -> ";
        }
        res += path.get(path.size() - 1).getName() + ".";
        return res;
    }

    public String doubleToTime(double time) {
        int hh = (int)(time);
        int mm = (int)((time - (double)(hh)) * 60);

        String h = hh + "";
        String m = mm + "";

        if (hh < 10) h = "0" + hh;
        if (mm < 10) m = "0" + mm;

        String Time = h + ":" + m;

        return Time;
    }
}


class Pair {
    public double dist;
    public Vertex v;

    public Pair(double dist, Vertex v) {
        this.dist = dist;
        this.v = v;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }


}
