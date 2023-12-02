import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dia2parte1 {

    public static void main(String[] args) throws FileNotFoundException {
        File arch = new File(
                "C:\\Users\\santi\\OneDrive\\Documentos\\Santiago uni\\AdventofCode2023\\adventDay2\\src\\day2testdata.txt");
        Scanner input = new Scanner(arch);
        int id = 0;
        int res = 0;
        String line = "";
        while (input.hasNextLine()) {// comprueba texto entero
            line = input.nextLine();
            id = grabid(line);
            boolean valid = true;
            char[] linchar = line.toCharArray();
            int start = pointStart(linchar);
            while (valid && start < linchar.length - 1) {// comprueba linea entera palabra a palabra
                int[] numberofcube = getNumber(linchar, start);
                start = numberofcube[1];
                char colour = getcolour(linchar, start);// r para red, g para green y b para blue
                if (colour == 'b' && numberofcube[0] > 14) {
                    valid = false;
                    //System.out.println("Too many blue cubes at "+start);
                    //System.out.println("There were "+ numberofcube[0] + " cubes");
                } else if (colour == 'g' && numberofcube[0] > 13) {
                    valid = false;
                    //System.out.println("Too many green cubes at "+start);
                    //System.out.println("There were "+ numberofcube[0] + " cubes");
                } else if (colour == 'r' && numberofcube[0] > 12) {
                    valid = false;
                    //System.out.println("Too many red cubes at "+start);
                    //System.out.println("There were "+ numberofcube[0] + " cubes");
                }
                start = nextNumber(start, linchar);
            }
            if (valid == true) {
                res = id + res;
                //System.out.println("Id of valid game is= "+ id + '\n');
            }
        }
        input.close();
        System.out.println("The sum of IDs is " + res);
    }

    public static int grabid(String line) {
        int n = 5;// empezamos ya en el numero
        char a = line.charAt(n);
        int numcount = 2;
        int[] arr = new int[3];
        a = line.charAt(n);
        while (a != ':') {
            if (numcount == 1) {
                arr[1] = arr[2];
            }
            if (numcount == 0) {
                arr[0] = arr[1];
                arr[1] = arr[2];
            }
            // System.out.println(a);
            arr[2] = Character.getNumericValue(a);
            numcount--;
            n++;
            a = line.charAt(n);
        }
        int first = arr[0] * 100;
        int second = arr[1] * 10;
        int third = arr[2];
        int res = first + second + third;
        // System.out.println("Number: "+res);
        return res;
    }

    public static int pointStart(char[] linechar) {
        int n = 0;
        char a = 'a';
        while (a != ':') {
            a = linechar[n];
            n++;
        }
        n++;
        return n;
    }

    public static int[] getNumber(char[] line, int start) {
        int[] res = new int[2];
        int[] nfin = new int[2];
        char a = line[start];
        int ascii = (int) a;
        int val = 1;
        int n = start;
        while (ascii < 97 || ascii > 122) {
            a = line[n];
            ascii = (int) a;
            if (ascii >= 48 && ascii <= 57) {
                if (val == 0) {
                    nfin[1] = Character.getNumericValue(a);
                    val--;
                } else {
                    nfin[0] = Character.getNumericValue(a);
                    val--;
                }
            } else if (nfin[0] >= 49 || nfin[0] <= 57) {
                if (val == 0) {
                    nfin[1] = nfin[0];
                    nfin[0] = 0;
                    val--;
                }
            }
            n++;// proximo start
            // System.out.println(n);
            // System.out.println("process of 0 = " + nfin[0]);
            // System.out.println("process of 1 = " + nfin[1]);
        }
        int first = nfin[0] * 10;
        int second = nfin[1];
        // System.out.println("Summatories are: " + nfin[0]);
        // System.out.println("and " + nfin[1]);
        res[0] = first + second;// valor
        res[1] = n;// start
        // System.out.println("Final num is: " + res[0]);
        return res;
    }

    public static char getcolour(char[] line, int start) {
        char colour = 'a';
        //System.out.println("Detecto este valor == " + line[start-1]);
        if (line[start-1] == 'b') {
            colour = 'b';
        } else if (line[start-1] == 'g') {
            colour = 'g';
        } else {
            colour = 'r';
        }
        return colour;
    }

    public static int nextNumber(int pos, char[] linchar) {
        int n = pos;
        char a = linchar[pos];
        int ascii = (int) a;
        while (ascii < 48 || ascii > 57) {
            if (n >= linchar.length - 1) {
                //System.out.println("entro con " + n);
                return n;
            }
            n++;
            a = linchar[n];
            ascii = (int) a;
        }
        n--;
        return n;
    }
}
