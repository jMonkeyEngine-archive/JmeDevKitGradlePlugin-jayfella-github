package com.jayfella.devkit.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginMetaPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.with {
            extensions.create(DevKit.NAME, DevKit, project)
            extensions.create(JMonkey.NAME, JMonkey, project)
            extensions.create(Lemur.NAME, Lemur, project)
        }

    }

}
