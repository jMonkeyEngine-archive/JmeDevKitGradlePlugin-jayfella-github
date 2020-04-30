Gradle Plugin for JmeDevKit
===

A Gradle plugin to make creating JmeDevKit plugins easier.

Still under development and subject to change.

#### build.gradle
```groovy

plugins {
    id "com.jayfella.jme-devkit" version "0.0.3"
}

group 'com.jayfella'
version '1.0.0'

devkit {
    // manually select a version, else the latest release is chosen.
    version = "0.0.1"
}

jmonkey {
    // manually select a version, else the latest release is chosen.
    // version = "3.2.2-stable"
}

lemur {
    // manually select a version, else the latest release is chosen.
    // version = "1.13.0"
    // props = "1.1.0"
    // proto = "1.11.0"
}

dependencies {

    // required
    compileOnly devKitApi()

    // optional
    compileOnly jmeCore()
    compileOnly lemur()

}

```
