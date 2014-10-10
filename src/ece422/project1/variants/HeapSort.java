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

import ece422.utils.Random;

/**
 *
 * @author mansueli
 */
public class HeapSort extends Sort {

    /**
     *
     * @param hazard the hazard specified for the HeapSort &gt; 0 and &lt; 1
     */
    public HeapSort(double hazard) {
        super.setHazard(hazard);
    }

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
                    System.out.println("Hazard Value is invalid, redefining it to:");
                }
                double random = Random.getRand();
                if (random > 0.5
                        && random < (0.5 + super.getHazard())) {
                    //Enters in the error state (infinite loop)
                    super.setArray(null);
                    while (true) {
                        errorMethod();
                    }
                }
            }
        } catch (ThreadDeath e) {
            System.out.println("HeapSort Thread Failed");
            throw new ThreadDeath();
        }
    }

     /**
     * Based on the description on the document below: <br>
     * Stefanes, Marco Aurélio.<br>
     * Available in: <a href="http://www.facom.ufms.br/~marco/aed22008/aula06_4.pdf">http://www.facom.ufms.br/~marco/aed22008/aula06_4.pdf</a>
     *
     */
    public void doSort() {
        int aux = super.getArray().length;
        buildHeap(super.getArray(), aux);
        int last = --aux;
        while (last > 0) {
            swap(super.getArray(), 0, last);
            heapify(super.getArray(), 0, --last);
        }
    }

    /**
     * Swap two variables in an Array
     *
     * @param array the array containing both variables
     * @param i position of the first element
     * @param j position of the last element
     */
    public void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    /**
     *
     * @param array the array being used in the heapSort
     * @param num last position of this heap
     */
    public void buildHeap(int[] array, int num) {
        int start = (num - 2) / 2;
        while (start >= 0) {
            heapify(array, start, num - 1);
            start--;
        }
    }
    /**
     * @param array the array being used in the heapSort
     * @param first first element of the heap
     * @param last last element of the heap
     */
    public void heapify(int[] array, int first, int last) {
        int parent = first;
        while (((parent * 2) + 1) <= last) {
            int leaf = (parent * 2) + 1;
            if (leaf + 1 <= last && array[leaf] < array[leaf + 1]) {
                leaf++;
            }
            if (array[parent] < array[leaf]) {
                swap(array, parent, leaf);
                parent = leaf;
            } else {
                break;
            }
        }
    }
    /**
     * Just to avoid warnings
     */
    private void errorMethod() {}
}
