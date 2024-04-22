package common

import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readLines
import kotlin.io.path.toPath

object FileReader {
    /**
     * Reads the contents of a file located at the specified path.
     *
     * @param path The path of the file to read.
     * @return A list of strings representing the lines of the file.
     * @throws NullPointerException if the resource at the specified path is null.
     */
    fun readFileInResources(path: String): List<String> {
        val normalizedPath = path.takeIf { it.startsWith("/") } ?: "/$path"
        return requireNotNull(this.javaClass.getResource(normalizedPath.toString())?.toURI()?.toPath()) {
            "Unresolved path."
        }.readLines()
    }
}