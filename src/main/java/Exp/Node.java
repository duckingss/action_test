package Exp;

import java.math.BigInteger;
import java.util.ArrayList;

public abstract class Node implements Comparable<Node>{
//    ArrayList<Node> lis = new ArrayList<>();
//    public Node getlSon() {
//        return lSon;
//    }
//
//    public void setlSon(Node lSon) {
//        this.lSon = lSon;
//    }
//
//    public Node getrSon() {
//        return rSon;
//    }
//    public abstract Node simpify();
//
//    public void setrSon(Node rSon) {
//        this.rSon = rSon;
//    }

//    public boolean checkConst(Node nd, int x) {
//        return nd.getClass().equals(NConst.class) && ((NConst) nd).check(x);
//
//    }

    public static BigInteger myOne = new BigInteger("1"),
            myZero = new BigInteger("0"), myNeg = new BigInteger("-1");

    public abstract Node diff();

    public abstract Node reform();

    public abstract Node simpify();

    @Override
    public int compareTo( Node o) {
        if (this.getClass().equals(NVar.class)) {
            if (o.getClass().equals(NVar.class))
                return ((NVar) this).pow.compareTo(((NVar) o).pow);
            else
                return -1;
        } else if (o.getClass().equals(NVar.class))
            return 1;
        else
            return 0;
    }
}
