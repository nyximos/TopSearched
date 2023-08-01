import java.io.File

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"

    File(keywordsFilePath).bufferedReader().use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            println(line)

            line = reader.readLine()
        }
    }
}
