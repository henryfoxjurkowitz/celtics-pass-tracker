import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// class to get data from the csv file and wrangle into a workable format
public class Wrangler {
    private double[][] matrix; // matrix of the passing probabilities between all players
    private String[] players; // list of the players' names, given by the csv file

    /**
     * Constructs an instance of the Wrangler class to get data in a workable form
     * 
     * @param source The data file to read in
     * @param n      The number of players listed in the file
     * @throws FileNotFoundException
     */
    public Wrangler(File source, int n) throws FileNotFoundException {
        Scanner s = new Scanner(source);

        // populates list of players' names
        players = new String[n];
        for (int i = 0; i < n; i++) {
            players[i] = s.next();
        }

        // populates matrix of passing frequencies
        matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double val = Double.valueOf(s.next()) / 100;
                matrix[j][i] = val;
            }
        }
    }

    /**
     * @return list of players' names
     */
    public String[] getPlayers() {
        return players;
    }

    /**
     * @return matrix of all passing frequencies
     */
    public double[][] getMatrix() {
        return matrix;
    }
}
