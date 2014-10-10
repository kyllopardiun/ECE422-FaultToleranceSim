#!/bin/bash --
ant deps-clean
ant clean
ant jar
cp -r license dist
cd src
javah -jni ece422.project1.variants.InsertionSort
mv ece422_project1_variants_InsertionSort.h ../jni
cd ../jni
g++ -I /usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -o libInsertSort.so -shared sort.c -fPIC -shared
cd ../dist
mkdir ece422
cd ece422
mkdir project1
cd project1
mkdir variants
cd ../../../jni
cp libInsertSort.so ../dist/ece422/project1/variants/
cd ../dist/
jar uf *.jar ./ece422/project1/variants/*
cd ..
