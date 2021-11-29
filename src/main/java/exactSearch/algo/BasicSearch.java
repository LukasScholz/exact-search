package exactSearch.algo;

import sequences.model.Sequence;

/**
 * Abstrakte Oberklasse zur Entfernung von redundanten Quelltext.
 * Implementierung der Funktionen setPattern, setText und count
 *
 */

public abstract class BasicSearch<Searchtype extends Sequence> implements SearchAlgorithm<Searchtype> {
    public int lastpos;
    protected Sequence pattern;
    protected Sequence text;
    protected int comparecount;

    // Konstruktoren

    // Mit Text und Pattern
    BasicSearch(Searchtype text, Searchtype pattern) {
        this.text = text;
        this.pattern = pattern;
        this.lastpos = -1;
        comparecount = 0;
    }

    @Override
    public void setPattern(Searchtype sequence)  {
        this.pattern = sequence;
    }

    @Override
    public void setText(Searchtype text) {
        this.text = text;
    }

    @Override
    public int count() {
        return count(false, false);
    }

    public int count(boolean debug, boolean position) {
        int amount = 0;

        int currentposition = 0;

        while(currentposition < this.text.getSequence().length() - this.pattern.getSequence().length()) {
            try {
                currentposition = this.next();
                if (position){
                    System.err.println("Match found at: "+(currentposition+1));
                }
                this.lastpos=currentposition;
            }
            catch(IndexOutOfBoundsException IEOOB) {
                if (debug) System.err.printf("Anzahl an Vergleichen: %d%n", comparecount);
                return amount;
            }
            if (currentposition < this.text.getSequence().length()) amount += 1;
        }
        if (debug) System.err.printf("Anzahl an Vergleichen: %d%n", comparecount);
        return amount;
    }

    public boolean testforSequence(Searchtype seq, Searchtype[] sequences) {
        boolean found = false;
        for (Searchtype sequence : sequences) {
            this.setText(sequence);
            this.setPattern(seq);
            try {
                int position = this.next();
                System.out.println("Sequence found at Position " + position + " in ORF from Position" + sequence.getName());
                found = true;
            } catch (Exception ignored) {
            }
        }
        return found;
    }
}
