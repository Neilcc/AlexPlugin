package com.zcc.alex.plugin

import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property


class AlexExtension {

    Property<ConfigurableFileCollection> folderAndFileList

    AlexExtension(Project project) {
        folderAndFileList = project.objects.property(ConfigurableFileCollection)
    }

    Property<ConfigurableFileCollection> getFolderAndFileList() {
        return folderAndFileList
    }

    void setFolderAndFileList(Property<ConfigurableFileCollection> folderAndFileList) {
        this.folderAndFileList = folderAndFileList
    }
}
