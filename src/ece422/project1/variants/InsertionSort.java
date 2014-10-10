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

import ece422.utils.FileUtils;
import ece422.utils.Random;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mansueli
 */
public class InsertionSort extends Sort {

    /**
     *
     * @param hazard the hazard value specified for the InsertSort i.e hazard
     * &gt; 0 and &lt; 1.
     */
    public InsertionSort(double hazard) {
        super.setHazard(hazard);
    }

    static {
        String os = System.getProperty("os.name");
        /*   try {
         if (os.startsWith("Wind")) {
         FileUtils.loadLibrary("/ece422/project1/variants/libInsertSort.dll");
         } else {
         if (os.contains("OS X")) {
         System.out.println("System not supported yet");
         } else {
         FileUtils.loadLibrary("/ece422/project1/variants/libInsertSort.so");
         }
         }
         } catch (Exception ex) {
         Logger.getLogger(InsertionSort.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        try {
            FileUtils.loadLibrary("/ece422/project1/variants/libInsertSort.so");
        } catch (Exception ep) {
            System.out.println("Couldn't Load the JNI Library");
            System.exit(0);
        }

    }

    // Declare native method
    /**
     * Native Method look at the C file for seeing the implementation
     *
     * @param array the array to be sorted in C.
     * @return sorted array
     */
    private native int[] sort(int[] array);

    @Override
    public void run() {
        try {
            if (super.getArray() == null || super.getArray().length == 0) {
                System.out.println("Array wasn´t set.");
            } else {
                doSort();
                if (super.getHazard() > 0.5) {
                    //Handles both numbers bigger than 0.5 and negative values
                    super.setHazard(Math.log(Math.abs(super.getHazard())) * 0.05);
                    System.out.println("Hazard Value is invalid, redefining it to:" + super.getHazard());
                }
                double random = Random.getRand();
                if (random > 0.5
                        && random < (0.5 + super.getHazard())) {
                    super.setArray(null);
                    //Enters in the error state (infinite loop)
                    while (true) {
                        errorMethod();
                    }
                }
            }
        } catch (ThreadDeath e) {
            System.out.println("InsertionSort Thread Failed");
            throw new ThreadDeath();
        }
    }

    private void errorMethod() {
    }

    /**
     * A public wrapper for the C code
     */
    public void doSort() {
        int[] array=sort(super.getArray());
        super.setArray(array);
    }
}
