package com.jayfella.devkit.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
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
        addCustomMethods()
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

    private void addCustomMethods() {

        // removes all transient dependencies already included in DevKit
        project.extensions.removeIncludedTransients = { groupId , artifactId, version ->

            ExternalModuleDependency dep = project.dependencies.create("$groupId:$artifactId:$version") as ExternalModuleDependency
            DevKit.removeIncludedTransients(dep)
            return dep

        }

    }

}
