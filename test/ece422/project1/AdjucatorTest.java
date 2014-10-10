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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class AdjucatorTest {
    /**
     * Test of verify method, of class Adjucator.
     */
    @Test
    public void testVerify() {
        System.out.println("verify");
        int[] arrayFalse = {2,3,2,3,4,5,23,23,12};
        int[] arrayTrue = {-21,-10,2,12,20,21,21,25,27};
        boolean expResultFalse = false;
        boolean expResultTrue = true;
        boolean result = Adjucator.verify(arrayFalse);
        assertEquals(expResultFalse, result);
        result = Adjucator.verify(arrayTrue);
        assertEquals(expResultTrue, result);
    }
    
}
