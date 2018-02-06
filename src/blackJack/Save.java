package blackJack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {

    private File path = new File("src/save/newSave.txt");
    private ArrayList<String> list;

    public void saveFile(String n, int c)
    {
        //write to a file
        try( PrintWriter writer = new PrintWriter(path)) {
            writer.printf("%s %d", n, c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> readFile()
    {
        //read strings from a file and place them into an array
        list = new ArrayList<String>();

        try(Scanner scan = new Scanner(path)) {
            while (scan.hasNext())
            {
                String a = scan.next();
                String b = scan.next();
                list.add(a);
                list.add(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            saveFile(null, 0);
        }
        return list;
    }



}

