package com.anfahrul.researchfund.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FileGuideBookStorage {
    fun store(file: MultipartFile): String
    fun loadFile(filename: String): Resource
    fun deleteAll()
    fun init()
    fun loadFiles(): Stream<Path>
}