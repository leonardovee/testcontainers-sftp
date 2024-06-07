package dev.leonardovee.sftp

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile

@SpringBootTest
@Testcontainers
class SftpApplicationTests {
    companion object {
        @Container
        private val sftpContainer = GenericContainer<Nothing>("atmoz/sftp:latest")
            .apply {
                withCommand("foo::1001")
                withExposedPorts(22)
                withCopyFileToContainer(
                    MountableFile.forHostPath("ssh_host_ed25519_key"),
                    "/home/foo/.ssh/keys/ssh_host_ed25519_key"
                )
                withCopyFileToContainer(
                    MountableFile.forHostPath("ssh_host_ed25519_key.pub"),
                    "/home/foo/.ssh/keys/ssh_host_ed25519_key.pub"
                )
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("sftp.host") { sftpContainer.host }
            registry.add("sftp.port") { sftpContainer.firstMappedPort }
            registry.add("sftp.user") { "foo" }
        }
    }

    @Test
    fun contextLoads() {
    }
}
