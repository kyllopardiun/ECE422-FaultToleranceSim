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
    (JNIEnv *env, jobject obj, jintArray arr){
    jsize arrLength = env->GetArrayLength(arr);
    jintArray arrSorted = env->NewIntArray(arrLength); 
    jint *arrOut = NULL;
    arrOut = env->GetIntArrayElements(arr, 0);
//    for(jsize x = 0; x < arrLength; x++){
//        for(jsize y = 0; y < arrLength - 1; y++){
//                        if(arrOut[y] > arrOut[y+1]){
//				jsize temp = arrOut[y+1];
//				arrOut[y+1] = arrOut[y];
//				arrOut[y] = temp;
//			}
//		}
//	}
    insertSort(arrOut, arrLength);
    printf("\nMemory accesses occured in InsertionSort: %d\n", memoryAccess);
    env->SetIntArrayRegion(arrSorted, 0, arrLength, arrOut);
    env->ReleaseIntArrayElements(arr, arrOut, 0);
    return arrSorted;
}

/**
 * Based on the description on the site below:
 * Feofiloff, Paulo. Available in: <http://www.ime.usp.br/~pf/analise_de_algoritmos/aulas/insert.html>
 * @param p jint Array to be sorted
 * @param size size of the array that will be sorted
 */
void insertSort(jint *p, jsize size){
    int key, i;
    if (size>1){
        insertSort(p, size-1);
        memoryAccess++;
        key = p[size];
        memoryAccess++;
        i = size -1;
        memoryAccess++;
        while(i>0 && p[i]>key){
            memoryAccess++;
            p[i+1] = p[i];
            memoryAccess++;
            i--;
            memoryAccess++;
        }
        p[i+1]=key;
        memoryAccess++;
    }
}
/**
 * Based on the description on the site below:
 * Feofiloff, Paulo. Available in: <http://www.ime.usp.br/~pf/analise_de_algoritmos/aulas/insert.html>
 * @param p jint Array to be sorted
 * @param size size of the array that will be sorted
 */
void insertionSort(jint *p, int size){
    int key, i;
    if (size>1){
        insertionSort(p, size-1);
        memoryAccess++;
        key = p[size];
        memoryAccess++;
        i = size -1;
        memoryAccess++;
        while(i>0 && p[i]>key){
            memoryAccess++;
            p[i+1] = p[i];
            memoryAccess++;
            i--;
            memoryAccess++;
        }
        p[i+1]=key;
        memoryAccess++;
    }
}