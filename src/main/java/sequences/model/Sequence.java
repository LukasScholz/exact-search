package  sequences.model;

public interface Sequence {
    public String getSequence();
    public String getName();
    public char charAt(int x);
    public int indexOf(char x);
    public int alphabetSize();
    public String getAlphabetString();
    public String getType();
}
