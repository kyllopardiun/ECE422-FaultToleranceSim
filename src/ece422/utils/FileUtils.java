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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mansueli
 */
public class FileUtils {

    /**
     * @throws java.io.IOException couldn't find the file
     * @param file the file which will be used as input
     * @return an array with the parsed values
     */
    public static int[] file2Array(File file) throws IOException {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        int[] result = null;
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            fis.read(bytes);
            String[] rawArray = new String(bytes).trim().split("\\s+");
            result = new int[rawArray.length];
            for (int i = 0; i < rawArray.length; i++) {
                result[i] = Integer.parseInt(rawArray[i]);
            }
        } catch (IOException e) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    /**
     *
     * @param file the file to be written
     * @param content the array that will be written in the file
     */
    public static void saveFile(File file, int[] content) {
        try (Writer writer = new FileWriter(file.getAbsolutePath(), false)) {
            for (int i : content) {
                writer.write(Integer.toString(i) + " ");
            }
        } catch (IOException e) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     /**
     * 
     * @param path the path inside the jar
     * @throws java.lang.Exception couldn't create or use the file
     */
    public static void loadLibrary(String path) throws Exception {
        File temp = File.createTempFile("libJNI","so");
        temp.deleteOnExit();
        byte[] buffer = new byte[256];
        int readBytes;
        InputStream is = FileUtils.class.getResourceAsStream(path);
        try (OutputStream os = new FileOutputStream(temp)) {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            is.close();
        }
        System.load(temp.getAbsolutePath());
    }
}
