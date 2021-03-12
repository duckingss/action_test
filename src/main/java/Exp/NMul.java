package Exp;


import javax.print.attribute.standard.MediaSize;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.stream.Collectors;

public class NMul extends Node {

    ArrayList<Node> lis;

    public NMul(Node x, Node y) {
        lis = new ArrayList<Node>() {{
            add(x);
            add(y);
        }};
    }

    public NMul(ArrayList<Node> mlis) {
        lis = new ArrayList<Node>() {{
            addAll(mlis);
        }};
    }

    public Node Helper(Node a, Node b) {
        if (a.getClass().equals(NVar.class)) {
            if (((NVar) a).constCheck(myZero))
                return new NVar("0");
            else if (((NVar) a).constCheck(myOne))
                return b;
            else if (((NVar) a).constCheck(myNeg))
                return new NNeg(b);
        }
        return null;
    }

    @Override
    public Node simpify() {
        ArrayList<Node> mlis = lis.stream().filter(x -> !(x.getClass().equals(NVar.class) && ((NVar) x).constCheck(myOne))).
                map(Node::simpify).sorted().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Node> nmlis = new ArrayList<>();
        for (Node nd : mlis) {
            //ToDO 为0时加入0
            if (nd.getClass().equals(NVar.class) && ((NVar) nd).constCheck(myZero)) {
                nmlis = new ArrayList<Node>() {{
                    add(new NVar("0"));
                }};
                break;
            }
            if (nmlis.isEmpty())
                nmlis.add(nd);
            else if (nd.getClass().equals(NVar.class)) {
                Node nmd = nmlis.remove(nmlis.size() - 1);
                nmlis.add(((NVar) nmd).Operate("mult", (NVar) nd));
            } else
                nmlis.add(nd);
        }
        if (nmlis.size() == 0)
            return new NVar("1");
        if (nmlis.size() == 1)
            return nmlis.get(0);
        return new NMul(nmlis);
    }

    @Override
    public Node diff() {
        ArrayList<Node> mlis = new ArrayList<Node>() {{
            addAll(lis);
        }};
        NAdd add = new NAdd(new ArrayList<Node>());
        for (int i = 0; i < lis.size(); i++) {
            mlis.remove(i);
            int finalI = i;
            add.append(new NMul(new ArrayList<Node>() {{
                addAll(mlis);
                add(lis.get(finalI).diff());
            }}));
            mlis.add(i, lis.get(i));
        }
        return add;
    }

    @Override
    public Node reform() {
        ArrayList<Node> mlis = new ArrayList<>();
        for (Node nd : lis) {
            Node nnd = nd.reform();
            if (nnd.getClass().equals(NMul.class))
                mlis.addAll(((NMul) nnd).lis);
            else
                mlis.add(nnd);
        }
        return new NMul(mlis).simpify();

    }

    @Override
    public String toString() {
        ArrayList<String> slis = new ArrayList<>();
        for (Node nd : lis) {
            if (nd.getClass().equals(NAdd.class))
                slis.add("(" + nd.toString() + ")");
            else
                slis.add(nd.toString());
        }
        return String.join("*", slis);
    }
}
