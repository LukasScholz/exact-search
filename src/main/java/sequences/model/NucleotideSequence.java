package sequences.model;

import exactSearch.algo.BasicSearch;
import exactSearch.algo.KMPSearch;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NucleotideSequence extends BasicSequence implements Sequence{


    // Konstruktoren
    public NucleotideSequence(String header, String sequence) throws Exception{
        initalphabet();
        this.name = header;
        this.sequence = sequence;
        this.CheckSequence(sequence, this.alphabet);
        this.type = "NucleotideSequence";
    }

    void initalphabet() {
        this.alphabet = new HashSet();
        alphabet.add('A');
        alphabet.add('C');
        alphabet.add('G');
        alphabet.add('T');
        alphabet.add('a');
        alphabet.add('c');
        alphabet.add('g');
        alphabet.add('t');
    }



    // Zusatzmethoden

    public AminoacidSequence translate(int Leseraster) {
        String in = this.sequence.substring(Leseraster % 3);
        String aminoseq = translateSequence(in);

        try {
            return new AminoacidSequence(this.getName(), aminoseq);
        }
        catch (Exception E) { }

        // Unreachable
        return null;
    }

    private String translateSequence(@NotNull String input) {
        input = input.toUpperCase();


        // Hashmap für Translationen
        Map<String, String> translationtable = new HashMap<>();

        /* Translationsübersetzungen */
        // A--
        translationtable.put("AAA", "F");
        translationtable.put("AAG", "F");
        translationtable.put("AAT", "L");
        translationtable.put("AAC", "L");
        translationtable.put("AGA", "S");
        translationtable.put("AGG", "S");
        translationtable.put("AGT", "S");
        translationtable.put("AGC", "S");
        translationtable.put("ATA", "Y");
        translationtable.put("ATG", "Y");
        translationtable.put("ATT", "$");
        translationtable.put("ATC", "$");
        translationtable.put("ACA", "C");
        translationtable.put("ACG", "C");
        translationtable.put("ACT", "$");
        translationtable.put("ACC", "W");
        // G--
        translationtable.put("GAA", "L");
        translationtable.put("GAG", "L");
        translationtable.put("GAT", "L");
        translationtable.put("GAC", "L");
        translationtable.put("GGA", "P");
        translationtable.put("GGG", "P");
        translationtable.put("GGT", "P");
        translationtable.put("GGC", "P");
        translationtable.put("GTA", "H");
        translationtable.put("GTG", "H");
        translationtable.put("GTT", "Q");
        translationtable.put("GTC", "Q");
        translationtable.put("GCA", "R");
        translationtable.put("GCG", "R");
        translationtable.put("GCT", "R");
        translationtable.put("GCC", "R");
        // T--
        translationtable.put("TAA", "I");
        translationtable.put("TAG", "I");
        translationtable.put("TAT", "I");
        translationtable.put("TAC", "M");
        translationtable.put("TGA", "T");
        translationtable.put("TGG", "T");
        translationtable.put("TGT", "T");
        translationtable.put("TGC", "T");
        translationtable.put("TTA", "N");
        translationtable.put("TTG", "N");
        translationtable.put("TTT", "K");
        translationtable.put("TTC", "K");
        translationtable.put("TCA", "S");
        translationtable.put("TCG", "S");
        translationtable.put("TCT", "R");
        translationtable.put("TCC", "R");
        //C--
        translationtable.put("CAA", "V");
        translationtable.put("CAG", "V");
        translationtable.put("CAT", "V");
        translationtable.put("CAC", "V");
        translationtable.put("CGA", "A");
        translationtable.put("CGG", "A");
        translationtable.put("CGT", "A");
        translationtable.put("CGC", "A");
        translationtable.put("CTA", "D");
        translationtable.put("CTG", "D");
        translationtable.put("CTT", "E");
        translationtable.put("CTC", "E");
        translationtable.put("CCA", "G");
        translationtable.put("CCG", "G");
        translationtable.put("CCT", "G");
        translationtable.put("CCC", "G");

        StringBuilder output = new StringBuilder();

        int laenge = input.length();

        for(int i=0; i<laenge-2; i = i+3) {
            String codon = ""+input.charAt(i) + input.charAt(i+1) +input.charAt(i+2);
            String aminoacid = translationtable.get(codon);
            if(aminoacid == "$") return output.toString();
            output.append(aminoacid);
        }
        return output.toString();
    }

    public AminoacidSequence[] create_all_AS_fromDNA(NucleotideSequence sequence) throws Exception {
        String seq = sequence.sequence;
        NucleotideSequence pattern = new NucleotideSequence(">Start-Codon", "TAC");
        KMPSearch kmp = new KMPSearch(sequence, pattern);
        ArrayList<Integer> position = new ArrayList<>();
        try {
            while (true){
                int found = (kmp.next());
                position.add(found);
                kmp.lastpos = found;

            }
        }catch (Exception ignored){        }

        AminoacidSequence[] aminoacidsequences = new AminoacidSequence[position.size()];

        for (int i=0; i<position.size();i++){
            String translate = seq.substring(position.get(i));
            aminoacidsequences[i] = new AminoacidSequence(">".concat(String.valueOf(position.get(i))),translateSequence(translate));
        }

        return aminoacidsequences;
    }


    public NucleotideSequence complement() {
        String complementary = sequence;
        char[] chars = complementary.toCharArray();

        for(int i=0; i<complementary.length(); i++) {

            switch (chars[i]) {
                case 'A':
                    chars[i] = 'T';
                    break;
                case 'C':
                    chars[i] = 'G';
                    break;
                case 'G':
                    chars[i] = 'C';
                    break;
                case 'T':
                    chars[i] = 'A';
                    break;
                case 'a':
                    chars[i] = 't';
                    break;
                case 'c':
                    chars[i] = 'g';
                    break;
                case 'g':
                    chars[i] = 'c';
                    break;
                case 't':
                    chars[i] = 'a';
                    break;

            }
        }


        String out = String.valueOf(chars);

        try {
            return new NucleotideSequence(this.getName(), out);
        } catch (Exception e) {}

        // Unreachable
        return null;
    }

    public void trans_find_start(Sequence text, Sequence pattern, BasicSearch searchalgo, boolean continues) throws Exception {
            NucleotideSequence tempseq = null;
            try {
                tempseq = (NucleotideSequence) text;
            }
            catch (ClassCastException E) {System.out.println("Text muss eine Nukleotidsequenz sein!");}
            if (continues) System.out.println("Pattern gefunden auf Matritzenstrang: " +searchalgo.testforSequence(pattern, tempseq.create_all_AS_fromDNA(tempseq)));
            if (!(continues))System.out.println("Pattern gefunden auf Folgestrang: " +searchalgo.testforSequence(pattern, tempseq.create_all_AS_fromDNA(tempseq)));
        if (continues) {
                tempseq = tempseq.complement();
                trans_find_start(tempseq, pattern, searchalgo, false);
            }
    }
}
