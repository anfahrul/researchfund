package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.repository.proposalRepository
import com.anfahrul.researchfund.service.FileGuideBookStorage
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

@Service
class FileGuideBookStorageImpl(
    val proposalRepository: proposalRepository
): FileGuideBookStorage {

    val log = LoggerFactory.getLogger(this::class.java)
    val rootLocation = Paths.get("filestorage/guidebook")

    override fun store(file: MultipartFile): String {
//        val proposal = proposalRepository.findByIdOrNull(researchOfferId)
//        if (proposal == null) {
//            throw NotFoundException("Research offer tidak ditemukan")
//        }
        val fileName = (file.originalFilename)?.replace(" ", "_")
        val guidebookFileName = "${System.currentTimeMillis()}_${fileName}"
        Files.copy(file.inputStream, this.rootLocation.resolve(guidebookFileName))

        return guidebookFileName
    }

    override fun loadFile(filename: String): Resource {
        val file = rootLocation.resolve(filename)
        val resource = UrlResource(file.toUri())

        if (resource.exists() || resource.isReadable) {
            return resource
        } else {
            throw BadRequestException("FAIL!")
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun init() {
        Files.createDirectory(rootLocation)
    }

    override fun loadFiles(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
            .filter { path -> !path.equals(this.rootLocation) }
            .map(this.rootLocation::relativize)
    }
}