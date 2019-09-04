package com.avijit.together.ftp.contoller;

import com.avijit.together.core.dto.FileResponse;
import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.dto.ResponseFactory;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.ftp.service.FTPService;
import com.avijit.together.ftp.service.IConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.avijit.together.core.data.Constants.API_CONVERSATION_FILES;

@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATION_FILES)
public class FTPController {

    private static final Logger logger = LoggerFactory.getLogger(FTPController.class);

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private FTPService ftpService;

    @PostMapping
    public HttpEntity<?> uploadFile(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("conversation_id") String conversationId, @RequestParam("file") MultipartFile file) {

        try {
            if (conversationService.isExists(conversationId)) {
                FileResponse fileResponse = save(file, request);
                return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.CONVERSATION_NOT_FOUND,
                    String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
                    IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
        }
    }

    @PostMapping("/multiple")
    public ResponseDTO<List<FileResponse>> uploadMultipleFiles(HttpServletRequest request, HttpServletResponse response,
                                                               @PathVariable("conversation_id") String conversationId, @RequestParam("files") MultipartFile[] files) {
        try {
            if (conversationService.isExists(conversationId)) {
                List<FileResponse> res =  Arrays.asList(files)
                        .stream()
                        .map(file -> {
                            try {
                                return save(file, request);
                            } catch (TogetherException exception) {
                                exception.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());
                return new ResponseDTO<>(res);
            }
            return new ResponseDTO<>();
        } catch (TogetherException togetherException) {
            return new ResponseDTO<>();
        }
    }

    @GetMapping("/{fileName:.+}")
    public HttpEntity<?> downloadFile(HttpServletRequest request, HttpServletResponse response,
                                                 @PathVariable("conversation_id") String conversationId, @PathVariable String fileName) {

        try {
            if (conversationService.isExists(conversationId)) {

                Resource resource = ftpService.loadFileAsResource(fileName);

                String contentType = null;
                try {
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                } catch (Exception ex) {
                    logger.warn("Could not determine file type.");
                }

                // set default contentType if not found
                if(contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.CONVERSATION_NOT_FOUND,
                    String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
                    IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
        }
    }

    private FileResponse save(MultipartFile file, HttpServletRequest request) throws TogetherException {
        FileResponse fileResponse = ftpService.save(file);
        String uriString = request.getRequestURL().append("/").append(fileResponse.getFileName()).toString();
        String fileDownloadUri = "";
        try {
            fileDownloadUri = new URI(uriString).normalize().toString();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        fileResponse.setFileDownloadUri(fileDownloadUri);
        return fileResponse;
    }
}
