
// This class represents a lineup of players on the court together
// The class is responsible for determining the passing probabilities for this team

public class Team {
    private Graph g; // Graph representing passing frequencies for this team's players
    private int[] lineup; // list of ints representing which players are in the lineup
    private double[] startFrequencies; // list of relative frequency that each player starts with the ball

    /**
     * Constructs an instance of Team given a list of players and a matrix
     * representing the passing between all players
     * 
     * @param line   The int list representing the players in this team object
     * @param matrix The matrix of all players' passing frequencies
     */
    public Team(int[] line, double[][] matrix) {
        lineup = line;
        startFrequencies = startFreq(line);
        g = getGraph(matrix, lineup);
    }

    /**
     * Takes each players' number of times starting with ball per two possessions,
     * converts it into the proportion of times they will start with the ball in
     * this lineup
     * 
     * @param players The list of ints representing players
     */
    public double[] startFreq(int[] players) {
        final double[] FREQUENCIES = { 0.850, 1.528, 0.910, 1.284, 0.4495, 0.670, 0.511, 0.400 }; // from nba.com

        double sum = 0;
        for (int player : players) {
            sum += FREQUENCIES[player];
        }

        double[] output = new double[players.length];
        for (int i = 0; i < players.length; i++) {
            output[i] = FREQUENCIES[players[i]] / sum;
        }

        return output;
    }

    /**
     * Creates the graph that represents this Team object's passing
     * 
     * @param matrix The matrix of all passing probabilities
     * @param lineup The lineup representing which players are in this Team
     * @return Graph representing the passing probabilities specifically for this
     *         Team
     */
    public Graph getGraph(double[][] matrix, int[] lineup) {
        int size = lineup.length;

        // comment out one of these lines, toggles between ListGraph and MatrixGraph
        g = new ListGraph(size);
        // g = new MatrixGraph(size);

        // looks at frequencies that each player passes to each other player, and
        // converts
        // them into percentages based on which players are on the court
        double rowSum;
        for (int i = 0; i < size; i++) {
            rowSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum += matrix[lineup[i]][lineup[j]];
            }
            for (int j = 0; j < size; j++) {
                g.addEdge(i, j, matrix[lineup[i]][lineup[j]] / rowSum);
            }
        }
        return g;
    }

    /**
     * Prints the probabilities that each player on this Team's lineup will receive
     * the ball on a given possession, using the graph initialized in the Team's
     * constructor
     */
    public void printTeamProbabilities() {
        for (int targ = 0; targ < lineup.length; targ++) {
            double probOfReceiving = 0;
            for (int bh = 0; bh < lineup.length; bh++) {
                // The overall probability that this player receives the ball from this
                // ballhandler is the probability that this ballhandler starts with the ball,
                // multiplied by the probability the ball reaches this target from this
                // ballhandler
                probOfReceiving += startFrequencies[bh] * probability(bh, targ, 3);
            }
            System.out.println(playerName(targ) + ": " + Double.toString(probOfReceiving).substring(0, 5));
        }
    }

    /**
     * Prints the probabilities that each player on this Team's lineup will receive
     * the ball if a certain player starts with the ball
     * 
     * @param ballHandler The player starting with the ball
     */
    public void printProbabilitiesFromX(int ballHandler) {
        System.out.println("\nWhen the ball handler is " + playerName(ballHandler)
                + ", the probabilities that each player receives the ball are:");
        for (int i = 0; i < g.size(); i++) {
            if (i == ballHandler) // the ball obviously reaches the ballhandler, no need to print
                continue;
            System.out.println(playerName(i) + ": " + Double.toString(probability(ballHandler, i, 3)).substring(0, 5));
        }
    }

    /**
     * Recursively determines the probability that the ball goes from passer to
     * target within passesLeft passes
     * 
     * @param passer     The player starting with the ball
     * @param target     The target to receive the ball
     * @param passesLeft The number of passes left in the possession
     * @return The probability that the ball goes from passer to target within
     *         passesLeft passes
     */
    public double probability(int passer, int target, int passesLeft) {
        if (passer == target) // if the passer is the target, there is a 100% chance of reaching the target
            return 1;
        if (passesLeft == 1) { // if we don't have any passes left, return the chance that this passer directly
                               // passes to the target
            return g.outEdges(passer).getWeight(target);
        }

        // add the probabilities of a pass to each player eventually reaching the target
        double totalProb = 0;
        for (int i = 0; i < g.size(); i++) {
            double probOfPass = g.outEdges(passer).getWeight(i);
            totalProb += probOfPass * probability(i, target, passesLeft - 1);
        }
        return totalProb;
    }

    /**
     * 
     * @param index The index representing the player whose name to return
     * @return The name of the player represented by the given index
     */
    public String playerName(int index) {
        switch (lineup[index]) {
            case 0:
                return "Tatum";
            case 1:
                return "Walker";
            case 2:
                return "Smart";
            case 3:
                return "Wanamaker";
            case 4:
                return "Theis";
            case 5:
                return "Hayward";
            case 6:
                return "Brown";
            case 7:
                return "Kanter";
            default:
                return "Not a player";
        }
    }
}
