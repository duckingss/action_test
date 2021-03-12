package Exp;

import java.math.BigInteger;
import java.util.Objects;

public class NCos extends Node{
    Node Son;
    public NCos(Node x) {
        Son = x;
    }

    @Override
    public Node simpify() {
        Node mSon =  Son.simpify();
        if (mSon.getClass().equals(NVar.class) && Objects.equals(((NVar) mSon).num, new BigInteger("0"))){
            return new NVar("1");
        }
        return new NCos(mSon);
    }

    @Override
    public Node diff() {
        return new NMul(
                new NNeg(new NSin(Son)),
                Son.diff()
        );
    }

    @Override
    public Node reform() {
       Node mSon = Son.reform();
       return new NCos(mSon).simpify();
    }

    @Override
    public String toString() {
        return "cos" + "(" + Son.toString() + ")";
    }
}
