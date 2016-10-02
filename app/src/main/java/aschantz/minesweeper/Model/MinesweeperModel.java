package aschantz.minesweeper.Model;

import android.util.Log;

import java.util.Random;

/**
 * Created by aschantz on 9/23/16.
 */
public class MinesweeperModel {

    private static MinesweeperModel instance = null;

    public static MinesweeperModel getInstance() {
        if (instance == null) {
            instance = new MinesweeperModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short FLAG = 1;
    public static final short MINE = 2;
    public static final short CLICKED = 3;
    public static final short SHOWBOMB = 4;
    public static final short SURROUND = 5;
    public static final short INDIVIDUAL = 6;
    public static final short BOMBS = 7;
    public static final short HIDEBOMB = 8;
    public int countClicked = 0;

    private short currentToggle = CLICKED;

    private short numberDisplayMode = INDIVIDUAL;

    private short bombMode = HIDEBOMB;

    private short[][] model = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void setFieldContent(int x, int y, short player) {
        model[x][y]=player;
    }

    public short getCurrentToggle() {
        return currentToggle;
    }

    public void flipToggle() {
        currentToggle = (currentToggle == FLAG) ? CLICKED : FLAG;
    }

    public short getNumberMode() { return numberDisplayMode; }

    public void switchNumberDisplay() { numberDisplayMode = (numberDisplayMode == SURROUND) ? INDIVIDUAL : SURROUND;
//        numberDisplayMode = (numberDisplayMode == SURROUND) ? INDIVIDUAL : SURROUND;
    }

    public short getBombMode() { return bombMode; }

    public void switchBombMode() {
        bombMode = (bombMode == BOMBS) ? HIDEBOMB : BOMBS;
    }

    public int getCountClicked() {return countClicked;}
    public void plusOneToCountClicked() { countClicked = countClicked + 1;}


    public void resetModel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                model[i][j] = EMPTY;
            }
        }
        Random r = new Random();
        int mines = 0;
        while (mines < 3) {
            int randomX = r.nextInt(5);
            int randomY = r.nextInt(5);
            if (model[randomX][randomY] == EMPTY) {
                model[randomX][randomY] = MINE;
            } else {
                resetModel();
            }
            mines = mines + 1;
        }
        numberDisplayMode = INDIVIDUAL;
        countClicked = 0;
    }

    public int setNumbers(int x, int y) {
        int count = 0;
        if ((x+1 < 5) && (y + 1 < 5)) {
            if (model[x + 1][y+1] == MINE) {
                    count = count + 1;
                }
            }
        if (x+1 < 5) {
            if (model[x + 1][y] == MINE) {
                count = count + 1;
            }
        }
        if ((x-1 > -1) && (y-1 > -1)) {
            if (model[x-1][y-1] == MINE) {
                count = count + 1;
            }
        }
        if (x-1 > -1) {
            if (model[x-1][y] == MINE) {
                count = count + 1;
            }
        }
        if ((y+1 < 5) && (x-1 > -1)) {
            if (model[x-1][y+1] == MINE) {
                count = count + 1;
            }
        }
        if (y+1 < 5) {
            if (model[x][y+1] == MINE) {
                count = count + 1;
            }
        }
        if (( y-1 > -1) &&  (x + 1 < 5)){
            if (model [x+1][y-1] == MINE) {
                    count = count + 1;
            }
        }
        if (y-1 > -1) {
            if (model [x][y-1] == MINE) {
                count = count + 1;
            }
        }
        return count;
    }
}
