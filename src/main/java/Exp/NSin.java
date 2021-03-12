package Exp;

import java.math.BigInteger;

public class NSin extends Node {
    Node Son;
    public NSin(Node x) {
        Son =x;
    }

    @Override
    public Node simpify() {
        Node mSon = Son.simpify();
        if (mSon.getClass().equals(NVar.class) && ((NVar) mSon).num.equals(new BigInteger("0"))) {
            return new NVar("0");
        }
        return new NSin(mSon);
    }

    @Override
    public Node diff() {
        return new NMul(
                new NCos(Son),
                Son.diff()
        );
    }

    @Override
    public Node reform() {
       Node mSon = Son.reform();
        return  new NSin(mSon).simpify();
    }

    @Override
    public String toString() {
        return "sin" + "(" + Son.toString() + ")";
    }
}
