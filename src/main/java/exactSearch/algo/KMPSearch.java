package exactSearch.algo;

import sequences.model.Sequence;

import java.util.HashMap;

public class KMPSearch <Searchtype extends Sequence> extends BasicSearch<Searchtype> {
    private final HashMap<Integer, Integer> PREFIXTABLE;


    public KMPSearch(Searchtype Text, Searchtype Pattern){
        super(Text, Pattern);
        this.PREFIXTABLE = createPrefixtable(this.pattern.getSequence());
    }

    private HashMap<Integer, Integer> createPrefixtable(String sequence) {
        // init
        HashMap<Integer, Integer> output = new HashMap<>();
        int position = 0;
        int prefixlength = -1;
        output.put(position, prefixlength);


        while (position < sequence.length()) {
            while (prefixlength >= 0 && (!(sequence.charAt(prefixlength) == sequence.charAt(position)))) {
                prefixlength = output.get(prefixlength);
            }

            prefixlength++;
            position++;
            output.put(position, prefixlength);
        }
        return output;
    }


    @Override
    public int next() {
        String searchpattern = this.pattern.getSequence();
        String text = this.text.getSequence();
        int textpos = this.lastpos+1;
        int patternpos = 0;

        while (textpos < text.length()) {
            // Textende nicht erreicht
            comparecount++;
            while ((patternpos >= 0) && (!(text.charAt(textpos) == searchpattern.charAt(patternpos))) ) {
                // Zeichen stimmen überein
                patternpos = this.PREFIXTABLE.get(patternpos);
            }
            textpos++;
            patternpos++;

            if (patternpos == searchpattern.length()) {
                return (textpos - searchpattern.length());
            }

        }
        throw new IndexOutOfBoundsException("Pattern nicht im Text");
    }
}
