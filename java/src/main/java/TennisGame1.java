import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    public static final String ADVANTAGE = "Advantage ";
    public static final String WIN_FOR = "Win for ";
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    private final Map<Integer, String> pointsToCall = new HashMap<>(){{
        put(0, "Love");
        put(1, "Fifteen");
        put(2, "Thirty");
        put(3, "Forty");
    }};

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        String score = "";
        if (isTied()) {
            score = callSameNumberOfPoints();
        } else if (isAdvantageOrWin()) {
            score = callAdvantageOrWin();
        } else {
            score = callFromLoveToForty(score);
        }
        return score;
    }

    private boolean isTied() {
        return player1Score == player2Score;
    }

    private boolean isAdvantageOrWin() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private String callSameNumberOfPoints() {
        if (player1Score <= 2) {
            return pointsToCall.get(player1Score) + "-All";
        } else {
            return "Deuce";
        }
    }

    private String callAdvantageOrWin() {
        String score;
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) score = ADVANTAGE + player1Name;
        else if (minusResult == -1) score = ADVANTAGE + player2Name;
        else if (minusResult >= 2) score = WIN_FOR + player1Name;
        else score = WIN_FOR + player2Name;
        return score;
    }

    private String callFromLoveToForty(String score) {
        int tempScore;
        StringBuilder scoreBuilder = new StringBuilder(score);
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = player1Score;
            else {
                scoreBuilder.append("-");
                tempScore = player2Score;
            }
            scoreBuilder.append(pointsToCall.get(tempScore));
        }
        return scoreBuilder.toString();
    }

}
