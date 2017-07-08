import java.util.Scanner;
public class TicTacToe {
    //фиксированные переменные для отображения содержимого ячейки
    public static final String EMPTY = "   ";
    public static final String CROSS = " X ";
    public static final String NULL = " O ";
    public static String activPlayer;
    //  переменные для отображение размера и состояния поля
    public static final int COLUMN = 3;
    public static final int ROW = 3;
    public static String[][] gameField = new String[COLUMN][ROW];
    public static int gameStatus;
    public static final int CONTINUES = 0, DRAW = 1, WIN_X = 3, WIN_O = 4;
    public static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        startGame();

        do {

            inputPlayer();
            analysisGame();
            outputField();
            if (gameStatus == WIN_X) {
                System.out.println("X" + "  " + "победил,поздравляем");
            } else if (gameStatus == WIN_O) {
                System.out.println("O" + "  " + "победил,поздравляем");
            } else if (gameStatus == DRAW) {
                System.out.println("Игра закончилась");
            }
            activPlayer = (activPlayer == CROSS ? NULL : CROSS);
        }
        while (gameStatus == CONTINUES);
    }
    //метод начать игру
    public static void startGame() {
        for (int row = 0; row < ROW; row++) {
            for (int column = 0; column < COLUMN; column++) {
                gameField[row][column] = EMPTY;

            }
        }

        activPlayer = CROSS;
        outputField();
    }

    //ввод игрока
    public static void inputPlayer() {
        boolean inPutTrue = false;


        do {
            try {
                System.out.print("Игрок" + activPlayer + "Введите ряд  и ячейку от 1-3 через пробел ");

                int row = Integer.parseInt(in.next()) - 1;
                int column = Integer.parseInt(in.next()) - 1;

                if (row >= 0 && row < ROW && column >= 0 && column < COLUMN && gameField[row][column] == EMPTY) {
                    gameField[row][column] = activPlayer;
                    inPutTrue = true;
                } else {
                    System.out.print("Выбранное размещение(" + (row + 1) + "," +
                            (column + 1) + ")Не может быть использовано.Попробуйте еще раз   ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Вводим только числа");

            }   }
        while (!inPutTrue) ;


    }

    // анализ  игры,поиск победителя
    public static void analysisGame() {
        String winner = findWinner();
        if (winner.equals(CROSS)) {
            gameStatus = WIN_X;
        } else if (winner.equals(NULL)) {
            gameStatus = WIN_O;
        } else if (fieldCells()) {
            gameStatus = DRAW;
        } else {
            gameStatus = CONTINUES;
        }
    }
    //метод количество одинаковых ячеек
    public static boolean fieldCells(){
        {for (int row = 0; row < ROW; row++) {
            for (int column = 0; column < COLUMN; column++) {
                if(gameField[row][column]==EMPTY){
                    return false;
                }
            }
        }
            return  true;


        }}

    //метод все ли клеточки заполненны
    public static String findWinner() {
        int numberOfIdentical;
        for (int row = 0; row < ROW; row++) {
            numberOfIdentical = 0;
            for (int column = 0; column < COLUMN; column++) {
                if (gameField[row][0] != EMPTY && gameField[row][0] == gameField[row][column]) {
                    numberOfIdentical++;
                }

            }
            if (numberOfIdentical == 3) {
                return gameField[row][0];
            }
        }
        for (int column = 0; column < COLUMN; column++) {
            numberOfIdentical = 0;
            for (int row = 0; row < ROW; row++) {
                if (gameField[0][column] != EMPTY && gameField[0][column] == gameField[row][column]) {
                    numberOfIdentical++;
                }

            }
            if (numberOfIdentical == 3) {
                return gameField[0][column];
            }
        }
        if (gameField[0][0] != EMPTY && gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2]) {


            return gameField[0][0];
        }

        if (gameField[0][2] != EMPTY && gameField[1][1] == gameField[0][2] && gameField[2][0] == gameField[0][2]) {

            return gameField[0][2];
        }
        return EMPTY;

    }
    //метод вывод разметки поля
    public static void outputField() {
        for (int row = 0; row < ROW; row++) {
            for (int column = 0; column < COLUMN; column++) {
                System.out.print(gameField[row][column]);
                if (column != COLUMN - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
            if (row != ROW - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

}