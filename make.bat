ant deps-clean
ant clean
ant jar
cd src
javah -jni ece422.project1.variants.InsertionSort
mv ece422_project1_variants_InsertionSort.h ../jni
cd ../jni
g++ -I%JAVA_HOME%\..\include -I%JAVA_HOME%\..\include\win32 -o libInsertSort.dll -shared sort.c -fPIC -shared
cd ../dist
mkdir ece422
cd ece422
mkdir project1
cd project1
mkdir variants
cd ../../../jni
copy libInsertSort.dll ../dist/ece422/project1/variants/
cd ../dist/
jar uf *.jar ./ece422/project1/variants/*
cd ..
