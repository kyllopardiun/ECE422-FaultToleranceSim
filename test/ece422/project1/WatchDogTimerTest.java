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

import java.util.Timer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class WatchDogTimerTest {

    /**
     * Test of run method, of class WatchDogTimer.
     */
    @Test
    public void testRun() {
        System.out.println("WDog run");
        int time = 1000;
        Timer t = new Timer();
        Thread tr = new Thread() {
            @Override
            public void run() {
                while (true) {
                    doNothing();
                }
            }
            private void doNothing() {
            }
        };
        WatchDogTimer dog = new WatchDogTimer(tr);
        tr.start();
        t.schedule(dog, time);
        try {
            tr.join();
        } catch (InterruptedException ex) {
            fail("This test failed.");
        }
        
    }

}
