package exactSearch.algo;

import sequences.model.Sequence;

public class NaiveSearch<Searchtype extends Sequence> extends BasicSearch<Searchtype> {

    public NaiveSearch(Searchtype Text, Searchtype Pattern){
        super(Text, Pattern);
    }


    public int next() {
        int position = this.lastpos;
        String pattern = this.pattern.getSequence();
        String text = this.text.getSequence();

        // Überprüfe jedes Zeichen
        for (int textpos = position+1; textpos < text.length(); textpos++){
            int i = 0;
            for (int patternpos = 0; patternpos < pattern.length(); patternpos++){
                comparecount++;
                if (text.charAt(textpos+patternpos) == pattern.charAt(patternpos)){
                    i++;
                    if (i==pattern.length()){
                        return textpos;
                    }
                }else{
                    break;
                }
            }
        }
        throw new IndexOutOfBoundsException("Pattern nicht im Text");
    }
}

