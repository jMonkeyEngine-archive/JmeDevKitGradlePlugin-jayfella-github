package com.jayfella.devkit.gradle

import org.gradle.api.Project

class JMonkey {

    public static final String NAME = "jmonkey"
    public static final String LATEST = "+"

    private final Project project

    private String version

    JMonkey(Project project) {
        this.project = project
    }

    /**
     * Returns chosen JME version:
     * If version not assigned returns dynamic latest version
     *
     * @return Chosen JME version
     */
    String getVersion() {
        return version ? "$version" : LATEST
    }

}
