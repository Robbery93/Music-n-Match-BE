package nl.robbertij.matchnmusic.controller;

import nl.robbertij.matchnmusic.dto.request.FileRequestDto;
import nl.robbertij.matchnmusic.dto.response.FileResponseDto;
import nl.robbertij.matchnmusic.model.File;
import nl.robbertij.matchnmusic.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("api/method1")
@CrossOrigin
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/files")
    public ResponseEntity<Object> getFiles() {
        Iterable<File> files = fileService.getFiles();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Object> getFileInfo(@PathVariable long id) {
        FileResponseDto response = fileService.getFileById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/files/{id}/download")
    public ResponseEntity downloadFile(@PathVariable long id) {
        Resource resource = fileService.downloadFile(id);
        String mediaType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/files",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<Object> uploadFile(FileRequestDto fileRequestDto) {
        long newId = fileService.uploadFile(fileRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable long id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

}
