package aschantz.minesweeper.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.plattysoft.leonids.ParticleSystem;

import aschantz.minesweeper.MinesweeperActivity;
import aschantz.minesweeper.Model.MinesweeperModel;
import aschantz.minesweeper.R;

/**
 * Created by aschantz on 9/23/16.
 */
public class MinesweeperView extends View{

    private Paint paintBackground;
    private Paint paintLine;
    private Paint paintText;
    private Paint paintFlag;
    private Paint paintMine;
    private Paint paintClicked;
    private Paint paintShowBomb;
    private Paint paintGameOver;
    private Paint paintWin;
    private Paint paintWinText;
    private Bitmap bitFlag;
    private Bitmap bitOne;
    private Bitmap bitTwo;
    private Bitmap bitThree;
    private Bitmap bitQuestion;
    private Bitmap bitZero;
    private Bitmap bitBomb;
    private Bitmap bitZeroBlack;
    private Bitmap bitOneBlack;
    private Bitmap bitTwoBlack;
    private Bitmap bitThreeBlack;

    public boolean gameOver = false;
    public boolean emptyFlag = false;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitFlag = Bitmap.createScaledBitmap(bitFlag, getWidth()/5, getHeight()/5, false);
        bitOne = Bitmap.createScaledBitmap(bitOne, getWidth()/5, getHeight()/5, false);
        bitTwo = Bitmap.createScaledBitmap(bitTwo, getWidth()/5, getHeight()/5, false);
        bitThree = Bitmap.createScaledBitmap(bitThree, getWidth()/5, getHeight()/5, false);
        bitQuestion = Bitmap.createScaledBitmap(bitQuestion, getWidth()/5, getHeight()/5, false);
        bitZero = Bitmap.createScaledBitmap(bitZero, getWidth()/5, getHeight()/5, false);
        bitBomb = Bitmap.createScaledBitmap(bitBomb, getWidth()/5, getHeight()/5, false);
        bitZeroBlack = Bitmap.createScaledBitmap(bitZeroBlack, getWidth()/5, getHeight()/5, false);
        bitOneBlack = Bitmap.createScaledBitmap(bitOneBlack, getWidth()/5, getHeight()/5, false);
        bitTwoBlack = Bitmap.createScaledBitmap(bitTwoBlack, getWidth()/5, getHeight()/5, false);
        bitThreeBlack = Bitmap.createScaledBitmap(bitThreeBlack, getWidth()/5, getHeight()/5, false);
        paintText.setTextSize(getWidth()/8);
        paintGameOver.setTextSize(getWidth()/8);
        paintWin.setTextSize(getWidth()/8);
        paintWinText.setTextSize(getWidth()/6);
    }

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flagop);
        bitOne = BitmapFactory.decodeResource(getResources(), R.drawable.oneop);
        bitTwo = BitmapFactory.decodeResource(getResources(), R.drawable.twoop);
        bitThree = BitmapFactory.decodeResource(getResources(), R.drawable.threeop);
        bitQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.questionop);
        bitZero = BitmapFactory.decodeResource(getResources(), R.drawable.zeroop);
        bitBomb = BitmapFactory.decodeResource(getResources(), R.drawable.bombop);
        bitZeroBlack = BitmapFactory.decodeResource(getResources(), R.drawable.zeroblackop);
        bitOneBlack = BitmapFactory.decodeResource(getResources(), R.drawable.oneblackop);
        bitTwoBlack = BitmapFactory.decodeResource(getResources(), R.drawable.twoblackop);
        bitThreeBlack = BitmapFactory.decodeResource(getResources(), R.drawable.threeblackop);


        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(100);

        paintGameOver = new Paint();
        paintGameOver.setColor(Color.WHITE);
        paintGameOver.setTextSize(150);

        paintFlag = new Paint();
        paintFlag.setColor(Color.RED);
        paintFlag.setStyle(Paint.Style.STROKE);
        paintFlag.setStrokeWidth(5);

        paintMine = new Paint();
        paintMine.setColor(Color.BLUE);
        paintMine.setStyle(Paint.Style.STROKE);
        paintMine.setStrokeWidth(5);

        paintClicked = new Paint();
        paintClicked.setColor(Color.GRAY);
        paintClicked.setStyle(Paint.Style.FILL);

        paintShowBomb = new Paint();
        paintShowBomb.setColor(Color.RED);
        paintShowBomb.setStyle(Paint.Style.FILL);

        paintWin = new Paint();
        paintWin.setColor(Color.GREEN);
        paintWin.setStyle(Paint.Style.FILL);
        paintWinText = new Paint();
        paintWinText.setColor(Color.WHITE);
        paintWinText.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

        if(drawWin(canvas)){
            drawWin(canvas);

        } else if (gameOver || emptyFlag){
            drawLose(canvas);
        } else {
            drawGameField(canvas);
            drawPlayers(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    /**
     * draws the game board
     */
    private void drawGameField(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        // four horizontal lines
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(),
                2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(),
                3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(),
                4 * getHeight() / 5, paintLine);

        // four vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(),
                paintLine);
    }

    /**
     * draws the Xs and Os within the square
     */
    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.FLAG) {

                    canvas.drawBitmap(bitFlag, i * getWidth() / 5, j * getHeight() / 5, paintFlag);

                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.MINE) {

                    if (MinesweeperModel.getInstance().getBombMode() == MinesweeperModel.BOMBS) {
                        canvas.drawBitmap(bitBomb, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
                    }

                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.CLICKED) {
                    canvas.drawRect(i * getWidth() / 5, j * getHeight() / 5,
                            (i + 1) * getWidth() / 5, (j + 1) * getHeight() / 5, paintClicked);

                    drawNumbers(canvas, i, j);

                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.SHOWBOMB) {
                    drawLose(canvas);
                }
            }
        }
    }

    private void drawLose(Canvas canvas){
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintShowBomb);
        canvas.drawText("GAME OVER", getWidth() / 10, getHeight() / 2, paintGameOver);
    }

    private boolean drawWin(Canvas canvas) {
        if(MinesweeperModel.getInstance().getCountClicked() == 25) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paintWin);
            canvas.drawText("YOU WIN!", getWidth() / 10, getHeight() / 2, paintWinText);
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 5); //makes the coordinate correspond to a box
            int tY = ((int) event.getY()) / (getHeight() / 5);

            //when entire board is discovered, game ends, you win.
            if(checkWin() == 1) {
                invalidate();
            }

            //if its empty, draw it
            else if (tX < 5 && tY < 5 && MinesweeperModel.getInstance().
                    getFieldContent(tX, tY) == MinesweeperModel.EMPTY) {
                MinesweeperModel.getInstance().plusOneToCountClicked();
                if(checkWin() == 1) {
                    invalidate();
                }
                ifEmptyDraw(tX, tY);
            }

            //if it's a mine
            else if (tX < 5 && tY < 5 && MinesweeperModel.getInstance().
                    getFieldContent(tX, tY) == MinesweeperModel.MINE) {
                if(itsAMine(tX, tY)){
                    MinesweeperModel.getInstance().plusOneToCountClicked();
                    gameOver = true;
                } else {
                    MinesweeperModel.getInstance().plusOneToCountClicked();
                    itsAMine(tX, tY);
                }
            }
        }
        return true;
    }

    private boolean ifEmptyDraw(int tX, int tY) {
            //game over if flag an empty square
            if (MinesweeperModel.getInstance().getCurrentToggle() == MinesweeperModel.FLAG) {
                ifEmptyAndFlag(tX, tY);
                return true;
            } else {

                MinesweeperModel.getInstance().setFieldContent(tX,
                        tY, MinesweeperModel.getInstance().getCurrentToggle());
                Log.d("IFEMPTYDRAWELSE", "IF EMPTY DRAW ELSE");

                invalidate();
                return true;
            }
    }
    private boolean ifEmptyAndFlag(int tX, int tY){
        showNotABombMessage();
        MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.SHOWBOMB);
        emptyFlag = true;
        invalidate();
        return true;
    }

    private boolean itsAMine(int tX, int tY){
        if (MinesweeperModel.getInstance().getCurrentToggle() == MinesweeperModel.FLAG) {
            MinesweeperModel.getInstance().setFieldContent(tX,
                    tY, MinesweeperModel.getInstance().getCurrentToggle());
            invalidate();
            Log.d("ITSAMINEIF", "ITS A MINE IF");
            return false;
        } else {
            showGameOverMessage();
            MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.SHOWBOMB);
            invalidate();
            return true;
        }
    }

    /**
     * restart the game
     */
    public void restartGame() {
        MinesweeperModel.getInstance().resetModel();
        gameOver = false;
        emptyFlag = false;
        invalidate();
    }

    /**
     * Flip the toggle between flags and normal clicking.
     */
    public void flipToggle() {
        MinesweeperModel.getInstance().flipToggle();
        MinesweeperModel.getInstance().getCurrentToggle();

    }

    public void switchNumberDisplay() {
        MinesweeperModel.getInstance().switchNumberDisplay();
    }

    public void switchBombMode() {
        MinesweeperModel.getInstance().switchBombMode();
    }

    /**
     * Draws in the number for the square clicked.
     */
    public  void drawNumbers(Canvas canvas, int i, int j){
        if (MinesweeperModel.getInstance().getNumberMode() == MinesweeperModel.INDIVIDUAL) {
            numberCreation(canvas, i, j);
        } else {
            drawNumbersSurround(canvas, i, j);
        }
    }

    public void drawNumbersSurround(Canvas canvas, int i, int j) {
        if ((i+1 < 5) && MinesweeperModel.getInstance().getFieldContent(i+1, j) == MinesweeperModel.EMPTY) {
            numberCreationBlack(canvas, i+1, j);
        }
        if ((i-1 > -1) && MinesweeperModel.getInstance().getFieldContent(i-1, j) == MinesweeperModel.EMPTY) {
            numberCreationBlack(canvas, i-1, j);
        }
        if ((j+1 < 5) && MinesweeperModel.getInstance().getFieldContent(i, j+1) == MinesweeperModel.EMPTY) {
            numberCreationBlack(canvas, i, j+1);
        }
        if ((j-1 > -1) && MinesweeperModel.getInstance().getFieldContent(i, j-1) == MinesweeperModel.EMPTY) {
            numberCreationBlack(canvas, i, j-1);
        }
        if ((i+1 < 5) && (j+1 < 5) && MinesweeperModel.getInstance().getFieldContent(i+1, j+1) == MinesweeperModel.EMPTY){
            numberCreationBlack(canvas, i+1, j+1);
        }
        if ((i-1 > -1) && (j-1 > -1) && MinesweeperModel.getInstance().getFieldContent(i-1, j-1) == MinesweeperModel.EMPTY){
            numberCreationBlack(canvas, i-1, j-1);
        }
        if ((i+1 < 5) && (j-1 > -1) && MinesweeperModel.getInstance().getFieldContent(i+1, j-1) == MinesweeperModel.EMPTY){
            numberCreationBlack(canvas, i+1, j-1);
        }
        if ((i-1 > -1) && (j+1 < 5) && MinesweeperModel.getInstance().getFieldContent(i-1, j+1) == MinesweeperModel.EMPTY){
            numberCreationBlack(canvas, i-1, j+1);
        }
        numberCreation(canvas, i, j);
    }

    public void numberCreation(Canvas canvas, int i, int j){
        int number = MinesweeperModel.getInstance().setNumbers(i, j);
        if (number == 1) {
            canvas.drawBitmap(bitOne, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 0) {
            canvas.drawBitmap(bitZero, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 2) {
            canvas.drawBitmap(bitTwo, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 3) {
            canvas.drawBitmap(bitThree, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else {
            canvas.drawBitmap(bitQuestion, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        }
    }

    public void numberCreationBlack(Canvas canvas, int i, int j){
        int number = MinesweeperModel.getInstance().setNumbers(i, j);
        if (number == 1) {
            canvas.drawBitmap(bitOneBlack, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 0) {
            canvas.drawBitmap(bitZeroBlack, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 2) {
            canvas.drawBitmap(bitTwoBlack, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else if (number == 3) {
            canvas.drawBitmap(bitThreeBlack, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        } else {
            canvas.drawBitmap(bitQuestion, i * getWidth() / 5, j * getHeight() / 5, paintFlag);
        }
    }

    //game over (bomb) message
    private void showGameOverMessage() {
        ((MinesweeperActivity)getContext()).showGameOverMessage(
                getContext().getString(R.string.game_over)
        );

    }

    //game over (not a bomb) message
    private void showNotABombMessage() {
        ((MinesweeperActivity)getContext()).showGameOverMessage(
                getContext().getString(R.string.not_a_bomb)
        );

    }

    public int checkWin() {
        if (MinesweeperModel.getInstance().getCountClicked() == 25) {
            return 1;
        } else {
            return 0;
        }

    }

}
