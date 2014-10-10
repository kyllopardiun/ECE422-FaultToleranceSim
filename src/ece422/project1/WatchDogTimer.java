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

import java.util.TimerTask;

/**
 *
 * @author mansueli
 */
public class WatchDogTimer extends TimerTask {

    private final Thread watched;

    /**
     *
     * @param thread the thread that will be watched
     */
    public WatchDogTimer(Thread thread) {
        this.watched = thread;
    }

    @Override
    public void run() {
        watched.stop();
    }
}
