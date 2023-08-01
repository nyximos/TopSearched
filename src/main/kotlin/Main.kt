import java.io.File

fun main() {
    val keywordsFilePath = "src/main/resources/popular_keywords.csv"

    File(keywordsFilePath).bufferedReader().use { reader ->
        reader.readLine()

        for (line in reader.lines()) {
            println(line)
        }
    }
}
