cmake_minimum_required(VERSION 3.3)
project(nativeFunctions)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

set(SOURCE_FILES
        matrix.cpp
        Matrix.h)

add_library(nativeFunctionsShared SHARED matrix.cpp Matrix.h)
set_target_properties(nativeFunctionsShared PROPERTIES COMPILE_FLAGS "-m32" LINK_FLAGS "-m32")
add_library(nativeFunctionsStatic STATIC matrix.cpp Matrix.h)
add_library(nativeFunctionsModule MODULE matrix.cpp Matrix.h)
add_executable(nativeFunctions ${SOURCE_FILES})