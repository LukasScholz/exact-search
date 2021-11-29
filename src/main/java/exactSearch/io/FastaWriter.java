package exactSearch.io;

import sequences.model.BasicSequence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FastaWriter {

    public static void DateiSchreiben(BasicSequence sequence, String dateiname){
        File file = new File(dateiname);
        try(BufferedWriter Writer = new BufferedWriter(new FileWriter(file))) {

            Writer.write(sequence.getName());
            Writer.newLine();

            int off = 0;
            int len = 80;
            int lines = sequence.getSequence().length()/80;
            for (int i=0;i<lines;i++){
                Writer.write(sequence.getSequence(),off,len);
                Writer.newLine();
                off=off+80;
            }
            Writer.write(sequence.getSequence(),lines*80, sequence.getSequence().length()-lines*80);
            Writer.newLine();

            System.out.println("Datei wurde fertiggestellt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
