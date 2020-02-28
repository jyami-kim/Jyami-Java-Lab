package com.jyami.gcsProejct.controller;

import com.google.cloud.storage.Blob;
import com.jyami.gcsProejct.service.GCSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by jyami on 2020/02/28
 */
@RestController
@RequiredArgsConstructor
public class GCSController {

    private final GCSService gcsService;

    @GetMapping("gcs/download")
    public ResponseEntity localDownloadFromStorage(){
        Blob fileFromGCS = gcsService.getFileFromGCS("JavaBomLogo.png", "download/java-bom.png");
        return ResponseEntity.ok(fileFromGCS.toString());
    }

    @GetMapping("gcs/upload")
    public ResponseEntity localUploadToStorage() throws IOException {
        String fileFromGCS = gcsService.uploadFileToGCS("jyami_logo.png", "download/jyami_logo.png");
        return ResponseEntity.ok(fileFromGCS);
    }
}
