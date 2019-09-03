package com.avijit.together.ftp.service;

import com.avijit.together.core.dto.FileResponse;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.ftp.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service("ftpService")
public class FTPService {

    private final Path fileStorageLocation;

    @Autowired
    public FTPService(FileStorageProperties fileStorageProperties) throws TogetherException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new TogetherException(ErrorCode.DIRECTORY_NOT_CREATED, IErrorDetails.UNABLE_TO_CREATE_DIRECTORY);
        }
    }

    public FileResponse save(MultipartFile file) throws TogetherException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileResponse response = new FileResponse(fileName, null, file.getContentType(), file.getSize());
        try {
            // Check file name contains invalid characters
            if(fileName.contains("..")) {
                throw new TogetherException(ErrorCode.INVALID_FILE_NAME, String.format(IErrorDetails.INVALID_FILE_NAME, fileName));
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return response;
        } catch (Exception ex) {
            throw new TogetherException(ErrorCode.FILE_NOT_ADDED, String.format(IErrorDetails.FILE_NOT_ADDED, fileName));
        }
    }

    public Resource loadFileAsResource(String fileName) throws TogetherException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new TogetherException(ErrorCode.FILE_NOT_FOUND, String.format(IErrorDetails.FILE_NOT_FOUND, fileName));
            }
        } catch (Exception ex) {
            throw new TogetherException(ErrorCode.FILE_NOT_FOUND, String.format(IErrorDetails.FILE_NOT_FOUND, fileName));
        }
    }
}