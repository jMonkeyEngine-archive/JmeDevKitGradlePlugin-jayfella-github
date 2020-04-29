Gradle Plugin for JmeDevKit
===

A Gradle plugin to make creating JmeDevKit plugins easier.

Still under development and subject to change.

#### build.gradle
```groovy

apply plugin: JmeDevKitGradlePlugin

group 'com.jayfella'
version '1.0.0'

dependencies {

    // required
    compileOnly devKitApi()

    // optional
    compileOnly jmeCore()
    compileOnly lemur()

}

```
