package exactSearch.io;

import sequences.model.AminoacidSequence;
import sequences.model.AsciiSequence;
import sequences.model.NucleotideSequence;
import sequences.model.Sequence;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class FastaReader {

    String Header;
    String Sequence;

    // Defaultkonstruktor
    public FastaReader() {}

    public FastaReader(String header, String sequence) {
        this.Header = header;
        this.Sequence = sequence;
    }


    public sequences.model.Sequence DateiLesen(String Pfad, String type) throws Exception{
        StringBuilder seq = new StringBuilder();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(Pfad));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert scan != null;
        String h = scan.nextLine();
        if (Fasta.richtigerHeader(h)) {
            Header = h;
        } else {
            throw new IllegalArgumentException(h);
        }
        while (scan.hasNext()) {
            String s = scan.nextLine();
                seq.append(s);
        }
        Sequence = seq.toString();
        scan.close();


        if (type == null) {
            return findSequence();
        }

        return setSequence(type);

    }

    public sequences.model.Sequence findSequence() throws Exception{
        int x = Fasta.SequenceType(Sequence);
        switch (x) {
            case 1:
                return new NucleotideSequence(Header, Sequence);
            case 2:
                return new AminoacidSequence(Header, Sequence);
            case 3:
                return new AsciiSequence(Header, Sequence);
        }
        throw new RuntimeException("Unknown Sequence");
    }

    public sequences.model.Sequence setSequence(String type) throws Exception {
        if (Objects.equals(type, "Nucleotide")) return new NucleotideSequence(Header, Sequence);
        if (Objects.equals(type, "Aminoacid")) return new AminoacidSequence(Header, Sequence);
        return new AsciiSequence(Header, Sequence);
    }
}