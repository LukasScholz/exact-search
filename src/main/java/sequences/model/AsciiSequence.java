package sequences.model;

public class AsciiSequence extends BasicSequence implements Sequence{

    @Override
    public int indexOf(char x) {
        return (byte) x;
    }

    @Override
    public int alphabetSize() {
        return 128;
    }

    @Override
    public String getAlphabetString() {
        StringBuilder out = new StringBuilder();
        for(int i=0; i <= 127; i++) {
            char currentchar = (char) i;
            out.append(currentchar);
        }
        return out.toString();
    }

    public AsciiSequence(String header, String sequence) {
        this.name = header;
        this.sequence = sequence;
        this.type = "AsciiSequence";
    }

}
