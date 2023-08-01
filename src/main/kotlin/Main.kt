import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"
    val map = mutableMapOf<LocalTime, String>()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val logger = LoggerFactory.getLogger("Top Searched")

    File(keywordsFilePath).bufferedReader().use { reader ->
        reader.readLine()

        for (line in reader.lines()) {
            val (key, value) = line.split(",")
            val dateTime = LocalDateTime.parse(key, formatter)
            map[dateTime.toLocalTime()] = value
        }
    }
    printMap(map, logger)
}

fun printMap(map: Map<LocalTime, String>, logger: org.slf4j.Logger) {
    for ((key, value) in map) {
        logger.info("$key -> $value")
    }
}