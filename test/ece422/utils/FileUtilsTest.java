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
package ece422.utils;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mansueli
 */
public class FileUtilsTest {

    /**
     * Test of file2Array method, of class FileUtils.
     */
    @Test
    public void testFile2Array() {
        try {
            System.out.println("file2Array");
            File file = new File("/home/mansueli/NetBeansProjects/ECE422.Project1/build/test/classes/ece422/utils/file.input");
            int[] expResult = {12, 28, 121, 892, 29, 9, 23, 29, 77, 7, -32, -10, 24, 42};
            int[] result = FileUtils.file2Array(file);
            //for(int oi: result) System.out.print(oi + " ");
            assertArrayEquals(expResult, result);
        } catch (Exception e) {
            fail("Exception");
        }
    }

    /**
     * Test of saveFile method, of class FileUtils.
     */
    @Test
    public void testSaveFile() {
        System.out.println("saveFile");
        File file = new File("output.txt");
        int[] content = {12, 28, 121, 892, 29, 9, 23, 29, 77, 7, -32, -10, 24, 42};
        FileUtils.saveFile(file, content);
        assertEquals(file.exists(), true);
    }

}
