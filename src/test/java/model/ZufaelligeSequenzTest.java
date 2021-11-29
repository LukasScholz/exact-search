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
import java.util.concurrent.ThreadLocalRandom;

@RunWith(Parameterized.class)
public class ZufaelligeSequenzTest {
    NucleotideSequence Text;
    NucleotideSequence Pattern;

    @Parameterized.Parameter()
    public String text;
    @Parameterized.Parameter(1)
    public String pattern;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return List.of(new Object[][]{
                {randomSequence(5000), randomSequence(5)},
        });
    }

    public static String randomSequence(int x) {
        StringBuilder seq = new StringBuilder();
        for (int i = 0; i < x; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
            switch (randomNum){
                case 1: seq.append("A"); break;
                case 2: seq.append("C"); break;
                case 3: seq.append("T"); break;
                case 4: seq.append("G"); break;
            }
        }
        return seq.toString();
    }

    @Before
    public void init() throws Exception {
        Text = new NucleotideSequence(">Test1", text);
        Pattern = new NucleotideSequence(">Test2", pattern);
    }

    @Test
    public void test() {
        Zeitmessung timer = new Zeitmessung();
        timer.start();
        NaiveSearch<NucleotideSequence> NaiveSearchTest = new NaiveSearch<>(Text, Pattern);
        Integer naivecount = NaiveSearchTest.count();
        BMSearch<NucleotideSequence> BMSearchTest = new BMSearch<>(Text, Pattern);
        Integer bmcount = BMSearchTest.count();
        KMPSearch<NucleotideSequence> KMPSearchTest = new KMPSearch<>(Text, Pattern);
        Integer kmpcount = KMPSearchTest.count();
        Assert.assertTrue(naivecount.equals(bmcount)&&naivecount.equals(kmpcount));
        System.out.println(timer.stop());
    }
}
