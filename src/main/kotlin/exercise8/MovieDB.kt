package exercise8

/**
 * Task: Write a MovieDB class that implements the MovieDBApi interface.
 *
 * Define a constructor of MovieDB class which accepts the list of movies as parameter.
 *
 * Implement methods defined by MovieDBApi.
 *
 */

class MovieDB(
    private val movies: List<Movie>
): MovieDBApi {
    override fun getAllMoviesByActor(actor: MovieActor): List<Movie> {
        return movies.filter { movie -> movie.actors.contains(actor) }
    }

    override fun getMoviesWithBiggestProfit(numOfMovies: Int): List<Movie> {
        return movies.sortedBy { it.revenue - it.budget }
            .take(numOfMovies)
    }

    override fun getBestRatedMovieByActor(actor: MovieActor): Movie? {
        return movies.filter { movie -> movie.actors.contains(actor) }
            .maxBy { it.rating }
    }

    override fun getAllMoviesByYear(year: Int): List<Movie> {
        return movies.filter { movie -> movie.releaseDate.year == year }
    }

    override fun getAllMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { movie -> movie.genres.contains(genre) }
    }

    override fun getBestRatedMovies(numOfMovies: Int): List<Movie> {
        return movies.sortedBy { it.rating }
            .take(numOfMovies)
    }

    override fun getDirectorWithMostMoviesDirected(): MovieDirector {
        return movies.groupingBy { it.director }
            .eachCount()
            .maxBy { it.value }
            .key
    }

    override fun getActorsWithMostCostarredMovies(): List<Pair<MovieActor, MovieActor>> {
        TODO("IMPL")


//        return movies.groupingBy { Pair(it.actors, it.actors) }
//            .eachCount()
//            .maxBy { it.value }
//
//        val appearances = movies.groupingBy { it.actors }



    }


}