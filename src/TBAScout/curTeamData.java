package TBAScout;

import java.util.ArrayList;

public class curTeamData {
    public static double avgScore;
    public static int[] lowestScores = new int[3]; // take top 3 highest/lowest scores to try and combat outliers
                                                   // screwing up data
    public static int[] highestScores = new int[3];
    public static ArrayList<Integer> scores = new ArrayList<Integer>();

    public static void calcScoreInfo() {
        int firstLow = 200;
        int secondLow = 201;
        int thirdLow = 202;
        int firstHigh = 0;
        int secondHigh = 0;
        int thirdHigh = 0;
        int avg = 0;

        for (int score : scores) {
            if (score < firstLow) {
                firstLow = score;
            } else if (score < secondLow && score > firstLow) {
                secondLow = score;
            } else if (score < thirdLow && score > secondLow) {
                thirdLow = score;
            }

            if (score > firstHigh) {
                firstHigh = score;
            } else if (score > secondHigh && score < firstHigh) {
                secondHigh = score;
            } else if (score > thirdHigh && score < secondHigh) {
                thirdHigh = score;
            }

            avg += score;
        }

        avgScore = avg / scores.size() - 1;

        lowestScores[0] = firstLow;
        lowestScores[1] = secondLow;
        lowestScores[2] = thirdLow;

        highestScores[0] = firstHigh;
        highestScores[1] = secondHigh;
        highestScores[2] = thirdHigh;
    }

    public static String toOutputString() {
        return "lowest scores: " + lowestScores[0] + ", " + lowestScores[1] + ", " + lowestScores[2]
                + "; highest scores: " + highestScores[0] + ", " + highestScores[1] + ", " + highestScores[2]
                + "; average score: " + avgScore;
    }
}