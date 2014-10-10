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

/**
 *
 * @author mansueli
 */
public class Adjucator {

    /**
     *
     * @param array to be verified
     * @return whether it passed the Acceptance Test
     */
    public static boolean verify(int[] array){
        if(array == null) return false;
        int aux = -Integer.MAX_VALUE;
        for(int i: array){
            if(i>=aux)
                aux = i;
            else
                return false;
        }
        return true;
    }
}
