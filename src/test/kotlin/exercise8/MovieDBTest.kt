package exercise8

import common.FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class MovieDBTest {
    private val movieDBApi: MovieDBApi = TODO("Instantiate object as follows: MovieDB(parseMovies(FileReader.readFileInResources(\"exercise8/movies.csv\")))")

    @Test
    fun `test - api returns all movies by actor`() {
        TestMovieDB.ACTORS_TO_EXPECTED_MOVIES.forEach { (actor, expectedMovies) ->
            val actualMoviesByActor = movieDBApi.getAllMoviesByActor(actor)
                .map { it.title }
                .sorted()

            assertEquals(expectedMovies.sorted(), actualMoviesByActor)
        }
    }

    @Test
    fun `test - api returns top 10 movies with a biggest profit`() {
        val expectedTopGrossingMovies = TestMovieDB.TOP_10_BEST_GROSSING_MOVIES

        val actualMoviesWithBiggestProfits = movieDBApi.getMoviesWithBiggestProfit(expectedTopGrossingMovies.count())
            .map { it.title }

        assertEquals(expectedTopGrossingMovies, actualMoviesWithBiggestProfits)
    }

    @Test
    fun `test - api returns best rated movie by actor`() {
        val actorsToExpectedBestRatedMovies = TestMovieDB.ACTOR_TO_BEST_RATED_MOVIES

        actorsToExpectedBestRatedMovies.forEach { (actor, expectedBestRatedMovie) ->
            val actualBestRatedMovieTitle = movieDBApi.getBestRatedMovieByActor(actor)?.title

            assertEquals(expectedBestRatedMovie, actualBestRatedMovieTitle)
        }
    }

    @Test
    fun `test - api returns all movies of year`() {
        TestMovieDB.MOVIES_PER_YEAR.forEach { (year, expectedMovieTitlesOfYear) ->
            val actualMovieTitlesOfYear = movieDBApi.getAllMoviesByYear(year)
                .map { it.title }

            assertEquals(expectedMovieTitlesOfYear.sorted(), actualMovieTitlesOfYear.sorted())
        }
    }

    @Test
    fun `test - api returns all movies by genre`() {
        val moviesPerGenre: Map<String, List<String>> = TestMovieDB.MOVIES_BY_GENRE

        moviesPerGenre.forEach { (genre, expectedMovieTitles) ->
            val actualMoviesTitlePeGenre = movieDBApi.getAllMoviesByGenre(genre).map { it.title }

            assertEquals(expectedMovieTitles.sorted(), actualMoviesTitlePeGenre.sorted())
        }
    }

    @Test
    fun `test - api returns top 10 best rated movies`() {
        val expectedTop10BestRatedMovieTitles: List<String> = TestMovieDB.TOP_10_BEST_RATED_MOVIES

        val actualMoviesTitlePeGenre = movieDBApi.getBestRatedMovies(expectedTop10BestRatedMovieTitles.count())
            .map { it.title }

        assertEquals(expectedTop10BestRatedMovieTitles.sorted(), actualMoviesTitlePeGenre.sorted())
    }

    @Test
    fun `test - api returns director with most directed movies`() {
        val actualMovieDirector = movieDBApi.getDirectorWithMostMoviesDirected()

        assertEquals(TestMovieDB.DIRECTOR_WITH_MOST_MOVIES_DIRECTED, actualMovieDirector.name)
    }

    @Test
    fun `test - api returns actors with most costarred movies`() {
        val actorsWithMostCostarredMovies = movieDBApi.getActorsWithMostCostarredMovies()

        assertEquals(TestMovieDB.ACTOR_PAIRS_WITH_MOST_COSTARRED_MOVIES, actorsWithMostCostarredMovies)
    }
}