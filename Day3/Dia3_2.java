import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dia3_2 {

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
        int numGear = 0;
        int numofgear1 = 1;
        int numofgear2 = 1;
        int numofgear3 = 1;
        int numofgear4 = 1;
        int numofgear5 = 1;
        int numofgear6 = 1;
        while (puntero <= linchar2.length - 1) {// comprobamos la primera linea
            char actual = linchar2[puntero];
            if (actual == '*') {
                int[] numsides = checkSides(linchar2, puntero);
                numGear = numsides[0];
                numofgear1 = numsides[1];
                numofgear2 = numsides[2];
                int[] numtop = checkTopBot(linchar3, puntero);
                numGear = numGear + numtop[0];
                numofgear3 = numtop[1];
                numofgear4 = numtop[2];
                if (numGear == 2) {
                    res = numofgear1 * numofgear2 * numofgear3 * numofgear4;
                }
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
            while (puntero < linchar2.length) {// comprueba linea entera palabra a palabra
                char actual = linchar2[puntero]; // puntero apunta al caracter actual
                if (actual == '*') {
                    int[] numtop = checkTopBot(linchar, puntero);
                    numGear = numtop[0];
                    numofgear1 = numtop[1];
                    numofgear2 = numtop[2];
                    int[] numsides = checkSides(linchar2, puntero);
                    numGear = numGear + numsides[0];
                    numofgear3 = numsides[1];
                    numofgear4 = numsides[2];
                    int[] numbot = checkTopBot(linchar3, puntero);
                    numGear = numGear + numbot[0];
                    numofgear5 = numbot[1];
                    numofgear6 = numbot[2];
                    //System.out.println("La multiplicacion es de= "+ numofgear1 + " * " + numofgear2+ " * " +numofgear3+ " * " +numofgear4+ " * " +numofgear5+ " * " +numofgear6);
                    if (numGear == 2) {
                        res = (numofgear1 * numofgear2 * numofgear3 * numofgear4*numofgear5*numofgear6)+res;
                        //System.out.println("La cuenta actual es de " + res + " gracias al puntero en "+ puntero + "\n");
                    }
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
        return res;
    }

    public static int startNum(char[] linchar, int pos) {
        char a = linchar[pos];
        int ascii = (int) a;
        while (ascii >= 48 && ascii <= 57 && pos > 0) {
            pos--;
            a = linchar[pos];
            ascii = (int) a;
        }
        pos++;
        a = linchar[pos-1];
        ascii = (int) a;
        if(ascii >= 48 && ascii <= 57){
            pos--;
        }
        return pos;
    }

    public static int[] checkTopBot(char[] linchar, int pos) {
        int[] res = new int[3];
        res[0] = 0;
        res[1] = 1;
        res[2] = 1;
        char a = linchar[pos];
        int ascii = (int) a;
        int num = 1;
        int numl = 1;
        int numr = 1;
        int numGear = 0;
        if (ascii >= 48 && ascii <= 57) {// case el de arriba o el de abajo directamente son numeros
            int newpos = startNum(linchar, pos);
            int[] numaux = getFullnum(linchar, newpos);
            num = numaux[0];
            numGear++;
        } else {
            if (pos > 0) { // case hay numero en la izquierda pero no en medio
                int posleft = pos - 1;
                char l = linchar[posleft];
                int asciil = (int) l;
                if (asciil >= 48 && asciil <= 57) {
                    int newposl = startNum(linchar, posleft);
                    //System.out.println(newposl);
                    int[] numauxl = getFullnum(linchar, newposl);
                    numl = numauxl[0];
                    numGear++;
                }
            }
            if (linchar.length > pos + 1) {// case hay numero en la derecha pero no en medio
                int posright = pos + 1;
                char r = linchar[posright];
                int asciir = (int) r;
                if (asciir >= 48 && asciir <= 57) {
                    int[] numauxr = getFullnum(linchar, posright);
                    numr = numauxr[0];
                    numGear++;
                }
            }
        }
        res[0] = numGear;
        if (num == 1) {// si num no ha cambiado
            res[1] = numl;
            res[2] = numr;
        } else {// si num ha cambiado
            res[1] = num;
        }
        return res;
    }

    public static int[] checkSides(char[] linchar, int pos) {
        int[] res = new int[3];
        res[0] = 0;
        res[1] = 1;
        res[2] = 1;
        int numl = 1;
        int numr = 1;
        int numGear = 0;
        if (pos > 0) { // case hay numero en la izquierda
            int posleft = pos - 1;
            char l = linchar[posleft];
            int asciil = (int) l;
            if (asciil >= 48 && asciil <= 57) {
                int newposl = startNum(linchar, posleft);
                int[] numauxl = getFullnum(linchar, newposl);
                numl = numauxl[0];
                numGear++;
            }
            if (linchar.length > pos + 1) {// case hay numero en la derecha pero no en medio
                int posright = pos + 1;
                char r = linchar[posright];
                int asciir = (int) r;
                if (asciir >= 48 && asciir <= 57) {
                    int[] numauxr = getFullnum(linchar, posright);
                    numr = numauxr[0];
                    numGear++;
                }
            }
        }
        res[0] = numGear;
        res[1] = numl;
        res[2] = numr;
        return res;
    }
}
