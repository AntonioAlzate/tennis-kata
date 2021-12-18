import java.util.Map;
import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN = "Win for ";
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, "player1"))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {

        if (existeIgualdad())
            return igualdad();

        if (puntajeMayorIgualCuatro())
            return ventajaOGanador();

        return puntuacion();
    }

    private boolean existeIgualdad() {
        return player1Score - player2Score == 0;
    }

    private String igualdad() {
        Map<Integer, String> igualdades = Map.of(
                0, "Love-All",
                1, "Fifteen-All",
                2, "Thirty-All",
                3, "Deuce"
        );

        int score = player1Score > 3 ? 3 : player1Score;

        return igualdades.get(score);
    }


    private boolean puntajeMayorIgualCuatro() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private String ventajaOGanador() {
        int minusResult = player1Score - player2Score;

        if (existeGanador(minusResult))
            return elegirGanador(minusResult);

        return elegirVentaja(minusResult);

    }

    private boolean existeGanador(int minusResult) {
        return Math.abs(minusResult) >= 2;
    }

    private String elegirGanador(int minusResult) {
        String ganador = minusResult >= 2 ? player1Name : player2Name;

        return WIN + ganador;
    }

    private String elegirVentaja(int minusResult) {
        String ventajaPara = minusResult == 1 ? player1Name : player2Name;

        return ADVANTAGE + ventajaPara;
    }

    private String puntuacion() {
        Map<Integer, String> puntos = Map.of(
                0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );

        return puntos.get(player1Score) + "-" + puntos.get(player2Score);
    }

}
