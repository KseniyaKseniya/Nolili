import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
public class TicTacToe {
    //фиксированнеі переменніе для отображения содержимого ячейки
    public static final String Pusto = "   ";
    public static final String Krestik = " X ";
    public static final String Nolik = " O ";
    public static String ActivnyIgrok;
    //  переменные для отображение размера и состояния поля
    public static final int Stolbci = 3;
    public static final int Ryadi = 3;
    public static String[][] Сетка = new String[Stolbci][Ryadi];
    public static int Statusigri;
    public static final int prodolchetsya = 0, nichya = 1, pobedaX = 3, pobedaO = 4;
    public static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        start_game();

        do {

            VvodIgroka();
            analyzgame();
            VivodPolya();
            if (Statusigri == pobedaX) {
                System.out.println("X" + "  " + "победил,поздравляем");
            } else if (Statusigri == pobedaO) {
                System.out.println("O" + "  " + "победил,поздравляем");
            } else if (Statusigri == nichya) {
                System.out.println("Игра закончилась");
            }
            ActivnyIgrok = (ActivnyIgrok == Krestik ? Nolik : Krestik);
        }
        while (Statusigri == prodolchetsya);
    }

    public static void start_game() {
        for (int ryad = 0; ryad < Ryadi; ryad++) {
            for (int stolbec = 0; stolbec < Stolbci; stolbec++) {
                Сетка[ryad][stolbec] = Pusto;

            }
        }

        ActivnyIgrok = Krestik;
        VivodPolya();
    }

    //
    public static void VvodIgroka() {
        boolean VvodVeren = false;



        do {
            System.out.print("Игрок" + ActivnyIgrok + "Введите ряд от 1-3 через пробел ");

            int ryad = in.nextInt() - 1;
            int stolbec = in.nextInt() - 1;

            if (ryad >= 0 && ryad < Ryadi && stolbec >= 0 && stolbec < Stolbci && Сетка[ryad][stolbec] == Pusto) {
                Сетка[ryad][stolbec] = ActivnyIgrok;
                VvodVeren = true;
            } else{
                System.out.print("Выбранное размещение(" + (ryad + 1) + "," +
                        (stolbec + 1) + ")Не может быть использовано.Попробуйте еще раз   ");
        }
    }
        while (!VvodVeren);



    }
    //
    public static void analyzgame() {
        String Pobeditel = NaitiPobeditelya();
        if (Pobeditel.equals(Krestik)) {
            Statusigri = pobedaX;
        } else if (Pobeditel.equals(Nolik)) {
            Statusigri = pobedaO;
        } else if (fieldcells()) {
            Statusigri = nichya;
        } else {
            Statusigri = prodolchetsya;
        }
    }
    //
    public static boolean fieldcells(){
        {for (int ryad = 0; ryad < Ryadi; ryad++) {
            for (int stolbec = 0; stolbec < Stolbci; stolbec++) {
                if(Сетка[ryad][stolbec]==Pusto){
                    return false;
                }
            }
        }
            return  true;


    }}

    //метод все ли клеточки заполненны
    public static String NaitiPobeditelya() {
        int kolichestvoodinakovix;
        for (int ryad = 0; ryad < Ryadi; ryad++) {
            kolichestvoodinakovix = 0;
            for (int stolbec = 0; stolbec < Stolbci; stolbec++) {
                if (Сетка[ryad][0] != Pusto && Сетка[ryad][0] == Сетка[ryad][stolbec]) {
                    kolichestvoodinakovix++;
                }

            }
            if (kolichestvoodinakovix == 3) {
                return Сетка[ryad][0];
            }
        }
        for (int stolbec = 0; stolbec < Stolbci; stolbec++) {
            kolichestvoodinakovix = 0;
            for (int ryad = 0; ryad < Ryadi; ryad++) {
                if (Сетка[stolbec][0] != Pusto && Сетка[stolbec][0] == Сетка[stolbec][ryad]) {
                    kolichestvoodinakovix++;
                }

            }
            if (kolichestvoodinakovix == 3) {
                return Сетка[stolbec][0];
            }
        }
        if (Сетка[0][0] != Pusto && Сетка[0][0] == Сетка[1][1] && Сетка[0][0] == Сетка[2][2]) {


            return Сетка[0][0];
        }

        if (Сетка[0][2] != Pusto && Сетка[1][1] == Сетка[0][2] && Сетка[2][0] == Сетка[0][2]) {

            return Сетка[0][0];
        }
      return Pusto;
    }

    public static void VivodPolya() {
        for (int ryad = 0; ryad < Ryadi; ryad++) {
            for (int stolbec = 0; stolbec < Stolbci; stolbec++) {
                System.out.print(Сетка[ryad][stolbec]);
                if (stolbec != Stolbci - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
            if (ryad != Ryadi - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

}
