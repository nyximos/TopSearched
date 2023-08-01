import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val inputStream = object {}.javaClass.getResourceAsStream("/popular_keywords.csv")

    BufferedReader(InputStreamReader(inputStream)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            println(line)

            line = reader.readLine()
        }
    }
}
