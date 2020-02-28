package com.jyami.gcsProejct.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jyami on 2020/02/25
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class GCSService {

    private final Storage storage;

    public Blob getFileFromGCS(String gcsLocation, String saveLocation) {
        Blob blob = storage.get("test-storage-mj", gcsLocation);
        log.info("download File From GCS : " + blob.toString());
        blob.downloadTo(Paths.get(saveLocation));
        return blob;
    }

    @SuppressWarnings("deprecation")
    public String uploadFileToGCS(String fileName, String saveLocation) throws IOException {
        log.info("fileName : " + fileName);
        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder("test-storage-mj", fileName) // 폴더 만들 때는 맨 앞에 / 빼고 만들
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                        .build(),
                new FileInputStream(saveLocation));

        log.info("upload File to GCS : " + blobInfo.toString());

        return blobInfo.toString();
    }


}
