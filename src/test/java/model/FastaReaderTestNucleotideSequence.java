package model;

import exactSearch.io.FastaReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sequences.model.NucleotideSequence;
import sequences.model.Sequence;

import java.util.List;

@RunWith(Parameterized.class)
public class FastaReaderTestNucleotideSequence {
    NucleotideSequence inputSeq;

    @Parameterized.Parameter()
    public String header;
    @Parameterized.Parameter(1)
    public String sequence;
    @Parameterized.Parameter(2)
    public String file;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return List.of(new Object[][] {
                {">TestNucleotideSequence", "ACGATCGTAGCT", "src/test/resources/TestNucleotideSequence.fasta"},
        });
    }

    @Before
    public void init() throws Exception{
        inputSeq = new NucleotideSequence(header, sequence);
    }

    @Test
    public void fastaNucleotide() throws Exception {
        FastaReader fastaReader = new FastaReader();
        Sequence test = fastaReader.DateiLesen(file, null);
        Assert.assertEquals(inputSeq.getName(),test.getName());
        Assert.assertEquals(inputSeq.getSequence(),test.getSequence());
        Assert.assertEquals(inputSeq.getType(),test.getType());
    }
}