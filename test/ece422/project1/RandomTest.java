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

import ece422.utils.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class RandomTest {
    
    /**
     * Test of getRand method, of class Random.
     */
    @Test
    public void testGetRand() {
        System.out.println("getRand");
        double expResult = 0.0;
        double results[] = new double[10];
        for(double result : results){
            result = Random.getRand();
            assertEquals(expResult, result, 1);
        }
    }

    /**
     * Test of get method, of class Random.
     * @throws java.lang.Exception failed to connect
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("testGet");
        String urlString = "http://www.random.org/integers/?format=plain&min=0&max=100&num=1&base=10&col=1";
        double expResult = 0.0;
        String result = Random.get(urlString);
        double num = Double.parseDouble(result);
        assertEquals(num, expResult,100);
    }
    
}
