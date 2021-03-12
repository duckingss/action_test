import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {
    ArrayList<Token> tokens = new ArrayList<>();
    int id = 0;

    Tokens(String s) {
        Pattern pat = Pattern.compile("sin|cos|\\(|\\)|x|\\*\\*|\\*|\\+|-|[0-9]+|" + "(?<sp>[\t ]+)");
        Matcher r = pat.matcher(s);
        int len = s.length();
        while (r.find()) {
            len -= r.group().length();
            if (r.group("sp") == null) {
                tokens.add(new Token(r.group()));
            }
        }
        if (len != 0)
            throw new RuntimeException("Wrong Format");
    }

    public Token getNext() {
        if (id == tokens.size())
            return new Token(Token.Type.END);
        return tokens.get(id++);
    }

//    public Token getPre() {
//        if (id >= tokens.size()) {
//            id--;
//            return null;
//        } else {
//            id -= id >= 0 ? 1 : 0;
//            return tokens.get(id);
//        }
//    }
}

class Token {
    Type tp;
    String ss;

    public enum Type {
        SIN,
        COS,
        LF,
        RF,
        VAR,
        MUL,
        POW,
        ADD,
        MIN,
        NUM,
        END;
    }

    Token(String s) {
        ss = s;
        if (s.equals("sin")) {
            tp = Type.SIN;
        } else if (s.equals("cos")) {
            tp = Type.COS;
        } else if (s.equals("(")) {
            tp = Type.LF;
        } else if (s.equals(")")) {
            tp = Type.RF;
        } else if (s.equals("-")) {
            tp = Type.MIN;
        } else if (s.equals("+")) {
            tp = Type.ADD;
        } else if (s.equals("*")) {
            tp = Type.MUL;
        } else if (s.equals("x")) {
            tp = Type.VAR;
        } else if (s.equals("**")) {
            tp = Type.POW;
        } else {
            tp = Type.NUM;
        }
    }

    Token(Type tp) {
        this.tp = tp;
        ss = "end";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return tp == token.tp && Objects.equals(ss, token.ss);
    }
}