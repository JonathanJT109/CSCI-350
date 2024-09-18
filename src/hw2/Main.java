package hw2;

import java.util.*;

// TODO: Make parser for String. Include types???
class Rules {
    HashMap<String, ArrayList<String>> bnf = new HashMap<>();

    Rules(HashMap<String, ArrayList<String>> bnf) { this.bnf = bnf; }

    public void generate(String start) throws Exception {
        ArrayList<String> values;
        if (bnf.containsKey(start)) {
            values = bnf.get(start);
        } else {
            System.out.println(start);
            throw new Exception("Value does not exist");
        }

        Random random = new Random();
        int i = random.nextInt(values.size());
        String choice = values.get(i);
        String[] nextStep = choice.split(" ");


        for (String step : nextStep) {
            if (!step.contains("<")) {
                System.out.print(step + " ");
            } else {
                generate(step);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<String, ArrayList<String>> bnf = new HashMap<>();
        bnf.put("<start>", new ArrayList<>(List.of("<sentence>")));
        // Added "<noun> <verb> <punctuation> for more varied results
        bnf.put("<sentence>", new ArrayList<>(List.of("<words> <punctuation>")));
        bnf.put("<words>", new ArrayList<>(Arrays.asList("<article> <ns> <verb>", "<noun> <punctuation>")));
        bnf.put("<ns>", new ArrayList<>(Arrays.asList("<noun>", "<adjective> <noun>")));
        bnf.put("<adjective>", new ArrayList<>(Arrays.asList("fat", "thin", "tall", "smart", "small")));
        bnf.put("<noun>", new ArrayList<>(Arrays.asList("cat", "dog", "tree", "balloon", "ball")));
        bnf.put("<article>", new ArrayList<>(Arrays.asList("The", "A")));
        bnf.put("<punctuation>", new ArrayList<>(Arrays.asList("!", ".", "?")));
        bnf.put("<verb>", new ArrayList<>(Arrays.asList("popped", "slept", "fell", "<verb> <adverb>")));
        bnf.put("<adverb>", new ArrayList<>(Arrays.asList("quickly", "slowly")));

        Rules rules = new Rules(bnf);
        for (int i = 0; i < 20; i++) {
            rules.generate("<start>");
            System.out.println();
        }
    }
}
