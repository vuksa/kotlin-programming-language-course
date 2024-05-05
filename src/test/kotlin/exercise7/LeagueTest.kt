package exercise7

import common.FileReader
import common.TestCase
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.io.path.readLines
import kotlin.test.assertEquals

class LeagueTest : TestCase() {
    companion object {
        private lateinit var league: LeagueApi

        @JvmStatic
        @BeforeAll
        fun setup() {
            val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
            val fixtures = parseFixtures(fixturesText)
            val teams = fixtures.flatMap { fixture ->
                fixture.matches.flatMap { match -> listOf(match.awayTeam, match.homeTeam) }
            }.distinct()

            league = League(teams, fixtures)
        }
    }

    private val testData = getTestData("exercise7/goals-scored.csv")
        .readLines()
    private val expectedGoalsScoredConcededEntries = ExpectedGoalsScoredConcededEntries(testData)

    @Test
    fun `test - verify league table`() {
        val expectedTableOrder = listOf(
            "Manchester City" to 86,
            "Manchester Utd" to 74,
            "Liverpool" to 69,
            "Chelsea" to 67,
            "Leicester City" to 66,
            "West Ham" to 65,
            "Tottenham" to 62,
            "Arsenal" to 61,
            "Leeds United" to 59,
            "Everton" to 59,
            "Aston Villa" to 55,
            "Newcastle Utd" to 45,
            "Wolves" to 45,
            "Crystal Palace" to 44,
            "Southampton" to 43,
            "Brighton" to 41,
            "Burnley" to 39,
            "Fulham" to 28,
            "West Brom" to 26,
            "Sheffield Utd" to 23,
        )

        assertEquals(
            expectedTableOrder,
            league.leagueTable.map { it.team.name to it.totalPoints },
            "League order doesn't match expected one."
        )
    }

    @Test
    fun getLeagueWinner() {
        val actual = league.leagueWinner.name
        assertEquals(
            "Manchester City",
            actual,
            "Expected winner of the league is \"Manchester City\", but actual one was $actual."
        )
    }

    @Test
    fun getTeamWithMostWins() {
        val actual = league.teamWithMostWins.name
        assertEquals(
            "Manchester City",
            actual,
            "Team with a most wins is \"Manchester City\", but the actual is $actual"
        )
    }

    @Test
    fun getTeamWithMostDraws() {
        val actual = league.teamWithMostDraws.name
        assertEquals(
            "Brighton",
            actual,
            "Team with a most draws is \"Brighton\", but the actual is $actual"
        )
    }

    @Test
    fun getTeamWithMostLoses() {
        val actual = league.teamWithMostLoses.name
        assertEquals(
            "Sheffield Utd",
            actual,
            "Team with a most draws is \"Sheffield Utd\", but the actual is $actual"
        )
    }

    @Test
    fun getTeamWithBestGoalDifference() {
        val actual = league.teamWithBestGoalDifference.name
        assertEquals(
            "Manchester City",
            actual,
            "Team with a most draws is \"Manchester City\", but the actual is $actual"
        )
    }

    @Test
    fun teamsWithBestDefence() {
        val actual = league.teamsWithBestDefence(3).map { it.name }.sorted()
        val expected = listOf("Arsenal", "Chelsea", "Manchester City")
        assertEquals(
            expected,
            actual,
            "Team with a best defense are $expected, but the actual is $actual"
        )
    }

    @Test
    fun teamsWithBestOffense() {
        val actual = league.teamsWithBestOffense(3).map { it.name }.sorted()
        val expected = listOf("Liverpool", "Manchester City", "Manchester Utd")
        assertEquals(
            expected,
            actual,
            "Team with a best offense are $expected, but the actual is $actual"
        )
    }

    @Test
    fun numOfGoalsTeamScoredAgainst() {
        doTestTeamAgainstTeam { (team, otherTeam) ->
            val expectedScoredGoals = expectedGoalsScoredConcededEntries.numOfGoalsTeamScoredAgainst(team, otherTeam)
            val actualScoredGoals = league.numOfGoalsTeamScoredAgainst(team, otherTeam)

            assertEquals(
                expectedScoredGoals,
                actualScoredGoals,
                "Team ${team.name} scored $expectedScoredGoals against ${otherTeam.name} but actual was $actualScoredGoals"
            )
        }
    }

    @Test
    fun numOfGoalsTeamConcededAgainst() {
        doTestTeamAgainstTeam { (team, otherTeam) ->
            val expectedConcededGoals =
                expectedGoalsScoredConcededEntries.numOfGoalsTeamConcededAgainst(team, otherTeam)
            val actualConcededGoals = league.numOfGoalsTeamConcededAgainst(team, otherTeam)

            assertEquals(
                expectedConcededGoals,
                actualConcededGoals,
                "Team ${team.name} conceded $expectedConcededGoals against ${otherTeam.name} but actual was $actualConcededGoals"
            )
        }
    }

    private fun doTestTeamAgainstTeam(test: (Pair<Team, Team>) -> Unit) {
        val teams = league.teams
        val teamAgainstEachTeam = teams.flatMap { team ->
            teams.filter { otherTeam -> otherTeam != team }
                .map { otherTeam -> team to otherTeam }
        }

        teamAgainstEachTeam.forEach { teamToOtherTeam ->
            test(teamToOtherTeam)
        }
    }

    private class ExpectedGoalsScoredConcededEntries(
        testData: List<String>
    ) {
        private val teamAgainstEachTeam = loadTeamGoalsScoredConcededExpectedEntries(testData)

        fun numOfGoalsTeamScoredAgainst(team: Team, otherTeam: Team): Int {
            return teamAgainstEachTeam
                .first { it.team == team.name && it.otherTeam == otherTeam.name }
                .scoredGoals
        }

        fun numOfGoalsTeamConcededAgainst(team: Team, otherTeam: Team): Int {
            return teamAgainstEachTeam
                .first { it.team == team.name && it.otherTeam == otherTeam.name }
                .concededGoals
        }

        private fun loadTeamGoalsScoredConcededExpectedEntries(testData: List<String>): List<ExpectedGoalsScoredConcededEntry> {
            return testData
                .drop(1)
                .map { line ->
                    val (team, otherTeam, scored, conceded) = line.trim('\t', '\n').trim().split(",")
                    ExpectedGoalsScoredConcededEntry(team, otherTeam, scored.toInt(), conceded.toInt())
                }
        }

        private data class ExpectedGoalsScoredConcededEntry(
            val team: String,
            val otherTeam: String,
            val scoredGoals: Int,
            val concededGoals: Int
        )
    }
}