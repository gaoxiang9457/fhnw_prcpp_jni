#include <iostream>
#include "Test.h"

using namespace std;

JNIEXPORT void JNICALL Java_Test_display
        (JNIEnv *env, jclass clazz) {
    cout << "It works!";
}

JNIEXPORT jint JNICALL Java_Test_increment
        (JNIEnv *env, jclass clazz, jint a) {
    return a++;
}


int main() {
    return 0;
}