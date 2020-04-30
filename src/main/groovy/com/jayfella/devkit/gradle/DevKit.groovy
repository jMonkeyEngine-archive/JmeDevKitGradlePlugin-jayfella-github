package com.jayfella.devkit.gradle

import org.gradle.api.Project

class DevKit {

    public static final String NAME = "devkit"
    public static final String LATEST = "+"

    private final Project project

    String version

    DevKit(Project project) {
        this.project = project
    }

    /**
     * Returns chosen DevKit version:
     * If version not assigned returns dynamic latest version
     *
     * @return Chosen DevKit version
     */
    String getVersion() {
        return version ? "$version" : LATEST
    }


}
