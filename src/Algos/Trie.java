package Algos;

import Graph.Vertex;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }
    public void AddName(Vertex v) {
        Node cur = root;

        String Name = v.getName();

        for (int i = 0; i < Name.length(); i++) {
            Character ch = Name.charAt(i);
            if (!cur.next.containsKey(ch)) {
                cur.next.put(ch, new Node());
                cur = cur.next.get(ch);
            } else {
                cur = cur.next.get(ch);
            }
        }
        cur.setV(v);
    }

    public Vertex get(String Name) {
        Node cur = root;

        for (int i = 0; i < Name.length(); i++) {
            Character ch = Name.charAt(i);
            if (!cur.next.containsKey(ch)) {
                return null;
            }
            cur = cur.next.get(ch);
        }

        return cur.getV();
    }

    public String FindByName(String Name) {
        Vertex v = get(Name);
        if (v == null) {
            return "Sorry, but there is no vertex with such name";
        }
        return "Hey, we found vertex with" + " " + v.getId() + " id and vertex has name" + " " + v.getName() + " and he stored in memory " + v + ".";
    }
}

class Node {
    public Vertex v;
    public Map<Character, Node> next;
    Node () {
        this.v = null;
        this.next = new HashMap<Character, Node>();
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }
}
