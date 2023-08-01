import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"
    val logger = LoggerFactory.getLogger("Top Searched")


    File(keywordsFilePath).bufferedReader().use { reader ->
        reader.readLine()

        for (line in reader.lines()) {
            logger.info(line)
        }
    }
}
