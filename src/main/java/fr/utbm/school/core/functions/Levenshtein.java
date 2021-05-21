package fr.utbm.school.core.functions;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * author Neil Farmer / Ruiqing Zhu
 */
public class Levenshtein {

    // logger
    private static final Logger logger = Logger.getLogger(Levenshtein.class.getName());

    /**
     * Method to calcul the levenstein distance between to string
     * @param x first string
     * @param y second string
     * @return Levenshtein distance
     */
    public static int calculate(String x, String y) {
        logger.trace("Calcul Levenshtein distance for the word " + x + " and " + y);

        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        logger.trace("The Levenshtein distance for the word " + x + " and " + y + " is " + dp[x.length()][y.length()]);
        return dp[x.length()][y.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static String closestKeyword(String wordToFind, ArrayList<String> wordsList,
                                        int approximativeStop, int minEvaluation){

        Integer bestEval = Integer.MAX_VALUE;
        String bestWord = null;

        for(String word:wordsList){
            Integer evaluation = calculate(word, wordToFind);

            if(evaluation <= approximativeStop){
                logger.info("The word " + word + " have been found with the stopping evaluation (" + String.valueOf(approximativeStop) +")");
                return word;
            }else if(evaluation < bestEval && evaluation < minEvaluation){
                bestEval = evaluation;
                bestWord = word;
            }
        }

        logger.info("The word " + bestWord + " was the closest of " + wordToFind + ", with a Levenstein distance of " + String.valueOf(bestEval));
        return bestWord;
    }

    private static int min(int firstInt, int secondInt, int thirdInt) {
        return Math.min(Math.min(firstInt, secondInt), thirdInt);
    }
}
