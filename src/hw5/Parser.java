package hw5;

interface Node {
    void print(Integer spacing);

    String eval();

    Integer numNodes();

    default boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

class Leaf implements Node {
    public String value;

    public Leaf(String i) {
        this.value = i;
    }


    public void print(Integer spacing) {
        for (int i = 0; i < spacing; i++) {
            System.out.print(" ");
        }
        if (isInteger(this.value)) {
            System.out.println("Int: " + value);
        } else {
            System.out.println("Identifier: " + value);
        }
    }

    public String eval() {
        return this.value;
    }

    public Integer numNodes() {
        return 1;
    }
}

class Op implements Node {
    public char operator;
    public Node left;
    public Node right;

    public Op(char op, Node left, Node right) {
        this.operator = op;
        this.left = left;
        this.right = right;
    }

    public void print(Integer spacing) {
        left.print(spacing + 2);
        for (int i = 0; i < spacing; i++) {
            System.out.print(" ");
        }
        System.out.println("Op: " + operator);
        right.print(spacing + 2);
    }

    public String eval() {
        if (operator == '=') {
            return left.eval() + " = " + right.eval();
        }
        Integer left_value = Integer.parseInt(left.eval());
        Integer right_value = Integer.parseInt(right.eval());
        if (operator == '+') {
            return String.valueOf(left_value + right_value);
        } else if (operator == '-') {
            return String.valueOf(left_value - right_value);
        } else if (operator == '*') {
            return String.valueOf(left_value * right_value);
        } else if (operator == '/') {
            return String.valueOf((float) left_value / right_value);
        } else {
            System.out.println("Op: " + operator + " NOT IMPLEMENTED");
            return "";
        }
    }

    public Integer numNodes() {
        return 1 + left.numNodes() + right.numNodes();
    }
}

public class Parser {
    Node root;

    public Parser() {
        this.root = null;
    }

    public boolean parse(String input) {
        try {
            root = expr(input.strip());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return root != null;
    }

    public void print() {
        root.print(0);
    }

    public String eval() {
        if (root == null) {
            System.out.println("Last parse was unsuccessful!");
            return "";
        }
        return root.eval();
    }

    public Node expr(String input) throws Exception {
        /*
        <exp> -> <factor>
        <exp> -> <factor> - <factor>
        <exp> -> <factor> + <factor>
         */
        System.out.println("Expr: '" + input + "'");
        if (input.isEmpty()) return null;
        if ("+-/*=".contains(String.valueOf(input.charAt(input.length() - 1)))) return null;

        int equal_loc = input.indexOf("=");
        while (equal_loc != -1 && equal_loc != 0 && equal_loc != input.length() - 1) {
            Node left = factorIdent(input.substring(0, equal_loc).strip());
            Node right = expr(input.substring(equal_loc + 1).strip());
            if (left == null || isInteger(left.eval())) {
                throw new Exception("Invalid string.");
            }
            if (right != null) {
                return new Op('=', left, right);
            }
            equal_loc = input.indexOf("=", equal_loc + 1);
        }

        int minus_loc = input.indexOf("-");
        while (minus_loc != -1 && minus_loc != 0 && minus_loc != input.length() - 1) {
            Node left = expr(input.substring(0, minus_loc).strip());
            Node right = expr(input.substring(minus_loc + 1).strip());
            if (left != null && right != null) {
                return new Op('-', left, right);
            }
            minus_loc = input.indexOf("-", minus_loc + 1);
        }

        int plus_loc = input.indexOf("+");
        while (plus_loc != -1 && plus_loc != 0 && plus_loc != input.length() - 1) {
            Node left = expr(input.substring(0, plus_loc).strip());
            Node right = expr(input.substring(plus_loc + 1).strip());
            if (left != null && right != null) {
                return new Op('+', left, right);
            }
            plus_loc = input.indexOf("+", plus_loc + 1);
        }

        int multi_loc = input.indexOf("*");
        while (multi_loc != -1 && multi_loc != 0 && multi_loc != input.length() - 1) {
            Node left = expr(input.substring(0, multi_loc).strip());
            Node right = expr(input.substring(multi_loc + 1).strip());
            if (left != null && right != null) {
                return new Op('*', left, right);
            }
            multi_loc = input.indexOf("*", multi_loc + 1);
        }

        int div_loc = input.indexOf("/");
        while (div_loc != -1 && div_loc != 0 && div_loc != input.length() - 1) {
            Node left = expr(input.substring(0, div_loc).strip());
            Node right = expr(input.substring(div_loc + 1).strip());
            if (left != null && right != null) {
                return new Op('/', left, right);
            }
            div_loc = input.indexOf("/", div_loc + 1);
        }

        return factor(input);
    }

    public Node factorIdent(String input) throws Exception {
        if (input.isEmpty()) return null;
        boolean match = input.matches("^\\d+[a-zA-Z]+$");

        if (input.contains(" ") || match) {
            throw new Exception("Invalid string.");
        }

        return new Leaf(input);
    }

    public Node factor(String input) throws Exception {
        System.out.println("Factor: '" + input + "'");
        if (input.isEmpty()) return null;
        char[] chars = input.toCharArray();

        if (chars[0] == '(' && chars[input.length() - 1] == ')') {
            return expr(input.substring(1, input.length() - 1).strip());
        }

        for (char c : chars) {
            if ("()".contains(String.valueOf(c))) {
                return null;
            }
        }

        return new Leaf(input);
    }

    public Integer numNodes() {
        return root.numNodes();
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] tests = {"a = (4-3*3)", "5 +"};
        Parser p = new Parser();
        for (String t : tests) {
            System.out.println("\nTrying " + t);
            if (p.parse(t)) {
                System.out.println(t + " Accepted: Value " + p.eval());
                p.print();
                System.out.println("Number of nodes: " + p.numNodes());
            } else {
                System.out.println(t + " NOT Accepted");
            }
        }
    }
}
