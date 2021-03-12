import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TokensTest {
    static Tokens tks;

    @BeforeAll
    static void generate() {
        tks = new Tokens("sin (cos(x))**-8 + x**2-5*x");
//        tks = new Tokens("564");
    }

    @Test
    void getNext() {
        ArrayList<Token> myTks = new ArrayList<Token>() {
            {
                add(new Token("sin"));
                add(new Token("("));
                add(new Token("cos"));
                add(new Token("("));
                add(new Token("x"));
                add(new Token(")"));
                add(new Token(")"));
                add(new Token("**"));
                add(new Token("-"));
                add(new Token("8"));
                add(new Token("+"));
                add(new Token("x"));
                add(new Token("**"));
                add(new Token("2"));
                add(new Token("-"));
                add(new Token("5"));
                add(new Token("*"));
                add(new Token("x"));
            }
        };
        for (Token tk : myTks) {
            assertEquals(tks.getNext(), tk);
//            System.out.println(tks.getNext().tp);
        }
    }

}