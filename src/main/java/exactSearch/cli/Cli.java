package exactSearch.cli;


import exactSearch.algo.BMSearch;
import exactSearch.algo.BasicSearch;
import exactSearch.algo.KMPSearch;
import exactSearch.algo.NaiveSearch;
import exactSearch.io.FastaReader;
import other.Zeitmessung;
import picocli.CommandLine;
import sequences.model.AminoacidSequence;
import sequences.model.NucleotideSequence;
import sequences.model.Sequence;

import java.nio.file.Path;

public class Cli {
    static CliOptions opts;

    public static void CLInterface(String[] args) {
        opts = new CliOptions();
        CommandLine cmd = new CommandLine(opts);
        Zeitmessung timer = new Zeitmessung();
        timer.start();

        try {
        CommandLine.ParseResult result = cmd.parseArgs(args);

            if (result.isUsageHelpRequested()) {
                cmd.usage(System.err);
                System.exit(0);
            }
            else {
                String SequenceType = "";
                if (opts.getSequenceType() != null){
                    SequenceType = opts.getSequenceType().toString();
                }
                for (Path inputFile : opts.getInputFiles()) {

                    System.err.println("Searching pattern " + opts.getPatteropts().getPatternString()+ " with Algorithm " +opts.getAlgo().name() +" in "+SequenceType+"Sequence "+  inputFile.getFileName().toString() + " ...");
                    System.err.println("Positions enabled: "+opts.getpostion());
                    System.err.println("Find Translate: "+ opts.gettrans_find());
                    System.err.println("Compare Operations enabled: "+ opts.getc());
                    try {
                        parseArguments(opts.getPatteropts().getPatternString(), opts.getAlgo().name(), SequenceType, inputFile.toString(), opts.getc(), opts.getpostion(), opts.gettrans_find());
                    }
                    catch(Exception E) {
                        E.printStackTrace();
                    }
                    finally {
                        String time = timer.stop();
                        if (opts.getTime()) {
                            System.err.println(time);
                        }
                    }
                }
            }
        } catch (CommandLine.MissingParameterException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void parseArguments(String pattern, String algorithm, String Sequencetypes, String path, boolean debug, boolean position, boolean trans_find) throws Exception {
        FastaReader textreader = new FastaReader();
        FastaReader patternreader = new FastaReader(">pattern", pattern);
        Sequence textseq = null;
        Sequence patternseq = null;
        BasicSearch searchalgo = null;

        if(Sequencetypes.isEmpty()) {
            System.err.println("No Sequencetype defined... Trying auto-detection");
            try {
                textseq = textreader.DateiLesen(path, null);
                patternseq = patternreader.findSequence();
            } catch (Exception ignored) {}
        }
        else {
            // Werte Angegeben
            try {
                textseq = textreader.DateiLesen(path, Sequencetypes);
                patternseq = patternreader.setSequence(Sequencetypes);
            } catch (Exception ignored) {}
        }

        // Choose Algorithm
        switch (algorithm) {
            case "KMP":
                searchalgo = new KMPSearch<>(textseq, patternseq);
                break;
            case "BM":
                searchalgo = new BMSearch<>(textseq, patternseq);
                break;
            case "NAIVE":
                searchalgo = new NaiveSearch<>(textseq, patternseq);
                break;
        }

        assert searchalgo != null;
        if (!(trans_find)) {
            System.out.printf("Gefundene Anzahl an Treffern: %d%n", searchalgo.count(debug, position));
        }
        else {
            NucleotideSequence tempseq = (NucleotideSequence) textseq;
            tempseq.trans_find_start(tempseq, patternseq, searchalgo, true);
        }

    }
}