#include "ece422_project1_variants_InsertionSort.h"
#include "jni.h"
#include <stdio.h>
int memoryAccess = 0;
/**
 Prototype
 */
void insertSort(jint*, jsize);

/*
 * Class:     SortArray
 * Method:    sort
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_ece422_project1_variants_InsertionSort_sort
(JNIEnv *env, jobject obj, jintArray array) {
    jsize arrayLength = env->GetArrayLength(array);
    jintArray arrSorted = env->NewIntArray(arrayLength);
    jint *outputArray = NULL;
    outputArray = env->GetIntArrayElements(array, 0);
    insertSort(outputArray, arrayLength);
    printf("\nMemory accesses occured in InsertionSort: %d\n", memoryAccess);
    env->SetIntArrayRegion(arrSorted, 0, arrayLength, outputArray);
    env->ReleaseIntArrayElements(array, outputArray, 0);
    return arrSorted;
}

/**
 * Based on the description on the site below:
 * Feofiloff, Paulo. Available in: <http://www.ime.usp.br/~pf/analise_de_algoritmos/aulas/insert.html>
 * @param p jint Array to be sorted
 * @param size size of the array that will be sorted
 */
void insertSort(jint *p, jsize size) {
    int key, i;
    if (size > 1) {
        insertSort(p, size - 1);
        memoryAccess++;
        key = p[size];
        memoryAccess++;
        i = size - 1;
        memoryAccess++;
        while (i > 0 && p[i] > key) {
            memoryAccess++;
            p[i + 1] = p[i];
            memoryAccess++;
            i--;
            memoryAccess++;
        }
        p[i + 1] = key;
        memoryAccess++;
    }
}
