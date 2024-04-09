package com.mshop.fileservice.controller;

import com.mshop.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;

    @PostMapping("/string")
    public Object saveFileString(@RequestBody String base64File) throws Exception {
        return fileService.saveFileString(base64File);
    }

    @GetMapping("/string")
    public Object getFileString(@RequestParam("key") String key) throws Exception {
        return fileService.readFileString(key);
    }

    @DeleteMapping("/")
    public void deleteFile(@RequestParam("key") String key) throws Exception {
        fileService.deleteFile(key);
    }

}
