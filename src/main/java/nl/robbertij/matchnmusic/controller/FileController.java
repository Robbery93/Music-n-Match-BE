package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.model.file.FileUploadResponse;
import nl.robbertij.matchnmusic.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping(path = "/files")
public class FileController {
    @Autowired
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = "/upload")
    public FileUploadResponse fileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = fileService.storeFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentRequest().path("/download/").path(fileName).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileName, contentType, url);
    }

//    @GetMapping(path = "/download/{fileName}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, )
}
