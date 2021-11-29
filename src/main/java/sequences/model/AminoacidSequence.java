package sequences.model;

import java.util.HashSet;

public class AminoacidSequence extends BasicSequence implements Sequence {

    // Konstruktoren
    public AminoacidSequence(String header, String sequence) throws Exception {
        initalphabet();
        this.name = header;
        this.sequence = sequence;
        this.CheckSequence(sequence, alphabet);
        this.type = "AminoacidSequence";
    }


    void initalphabet() {
        alphabet = new HashSet<>();
        alphabet.add('A');
        alphabet.add('R');
        alphabet.add('N');
        alphabet.add('D');
        alphabet.add('C');
        alphabet.add('Q');
        alphabet.add('E');
        alphabet.add('G');
        alphabet.add('H');
        alphabet.add('I');
        alphabet.add('L');
        alphabet.add('K');
        alphabet.add('M');
        alphabet.add('F');
        alphabet.add('P');
        alphabet.add('S');
        alphabet.add('T');
        alphabet.add('W');
        alphabet.add('Y');
        alphabet.add('V');
        alphabet.add('$');
    }
}