/* hw4dfa.java
 * <name>
 * <date>
 *
 * <description>
 *
 */

package hw3;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class hw3dfa {

    // You'll need to put your extra functions up here!

    // fn1
    public static Boolean fn1_start(String in) {
        if (in.isEmpty()) return true;
        if (in.charAt(0) == 'a') {
            return fn1_s1(in.substring(1));
        } else if (in.charAt(0) == 'z') {
            return fn1_end(in.substring(1));
        }
        return false;
    }

    public static Boolean fn1_s1(String in) {
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn1_end(in.substring(1));
        return false;
    }

    public static Boolean fn1_end(String in) {
        if (in.isEmpty()) return true;
        if (in.charAt(0) == 'a') return fn1_end(in.substring(1));
        return false;
    }

    public static Boolean fn1(String in) {
        return fn1_start(in);
    }

    // fn 2
    public static Boolean fn2_start(String in) {
        if (in.isEmpty()) return true;
        if (in.charAt(0) == 'a') {
            return fn2_s1(in.substring(1));
        } else if (in.charAt(0) == 'b') {
            return fn2_end(in.substring(1));
        }
        return false;
    }

    public static Boolean fn2_s1(String in) {
        if (in.isEmpty()) return fn2_end("");
        if (in.charAt(0) == 'a') {
            return fn2_s1(in.substring(1));
        } else if (in.charAt(0) == 'b') {
            return fn2_end(in.substring(1));
        }
        return false;
    }

    public static Boolean fn2_end(String in) {
        if (in.isEmpty()) return true;
        if (in.charAt(0) == 'b') {
            return fn2_end(in.substring(1));
        }
        return false;
    }

    public static Boolean fn2(String in) {
        return fn2_start(in);
    }

    // fn3. Used ChatGPT to see the equivalent to char in str
    public static Boolean fn3_start(String in, int n) {
        if (in.isEmpty() && n == 0) return true;
        if (!in.isEmpty() && "abc".contains(String.valueOf(in.charAt(0)))) {
            return fn3_start(in.substring(1), n - 1);
        }
        return false;
    }

    public static Boolean fn3(String in) {
        return fn3_start(in, 4);
    }

    // fn4
    public static Boolean fn4_start(String in, int n) {
        if (in.isEmpty() && n % 2 == 0) return true;
        if (!in.isEmpty() && "abcd".contains(String.valueOf(in.charAt(0)))) {
            return fn4_start(in.substring(1), n + 1);
        }
        return false;
    }

    public static Boolean fn4(String in) {
        return fn4_start(in, 0);
    }

    // fn5
    public static Boolean fn5_start(String in) {
        if (in.isEmpty()) return false;
        // a
        if (in.charAt(0) == 'a') return fn5_s1(in.substring(1));
            // b
        else if (in.charAt(0) == 'b') return fn5_s2(in.substring(1));
        return false;
    }

    // RIGHT
    public static Boolean fn5_s1(String in) {
        // aa
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_s3(in.substring(1));
            // ab
        else if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_s4(in.substring(1));
        return false;
    }

    // RIGHT: FIRST BRANCH
    public static Boolean fn5_s3(String in) {
        // aa[a-b]
        if (!in.isEmpty() && in.charAt(0) == 'a' || in.charAt(0) == 'b') return fn5_s7(in.substring(1));
        return false;
    }

    public static Boolean fn5_s7(String in) {
        // aa[a-b]a
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_s11(in.substring(1));
        return false;
    }

    public static Boolean fn5_s11(String in) {
        // aa[a-b]aa
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_end(in.substring(1));
        return false;
    }

    // RIGHT: SECOND BRANCH
    public static Boolean fn5_s4(String in) {
        // ab[a-b]
        if (!in.isEmpty() && in.charAt(0) == 'a' || in.charAt(0) == 'b') return fn5_s8(in.substring(1));
        return false;
    }

    public static Boolean fn5_s8(String in) {
        // ab[a-b]b
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_s12(in.substring(1));
        return false;
    }

    public static Boolean fn5_s12(String in) {
        // ab[a-b]ba
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_end(in.substring(1));
        return false;
    }

    // LEFT
    public static Boolean fn5_s2(String in) {
        // ba
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_s5(in.substring(1));
        // bb
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_s6(in.substring(1));
        return false;
    }

    // LEFT: FIRST BRANCH
    public static Boolean fn5_s5(String in) {
        // ba[a-b]
        if (!in.isEmpty() && in.charAt(0) == 'a' || in.charAt(0) == 'b') return fn5_s9(in.substring(1));
        return false;
    }

    public static Boolean fn5_s9(String in) {
        // ba[a-b]a
        if (!in.isEmpty() && in.charAt(0) == 'a') return fn5_s13(in.substring(1));
        return false;
    }

    public static Boolean fn5_s13(String in) {
        // ba[a-b]ab
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_end(in.substring(1));
        return false;
    }

    // LEFT: SECOND BRANCH
    public static Boolean fn5_s6(String in) {
        // bb[a-b]
        if (!in.isEmpty() && in.charAt(0) == 'a' || in.charAt(0) == 'b') return fn5_s10(in.substring(1));
        return false;
    }

    public static Boolean fn5_s10(String in) {
        // bb[a-b]b
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_s14(in.substring(1));
        return false;
    }

    public static Boolean fn5_s14(String in) {
        // bb[a-b]bb
        if (!in.isEmpty() && in.charAt(0) == 'b') return fn5_end(in.substring(1));
        return false;
    }

    public static Boolean fn5_end(String in) {
        return in.isEmpty();
    }

    public static Boolean fn5(String in) {
        return fn5_start(in);
    }

    public static void main(String[] args) {
        // Do whatever you want down here to develop your functions above.
        String[] a = {"b", "bbbbbbb"};

        for (String sentence : a) {
            if (fn2(sentence)) {
                System.out.println(sentence + ": IN LANGUAGE");
            } else {
                System.out.println(sentence + ": NOT IN LANGUAGE");
            }
        }
    }

}
