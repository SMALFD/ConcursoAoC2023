import java.util.Arrays;
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
            // System.out.println(a);
            // System.out.println(b);
            System.out.println(a + b + " == " + line + "\n");
            res = a + b + res;
        }
        input.close();
        System.out.println("The calibration results are: " + res);
    }

    public static int scanUntilA(String line) {
        char a = 'a';
        int anum = 0;
        int n = 0;
        int charcount = 0;
        char[] arr = new char[5];
        boolean searching = false;
        while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != '8' && a != '9'
                && a != 'Ç') {
            a = line.charAt(n);
            arr[charcount] = line.charAt(n);
            //System.out.println("["+arr[0]+"]"+"["+arr[1]+"]"+"["+arr[2]+"]"+"["+arr[3]+"]"+"["+arr[4]+"]");
            if (a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8'
                    || a == '9') {
                anum = Character.getNumericValue(a);
                arr[0] = '0';
            }
            if (arr[0] == 'o' || arr[0] == 't' || arr[0] == 'f' || arr[0] == 's' || arr[0] == 'e' || arr[0] == 'n') {
                searching = true;
                charcount = 1;
                if (arr[1] == 'n' || arr[1] == 'w' || arr[1] == 'h' || arr[1] == 'o' || arr[1] == 'i' || arr[1] == 'e'
                        || arr[1] == 'g') {
                    charcount = 2;
                    if (arr[2] == 'e' || arr[2] == 'o' || arr[2] == 'u' || arr[2] == 'v' || arr[2] == 'i'
                            || arr[2] == 'v' || arr[2] == 'n' || arr[2] == 'r' || arr[2] == 'g' || arr[2] == 'x') {
                        charcount = 3;
                        String number = new String(arr);
                        char[] arr2 = Arrays.copyOf(arr, 5);
                        arr2[0] = '?';
                        String number2 = new String(arr2);
                        char[] arr3 = Arrays.copyOf(arr2, 5);
                        arr3[0] = '?';
                        arr3[1] = '?';
                        String number3 = new String(arr3);
                        if (number.equals("one\0\0") || number2.equals("?one\0") || number3.equals("??one")) {
                            a = '1';
                            anum = 1;
                        } else if (number.equals("two\0\0") || number2.equals("?two\0") || number3.equals("??two")) {
                            a = '2';
                            anum = 2;
                        } else if (number.equals("six\0\0") || number2.equals("?six\0") || number3.equals("??six")) {
                            a = '6';
                            anum = 6;
                        } else if (arr[3] == 'e' || arr[3] == 'r' || arr[3] == 'h') {
                            charcount = 4;
                            if (number.equals("four\0") || number2.equals("?four")) {
                                a = '4';
                                anum = 4;
                            } else if (number.equals("five\0") || number2.equals("?five")) {
                                a = '5';
                                anum = 5;
                            } else if (number.equals("nine\0") || number2.equals("?nine")) {
                                a = '9';
                                anum = 9;
                            } else if (number.equals("three")) {
                                a = '3';
                                anum = 3;
                            } else if (number.equals("seven")) {
                                a = '7';
                                anum = 7;
                            } else if (number.equals("eight")) {
                                a = '8';
                                anum = 8;
                            } else if (arr[4] != '\0') {
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 4;
                            } else if (arr[4] != '\0') {
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 4;
                            }
                        } else if (arr[3] != '\0') {
                            arr[1] = '\0';
                            arr[2] = '\0';
                            arr[3] = '\0';
                            charcount = 0;
                            searching = false;
                            n = n - 3;
                        }
                    } else if (arr[2] != '\0') {
                        arr[1] = '\0';
                        arr[2] = '\0';
                        charcount = 0;
                        searching = false;
                        n = n - 2;
                    }
                } else if (arr[1] != '\0') {
                    arr[1] = '\0';
                    charcount = 0;
                    searching = false;
                    n = n - 1;
                }
            } else if (searching == true) {// en teoría inútil pero XD
                charcount = 0;
                arr[1] = '\0';
                arr[2] = '\0';
                arr[3] = '\0';
                arr[4] = '\0';
                searching = false;
            }
            n++;
            //System.out.println(anum);
        }
        return anum;
    }

    public static int scanUntilB(String line) {
        char a = 'b';
        int anum = 0;
        int n = 0;
        int charcount = 0;
        char[] arr = new char[5];
        boolean searching = false;
        while (a != 'Ç') {
            a = line.charAt(n);
            arr[charcount] = line.charAt(n);
            /* System.out.println("[" + arr[0] + "]" + "[" + arr[1] + "]" + "[" + arr[2] + "]" + "[" + arr[3] + "]" + "["
                    + arr[4] + "]"); */
            if (a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8'
                    || a == '9') {
                anum = Character.getNumericValue(a);
                arr[0] = '0';
            }
            if (arr[0] == 'o' || arr[0] == 't' || arr[0] == 'f' || arr[0] == 's' || arr[0] == 'e' || arr[0] == 'n') {
                searching = true;
                charcount = 1;
                if (arr[1] == 'n' || arr[1] == 'w' || arr[1] == 'h' || arr[1] == 'o' || arr[1] == 'i'
                        || arr[1] == 'e') {
                    charcount = 2;
                    if (arr[2] == 'e' || arr[2] == 'o' || arr[2] == 'u' || arr[2] == 'v' || arr[2] == 'i'
                            || arr[2] == 'n' || arr[2] == 'r' || arr[2] == 'g' || arr[2] == 'x') {
                        charcount = 3;
                        String number = new String(arr);
                        char[] arr2 = Arrays.copyOf(arr, 5);
                        arr2[0] = '?';
                        String number2 = new String(arr2);
                        char[] arr3 = Arrays.copyOf(arr, 5);
                        arr3[0] = '?';
                        arr3[1] = '?';
                        String number3 = new String(arr3);// estas ultimas lineas son el peor botch que he hecho jamas.
                                                          // ojala se pudiera marcar char cualquiera
                        if (number.equals("one\0\0") || number2.equals("?one\0") || number3.equals("??one")) {
                            a = '0';
                            anum = 1;
                            arr[1] = '\0';
                            arr[2] = '\0';
                            arr[3] = '\0';
                            arr[4] = '\0';
                            charcount = 0;
                            searching = false;
                            n = n - 1;
                        } else if (number.equals("two\0\0") || number2.equals("?two\0") || number3.equals("??two")) {
                            a = '0';
                            anum = 2;
                            arr[1] = '\0';
                            arr[2] = '\0';
                            arr[3] = '\0';
                            arr[4] = '\0';
                            charcount = 0;
                            searching = false;
                            n = n - 1;
                        } else if (number.equals("six\0\0") || number2.equals("?six\0") || number3.equals("??six")) {
                            a = '0';
                            anum = 6;
                            arr[1] = '\0';
                            arr[2] = '\0';
                            arr[3] = '\0';
                            arr[4] = '\0';
                            charcount = 0;
                            searching = false;
                            n = n - 1;
                        } else if (arr[3] == 'e' || arr[3] == 'r' || arr[3] == 'h') {
                            charcount = 4;
                            if (number.equals("four\0") || number2.equals("?four")) {
                                a = '0';
                                anum = 4;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (number.equals("five\0") || number2.equals("?five")) {
                                a = '0';
                                anum = 5;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (number.equals("nine\0") || number2.equals("?nine")) {
                                a = '0';
                                anum = 9;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (number.equals("three")) {
                                a = '0';
                                anum = 3;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (number.equals("seven")) {
                                a = '0';
                                anum = 7;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (number.equals("eight")) {
                                a = '0';
                                anum = 8;
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 1;
                            } else if (arr[4] != '\0') {
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 4;
                            } else if (arr[4] != '\0') {
                                arr[1] = '\0';
                                arr[2] = '\0';
                                arr[3] = '\0';
                                arr[4] = '\0';
                                charcount = 0;
                                searching = false;
                                n = n - 4;
                            }
                        } else if (arr[3] != '\0') {
                            arr[1] = '\0';
                            arr[2] = '\0';
                            arr[3] = '\0';
                            charcount = 0;
                            searching = false;
                            n = n - 3;
                        }
                    } else if (arr[2] != '\0') {
                        arr[1] = '\0';
                        arr[2] = '\0';
                        charcount = 0;
                        searching = false;
                        n = n - 2;
                    }
                } else if (arr[1] != '\0') {
                    arr[1] = '\0';
                    charcount = 0;
                    searching = false;
                    n = n - 1;
                }
            } else if (searching == true) {
                charcount = 0;
                arr[1] = '\0';
                arr[2] = '\0';
                arr[3] = '\0';
                arr[4] = '\0';
                searching = false;
            }
            n++;
            //System.out.println(anum);
        }
        return anum;
    }
}