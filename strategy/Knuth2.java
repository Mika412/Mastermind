package strategy;


import edu.princeton.cs.algs4.StdOut;


public class Knuth2 extends Strategy {


    public Knuth2(int codeLength) {
        super(codeLength);
    }

    @Override
    public Code guess(Answer answer) {
        this.removeInconsistentCodes(this.consistentCodes, this.lastGuess,
                answer);

        int maxMinimum = 0;
        StdOut.println(this.allCodes.size() + " " + this.consistentCodes.size());
        for (Code code : this.allCodes) {
            int minimum = Integer.MAX_VALUE;

            for (Answer a : this.allAnswers) {
                int removedCodesSize = getConsistentCodes(this.consistentCodes, code, a).size();
                minimum = Math.min(removedCodesSize, minimum);
            }
            if (minimum > maxMinimum) {
                maxMinimum = minimum;
                this.consistentCodes.add(code);
            }
        }
        this.removeInconsistentCodes(this.consistentCodes, this.lastGuess, answer);
        this.lastGuess = consistentCodes.get(0);
        return this.lastGuess;
    }
}
