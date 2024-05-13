package exercise8

import common.FileReader
import java.time.LocalDate
import java.time.LocalDateTime


private fun parseMovies(moviesLines: List<String>): List<Movie> {
    val movies : MutableList<Movie> = emptyList<Movie>().toMutableList()
    for(movie in moviesLines.drop(1)) {
        val actors : MutableList<MovieActor> = emptyList<MovieActor>().toMutableList()
        val line = movie.split(";")
        val budget = line[0].trim().toLong()
        val genres = line[1].split(",")
        val title = line[2].trim()
        val rating = line[3].trim().toDouble()
        val releaseDate = LocalDate.parse(line[4])
        val revenue = line[5].trim().toLong()
        val runtime = line[6].trim().toDouble().toInt()
        val cast = line[7].split(",")
        for(actor in cast) {
            actors.add(MovieActor(actor.trim()))
        }
        val director = MovieDirector(line[8])

        movies.add(Movie(title, budget, revenue, releaseDate, runtime, director, genres, actors, rating))
    }
    return movies
}

fun main() {
    val moviesCSVFile = FileReader.readFileInResources("exercise8/movies.csv")
    val movies = parseMovies(moviesCSVFile)

    val movieDBApi : MovieDBApi = MovieDB(movies)
    //println(movieDBApi.getAllMoviesByActor(MovieActor("Johnny Depp")))
    //println(movieDBApi.getMoviesWithBiggestProfit(2))
    //println(movieDBApi.getBestRatedMovieByActor(MovieActor("Johnny Depp")))
    //println(movieDBApi.getAllMoviesByYear(2003))
    //println(movieDBApi.getDirectorWithMostMoviesDirected())



}