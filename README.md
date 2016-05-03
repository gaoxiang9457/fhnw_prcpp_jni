# fhnw_prcpp_jni
The challenge to use jni in a java application.

## Prerequisites
* Java installed (I used 32 bit version)
* Some Java IDE (I used IntelliJ IDEA)
* Some C++ IDE (I used CLion)

## Procedure
1. Write a java class using native methods (eg. private native void doStuff())
2. Create a .h file out of that java file: ```javah <PATH_TO_YOUR_CLASS_FILE_FOLDER> -d <DESIRED_.h_FILE_PATH> <CLASS_NAME>```
3. Import the .h file to your c++ project
4. Make sure your environment loads jni properly (see fhnw_prcpp_jni/part_cpp/CMakeLists.txt)
5. Implement the functions / methods as you wish to
6. ```cd <YOUR_.cpp_IMPLEMENTATION_FILE_FOLDER>
7. ```g++ -Wl,--add-stdcall-alias -I "<JAVA_32_BIT_HOME>\include" -I"<JAVA_32_BIT_HOME>\include\win32" -shared -o <DESIRED_.dll_FILE_PATH> <IMPLEMENTATION_FILE>
   * special thanks to http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
8. Copy the newly created .dll to the root of your java project
9. Run your java application using the native methods (and cheer)
