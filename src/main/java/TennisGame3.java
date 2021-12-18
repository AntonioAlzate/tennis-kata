import java.util.Objects;

public class TennisGame3 implements TennisGame {

    private int pointsPlayer2;
    private int pointsPlayer1;
    private final String player1Name;
    private final String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String score;

        if (noWinNoAdvantageNoDeuce()) {
            String[] points = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            score = points[pointsPlayer1];
            score = getScoreNoWinNoAdvantage(score, points);
        }
        else if(pointsPlayer1 == pointsPlayer2)
            score = "Deuce";
        else
            score = getScoreAdvantageOrWin();

        return score;
    }

    private boolean noWinNoAdvantageNoDeuce() {
        return menosDeCuatroPuntos() && noSeConvirtieronSeisPuntos();
    }

    private String getScoreAdvantageOrWin() {
        return existAdvantage() ? "Advantage " + getOlderName() : "Win for " + getOlderName();
    }

    private String getOlderName() {
        return pointsPlayer1 > pointsPlayer2 ? player1Name : player2Name;
    }

    private boolean existAdvantage() {
        int diference = pointsPlayer1 - pointsPlayer2;
        return diference * diference == 1;
    }

    private String getScoreNoWinNoAdvantage(String score, String[] points) {
        return (pointsPlayer1 == pointsPlayer2) ? score + "-All" : score + "-" + points[pointsPlayer2];
    }

    private boolean noSeConvirtieronSeisPuntos() {
        return ((pointsPlayer1 + pointsPlayer2) != 6);
    }

    private boolean menosDeCuatroPuntos() {
        return pointsPlayer1 < 4 && pointsPlayer2 < 4;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, "player1"))
            this.pointsPlayer1 += 1;
        else
            this.pointsPlayer2 += 1;

    }

}
