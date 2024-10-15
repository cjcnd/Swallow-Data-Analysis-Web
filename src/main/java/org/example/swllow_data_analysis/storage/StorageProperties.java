package org.example.swllow_data_analysis.storage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Data
@Service
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload_dir";


}
