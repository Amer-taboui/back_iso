package com.crm.operis_app.controller.files;
import com.crm.operis_app.model.files.FileModel;
import com.crm.operis_app.model.files.View;
import com.crm.operis_app.repository.files.FileRepository;
import com.crm.operis_app.services.files.FileService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class FileController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileService fileService;


    /**
     * MultipartFile Upload
     */

    @PostMapping("/file/upload")
    public FileModel uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        try {
            // save file to PostgreSQL
            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemode);
            return filemode;
        } catch (Exception e) {
            return null;
        }
    }


//	@PostMapping("/file/upload")
//	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//		String message = "";
//		try {
//			fileService.store(file);
//
//			message = "Uploaded the file successfully: " + file.getOriginalFilename();
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//	}

    @PostMapping("/update/file/{id}")
    public FileModel uploadMultipartFile(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") Long id) {
        Optional<FileModel> fileModel = fileRepository.findById(id);
        try {

            FileModel fileModel1 = fileModel.get();
            fileModel1.setName(file.getOriginalFilename());
            fileModel1.setMimetype(file.getContentType());
            fileModel1.setPic(file.getBytes());

            fileRepository.save(fileModel1);
            return fileModel1;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * List Files
     */

    @JsonView(View.FileInfo.class)
    @GetMapping("/file/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }


    @RequestMapping(value = "/file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteFileById(@PathVariable(value = "id") Long id) {
        return fileService.deleteFileById(id);
    }

    /**
     * Download Files
     */

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileModel> fileOptional = fileRepository.findById(id);

        if (fileOptional.isPresent()) {
            FileModel file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }
        return ResponseEntity.status(404).body(null);
    }


    /** ADD /REMOVE Files By FORMATION */

    @JsonView(View.FileInfo.class)
    @GetMapping("/formation/{id}/files")
    public List<FileModel> getListFilesByFormation(@PathVariable(value = "id") Long id) {
        return fileRepository.findByFormationId(id);
    }

    @PostMapping(value = "/formation/{formationId}/addFile/{fileId}")
    public void addFileToFormation(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "fileId") Long fileId) {
        fileService.addFileToFormation(formationId,fileId);
    }

    @PostMapping(value = "/formation/{formationId}/removeFile/{fileId}")
    public void removeFileFromFormation(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "fileId") Long fileId) {
        fileService.removeFileFromFormation(formationId,fileId);
    }

    /** ADD /REMOVE Files By Personal */

    @JsonView(View.FileInfo.class)
    @GetMapping("/personal/{id}/files")
    public List<FileModel> getListFilesByPersonal(@PathVariable(value = "id") Long id) {
        return fileRepository.findByFormationId(id);
    }

    @PostMapping(value = "/personal/{personalId}/addFile/{fileId}")
    public void addFileToPersonal(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "fileId") Long fileId) {
        fileService.addFileToPersonal(personalId,fileId);
    }

    @PostMapping(value = "/personal/{personalId}/removeFile/{fileId}")
    public void removeFileFromPersonal(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "fileId") Long fileId) {
        fileService.removeFileFromFormation(personalId,fileId);
    }
   //----------------------------------//
    /** ADD /REMOVE Files By POST */

    @JsonView(View.FileInfo.class)
    @GetMapping("/post/{id}/files")
    public List<FileModel> getListFilesByPost(@PathVariable(value = "id") Long id) {
        return fileRepository.findByPostId(id);
    }


    @PostMapping(value = "/post/{postId}/addFile/{fileId}")
    public void addFileToPost(@PathVariable(value = "postId") Long postId, @PathVariable(value = "fileId") Long fileId) {
        fileService.addFileToPost(postId,fileId);
    }

    @PostMapping(value = "/post/{postId}/removeFile/{fileId}")
    public void removeFileFromPost(@PathVariable(value = "postId") Long postId, @PathVariable(value = "fileId") Long fileId) {
        fileService.removeFileFromPost(postId,fileId);
    }

    /** ADD /REMOVE Files By ACTION */

    @JsonView(View.FileInfo.class)
    @GetMapping("/action/{id}/files")
    public List<FileModel> getListFilesByAction(@PathVariable(value = "id") Long id) {
        return fileRepository.findByListeActionCorrectionId(id);
    }


    @PostMapping(value = "/action/{actionId}/addFile/{fileId}")
    public void addFileToAction(@PathVariable(value = "actionId") Long actionId, @PathVariable(value = "fileId") Long fileId) {
        fileService.addFileToAction(actionId,fileId);
    }

    @PostMapping(value = "/action/{actionId}/removeFile/{fileId}")
    public void removeFileFromAction(@PathVariable(value = "actionId") Long actionId, @PathVariable(value = "fileId") Long fileId) {
        fileService.removeFileFromAction(actionId,fileId);
    }
//-------------------------------------------------------------------------//
    @RequestMapping("/file/PlanComptable")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) throws IOException {

         File file = new ClassPathResource("PlanComptable.xlsx").getFile();

        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             */
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }



}