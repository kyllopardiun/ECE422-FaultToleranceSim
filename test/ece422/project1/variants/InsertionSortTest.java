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
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class InsertionSortTest {
    
    /**
     * Test of run method, of class InsertionSort.
     */
    @Test
    public void testRun() {
        System.out.println("doSort");
        int[] array = new int[200];
        Random rand = new Random();
        for(int i: array) i = rand.nextInt();
        InsertionSort instance = new InsertionSort(0.0);
        instance.setArray(array);
        instance.doSort();
        boolean result = Adjucator.verify(instance.getArray());
        assertEquals(true, result);
    }
    
}
