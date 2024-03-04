package common

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists

abstract class TestCase {

    /**
     * Retrieves the test data path for a given file name.
     *
     * @param fileName The name of the test data file.
     * @return The path of the test data file.
     * @throws IllegalArgumentException if the test data file doesn't exist.
     */
    fun getTestData(fileName: String): Path {
        val testDataPath = getTestDataDir().resolve(fileName)
        require(testDataPath.exists())
        return testDataPath
    }

    /**
     * Retrieves the path of the test data directory.
     *
     * @return The path of the test data directory.
     */
    private fun getTestDataDir(): Path {
        return Paths.get(File(".").canonicalPath).resolve("src/test/testData")
    }
}