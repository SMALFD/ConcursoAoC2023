import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dia1 {

    public static void main(String[] args) throws FileNotFoundException {
        File arch = new File(
                "C:\\Users\\santi\\OneDrive\\Documentos\\Santiago uni\\AdventofCode2023\\adventday1\\src\\adventofcode1.txt");
        Scanner input = new Scanner(arch);
        int res = 0;
        int a;
        int b;
        String line = "hm" + "\n";
        while (input.hasNextLine()) {
            line = input.nextLine() + "Ç";
            a = scanUntilA(line) * 10;
            b = scanUntilB(line);
            res = a + b + res;
        }
        input.close();
        System.out.println("The calibration results are: " + res);
    }

    public static int scanUntilA(String line) {
        char a = 'a';
        int anum = 0;
        int n = 0;
        while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != '8' && a != '9'
                && a != '0' && a != 'Ç') {
                    a = line.charAt(n);
            if (a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9'
                    || a == '0') {
                anum = Character.getNumericValue(a);
            }
            n++;
        }
        return anum;
    }

    public static int scanUntilB(String line) {
        char b = 'b';
        int anum = 0;
        int n = 0;
        while (b != 'Ç') {
            b = line.charAt(n);
            if (b == '1' || b == '2' || b == '3' || b == '4' || b == '5' || b == '6' || b == '7' || b == '8' || b == '9'
                    || b == '0') {
                anum = Character.getNumericValue(b);
            }
            n++;
        }
        return anum;
    }

}