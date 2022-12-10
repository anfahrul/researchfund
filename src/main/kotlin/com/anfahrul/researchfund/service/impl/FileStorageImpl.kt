package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.repository.proposalRepository
import com.anfahrul.researchfund.service.FileStorage
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

@Service
class FileStorageImpl(
    val proposalRepository: proposalRepository
): FileStorage {

    val log = LoggerFactory.getLogger(this::class.java)
    val rootLocation = Paths.get("filestorage/proposal")

    override fun store(proposalId: String, file: MultipartFile) {
        val proposal = proposalRepository.findByIdOrNull(proposalId)
        if (proposal == null) {
            throw NotFoundException("Profil organisasi tidak ditemukan")
        }

        val proposalFileName = "${System.currentTimeMillis()}_${file.originalFilename}"
        Files.copy(file.inputStream, this.rootLocation.resolve(proposalFileName))

        proposal.apply {
            filePath = proposalFileName
        }

        proposalRepository.save(proposal)
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