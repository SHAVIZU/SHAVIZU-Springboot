package com.shavizu.SHAVIZUSpringboot.facade;

import com.amazonaws.services.s3.AmazonS3;
import com.shavizu.SHAVIZUSpringboot.property.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AmazonS3Facade {

    private final AmazonS3 amazonS3;

    private final AwsS3Properties awsS3Properties;

    public void delete(String objectUrl) {
        String objectName = objectUrl.replace(awsS3Properties.getUrl() + awsS3Properties.getShopImageUrl(), "");
        amazonS3.deleteObject(awsS3Properties.getBucket(), objectName);
    }

}
