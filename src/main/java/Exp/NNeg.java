package Exp;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NNeg extends Node {
    Node Son;

    public NNeg(Node x) {
        Son = x;
    }

    @Override
    public Node simpify() {
        Node mSon = Son.simpify();
        if (mSon.getClass().equals(NVar.class)) {
            return new NVar(((NVar) mSon).num.negate(), ((NVar) mSon).pow);
        } else if (mSon.getClass().equals(NNeg.class)) {
            return ((NNeg) mSon).Son;
        }
        return new NNeg(mSon);
    }

    @Override
    public Node diff() {
        return new NNeg(Son.diff());
    }

    @Override
    public Node reform() {
        Node mSon = Son.reform();
        if (mSon.getClass().equals(NAdd.class)) {
            return new NAdd(((NAdd) mSon).lis.stream().map(NNeg::new).
                    collect(Collectors.toCollection(ArrayList::new))).simpify();
        }
        return new NNeg(mSon).simpify();
    }

    @Override
    public String toString() {
        if (Son.getClass().equals(NAdd.class)) {
            return "-(" + Son.toString() + ")";
        } else {
            return "-" + Son.toString();
        }
    }
}
