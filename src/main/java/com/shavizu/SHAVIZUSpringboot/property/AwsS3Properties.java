package com.shavizu.SHAVIZUSpringboot.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("aws.s3")
public class AwsS3Properties {

    private final String bucket;

    private final String shopImageUrl;

    private final String itemImageUrl;

    private final String url;

    public AwsS3Properties(String bucket, String shopImageUrl, String itemImageUrl, String url) {
        this.bucket = bucket;
        this.shopImageUrl = shopImageUrl;
        this.itemImageUrl = itemImageUrl;
        this.url = url;
    }

}
