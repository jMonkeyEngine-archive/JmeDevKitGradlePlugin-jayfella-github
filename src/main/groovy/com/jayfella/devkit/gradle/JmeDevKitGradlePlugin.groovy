package com.jayfella.devkit.gradle


import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.compile.JavaCompile

class JmeDevKitGradlePlugin implements Plugin<Project> {

    Project project;

    @Override
    void apply(Project project) {
        this.project = project
        configureProject()
    }

    /**
     * Configures project
     */
    private void configureProject() {
        addPlugins()
        configureEncoding()
        addRepositories()
        configureTasks()
        configureDependencies()
    }

    /**
     * Adds all needed plugins
     */
    private void addPlugins() {
        project.with {
            plugins.with {
                apply('java')
                apply('application')
            }
        }
    }

    /**
     * Sets force encoding on compile to UTF-8
     */
    private void configureEncoding() {
        project.tasks.withType(JavaCompile) {
            options.encoding = 'UTF-8'
        }
    }

    /**
     * Adds needed repositories
     */
    private void addRepositories() {
        project.repositories {
            mavenLocal()
            jcenter()
        }
    }

    /**
     * Adds tasks
     */
    private void configureTasks() {

        project.task("runSdk", type: JavaExec) {
            main = "com.jayfella.importer.Main"
            classpath = project.sourceSets.main.runtimeClasspath
        }
    }

    private void configureDependencies() {

        // include the devkit as a dependency
        Dependency dep = project.dependencies.create("com.jayfella:jme-swing-devkit:1.0.5")
        project.dependencies.add("runtimeOnly", dep)

    }

}
