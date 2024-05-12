package exercise8

import common.FileReader
import java.time.LocalDate

internal fun parseMovies(moviesLines: List<String>): List<Movie> {
    return moviesLines
        .drop(1)
        .map { line ->
            val components = line.split(";")
            val budget = components[0].trim().toLong()
            val genres = components[1].trim().split(",").map { it.trim() }
            val title = components[2].trim()
            val rating = components[3].trim().toDouble()

            val releaseDate = LocalDate.parse(components[4])
            val revenue = components[5].trim().toLong()
            val runtime = components[6].trim().toDouble().toInt()
            val actors = components[7].trim().split(",")
                .filter { it.isNotBlank() }
                .map { MovieActor(it.trim()) }
            val director = MovieDirector(components[8].trim())

            Movie(title, budget, revenue, releaseDate, runtime, director, genres, actors, rating)
        }
}

fun main() {
    val moviesCSVFile = FileReader.readFileInResources("exercise8/movies.csv")
    val movies = parseMovies(moviesCSVFile)

    val movieDBApi: MovieDBApi = MovieDB(movies)

    println(movieDBApi.getActorsWithMostCostarredMovies())
}