Gradle Plugin for JmeDevKit
===

A Gradle plugin to make creating JmeDevKit plugins easier.

Still under development and subject to change.

#### build.gradle
```groovy

plugins {
    id "com.jayfella.jme-devkit" version "0.0.4"
}

group 'com.jayfella'
version '1.0.0'

dependencies {

    // optionally specify a version on any dependencies
    // if no version is specified, the latest version is used.
    // compileOnly devkitCore("1.0.3")
    // compileOnly lemur("1.13.0)

    // required
    compileOnly devkitCore()
    compileOnly devkitPlugin()
    
    compileOnly devkitMenu() // if you use the menu system.
    compileOnly devkitWindow() // if you use the window system.
    compileOnly devkitTheme() // if you use the theming system.

    // optional
    compileOnly jmeCore() // if you use anything related to JME

    compileOnly lemur()
    compileOnly lemurProps()
    compileOnly lemurProto()

    // include a dependency but remove any dependencies that are already included in JmeDevKit
    implementation removeIncludedTransients("com.simsilica", "jmec", "1.2.0-SNAPSHOT")

}

configurations {
    releaseJar.extendsFrom implementation
}

jar {
    from configurations.releaseJar.collect { it.isDirectory() ? it : zipTree(it) }
}


```
