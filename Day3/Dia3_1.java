import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dia3_1 {

    public static void main(String[] args) throws FileNotFoundException {
        File arch = new File(
                "C:\\Users\\santi\\OneDrive\\Documentos\\Santiago uni\\AdventofCode2023\\adventDay3\\Day3\\src\\day3testdata.txt");
        Scanner input = new Scanner(arch);
        Scanner input2 = new Scanner(arch);
        Scanner input3 = new Scanner(arch);
        int res = 0;
        String line = "";
        String line2 = "";
        String line3 = "";
        line2 = input2.nextLine();
        line3 = input3.nextLine();
        line3 = input3.nextLine();
        int puntero = 0;
        char[] linchar2 = line2.toCharArray();
        char[] linchar3 = line3.toCharArray();
        boolean validin1 = false;
        boolean validin2 = false;
        boolean validin3 = false;
        while (puntero < linchar2.length - 1) {// comprobamos la primera linea
            char actual = linchar2[puntero];
            int ascii = (int) actual;
            if (ascii >= 48 && ascii <= 57) {
                int num[] = getFullnum(linchar2, puntero);
                int range = num[1];
                int number = num[0];
                validin2 = isThereSymbol(linchar2, puntero - 1, range);
                validin3 = isThereSymbol(linchar3, puntero - 1, range);
                if (validin2 == true || validin3 == true) {
                    res = res + number;
                }
                puntero = puntero + range - 1;
            }
            puntero++;
        }
        while (input3.hasNextLine()) {// comprueba texto entero menos la primera y ultima linea
            line = input.nextLine();
            line2 = input2.nextLine();
            line3 = input3.nextLine();
            char[] linchar = line.toCharArray();
            linchar2 = line2.toCharArray();
            linchar3 = line3.toCharArray();
            puntero = 0;
            int range = 0;
            while (puntero < linchar2.length ) {// comprueba linea entera palabra a palabra
                char actual = linchar2[puntero];
                int ascii = (int) actual;
                if (ascii >= 48 && ascii <= 57) {
                    int num[] = getFullnum(linchar2, puntero);
                    range = num[1];
                    int number = num[0];
                    //System.out.println("El puntero apunta a "+ linchar[puntero]+ " y el range es " + range);
                    validin1 = isThereSymbol(linchar, puntero - 1, range);
                    //System.out.println("El puntero apunta a "+ linchar2[puntero]+ " y el range es " + range);
                    validin2 = isThereSymbol(linchar2, puntero - 1, range);
                    //System.out.println("El puntero apunta a "+ linchar3[puntero]+ " y el range es " + range);
                    validin3 = isThereSymbol(linchar3, puntero - 1, range);
                    if (validin1 == true || validin2 == true || validin3 == true) {
                        res = res + number;
                    }
                    puntero = puntero + range - 1;
                }
                puntero++;
            }
        }
        puntero = 0;
        while (puntero < linchar3.length - 1) {// Comprobamos la ultima linea con 3 como el "jefe"
            char actual = linchar3[puntero];
            int ascii = (int) actual;
            if (ascii >= 48 && ascii <= 57) {
                int num[] = getFullnum(linchar3, puntero);
                int range = num[1];
                int number = num[0];
                validin2 = isThereSymbol(linchar2, puntero - 1, range);
                validin3 = isThereSymbol(linchar3, puntero - 1, range);
                if (validin2 == true || validin3 == true) {
                    res = res + number;
                }
                puntero = puntero + range - 1;
            }
            puntero++;
        }
        System.out.println("The gear ratio is " + res);
        input.close();
        input2.close();
        input3.close();
    }

    public static int[] getFullnum(char[] linechar, int puntero) {
        int[] res = new int[2];
        char a = linechar[puntero];
        int ascii = (int) a;
        int val[] = new int[3];
        int digitcount = 0;
        int n = 0;
        boolean end = false;
        while (ascii >= 48 && ascii <= 57 && end == false) {
            digitcount++;
            if (digitcount == 1) {
                val[2] = Character.getNumericValue(a);
            } else if (digitcount == 2) {
                val[1] = val[2];
                val[2] = Character.getNumericValue(a);
            } else if (digitcount == 3) {
                val[0] = val[1];
                val[1] = val[2];
                val[2] = Character.getNumericValue(a);
            }
            n++;
            if (linechar.length > puntero + n) {
                a = linechar[puntero + n];
                ascii = (int) a;
            } else {
                end = true;
            }
        }
        int fullNumber = val[0] * 100 + val[1] * 10 + val[2];
        res[0] = fullNumber;
        res[1] = n;
        //System.out.println("Found number " + fullNumber);
        return res;
    }

    public static boolean isThereSymbol(char[] linechar, int puntero, int range) {
        boolean valid = false;
        char a = 'a';
        int ascii = (int) a;
        int n = 0;
        if (puntero - 1 < 0) {
            puntero++;
        }
        while (n <= range+1 && puntero+n<linechar.length) {
            a = linechar[puntero + n];
            ascii = (int) a;
            if (a != '.') {
                if (ascii < 48 || ascii > 57) {
                    valid = true;
                }
            }
            n++;
        }
        //System.out.println(valid);
        //System.out.println('\0');
        return valid;
    }
}
