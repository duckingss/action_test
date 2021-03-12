package Exp;

import java.math.BigInteger;

public class NPow extends Node {
    Node lSon, rSon;

    public NPow(Node l, Node r) {
        lSon = l;
        rSon = r;
    }

    @Override
    public Node simpify() {
        Node mlSon = lSon.simpify(), mrSon = rSon.simpify();
        if (mrSon.getClass().equals(NVar.class) && ((NVar) mrSon).constCheck(myZero)) {
            return new NVar("1");
        }else if (mlSon.getClass().equals(NVar.class) && ((NVar) mlSon).constCheck(myZero)) {
            return new NVar("0");
        }
        else if (mrSon.getClass().equals(NVar.class) && ((NVar) mrSon).constCheck(myOne)) {
            return mlSon;
        }
        else if(mlSon.getClass().equals(NVar.class)&& mrSon.getClass().equals(NVar.class)&&((NVar)mrSon).pow.equals(myZero)){
           return new NVar(((NVar) mlSon).num,((NVar) mrSon).num.multiply(((NVar) mlSon).pow));
        }
        return new NPow(mlSon, mrSon);
    }

    @Override
    public Node diff() {
        return new NMul(
                lSon.diff(),
                new NMul(
                        new NVar(((NVar) rSon).num.toString()),
                        new NPow(lSon, ((NVar) rSon).Operate("add", new NVar("-1"))
                        )));
    }

    @Override
    public Node reform() {
        Node mlSon = lSon.reform(),mrSon = rSon.reform();
        return new NPow(mlSon,mrSon).simpify();
    }

    @Override
    public String toString() {
            return "(" + lSon.toString() + ")" + "**" + rSon.toString();
    }
}
