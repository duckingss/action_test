package Exp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class NAdd extends Node {
    ArrayList<Node> lis;

    public NAdd(ArrayList<Node> mlis) {
        lis = new ArrayList<Node>() {{
            addAll(mlis);
        }};
    }

    public NAdd(Node x, Node y) {
        lis = new ArrayList<Node>() {{
            add(x);
            add(y);
        }};
    }

    public void append(Node x) {
        lis.add(x.simpify());
    }


    @Override
    public Node simpify() {
        ArrayList<Node> mlis = lis.stream().filter(x -> !(x.getClass().equals(NVar.class) && ((NVar) x).constCheck(myZero))).
                map(Node::simpify).sorted().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Node> nmlis = new ArrayList<>();
        for (Node nd : mlis) {
            if (nmlis.isEmpty())
                nmlis.add(nd);
            else if (nd.getClass().equals(NVar.class) && ((NVar) nd).pow.equals(
                    ((NVar) nmlis.get(nmlis.size() - 1)).pow)) {
                Node nmd = nmlis.remove(nmlis.size() - 1);
                nmlis.add(((NVar) nmd).Operate("add", (NVar) nd));
            } else
                nmlis.add(nd);
        }
        if(nmlis.size()==0)
            return new NVar("0");
        if (nmlis.size() == 1)
            return nmlis.get(0);
        return new NAdd(nmlis);
    }

    @Override
    public Node diff() {
        ArrayList<Node> mlis = lis.stream().map(Node::diff).
                collect(Collectors.toCollection(ArrayList::new));
        return new NAdd(mlis);
    }

    @Override
    public Node reform() {
        ArrayList<Node> mlis = new ArrayList<>();
        for (Node nd : lis) {
            Node nnd = nd.reform();
            if (nnd.getClass().equals(NAdd.class))
                mlis.addAll(((NAdd) nnd).lis);
            else
                mlis.add(nnd);
        }
        return new NAdd(mlis).simpify();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < lis.size(); i++) {
            if (i == 0 || lis.get(i).getClass().equals(NNeg.class) ||
                    lis.get(i).getClass().equals(NVar.class) && ((NVar) lis.get(i)).num.compareTo(myZero) < 0)
                s.append(lis.get(i).toString());
            else
                s.append("+").append(lis.get(i).toString());
        }
        return s.toString();
    }
}
