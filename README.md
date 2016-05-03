# fhnw_prcpp_jni
The challenge to use jni in a java application.

## Prerequisites
* Java installed (I used 32 bit version)
* Some Java IDE (I used IntelliJ IDEA)
* Some C++ IDE (I used CLion)

## Procedure
1. Write a java class using native methods (eg. private native void doStuff())
2. Create a .h file out of that java file: javah *path to your class file* -d *desired .h file path* *className*
3. Import the .h file to your c++ project
4. Make sure your environment loads jni properly (see fhnw_prcpp_jni/part_cpp/CMakeLists.txt)
5. Implement the functions / methods as you wish to
6. cd to your .cpp implementation file (*implementation file*)
7. g++ -Wl,--add-stdcall-alias -I "*java 32 bit home*\include" -I"*java 32 bit home*\include\win32" -shared -o *desired dll file path* *implementation file*
   * special thanks to http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
8. Copy the newly created .dll to the root of your java project
9. Run your java application using the native methods (and cheer)