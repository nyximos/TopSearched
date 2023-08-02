import java.io.File
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"
    val map = mutableMapOf<LocalTime, MutableList<String>>()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val currentTime = LocalTime.now().withHour(0).withNano(0)

    val logger = LoggerFactory.getLogger("Top Searched")

    File(keywordsFilePath).bufferedReader().use { reader ->
        reader.readLine()

        for (line in reader.lines()) {
            val (key, value) = line.split(",")
            val dateTime = LocalDateTime.parse(key, formatter)
            val localTime = dateTime.toLocalTime().withHour(0)

            if (localTime.isAfter(currentTime.minusMinutes(5)) && localTime.isBefore(currentTime)) {
                map.computeIfAbsent(localTime) { mutableListOf() }.add(value)
            }
        }
    }

    val trends = map.values
        .flatten()
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }
        .take(10)

    for ((value, count) in trends) {
        logger.info("$value: $count ")
    }
}