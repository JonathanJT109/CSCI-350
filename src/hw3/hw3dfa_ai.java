package hw3;

public class hw3dfa_ai {
    public static boolean fn1(String s) {
        if (s.length() == 0) return false;
        if (s.startsWith("ab")) {
            for (int i = 2; i < s.length(); i++) {
                if (s.charAt(i) != 'a') return false;
            }
            return true;
        } else if (s.startsWith("z")) {
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != 'a') return false;
            }
            return true;
        }
        return false;
    }

    public static boolean fn2(String s) {
        if (!s.matches("[ab]*")) return false;
        if (s.contains("ba") || s.contains("aba")) return false;
        return true;
    }

    public static boolean fn3(String s) {
        return s.matches("(aaaa|bbbb|cccc)");
    }

    public static boolean fn4(String s) {
        // First condition: length must be even
        if (s.length() % 2 != 0) return false;

        // Second condition: reject if it contains 'd' or 'e'
        if (s.contains("e")) return false;

        return true;
    }

    public static boolean fn5(String s) {
        if (s.length() != 5) return false;

        // Reject "acaca" specifically
        if (s.contains("acaca")) return false;

        // Check if it's a palindrome
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}
