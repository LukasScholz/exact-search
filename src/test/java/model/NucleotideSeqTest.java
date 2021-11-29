package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sequences.model.NucleotideSequence;

import java.util.List;

@RunWith(Parameterized.class)
public class NucleotideSeqTest {
    NucleotideSequence inputSeq;

    @Parameterized.Parameter()
    public String header;
    @Parameterized.Parameter(1)
    public String input;
    @Parameterized.Parameter(2)
    public String expected;


    @Parameterized.Parameters
    public static List<Object[]>data() {
        return List.of(new Object[][] {
            {">UpperCase", "ACGT", "TGCA"},
                        {">LowerCase", "acgt", "tgca"},
            });
    }


    @Before
    public void init() throws Exception{
        inputSeq = new NucleotideSequence(header, input);
    }

    @Test
    public void testComplement() {
        NucleotideSequence expectedSeq = inputSeq.complement();
        Assert.assertEquals(expected, expectedSeq.getSequence());
    }

}
