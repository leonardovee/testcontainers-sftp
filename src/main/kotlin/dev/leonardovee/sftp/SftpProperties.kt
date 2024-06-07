package dev.leonardovee.sftp

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sftp")
data class SftpProperties(
    var host: String = "",
    var port: Int = 0,
    var user: String = "",
)
