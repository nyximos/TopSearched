import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main() {
    val fileName = "src/main/resources/popular_keywords.csv"
    val file = File(fileName)
    val inputStream = file.inputStream()

    BufferedReader(InputStreamReader(inputStream)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            println(line)

            line = reader.readLine()
        }
    }
}
