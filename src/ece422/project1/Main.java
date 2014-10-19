/*
 * The file commons-cli-1.2.jar is under Apache License Version 2.0. 
 * For more details read the file "Apache License.txt" or check it on their website: 
 *      <http://www.apache.org/licenses/LICENSE-2.0.txt> 
 *
 * All other components of this software is under dual licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage for commercial usage you must contact the author prior distribution, usage.
 *
 * @Author: Rodrigo Mansueli Nunes
 * @e-mail: mansueli@ualberta.ca
 * @site: http://kyllo.com.br
 */
package ece422.project1;

import ece422.utils.CliParser;
import ece422.project1.variants.HeapSort;
import ece422.project1.variants.InsertionSort;
import ece422.project1.variants.Sort;
import ece422.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mansueli
 */
public class Main {

    private static File input;
    private static File output;
    private static double cHazard, jHazard;
    private static int time;

    /**
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CliParser parser = new CliParser(args);
        parser.parse();
        input = parser.getInput();
        int[] inputArray = null;
        try {
            inputArray = FileUtils.file2Array(input);
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        output = parser.getOutput();
        time = parser.getTime() * 1000;
        cHazard = parser.getcHazard();
        jHazard = parser.getjHazard();
        Sort sorter = null;
        boolean succedded = false;
        Adjucator adj = new AcceptanceTest();
        try {
            sorter = new HeapSort(jHazard);
            sorter.setArray(inputArray);
            Timer t = new Timer();
            WatchDogTimer dog = new WatchDogTimer(sorter);
            System.out.println("Started the HeapSort");
            t.schedule(dog, time);
            sorter.start();
            sorter.join();
            t.cancel();
            succedded = adj.verify(sorter.getArray());
        } catch (InterruptedException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        if (succedded) {
            System.out.println("HeapSort succedded");
            FileUtils.saveFile(output, sorter.getArray());
        } else {
            try {
                System.out.println("Started the InsertionSort");
                sorter = new InsertionSort(cHazard);
                sorter.setArray(inputArray);
                Timer t = new Timer();
                WatchDogTimer dog = new WatchDogTimer(sorter);
                t.schedule(dog, 1000);
                sorter.start();
                sorter.join();
                t.cancel();
                succedded = adj.verify(sorter.getArray());
                if (succedded) {
                    System.out.println("InsertionSort succedded");
                    FileUtils.saveFile(output, sorter.getArray());
                } else {
                    System.out.println("An error occurred in the Backup method, the system failed.");
                }
            } catch (InterruptedException e) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
