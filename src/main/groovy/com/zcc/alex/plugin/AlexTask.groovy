package com.zcc.alex.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.CopySpec
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Zip

class AlexTask extends DefaultTask {
    Property<String> outputName = project.objects.property(String)
    Property<ConfigurableFileCollection> collectionFiles = project.objects.property(ConfigurableFileCollection)

    void setOutputName(Property<String> outputName) {
        this.outputName = outputName
    }

    void setCollectionFiles(Property<ConfigurableFileCollection> collectionFiles) {
        this.collectionFiles = collectionFiles
    }

    @Input
    Property<String> getOutputName() {
        return outputName
    }

    @Input
    Property<ConfigurableFileCollection> getCollectionFiles() {
        return collectionFiles
    }

    @TaskAction
    def mvAndZipFiles() {
        println('do alex task')
        File target = project.file(outputName.get())
        if (target.exists()) {
            project.delete outputName.get()
        }
        collectionFiles.get().files.each {
            File oneFile ->
                project.copy {
                    CopySpec copySpec ->
                        if (oneFile.isDirectory()) {
                            copySpec.from(oneFile)
                            copySpec.exclude("/build")
                            copySpec.into("$project.rootDir/${outputName.get()}/$oneFile.name")
                        } else {
                            copySpec.from(oneFile)
                            copySpec.into("$project.rootDir/${outputName.get()}")
                        }
                }
        }
    }
}
