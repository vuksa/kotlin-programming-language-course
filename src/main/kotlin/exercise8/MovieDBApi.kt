package exercise8

import java.time.LocalDate

/**
 * Represents a movie director.
 *
 * @property name The name of the movie director.
 */
data class MovieDirector(val name: String)

/**
 * Represents a movie actor.
 *
 * @property name The name of the actor.
 */
data class MovieActor(val name: String)

/**
 * Represents a Movie.
 *
 * @property title The title of the movie.
 * @property budget The budget of the movie in dollars.
 * @property revenue The revenue of the movie in dollars.
 * @property releaseDate The release date of the movie.
 * @property runtimeInMinutes The runtime of the movie in minutes.
 * @property director The director of the movie.
 * @property genres The genres of the movie.
 * @property actors The actors in the movie.
 * @property rating The rating of the movie.
 */
data class Movie(
    val title: String,
    val budget: Long,
    val revenue: Long,
    val releaseDate: LocalDate,
    val runtimeInMinutes: Int,
    val director: MovieDirector,
    val genres: List<String>,
    val actors: List<MovieActor>,
    val rating: Double
)

interface MovieDBApi {
    /**
     *
     * Retrieves a list of all movies that feature the given actor.
     *
     * @param actor The actor to search for.
     * @return A list of movies that feature the given actor.
     */
    fun getAllMoviesByActor(actor: MovieActor): List<Movie>

    /**
     * Retrieves a list of movies with the best profits.
     *
     * Movie profits are calculated by deducting revenue from its budget.
     *
     * @param numOfMovies The number of movies to retrieve.
     * @return A list of movies with the best earnings.
     */
    fun getMoviesWithBiggestProfit(numOfMovies: Int): List<Movie>

    /**
     * Retrieves the best-rated movie featuring the given actor.
     *
     * @param actor The actor to search for.
     * @return The best-rated movie featuring the given actor, or null if no movie is found.
     */
    fun getBestRatedMovieByActor(actor: MovieActor): Movie?

    /**
     * Retrieves a list of all movies released in the specified year.
     *
     * @param year The year of release.
     * @return A list of movies released in the specified year.
     */
    fun getAllMoviesByYear(year: Int): List<Movie>

    /**
     * Retrieves a list of all movies that belong to the given genre.
     *
     * @param genre The genre to search for.
     * @return A list of movies that belong to the given genre.
     */
    fun getAllMoviesByGenre(genre: String): List<Movie>

    /**
     * Retrieves a list of the best-rated movies.
     *
     * The best-rated movies are determined by their rating, with the highest-rated movies appearing first.
     *
     * @param numOfMovies The number of movies to retrieve.
     * @return A list of the best-rated movies.
     */
    fun getBestRatedMovies(numOfMovies: Int): List<Movie>

    /**
     * Retrieves the movie director who has directed the most movies.
     *
     * @return The movie director with the most movies directed.
     */
    fun getDirectorWithMostMoviesDirected(): MovieDirector

    /**
     * Retrieves a list of actor pairs who have the most movies they acted in together.
     *
     * @return A list of MovieActor objects representing the actors with the most costarred movies.
     */
    fun getActorsWithMostCostarredMovies(): List<Pair<MovieActor, MovieActor>>
}

