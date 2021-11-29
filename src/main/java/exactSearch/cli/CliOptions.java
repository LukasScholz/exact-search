package exactSearch.cli;

import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(mixinStandardHelpOptions = true, showDefaultValues = true)
public class CliOptions {
    enum Algorithm {NAIVE, KMP, BM}

    @CommandLine.Option(names = {"--algorithm", "--algo", "-a"}, description = "Select search algorithm", defaultValue = "KMP")
    protected Algorithm algo;

    public Algorithm getAlgo() {
        return algo;
    }

    @CommandLine.Option(names = {"--time"}, description = "Return the needed time ") boolean time;
    public boolean getTime() {return this.time;}

    @CommandLine.Option(names = {"--findtranslate", "-t"}, description = "Search for Aminoacidsequence in translated given DNA-Sequence")
    protected boolean trans_find;

    public boolean gettrans_find(){return trans_find; }


    enum SequenceType {Nucleotide, Aminoacid, Ascii}

    @CommandLine.Option(names = {"--sequenceType", "--sequence", "-s"}, description = "Select Sequence Type")
    protected SequenceType sequence;

    public SequenceType getSequenceType(){ return sequence; }



    @CommandLine.Option(names = "--debug", description = "Return number of comparisons to the error stream") boolean c;
    public boolean getc(){return c;}


    @CommandLine.Option(names = "--position", description = "Return next position after given input") boolean position;
    public boolean getpostion(){return position;}


    @CommandLine.ArgGroup(multiplicity = "1")
    protected PatternOptions patteropts;

    public PatternOptions getPatteropts() {
        return patteropts;
    }



    @CommandLine.Parameters(description = "input Files to be searched")
    protected List<Path> inputFiles;

    public List<Path> getInputFiles() {
        return inputFiles;
    }

    public static class PatternOptions {
        @CommandLine.Option(names = {"--pattern", "-p"},description = "Search pattern as String",required = true)
        protected String Pattern;

        @CommandLine.Option(names = {"--patternFile", "-f"},description = "Search pattern as File",required = true)
        protected Path patternFile;

        public String getPattern() { return Pattern; }

        public Path getPatternFile() {
            return patternFile;
        }

        public String getPatternString() {
            if (Pattern != null && !Pattern.isBlank()) {
                return Pattern;
            }
            else {
                try {
                    String output = String.join("", Files.readAllLines(patternFile));
                    String[] array = output.split(" ");
                    return array[(array.length-1)];
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
