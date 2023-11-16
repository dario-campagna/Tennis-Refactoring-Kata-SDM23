import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    public static final String ADVANTAGE = "Advantage ";
    public static final String WIN_FOR = "Win for ";
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    private final Map<Integer, String> pointsToCall = new HashMap<>() {{
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
        if (isTied()) {
            return callSameNumberOfPoints();
        } else if (isAdvantage()) {
            return callAdvantage();
        } else if (isWin()) {
            return callWin();
        } else {
            return callFromLoveToForty();
        }
    }

    private boolean isTied() {
        return player1Score == player2Score;
    }

    private boolean isAdvantage() {
        return atLeast4PointsScored() && onePointMoreThanOtherPlayer();
    }

    private boolean isWin() {
        return atLeast4PointsScored() && twoPointsMoreThanOtherPlayer();
    }

    private boolean atLeast4PointsScored() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private boolean onePointMoreThanOtherPlayer() {
        return Math.abs(player1Score - player2Score) == 1;
    }

    private boolean twoPointsMoreThanOtherPlayer() {
        return Math.abs(player1Score - player2Score) >= 2;
    }

    private String callSameNumberOfPoints() {
        if (player1Score <= 2) {
            return pointsToCall.get(player1Score) + "-All";
        } else {
            return "Deuce";
        }
    }

    private String callAdvantage() {
        if (player1Score > player2Score)
            return ADVANTAGE + player1Name;
        else
            return ADVANTAGE + player2Name;
    }

    private String callWin() {
        if (player1Score > player2Score)
            return WIN_FOR + player1Name;
        else 
            return WIN_FOR + player2Name;
    }

    private String callFromLoveToForty() {
        return pointsToCall.get(player1Score) + "-" + pointsToCall.get(player2Score);
    }

}
