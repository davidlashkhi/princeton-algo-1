import edu.princeton.cs.algs4.MinPQ;

import java.util.Deque;
import java.util.LinkedList;


public class Solver {
    private class Node implements Comparable<Node> {
        public final Board board;
        public final Node predecessor;
        public final int moves;
        public final int priority;

        public Node(Board board, Node predecessor) {
            this.board = board;
            this.predecessor = predecessor;
            this.moves = predecessor == null ? 0 : predecessor.moves + 1;
            this.priority = board.manhattan() + moves;

        }

        public int compareTo(Node that) {
            return this.priority - that.priority;
        }

        public int priority() {
            return board.manhattan() + moves;
        }
    }

    MinPQ<Node> solutionPQ = new MinPQ<Node>();
    Deque<Board> solution = new LinkedList<>();


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        Node initialNode = new Node(initial, null);
        solutionPQ.insert(initialNode);
        solutionPQ.deleteMin();
        solution.add(initial);
        Iterable<Board> neighbors = initial.neighbors();
        for (int i = 0; i < neighbors.length; i++) {
            Node node = new Node(neighbors[i], initialNode);
            solutionPQ.insert(node);
        }
        Board minBoard = solutionPQ.min();
        while (true) {
            if (solutionPQ.deleteMin().board.isGoal()) {
                break;
            }
            solution.add(solutionPQ.min());
            Iterable<Board> neighbors = initial.neighbors();
            for (int i = 0; i < neighbors.length; i++) {
                Node node = new Node(neighbors[i], initialNode);
                solutionPQ.insert(node);
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solution() != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable())
            return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            Deque<Board> solution = new LinkedList<>();
            Node nextNode = solutionNode;
            while (nextNode != null) {
                solution.addFirst(nextNode.board);
                nextNode = nextNode.predecessor;
            }

            return solution;
        } else {
            return null;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
