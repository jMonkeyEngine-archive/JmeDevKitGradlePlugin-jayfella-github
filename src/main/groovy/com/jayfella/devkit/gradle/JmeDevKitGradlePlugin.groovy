package com.jayfella.devkit.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
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
        addExtensionFunctions()
    }

    /**
     * Adds all needed plugins
     */
    private void addPlugins() {
        project.with {
            plugins.with {
                apply('java')
                apply('eclipse')
                apply('idea')
                apply(PluginMetaPlugin)
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
     * Adds repositories and dependencies extension functions
     */
    private void addExtensionFunctions() {
        project.repositories {
            mavenLocal()
            jcenter()
        }

        Dependencies.configureProject(project)
    }

}
