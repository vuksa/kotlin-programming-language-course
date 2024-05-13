package exercise8

/**
 * Task: Write a MovieDB class that implements the MovieDBApi interface.
 *
 * Define a constructor of MovieDB class which accepts the list of movies as parameter.
 *
 * Implement methods defined by MovieDBApi.
 *
 */

class MovieDB(private val movies: List<Movie>) : MovieDBApi {
    override fun getAllMoviesByActor(actor: MovieActor): List<Movie> = movies
        .filter { movie -> movie.actors.any { it.name == actor.name } }

    override fun getMoviesWithBiggestProfit(numOfMovies: Int): List<Movie> = movies
        .sortedByDescending { it.revenue - it.budget }
        .take(numOfMovies)

    override fun getBestRatedMovieByActor(actor: MovieActor): Movie? {
        return movies
            .asSequence()
            .filter { movie -> movie.actors.any { it.name == actor.name } }
            .maxByOrNull { it.rating }
    }

    override fun getAllMoviesByYear(year: Int): List<Movie> = movies.filter { it.releaseDate.year == year }

    override fun getAllMoviesByGenre(genre: String): List<Movie> = movies.filter { it.genres.contains(genre) }

    override fun getBestRatedMovies(numOfMovies: Int): List<Movie> = movies
        .sortedByDescending { it.rating }
        .take(numOfMovies)

    override fun getDirectorWithMostMoviesDirected(): MovieDirector {
        val eachCount = movies
            .groupingBy { it.director }
            .eachCount()
        return eachCount
            .maxBy { it.value }
            .key
    }

    data class ActorCostarring(val actor: MovieActor, val anotherActor: MovieActor, val numOfMovies: Int)

    override fun getActorsWithMostCostarredMovies(): List<Pair<MovieActor, MovieActor>> {
        val actorToMovies: Map<MovieActor, List<Movie>> = movies
            .flatMap { movie -> movie.actors.map { actor -> actor to movie } }
            .groupBy({ actorToMovie -> actorToMovie.first }, { actorToMovie -> actorToMovie.second })


        val actors = actorToMovies
            .keys
            .sortedBy { it.name }

        val costarringActors = actors.flatMap { actor ->
            actors.mapNotNull { anotherActor ->
                if (actor == anotherActor) return@mapNotNull null

                val (sortedActorOne, sortedActorTwo) = listOf(actor, anotherActor).sortedBy { it.name }
                val actorOneMovies = actorToMovies[sortedActorOne]?.map { it.title }?.toSet() ?: emptySet()
                val actorTwoMovies = actorToMovies[sortedActorTwo]?.map { it.title }?.toSet() ?: emptySet()
                val costarringMovies = actorOneMovies.intersect(actorTwoMovies).count()
                ActorCostarring(sortedActorOne, sortedActorTwo, costarringMovies)
            }
        }.sortedByDescending { it.numOfMovies }

        val maxCostarringMoviesCount = costarringActors.first().numOfMovies

        return costarringActors
            .takeWhile { it.numOfMovies == maxCostarringMoviesCount }
            .fold(setOf<Pair<MovieActor, MovieActor>>()) { actorsSet, (actor, anotherActor, _) ->
                actorsSet + setOf(
                    actor to anotherActor
                )
            }
            .toList()
    }
}