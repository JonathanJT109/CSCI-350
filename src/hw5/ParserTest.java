package hw5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ParserTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIntegers() {
        Parser p = new Parser();
        assertTrue(p.parse("1"));
        assertTrue(p.parse("9"));
        assertTrue(p.parse("56"));
    }

    @Test
    public void testVariables() {
        Parser p = new Parser();
        assertTrue(p.parse("a = 5"));
        assertTrue(p.parse("variable_a = 52 - 3"));
        assertTrue(p.parse("my_number = 42 / 3"));
        assertFalse(p.parse("4 = 5"));
        assertFalse(p.parse("a_variable = "));
    }

    @Test
    public void testParen() {
        Parser p = new Parser();
        assertTrue(p.parse("(1)"));
        assertTrue(p.parse("   (    9     )   "));
        assertFalse(p.parse("(1"));
        assertFalse(p.parse("(1+5) + 1)"));
    }

    @Test
    public void testEval() {
        Parser p = new Parser();
        p.parse("(1)");
        assertEquals("1", p.eval());
        p.parse("   (    9     )   ");
        assertEquals("9", p.eval());
        p.parse("a = 5 / 2");
        assertEquals("a = 2.5", p.eval());

        // Invalid strings
        assertFalse(p.parse("5 +"));
        assertFalse(p.parse("3a = 5"));
        assertFalse(p.parse("3 = 5"));
    }

    @Test
    public void testNumNodes() {
        Parser p = new Parser();
        p.parse("(1)");
        assertSame(1, p.numNodes());
        p.parse("9 + 5 * 8");
        assertSame(5, p.numNodes());
        p.parse("var_a = 3 - 8 + 3");
        assertSame(7, p.numNodes());
    }

    @Test
    public void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        Parser p = new Parser();
        p.parse("(1)");

        System.setOut(new PrintStream(out));
        p.print();

        System.setOut(originalOut);
        int length1 = out.toByteArray().length;

        p.parse("a = (1-3*4)");

        System.setOut(new PrintStream(out));
        p.print();

        System.setOut(originalOut);
        int length2 = out.toByteArray().length;
        assertTrue(length1 < length2);
    }

}
