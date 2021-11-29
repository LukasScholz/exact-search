package exactSearch.algo;

import sequences.model.Sequence;

import java.util.HashMap;

public class BMSearch <Searchtype extends Sequence> extends BasicSearch<Searchtype> {
    private final HashMap<Character, Integer> BadChar;
    private final HashMap<String, Integer> GoodSuffix;

    public BMSearch(Searchtype Text, Searchtype Pattern){
        super(Text, Pattern);

        this.GoodSuffix = createGoodSuffix(this.pattern.getSequence());
        this.BadChar = createBadCharacter(this.pattern.getSequence());

    }

    private HashMap<String, Integer> createGoodSuffix(String Pattern) {
        HashMap<String, Integer> Map = new HashMap<>();

        // Default für Mismatch an erster Stelle
        Map.put("", 1);

        String suffix = "";
        for(int i=0; i<Pattern.length(); i++) {
            Character newcharacter =  (Pattern.charAt(Pattern.length()-1-i));
            // Füge letztes Zeichen des Strings an
            suffix = String.valueOf(newcharacter).concat(suffix);

            // Find last occurrence of suffix
            for(int j=(Pattern.length()-2-i); j>=0; j-- ) {

                if(Pattern.substring(j, (Pattern.length() - 1 - i)).equals(suffix)) {
                   // Suffixe stimmen überein
                    Map.put(suffix, j);
                }
            }
            // Kein Good Suffix gefunden
            Map.put(suffix, 1);
        }

        return Map;
    }

    private HashMap<Character, Integer> createBadCharacter(String Pattern) {
        HashMap <Character, Integer> Map = new HashMap<>();
        for(int i=0; i<Pattern.length(); i++) {
            char currentchar = (Pattern.charAt(Pattern.length()-1-i));
            if (!(Map.containsKey(currentchar))) {
                Map.put(currentchar, i);
            }
        }
        return Map;
    }


    @Override
    public int next() {
        String searchpattern = pattern.getSequence();
        int textpos = this.lastpos+1;
        int patternpos = searchpattern.length()-1;

        while((textpos + searchpattern.length()) <= text.getSequence().length()) {
            comparecount++;
            if (this.text.charAt(textpos + patternpos) == searchpattern.charAt(patternpos)) {
                // Character Matchen
                patternpos--;

                if (patternpos < 0)  return textpos;
            } else {
                // Character Matchen nicht --> Maximum aus BadCharacter und GoodSuffix
                String suffix = searchpattern.substring(patternpos);
                char currentchar = searchpattern.charAt(patternpos);
                var badpos = this.BadChar.get(currentchar);
                var suffixpos = this.GoodSuffix.get(suffix);

                if (badpos == null) {
                    // If Textcharacter is not in Pattern
                    badpos = searchpattern.length();
                }

                textpos += Math.max(badpos, suffixpos);
                patternpos = searchpattern.length() - 1;

            }

        }
        throw new IndexOutOfBoundsException("Pattern nicht im Text");
    }

}
