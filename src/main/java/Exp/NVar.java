package Exp;

import java.math.BigInteger;

public class NVar extends Node {
    BigInteger num, pow;

    public NVar(BigInteger nnum, BigInteger npow) {
        num = new BigInteger(nnum.toString());
        pow = new BigInteger(npow.toString());
    }

    public NVar(String nnum) {
        num = new BigInteger(nnum);
        pow = new BigInteger("0");
    }

    public NVar() {
        num = new BigInteger("1");
        pow = new BigInteger("1");
    }

    public Node Operate(String op, NVar o) {
        if (op.equals("add")) {
            if (!o.pow.equals(pow))
                throw new RuntimeException("NOT COMPATABLE POW");
            return new NVar(num.add(o.num), pow);
        } else if (op.equals("mult")) {
            return new NVar(num.multiply(o.num), pow.add(o.pow));
        } else
            throw new RuntimeException("UNRECOGINEZE　OPERATE");
    }

    public boolean constCheck(BigInteger x) {
        if (!pow.equals(myZero)) return false;
        return num.equals(x);
    }


    @Override
    public Node simpify() {
        return new NVar(new BigInteger(num.toString()),new BigInteger(pow.toString()));
    }

    @Override
    public Node diff() {
        if (pow.equals(myZero))
            return new NVar("0");
        return new NVar(num.multiply(pow), pow.subtract(myOne));
    }

    @Override
    public Node reform() {
        return simpify();
    }

    @Override
    public String toString() {
        if(num.equals(myZero))
            return "0";
        if (pow.equals(myZero))
            return num.toString();

        String l,r;
//        num
        if (num.equals(myOne))
            l = "";
        else if (num.equals(myNeg))
            l = "-";
        else
            l = num.toString() + "*";
//        pow
        if (pow.equals(myOne))
            r = "";
        else
            r = "**" + pow.toString();
        return l + "x" + r;
    }
}
