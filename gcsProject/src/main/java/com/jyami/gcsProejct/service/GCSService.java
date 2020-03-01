package com.jyami.gcsProejct.service;

import com.google.cloud.storage.*;
import com.jyami.gcsProejct.dto.DownloadReqDto;
import com.jyami.gcsProejct.dto.UploadReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jyami on 2020/02/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GCSService {

    private final Storage storage;

    public Blob downloadFileFromGCS(DownloadReqDto downloadReqDto) {
        Blob blob = storage.get(downloadReqDto.getBucketName(), downloadReqDto.getDownloadFileName());
        blob.downloadTo(Paths.get(downloadReqDto.getLocalFileLocation()));
        return blob;
    }

    @SuppressWarnings("deprecation")
    public BlobInfo uploadFileToGCS(UploadReqDto uploadReqDto) throws IOException {

        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                        .build(),
                new FileInputStream(uploadReqDto.getLocalFileLocation()));

        return blobInfo;
    }

}
