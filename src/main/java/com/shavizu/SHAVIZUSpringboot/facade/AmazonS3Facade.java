package com.shavizu.SHAVIZUSpringboot.facade;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shavizu.SHAVIZUSpringboot.exception.BadRequestException;
import com.shavizu.SHAVIZUSpringboot.property.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AmazonS3Facade {

    private final AmazonS3 amazonS3;

    private final AwsS3Properties awsS3Properties;

    public void delete(String objectUrl) {
        String objectName = objectUrl.replace(awsS3Properties.getUrl() + awsS3Properties.getShopImageUrl(), "");
        amazonS3.deleteObject(awsS3Properties.getBucket(), objectName);
    }

    public String uploadImage(MultipartFile file, boolean isShop) {
        String extension = getExtension(file);
        String filePath;
        try{
            filePath = saveImage(file, extension, isShop);
        } catch (IOException e) {
            throw BadRequestException.FILE_SAVE_FAILED_EXCEPTION;
        }
        return filePath;
    }

    private String getExtension(MultipartFile file) {
        if(file == null || file.isEmpty() || file.getOriginalFilename() == null)
            throw BadRequestException.FILE_EMPTY_EXCEPTION;

        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }

    private String saveImage(MultipartFile file, String extension, boolean isShop) throws IOException {
        String baseImageUrl = isShop ? awsS3Properties.getShopImageUrl() : awsS3Properties.getItemImageUrl();

        String filePath = baseImageUrl + file.getOriginalFilename() + extension;

        amazonS3.putObject(new PutObjectRequest(awsS3Properties.getBucket(), filePath, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return awsS3Properties.getUrl() + filePath;
    }

}
