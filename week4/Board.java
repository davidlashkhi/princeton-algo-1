import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.List;

public class Board {
    int[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    int[][] tiles;
    private List<Board> neighbors;
    int priority;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int tile = tiles[i][j];
                this.tiles[i][j] = tile;
            }
        }
    }


    // string representation of this board
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension());


        for (int i = 0; i < dimension(); i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < dimension(); j++) {
                stringBuilder.append(" " + this.tiles[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles[0].length;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != goalTiles[i][j]) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != goalTiles[i][j]) {
                    int deltaI = Math.abs(goalIndexI(tiles[i][j]) - i);
                    int deltaJ = Math.abs(goalIndexI(tiles[i][j]) - j);
                    manhattan = manhattan + deltaI + deltaJ;
                }
            }
        }
        return manhattan;
    }

    public int goalIndexI(int x) {
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (x == goalTiles[i][j]) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public int goalIndexJ(int x) {
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (x == goalTiles[i][j]) {
                    return j;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;

        Board anotherBoard = (Board) y;

        if (dimension() != anotherBoard.dimension()) return false;

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != anotherBoard.tiles[i][j]) return false;
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors != null) return neighbors;

        neighbors = new LinkedList<Board>();

        int zeroI = 0;
        int zeroJ = 0;

        outerLoop:
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break outerLoop;
                }
            }
        }

        for (int k = 0; k < 4; k++) {
            int blockI = zeroI;
            int blockJ = zeroJ;

            switch (k) {
                case 0:
                    blockI++;
                    break;
                case 1:
                    blockI--;
                    break;
                case 2:
                    blockJ++;
                    break;
                case 3:
                    blockJ--;
                    break;
            }

            if (blockI < 0 || blockI >= dimension() || blockJ < 0 || blockJ >= dimension()) continue;
            int[][] heighborBlocks = tilesCopy();

            heighborBlocks[zeroI][zeroJ] = heighborBlocks[blockI][blockJ];
            heighborBlocks[blockI][blockJ] = 0;

            Board neighbor = new Board(heighborBlocks);
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinBlocks = tilesCopy();

        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            twinBlocks[0][0] = tiles[0][1];
            twinBlocks[0][1] = tiles[0][0];
        } else {
            twinBlocks[1][0] = tiles[1][1];
            twinBlocks[1][1] = tiles[1][0];
        }

        return new Board(twinBlocks);
    }

    private int[][] tilesCopy() {
        int[][] blocksCopy = new int[dimension()][dimension()];

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                blocksCopy[i][j] = tiles[i][j];
            }
        }

        return blocksCopy;
    }

    public int compareTo(Board that) {
        return this.priority - that.priority;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        Board initial = new Board(tiles);
        System.out.println(initial.manhattan());
    }

}
