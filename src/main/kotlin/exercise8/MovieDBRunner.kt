package exercise8

import common.FileReader


private fun parseMovies(moviesLines: List<String>): List<Movie> {
    TODO("Implement parsing of the file content")
}

fun main() {
    val moviesCSVFile = FileReader.readFileInResources("exercise8/movies.csv")
    val movies = parseMovies(moviesCSVFile)

    val movieDBApi : MovieDBApi = TODO("Instantiate MovieDB")
}