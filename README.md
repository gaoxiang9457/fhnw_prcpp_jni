# fhnw_prcpp_jni
The challenge to use jni in a java application.

## Prerequisites
* Java installed (I used 32 bit version)
* Some Java IDE (I used IntelliJ IDEA)
* Some C++ IDE (I used CLion)

## Procedure
1. Write a java class using native methods (eg. private native void doStuff())
2. Create a .h file out of that java file: javah <yourClassFile> -d <desiredOutputPath> <className>
3. Import the .h file to your c++ project
4. Make sure your environment loads jni properly (see fhnw_prcpp_jni/part_cpp/CMakeLists.txt)
5. Implement the functions / methods as you wish to
6. cd to your .cpp implementation file <IMPLEMENTATION_CPP_FILE>
7. g++ -Wl,--add-stdcall-alias -I "<JAVA_32_BIT>\include" -I"<JAVA_32_BIT>\include\win32" -shared -o <DESIRED_DLL_FILE_PATH>.dll <IMPLEMENTATION_CPP_FILE>
8. Copy the <DESIRED_DLL_FILE_PATH>.dll to the root of your java project
9. Run your java application using the native methods (and cheer)