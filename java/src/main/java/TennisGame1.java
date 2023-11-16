
public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            m_score1 += 1;
        else
            m_score2 += 1;
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
        return m_score1 == m_score2;
    }

    private boolean isAdvantageOrWin() {
        return m_score1 >= 4 || m_score2 >= 4;
    }

    private String callSameNumberOfPoints() {
        String score;   
        score = switch (m_score1) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
        return score;
    }

    private String callAdvantageOrWin() {
        String score;
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) score = "Advantage " + player1Name;
        else if (minusResult == -1) score = "Advantage " + player2Name;
        else if (minusResult >= 2) score = "Win for " + player1Name;
        else score = "Win for " + player2Name;
        return score;
    }

    private String callFromLoveToForty(String score) {
        int tempScore;
        StringBuilder scoreBuilder = new StringBuilder(score);
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = m_score1;
            else {
                scoreBuilder.append("-");
                tempScore = m_score2;
            }
            numberToCall(tempScore, scoreBuilder);
        }
        return scoreBuilder.toString();
    }

    private static void numberToCall(int tempScore, StringBuilder scoreBuilder) {
        switch (tempScore) {
            case 0 -> scoreBuilder.append("Love");
            case 1 -> scoreBuilder.append("Fifteen");
            case 2 -> scoreBuilder.append("Thirty");
            case 3 -> scoreBuilder.append("Forty");
        }
    }
}
