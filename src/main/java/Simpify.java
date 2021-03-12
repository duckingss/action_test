//import Exp.*;
//import com.sun.org.apache.xpath.internal.operations.Neg;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Simpify implements Comparable<Simpify> {
//    ArrayList<Simpify> lis = new ArrayList<>();
//    Type Op;
//    BigInteger pow, num;
//    Node nd;
//    HashMap<Class, Type> ma = new HashMap<Class, Type>() {{
//        put(NSin.class, Type.Sin);
//        put(NCos.class, Type.Cos);
//        put(Neg.class, Type.Neg);
//        put(NMul.class, Type.Mul);
//        put(NAdd.class, Type.Add);
//        put(NSub.class, Type.Sub);
//    }};
//
//    public Simpify(BigInteger npow, BigInteger nnum) {
//        pow = new BigInteger(npow.toString());
//        num = new BigInteger(nnum.toString());
//    }
//
//    public Simpify(Type mop, ArrayList<Simpify> mlis) {
//        Op = mop;
//        lis.addAll(mlis);
//    }
//
//    public ArrayList<Simpify> Neg(Simpify sp) {
//        return null;
//    }
//
//    public ArrayList<Simpify> dealAddSon(Simpify sp) {
//        ArrayList<Simpify> lis = new ArrayList<>();
//        if (sp.nd.getClass().equals(NNeg.class)) {
//            lis.addAll(Neg(sp));
//        } else if (sp.nd.getClass().equals(NAdd.class)) {
//            lis.addAll(sp.lis);
//        }
//        return lis;
//    }
//
//    public ArrayList<Simpify> dealSubSon(Simpify sp) {
//        ArrayList<Simpify> lis = new ArrayList<>();
//        if (sp.nd.getClass().equals(NNeg.class)) {
//            lis.addAll(sp.lis);
//        } else if (sp.nd.getClass().equals(NAdd.class)) {
//            lis.addAll(Neg(sp));
//        }
//        return lis;
//    }
//
//    public ArrayList<Simpify> dealMul(Simpify sp) {
//        if (sp.nd.getClass().equals(NMul.class))
//            return sp.lis;
//        return new ArrayList<Simpify>() {{
//            add(sp);
//        }};
//    }
//
//    public Simpify NodeToSimpfy(Node nd) {
//        Simpify lson, rson;
//        if (nd.getClass().equals(NConst.class)) {
//            return new Simpify(((NConst) nd).getNum(), new BigInteger("0"));
//        } else if (nd.getClass().equals(NVar.class)) {
//            return new Simpify(new BigInteger("1"), new BigInteger("1"));
//        }
//        //Todo single operator
//        lson = NodeToSimpfy(nd.getlSon());
//        if (nd.getClass().equals(NNeg.class) || nd.getClass().equals(NSin.class) || nd.getClass().equals(NCos.class)) {
//            return new Simpify(ma.get(nd.getClass()), new ArrayList<Simpify>() {{
//                add(lson);
//            }});
//        }
//        //Todo binary operator
//        ArrayList<Simpify> mlis = new ArrayList<>();
//        rson = NodeToSimpfy(nd.getrSon());
//        Type mOp;
//        if (nd.getClass().equals(NPow.class) && lson.Op.equals(Type.Var) && nd.getrSon().getClass().equals(NConst.class)) {
//            return new Simpify(lson.num, lson.pow.multiply(rson.num));
//        } else if (nd.getClass().equals(NAdd.class)) {
//            mlis.addAll(dealAddSon(lson));
//            mlis.addAll(dealAddSon(rson));
//            return new Simpify(Type.Add, mlis);
//        } else if (nd.getClass().equals(NSub.class)) {
//            mlis.addAll(dealAddSon(lson));
//            mlis.addAll(dealSubSon(rson));
//            return new Simpify(Type.Add, mlis);
//        } else if (nd.getClass().equals(NMul.class)) {
//            mlis.addAll(dealMul(lson));
//            mlis.addAll(dealMul(rson));
//            return new Simpify(Type.Mul, mlis);
//        } else {
//            return new Simpify(ma.get(nd.getClass()), new ArrayList<Simpify>() {{
//                add(lson);
//                add(rson);
//            }});
//        }
//    }
//
//    @Override
//    public String toString() {
//        switch (Op){
//            case Add:
//                break;
//            case Sub:
//                break;
//            case Pow:
//                break;
//            case Mul:
//                break;
//            case Var:{
//
//                break;
//            }
//            case Neg:
//                break;
//            case Sin:
//                break;
//            case Cos:
//                break;
//        }
//        return  "sfs";
//    }
//
//    @Override
//    public int compareTo(Simpify o) {
//        if (this.Op.equals(Type.Var)) {
//            if (o.Op.equals(Type.Var))
//                return pow.compareTo(o.pow);
//            else
//                return -1;
//        } else if (o.Op.equals(Type.Var)) {
//            return 1;
//        } else
//            return 0;
//    }
//}
//
//enum Type {
//    Add,
//    Sub,
//    Pow,
//    Mul,
//    Var,
//    Neg,
//    Sin,
//    Cos;
//}
