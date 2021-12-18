import java.util.Map;
import java.util.Objects;

public class TennisGame2 implements TennisGame
{
    private int p1Point = 0;
    private int p2Point = 0;

    private final String player1Name;
    private final String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        String score = "";

        if(mismosPuntos())
            score = empate();
        else
            score = puntuacionNormal();

        score = ventaja(score);

        score = ganador(score);

        return score;
    }

    private String ventaja(String score) {
        if(tienenMas3Puntos() && existeVentaja())
            score = elegirVentaja();
        return score;
    }

    private String ganador(String score) {
        if(diferenciaDosOMas() && llegoCuatroPuntos())
            score = elegirGanador();
        return score;
    }

    private boolean llegoCuatroPuntos() {
        return p1Point >=4 || p2Point >=4;
    }

    private boolean mismosPuntos() {
        return p1Point - p2Point == 0;
    }

    private String puntuacionNormal() {
        Map<Integer, String> puntos = Map.of(
                0, "Love",
                1, "Fifteen",
                2, "Thirty",
                3, "Forty"
        );

        return puntos.get(p1Point) + "-" + puntos.get(p2Point);
    }

    private String elegirVentaja() {
        String nombre = p1Point >= p2Point ? player1Name : player2Name;
        return "Advantage " + nombre;
    }

    private boolean existeVentaja() {
        return p1Point > p2Point || p2Point > p1Point;
    }

    private boolean tienenMas3Puntos() {
        return p2Point >= 3 && p1Point >= 3;
    }

    private boolean diferenciaDosOMas() {
        return Math.abs(p1Point - p2Point) >= 2;
    }

    private String elegirGanador() {
        int diferencia = getDiferencia();
        String nombre = diferencia >= 2 ? player1Name : player2Name;
        return "Win for " + nombre;
    }

    private int getDiferencia() {
        return p1Point - p2Point;
    }

    private String empate() {
        Map<Integer, String> igualdades = Map.of(
                0, "Love-All",
                1, "Fifteen-All",
                2, "Thirty-All",
                3, "Deuce"
        );

        int score = Math.min(p1Point, 3);

        return igualdades.get(score);
    }

    public void wonPoint(String player) {
        if (Objects.equals(player, "player1"))
            p1Point++;
        else
            p2Point++;
    }
}