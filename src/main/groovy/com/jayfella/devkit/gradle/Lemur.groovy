package com.jayfella.devkit.gradle

import org.gradle.api.Project

class Lemur {

    public static final String NAME = "lemur"
    public static final String LATEST = "+"

    private final Project project

    String version
    String props
    String proto

    Lemur(Project project) {
        this.project = project
    }

    /**
     * Returns chosen Lemur version:
     * If version not assigned returns dynamic latest version
     *
     * @return Chosen Lemur version
     */
    String getVersion() {
        return version ? "$version" : LATEST
    }

    /**
     * Returns chosen Lemur-Props version:
     * If version not assigned returns dynamic latest version
     *
     * @return Chosen Lemur-Props version
     */
    String getPropsVersion() {
        return props ? "$props" : LATEST
    }

    /**
     * Returns chosen Lemur-Proto version:
     * If version not assigned returns dynamic latest version
     *
     * @return Chosen Lemur-Proto version
     */
    String getProtoVersion() {
        return proto ? "$proto" : LATEST
    }

}
