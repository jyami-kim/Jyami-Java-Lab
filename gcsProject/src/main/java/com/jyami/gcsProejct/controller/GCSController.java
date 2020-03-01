package com.jyami.gcsProejct.controller;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.jyami.gcsProejct.dto.DownloadReqDto;
import com.jyami.gcsProejct.dto.UploadReqDto;
import com.jyami.gcsProejct.service.GCSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by jyami on 2020/02/28
 */
@RestController
@RequiredArgsConstructor
public class GCSController {

    private final GCSService gcsService;

    @PostMapping("gcs/download")
    public ResponseEntity localDownloadFromStorage(@RequestBody DownloadReqDto downloadReqDto){
        Blob fileFromGCS = gcsService.downloadFileFromGCS(downloadReqDto);
        return ResponseEntity.ok(fileFromGCS.toString());
    }

    @PostMapping("gcs/upload")
    public ResponseEntity localUploadToStorage(@RequestBody UploadReqDto uploadReqDto) throws IOException {
        BlobInfo fileFromGCS = gcsService.uploadFileToGCS(uploadReqDto);
        return ResponseEntity.ok(fileFromGCS.toString());
    }
}
