package exactSearch.algo;

import sequences.model.Sequence;

public interface SearchAlgorithm<Searchtype extends Sequence> {
    void setPattern(Searchtype sequence);
    void setText(Searchtype text);
    int next();
    int count();
}
