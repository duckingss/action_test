import Exp.*;

public class NewGet {
    private Tokens tks;
    private Token tk;
    public Node Exp;

    NewGet(String s) {
        tks = new Tokens(s);
        tk = tks.getNext();
        Exp = getExp();
        if(tk.tp!= Token.Type.END)
            throw new RuntimeException("UNFINISH");
    }

    public Node getVarFactor() {
        Node Factor;
        if (tk.tp == Token.Type.VAR) {
            Factor = new NVar();
            tk = tks.getNext();
            if (tk.tp == Token.Type.POW) {
                tk = tks.getNext();
                Factor = nodeFactory("pow", Factor, getConsFactor());
            }
        } else if (tk.tp == Token.Type.SIN || tk.tp == Token.Type.COS) {
            String s = tk.tp == Token.Type.SIN ? "sin" : "cos";
            if (tks.getNext().tp != Token.Type.LF)
                throw new RuntimeException("Wrong Format");
            tk = tks.getNext();
            Factor = nodeFactory(s, getFactor());
            if (tk.tp != Token.Type.RF)
                throw new RuntimeException("Wrong Format");
            tk = tks.getNext();
            if (tk.tp == Token.Type.POW) {
                tk = tks.getNext();
                Factor = nodeFactory("pow", Factor, getConsFactor());
            }
        } else {
            throw new RuntimeException("Wrong Format");
        }
        return Factor;
    }

    public Node getConsFactor() {
        int flag = 0;
        if (tk.tp == Token.Type.ADD || tk.tp == Token.Type.MIN) {
            if (tk.tp == Token.Type.MIN)
                flag = 1;
            tk = tks.getNext();
        }
        if (tk.tp != Token.Type.NUM)
            throw new RuntimeException("Wrong Format");
        Node Factor = flag == 1 ? (new NVar("-" + tk.ss)) : new NVar(tk.ss);
        tk = tks.getNext();
        return Factor;
    }

    public Node getExpFactor() {
        return getExp();
    }

    private Node getFactor() {
        if (tk.tp == Token.Type.VAR || tk.tp == Token.Type.SIN || tk.tp == Token.Type.COS) {
            return getVarFactor();
        } else if (tk.tp == Token.Type.LF) {
            tk = tks.getNext();
            Node Factor = getExpFactor();
            if (tk.tp != Token.Type.RF) {
                throw new RuntimeException("Loss RF");
            }
            tk = tks.getNext();
            return Factor;
        } else {
            return getConsFactor();
        }
    }

    private Node getItem() {
        Node Item;
        int flag = 0;
        if (tk.tp == Token.Type.ADD || tk.tp == Token.Type.MIN) {
            if (tk.tp == Token.Type.MIN)
                flag = 1;
            tk = tks.getNext();
        }
        Item = flag == 1 ? nodeFactory("neg", getFactor()) : getFactor();
        //TODO: neg operation
        while (tk.tp == Token.Type.MUL) {
            tk = tks.getNext();
            Item = nodeFactory("mul", Item, getFactor());
        }
        return Item;
    }

    private Node getExp() {
        Node Exp;
        int flag = 0;
        if (tk.tp == Token.Type.ADD || tk.tp == Token.Type.MIN) {
            if (tk.tp == Token.Type.MIN)
                flag = 1;
            tk = tks.getNext();
        }
        Exp = flag == 1 ? nodeFactory("neg", getItem()) : getItem();
        //TODO: neg operation
        while (tk.tp == Token.Type.ADD || tk.tp == Token.Type.MIN) {
            flag = tk.tp == Token.Type.ADD ? 1 : 0;
            tk = tks.getNext();
            Exp = flag == 1 ? nodeFactory("add", Exp, getItem()) : nodeFactory("sub", Exp, getItem());
        }
        return Exp;
    }

    private Node nodeFactory(String s, Node... args) {
//        neg,add,mul,pow,sin,cos,sub
        if (s.equals("neg")) {
            return new NNeg(args[0]);
        } else if (s.equals("add")) {
            return new NAdd(args[0], args[1]);
        } else if (s.equals("sub")) {
            return new NAdd(args[0], new NNeg(args[1]));
        } else if (s.equals("mul")) {
            return new NMul(args[0], args[1]);
        } else if (s.equals("pow")) {
            return new NPow(args[0], args[1]);
        } else if (s.equals("sin")) {
            return new NSin(args[0]);
        } else if (s.equals("cos")) {
            return new NCos(args[0]);
        } else {
            throw new RuntimeException("Wrong Format");
        }
//        return null;
    }
}

