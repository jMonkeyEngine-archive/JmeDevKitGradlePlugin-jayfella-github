package com.jayfella.devkit.gradle

import org.gradle.api.artifacts.ExternalModuleDependency

/**
 * A list of dependencies that are already included in the DevKit API, and do not need to be included in a plugin.
 */
class DevKit {

    private static final String GROUP = "group"
    private static final String MODULE = "module"

    // first index is group, the rest are modules.
    private static final String[][] DEPENDENCIES = [

            [ "org.jmonkeyengine",
                    "jme3-core", "jme3-desktop", "jme3-lwjgl", "jme3-lwjgl3", "jme3-plugins", "jme3-jogg"
            ],

            [ "com.simsilica",
                    "lemur", "lemur-props", "lemur-proto"
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
