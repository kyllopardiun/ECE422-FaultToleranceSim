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
package ece422.project1.variants;

import ece422.project1.Adjucator;
import ece422.project1.Main;
import ece422.project1.WatchDogTimer;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class InsertionSortIntegrationTest {
    
    /**
     * Test of run method, of class InsertionSort.
     */
    @Test
    public void testRun() {
        System.out.println("InsertionSort");
        int time = 1000;
        int[] inputArray = {12, 28, 121, 892, 29, 9, 23, 29, 77, 7, -32, -10, 24, 42};
        double cHazard = 0.01;
        Sort sorter;
        boolean succedded;
        try {
            sorter = new InsertionSort(cHazard);
            sorter.setArray(inputArray);
            Adjucator adj = new Adjucator();
            Timer t = new Timer();
            WatchDogTimer dog = new WatchDogTimer(sorter);
            t.schedule(dog, time);
            sorter.start();
            sorter.join();
            t.cancel();
            succedded = Adjucator.verify(sorter.getArray());
            assertEquals(succedded,true);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            fail("Exception" + e);
        }
    }
    
}
