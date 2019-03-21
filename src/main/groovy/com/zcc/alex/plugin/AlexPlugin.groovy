package com.zcc.alex.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Zip

class AlexPlugin implements Plugin<Project> {

    private static final String FILE_FOLDER_NAME = "Output"
    private static final String ZIP_OUT_PUT_NAME = "DemoOutput.zip"

    @Override
    void apply(Project project) {
        def alexExtension = project.extensions.create(
                'alexParam',
                AlexExtension,
                project
        )
        println('get params fin')

        AlexTask alexTask = project.tasks.create('moveFile', AlexTask) {
            outputName = FILE_FOLDER_NAME
            collectionFiles = alexExtension.folderAndFileList
        }
        String patht = project.rootDir.path
        println('execute doAlex task fin: root dir ' + patht)
        Zip ziptask = project.tasks.create('doAlex', Zip) {
            archiveName = ZIP_OUT_PUT_NAME
            destinationDir = project.file("${project.rootDir}")
            from "${project.rootDir}/$FILE_FOLDER_NAME"
        }
        ziptask.dependsOn(alexTask)
        ziptask.doFirst {
            println('do Alex task start')
        }
        ziptask.doLast {
            println('do Alex task end')
        }

    }
}

