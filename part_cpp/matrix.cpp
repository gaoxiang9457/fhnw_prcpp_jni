//
// Created by Ken on 07/05/2016.
//
#include <c++/4.8.3/iostream>
#include "Matrix.h"

jdouble getCellResult(int row, const jdouble *bValues, jint bColumns, int column, jint columns, const jdouble *values);

void multiply(jint aRows, jint bColumns, jint innerDimension, jdouble *aValueArray, jdouble *bValueArray,
              jdouble *resultValueArray);

void JNICALL Java_Matrix_multiplyC(JNIEnv *env, jobject object, jdoubleArray aValues,
                                   jdoubleArray bValues, jdoubleArray resultValues, jint aRows, jint bColumns,
                                   jint innerDimension) {
    jdouble *aValueArray = env->GetDoubleArrayElements(aValues, JNI_FALSE);
    jdouble *bValueArray = env->GetDoubleArrayElements(bValues, JNI_FALSE);
    jdouble *resultValueArray = env->GetDoubleArrayElements(resultValues, JNI_FALSE);

    multiply(aRows, bColumns, innerDimension, aValueArray, bValueArray, resultValueArray);

    env->ReleaseDoubleArrayElements(aValues, aValueArray, 0);
    env->ReleaseDoubleArrayElements(bValues, bValueArray, 0);
    env->ReleaseDoubleArrayElements(resultValues, resultValueArray, 0);
}

void multiply(jint aRows, jint bColumns, jint innerDimension, jdouble *aValueArray, jdouble *bValueArray,
              jdouble *resultValueArray) {
    for (int i = 0; i < aRows; i++) {
        for (int j = 0; j < bColumns; j++) {
            jdouble cellResult = getCellResult(i, bValueArray, bColumns, j, innerDimension, aValueArray);
            resultValueArray[i * bColumns + j] = cellResult;
        }
    }
}

JNIEXPORT jdouble getCellResult(int row, const jdouble *bValues, jint bColumns, int column, jint columns, const jdouble *values) {
    jdouble result = 0;
    for (int i = 0; i < columns; i++) {
        double aFactor = values[row * columns + i];
        double bFactor = bValues[i * bColumns + column];
        result += aFactor * bFactor;
    }
    return result;
}

JNIEXPORT void JNICALL Java_Matrix_powerC
        (JNIEnv *env, jobject object, jdoubleArray aValues, jdoubleArray resultValues, jint dimension, jint k) {
    jdouble *aValueArray = env->GetDoubleArrayElements(aValues, JNI_FALSE);
    jdouble *resultValueArray = env->GetDoubleArrayElements(resultValues, JNI_FALSE);

    for (int i = 0; i < dimension * dimension; i++) {
        resultValueArray[i] = aValueArray[i];
    }
    for (int i = 1; i < k; i++) {
        jdouble *ptr_tmp = new jdouble[dimension * dimension];
        for (int j = 0; j < dimension * dimension; j++) {
            ptr_tmp[j] = resultValueArray[j];
        }
        multiply(dimension, dimension, dimension, ptr_tmp, aValueArray, resultValueArray);
    }
    env->ReleaseDoubleArrayElements(aValues, aValueArray, 0);
    env->ReleaseDoubleArrayElements(resultValues, resultValueArray, 0);
}

int main() {
    return 0;
}