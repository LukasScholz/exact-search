package model;

import exactSearch.algo.BMSearch;
import exactSearch.algo.KMPSearch;
import exactSearch.algo.NaiveSearch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import other.Zeitmessung;
import sequences.model.NucleotideSequence;

import java.util.List;

@RunWith(Parameterized.class)
public class SearchTest {
    NucleotideSequence Text;
    NucleotideSequence Pattern;

    @Parameterized.Parameter()
    public String text;
    @Parameterized.Parameter(1)
    public String pattern;
    @Parameterized.Parameter(2)
    public int expected;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return List.of(new Object[][]{
                {"", "GT", 0},
                {"GTAAAAGTCAAAAGTAAGTAAGTGTGTGT", "", 0},
                {"GTAAAAGTCAAAAGTCAGTCAAGTGTGTGT", "GTA", 1},
                {"GTAAAAGTCAAAAGTCAGTCAAGTGTGTGTCCG", "CCG", 1},
                {"ATGCGAGCCGACGACGATGCGACTGACACGATG", "ATG", 3},
                {"ATATACAGTATATA", "ATA", 4}
        });
    }

    @Before
    public void init() throws Exception {
        Text = new NucleotideSequence(">Test1", text);
        Pattern = new NucleotideSequence(">Test2", pattern);
    }

    @Test
    public void testNaive() {
        Zeitmessung timer = new Zeitmessung();
        timer.start();
        NaiveSearch<NucleotideSequence> NaiveSearchTest = new NaiveSearch<>(Text, Pattern);
        Assert.assertEquals(expected, NaiveSearchTest.count());
        System.out.println(timer.stop());
    }

    @Test
    public void testBM() {
        Zeitmessung timer = new Zeitmessung();
        timer.start();
        BMSearch<NucleotideSequence> BMSearchTest = new BMSearch<>(Text, Pattern);
        Assert.assertEquals(expected, BMSearchTest.count());
        System.out.println(timer.stop());
    }

    @Test
    public void testKMP() {
        Zeitmessung timer = new Zeitmessung();
        timer.start();
        KMPSearch<NucleotideSequence> KMPSearchTest = new KMPSearch<>(Text, Pattern);
        Assert.assertEquals(expected, KMPSearchTest.count());
        System.out.println(timer.stop());
    }
}
