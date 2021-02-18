package com.company;

import java.util.Random;

public class PlayField {

    private boolean[][] minefield;
    private int xSize;
    private int ySize;

    private int openFields;

    public PlayField(int xSize, int ySize, int numberOfMines) {
        minefield = new boolean[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
        openFields = xSize * ySize - numberOfMines;
        Random random = new Random();
        for (int i = 0; i < numberOfMines;) {
            int randomX = random.nextInt(xSize);
            int randomY = random.nextInt(ySize);
            if (!minefield[randomX][randomY]) {
                minefield[randomX][randomY] = true;
                i++;
            }
        }
    }

    public void decrementOpenFields() {
        openFields = openFields - 1;
    }

    public boolean hasWon() {
        return openFields == 0;
    }

    public boolean isThereMine(int x, int y) {
        return minefield[x][y];
    }

    public int getNeighboursWithMine(int x, int y) {
        int mineCount = 0;

        // levy horni
        if (x > 0 && y > 0 && minefield[x-1][y-1]) mineCount++;

        // horni
        if (y > 0 && minefield[x][y-1]) mineCount++;

        // pravy horni
        if (x < xSize - 1 && y > 0 && minefield[x+1][y-1]) mineCount++;

        // levy
        if (x > 0 && minefield[x-1][y]) mineCount++;

        // pravy
        if (x < xSize - 1 && minefield[x+1][y]) mineCount++;

        // levy dolni
        if (x > 0 && y < ySize - 1 && minefield[x-1][y+1]) mineCount++;

        // dolni
        if (y < ySize - 1 && minefield[x][y+1]) mineCount++;

        // pravy dolni
        if (x < xSize - 1 && y < ySize -1 && minefield[x+1][y+1]) mineCount++;

        return mineCount;
    }

}
