package dev.leonardovee.sftp

import com.jcraft.jsch.Logger
import org.slf4j.LoggerFactory
import java.util.*

class JschLogger : Logger {
    private val log = LoggerFactory.getLogger(JschLogger::class.java)

    override fun isEnabled(level: Int): Boolean {
        return true
    }

    override fun log(level: Int, message: String) {
        log.info(message)
    }
}