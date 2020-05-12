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

            devkitCore = { version -> dep("com.jayfella", "devkit-core", version ? "$version" : "+") }
            devkitMenu = { version -> dep("com.jayfella", "lemur-menubar", version ? "$version" : "+") }
            devkitPlugin = { version -> dep("com.jayfella", "devkit-plugin", version ? "$version" : "+") }
            devkitTheme = { version -> dep("com.jayfella", "devkit-theme", version ? "$version" : "+") }
            devkitWindow = { version -> dep("com.jayfella", "lemur-window", version ? "$version" : "+") }

            jmeCore = { version -> dep('org.jmonkeyengine', 'jme3-core', version ? "$version" : "+") }
            jmeDesktop = { version -> dep('org.jmonkeyengine', 'jme3-desktop', version ? "$version" : "+") }
            jmePlugins = { version -> dep('org.jmonkeyengine', 'jme3-plugins', version ? "$version" : "+") }

            lemur = { version -> dep('com.simsilica', 'lemur', version ? "$version" : "+") }
            lemurProps = { version -> dep('com.simsilica', 'lemur-props', version ? "$version" : "+") }
            lemurProto = { version -> dep('com.simsilica', 'lemur-proto', version ? "$version" : "+") }

        }

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
