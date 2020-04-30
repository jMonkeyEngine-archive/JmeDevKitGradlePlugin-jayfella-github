package com.jayfella.devkit.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler

class Dependencies {

    private static final String GROUP = "group"
    private static final String MODULE = "module"

     // A list of dependencies that are already included in the DevKit API, and do not need to be included in a plugin.
     // The first index is group, the rest are modules.
    private static final String[][] DEPENDENCIES = [

            [ "org.jmonkeyengine",
              "jme3-core", "jme3-desktop", "jme3-lwjgl", "jme3-lwjgl3", "jme3-plugins", "jme3-jogg"
            ],

            [ "com.simsilica",
              "lemur", "lemur-props", "lemur-proto"
            ],

            [ "org.codehaus.groovy",
              "groovy-all"
            ],

            [ "com.fasterxml.jackson.core",
              "jackson-core", "jackson-annotations", "jackson-databind"
            ],

            [ "org.slf4j",
              "slf4j-api", "jul-to-slf4j", "slf4j-log4j12"
            ],

            [ "log4j",
              "log4j"
            ]
    ]

    private static Project project
    private static RepositoryHandler repoHandler
    private static DependencyHandler depHandler

    private Dependencies() {

    }

    static configureProject(Project project) {

        this.project = project
        repoHandler = project.repositories
        depHandler = project.dependencies
        addExtensions()
    }

    private static addExtensions() {

        depHandler.ext {

            devKitApi = { dep('com.jayfella', 'devkit-api', project.devkit.getVersion() as String) }

            jmeCore = { jmeDep('org.jmonkeyengine', 'jme3-core') }
            jmePlugins = { jmeDep('org.jmonkeyengine', 'jme3-plugins') }

            lemur = { dep('com.simsilica', 'lemur', project.lemur.getVersion() as String) }
            lemurProps = { dep('com.simsilica', 'lemur-props', project.lemur.getPropsVersion() as String) }
            lemurProto = { dep('com.simsilica', 'lemur-proto', project.lemur.getProtoVersion() as String) }

        }

    }

    private static Dependency jmeDep(String groupId, String artifactId) {
        String version = project.jmonkey.getVersion()

        return depHandler.create("$groupId:$artifactId:$version")
    }

    private static Dependency dep(String groupId, String artifactId, String version, String... requiredRepos) {

        for (repo in requiredRepos) {
            repoHandler.ext."$repo"()
        }

        ExternalModuleDependency dep = depHandler.create("$groupId:$artifactId:$version") as ExternalModuleDependency
        removeIncludedTransients(dep)

        return dep
    }

    private static exclude(ExternalModuleDependency dep, Map<String, String> map, String group, String module) {
        map.put(GROUP, group)
        map.put(MODULE, module)
        dep.exclude(map)
    }

    static removeIncludedTransients(ExternalModuleDependency dep) {

        Map<String, String> excluder

        for (String[] str : DEPENDENCIES) {

            for (int i = 1; i < str.length; i++) {
                excluder = new HashMap<>()
                exclude(dep, excluder, str[0], str[i])
            }

        }

    }

}
