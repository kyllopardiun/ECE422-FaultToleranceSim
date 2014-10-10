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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mansueli
 */
public class Random {
    /**
     * 
     * @return a double value between 0 and 1.
     */
        public static double getRand() {
        String rand;
        try {
            final String query = "http://www.random.org/integers/?format=plain&min=0&max=100&num=1&base=10&col=1";
            rand = get(query);
            return Double.parseDouble(rand)/100.0;
        } catch (IOException ex) {
            Logger.getLogger(Random.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Math.random();
    }
/**
 * 
 * @param urlString A website URL which responds with a Plain Text output (UTF-8)
 * @return A String with the contents of the website
 * @throws IOException when it couldn´t load the page.
 */
    public static String get(String urlString) throws IOException {
        HttpURLConnection connection;
        BufferedReader br = null;
        String number = "";
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            number = br.readLine();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return number;
    }
}
