package exactSearch.io;


import org.jetbrains.annotations.NotNull;
import sequences.model.*;

import java.util.HashSet;

public class Fasta {

    public String Header;
    public String Sequenz;

    public Fasta(String newHeader, String newSequenz){

        if(richtigerHeader(newHeader)){
            Header=newHeader;
        }else{
            throw new IllegalArgumentException("Header muss mit > beginnen");
        }
        if(richtigeSequenz(newSequenz)){
            Sequenz=newSequenz;
        }else{
            throw new IllegalArgumentException("Sequenz besteht nicht aus dem Alphabet: A, G, C, T");
        }
    }

    public static boolean richtigeSequenz(String s){
        char[] a = s.toCharArray();
        for(char c : a) {
            if (!(c == 'A' || c == 'C' || c == 'G' || c == 'T' || c == 'a' || c == 'c' || c == 'g' || c == 't')) {
                return false;
            }
        }
        return true;
    }

    public static int SequenceType(String s) {
        boolean nucleotide = true;
        boolean aminoacid = true;
        boolean ascii = true;
        try {
            NucleotideSequence nucleotideSequence = new NucleotideSequence("",s);
        } catch (Exception e){
            nucleotide = false;
        }

        try {
            AminoacidSequence aminoacidSequence = new AminoacidSequence("",s);
        }catch (Exception e){
            aminoacid = false;
        }

        try {
            AsciiSequence asciiSequence = new AsciiSequence("",s);
        }catch (Exception e){
            ascii = false;
        }
        if (nucleotide){
            return 1; //Nucleotide-Sequence
        }if (aminoacid){
            return 2; //Aminoacid-Sequence
        }if (ascii){
            return 3; //Ascii-Sequence
        }else{
            return 0; //not defined
        }
        //Problem: immer mindestens Ascii
    }

    public static boolean richtigerHeader(String s){
        return String.valueOf(s.charAt(0)).equals(">");
    }

    public String getHeader(){
        return Header;
    }

    public String getSequenz(){
        return Sequenz;
    }

    public int laenge(){
        return Sequenz.length();
    }
}