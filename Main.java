import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // reads in the data from a csv file, initializes a matrix of all passing
        // probabilities between players
        File f = new File("Data.csv");
        Wrangler w = new Wrangler(f, 8);
        double[][] matrix = w.getMatrix();

        // asks the user for the line-up whose statistics they want to see
        System.out.println(
                "\nType the five numbers (separated by spaces) corresponding to the players you want in the lineup. "
                        + "\n\n0-Tatum 1-Walker 2-Smart 3-Wanamaker 4-Theis 5-Hayward 6-Brown 7-Kanter");

        // scans which players the user chooses
        Scanner sc = new Scanner(System.in);
        int[] lineup = new int[5];
        for (int i = 0; i < 5; i++) {
            lineup[i] = sc.nextInt();
        }

        // prints passing probabilities for the selected team
        Team team = new Team(lineup, matrix);
        System.out.println("\n\nThe probabilities that each player receives the ball on a given possession are:");
        team.printTeamProbabilities();

        // continually asks user to choose a player and see specific probabilites, or
        // terminate the program
        boolean done = false;
        while (!done) {
            System.out.println(
                    "\nType the number of a player to view probabilities when they start with the ball or type \"done\".\n"
                            + "\n0-" + team.playerName(0) + " 1-" + team.playerName(1) + " 2-" + team.playerName(2)
                            + " 3-" + team.playerName(3) + " 4-" + team.playerName(4));
            if (sc.hasNextInt())
                team.printProbabilitiesFromX(sc.nextInt());
            else if (sc.hasNext("done"))
                done = true;
            System.out.println();
        }
    }
}