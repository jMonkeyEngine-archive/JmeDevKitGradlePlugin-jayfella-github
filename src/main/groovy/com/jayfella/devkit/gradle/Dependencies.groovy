package com.jayfella.devkit.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler

class Dependencies {

    private static Project project;
    private static RepositoryHandler repoHandler;
    private static DependencyHandler depHandler;

    private Dependencies() {

    }

    static configureProject(Project project) {

        this.project = project;
        repoHandler = project.repositories;
        depHandler = project.dependencies;
        addExtensions()
    }

    private static addExtensions() {

        depHandler.ext {

            devKitApi = { dep('com.jayfella', 'devkit-api', "0.1") }

            jmeCore = { jmeDep('org.jmonkeyengine', 'jme3-core') }
            jmePlugins = { jmeDep('org.jmonkeyengine', 'jme3-plugins') }

            lemur = { dep('com.simsilica', 'lemur', "1.13.0") }
            lemurProps = { dep('com.simsilica', 'lemur-props', "1.1.0") }
            lemurProto = { dep('com.simsilica', 'lemur-proto', "1.11.0") }

        }

    }

    private static Dependency jmeDep(String groupId, String artifactId) {
        String version = project.jmonkey.version

        return depHandler.create("$groupId:$artifactId:$version")
    }

    private static Dependency dep(String groupId, String artifactId, String version, String... requiredRepos) {

        for (repo in requiredRepos) {
            repoHandler.ext."$repo"()
        }

        ExternalModuleDependency dep = depHandler.create("$groupId:$artifactId:$version") as ExternalModuleDependency;
        DevKit.removeIncludedTransients(dep)

        return dep;
    }



}
