package dev.leonardovee.sftp

import com.jcraft.jsch.*
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
@EnableConfigurationProperties(SftpProperties::class)
class SftpApplication(
    private val sftpProperties: SftpProperties
) {
    companion object {
        const val PRIVATE_KEY_FILE: String = "ssh_host_ed25519_key"
    }

    @PostConstruct
    fun init() {
        JSch.setLogger(JschLogger())

        val jsch = JSch()
        var session: Session? = null
        var channel: Channel? = null
        var channelSftp: ChannelSftp? = null

        try {
            jsch.addIdentity(PRIVATE_KEY_FILE)
            session = jsch.getSession(sftpProperties.user, sftpProperties.host, sftpProperties.port)

            val config = Properties()
            config["StrictHostKeyChecking"] = "no"

            session.setConfig(config)
            session.connect()

            channel = session.openChannel("sftp")
            channel.connect()

            channelSftp = channel as ChannelSftp?
        } catch (e: JSchException) {
            e.printStackTrace()
        } catch (e: SftpException) {
            e.printStackTrace()
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect()
                channelSftp.exit()
            }
            channel?.disconnect()

            session?.disconnect()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SftpApplication>(*args)
}
