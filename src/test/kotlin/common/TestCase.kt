package common

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.toPath

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
        return requireNotNull(this.javaClass.getResource("/testdata")).toURI().toPath()
    }
}