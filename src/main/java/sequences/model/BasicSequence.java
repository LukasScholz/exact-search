package sequences.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

abstract public class BasicSequence<T> implements Sequence {
    String name;
    String sequence;
    static HashSet<Character> alphabet;
    protected String type;

    @Override
    public String getSequence() {
        return sequence;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public char charAt(int x) {
        return sequence.charAt(x);
    }

    @Override
    public int indexOf(char x) {
        return getAlphabetString().indexOf(x);
    }


    @Override
    public int alphabetSize() {
        return alphabet.size();
    }

    @Override
    public String getAlphabetString() {
        return alphabet.toString().replaceAll("\\W","");
    }

    @Override
    public String getType(){ return this.type;}


    protected void CheckSequence(@NotNull String sequence, HashSet<Character> alphabet) throws Exception{
        for (int i=0; i<sequence.length(); i++) {
            if(!(alphabet.contains(sequence.charAt(i)))) {
                // Invalid Character found!
                throw new Exception("Invalid Character in Sequence at Position: ".concat(String.valueOf(i)));
            }
        }
        // Everything is fine
    }

}
