import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import org.slf4j.LoggerFactory

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val currentTime = LocalTime.now().withHour(0).withNano(0)

    val logger = LoggerFactory.getLogger("Top Searched")

    val trends = File(keywordsFilePath).bufferedReader().useLines { lines ->
        lines.drop(1) // Skip the header
            .map { line -> line.split(",") }
            .map { (key, value) -> Pair(LocalDateTime.parse(key, formatter).toLocalTime().withHour(0), value) }
            .filter { (localTime, _) -> localTime.isAfter(currentTime.minusMinutes(5)) && localTime.isBefore(currentTime) }
            .groupBy { (_, value) -> value }
            .mapValues { (_, values) -> values.size }
            .toList()
            .sortedByDescending { (_, count) -> count }
            .take(10)
    }

    trends.forEach { (value, count) ->
        logger.info("$value: $count ")
    }
}