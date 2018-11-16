package at.htl.exam01.compress;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class StringCompress {

    private static final String FILE_NAME = "sample.txt";

    /**
     * Main-Methode, hier wird das StringCompress-Objekt erstellt
     * und die Methoden des Objekts werden aufgerufen
     *
     * @param args
     */
    public static void main(String[] args) {
        StringCompress sc = new StringCompress();
        String[] text = sc.readFromFile(FILE_NAME);
        sc.print(text);
    }


    /**
     *
     * Sämtliche Zeilen werden aus der Textdatei eingelesen
     * zB 5A
     * Nun wird in das String-Array AAAAA geschrieben
     *
     * Bsp Testdatei
     * 5A
     * 3B
     * 4C
     *
     * ergibt eine String-Array mit 3 Elementen
     * AAAAA
     * BBB
     * CCCC
     *
     * @param fileName
     * @return String-Array mit dem entpacktem Text
     */
    public String[] readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        String[] textArray = new String[4];
        int lines = getNoOfLines(fileName);
        int count = 0;
        double number = 0;
        String help = "";
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            do {
                number = 0;
                int t = 0;
                String text = scanner.nextLine();
                for (int i = 1; i < text.length(); i++) {
                    if(i >= 2){
                        number = number * Math.pow(10, t);
                        number += ((int)text.charAt(i) - 48);
                    } else {
                        number += ((int) text.charAt(i) - 48);
                    }
                    t++;

                }
                number++;
                for (int i = 1; i < number; i++) {
                    help += text.charAt(0);
                }
                textArray[count] = help;
                help = "";
            /*for (int i = 1; i < text.length(); i++) {
                count = (int)text.charAt(i) - 48;
                scanner.nextLine();
            }
            for (int i = 0; i < count; i++) {
                textArray[i] = text.charAt(0);
            }*/
            count++;
            } while (count < lines + 1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return textArray;
    }


    /**
     * Der Inhalt des String-Arrays wird zeilenweise auf der Console ausgegeben
     *
     *
     * @param lines String-Array
     */
    public void print(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                System.out.print(lines[i].charAt(j));
            }
            System.out.println();
        }
    }

    /**
     * Die Anzahl der Zeilen der übergebenen Textdatei wird bestimmt
     *
     * @param fileName
     * @return Anzahl der Zeilen in der Textdatei
     */
    public int getNoOfLines(String fileName) {
        int count = 0;
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            for (int i = 0; scanner.hasNextLine(); i++) {
                scanner.nextLine();
                count = i;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return count;
    }
}
