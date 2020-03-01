package com.jyami.gcsProejct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jyami on 2020/03/01
 */
@AllArgsConstructor
@Getter
public class DownloadReqDto {
    private String bucketName;
    private String downloadFileName;
    private String localFileLocation;
}
