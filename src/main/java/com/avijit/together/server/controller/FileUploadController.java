/*****************************************************************************
 * FILE NAME   : FileUploadController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.avijit.together.core.data.I_Constant;
import com.avijit.together.core.dto.FileUploadDownloadDTO;
import com.avijit.together.core.dto.ResponseFactory;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.server.service.ConversationService;
import com.avijit.together.server.service.FileNameService;
import com.avijit.together.server.util.FileNameUtility;

import static com.avijit.together.core.data.I_Constant.API_CONVERSATION_FILES;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATION_FILES)
public class FileUploadController {

    /**
     *
     */
    @Autowired
    private ConversationService conversationService;

    /**
     *
     */
    @Autowired
    private FileNameService fileNameService;

    /**
     * @param request
     * @param conversationId
     * @param uploadfile
     * @return
     */
    @PostMapping()
    public HttpEntity<?> uploadSingleFile(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable("conversation_id") String conversationId, @RequestParam("file") MultipartFile uploadfile) {

        try {
            if (!conversationService.isExists(conversationId)) {
                return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.CONVERSATION_NOT_FOUND,
                        String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
                        IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
            }
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, ErrorCode.CONVERSATION_NOT_FOUND,
                    String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
                    IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
        }

        if (uploadfile.isEmpty()) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, ErrorCode.WRONG_PARAMETER,
                    String.format(IErrorDetails.INVALID_FILE_ATTACHED, conversationId), IErrorDetails.ENTER_VALID_FILE,
                    request.getRequestURI());
        }

        String convertedFileName = FileNameUtility.convertFileName(conversationId);

        try {

            saveUploadedFiles(Arrays.asList(uploadfile), convertedFileName);
            fileNameService.save(uploadfile.getOriginalFilename(), convertedFileName);

        } catch (IOException ioException) {
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.FILE_NOT_ADDED,
                    IErrorDetails.UNABLE_TO_ADD_FILE, IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
                    IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        }

        response.setHeader("Location", String.format(I_Constant.FILE_URI, conversationId,
                uploadfile.getOriginalFilename(), FileNameUtility.getSessionId(convertedFileName)));
        return new ResponseEntity<>(
                new FileUploadDownloadDTO("Successfully uploaded - " + uploadfile.getOriginalFilename()),
                HttpStatus.OK);
    }

    /**
     * @param conversationId
     * @param extraField
     * @param uploadfiles
     * @return
     *//*
     * @RequestMapping(value = "/multiple", method = RequestMethod.POST) public
     * HttpEntity<FileUploadDownloadDTO>
     * uploadMultipleFile(@PathVariable("conversation_id") String conversationId,
     *
     * @RequestParam("extraField") String extraField, @RequestParam("files")
     * MultipartFile[] uploadfiles) {
     *
     * try { if (!conversationService.isExists(conversationId)) { return new
     * ResponseEntity<>(new FileUploadDownloadDTO("Conversation does not exists"),
     * HttpStatus.NOT_FOUND); } } catch (UUIDConversationException
     * uuidConversationException) { return new ResponseEntity<>(new
     * FileUploadDownloadDTO("Not a valid conversation ID"),
     * HttpStatus.BAD_REQUEST); }
     *
     * String uploadedFileName = Arrays.stream(uploadfiles).map(x ->
     * x.getOriginalFilename()) .filter(x ->
     * !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
     *
     * if (StringUtils.isEmpty(uploadedFileName)) { FileUploadDownloadDTO
     * fileUploadDownloadDTO = new FileUploadDownloadDTO("please select a file!");
     * return new ResponseEntity<>(fileUploadDownloadDTO, HttpStatus.BAD_REQUEST); }
     *
     * try {
     *
     * saveUploadedFiles(Arrays.asList(uploadfiles), conversationId);
     *
     * } catch (IOException e) { return new
     * ResponseEntity<>(HttpStatus.BAD_REQUEST); } FileUploadDownloadDTO
     * fileUploadDownloadDTO = new FileUploadDownloadDTO( "Successfully uploaded - "
     * + uploadedFileName); return new ResponseEntity<>(fileUploadDownloadDTO,
     * HttpStatus.OK);
     *
     * }
     */

    /**
     * save file
     *
     * @param files
     * @throws IOException
     */
    private void saveUploadedFiles(List<MultipartFile> files, String convertedFileName) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; // next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(I_Constant.FILES_LOCATION + convertedFileName);
            Files.write(path, bytes);

        }

    }
}
