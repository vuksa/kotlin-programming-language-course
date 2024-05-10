package exercise7

import common.FileReader

internal fun parseFixtures(fixturesText: List<String>): List<Fixture> {
    val teams = HashMap<String, Team>()

    return fixturesText
        // We are dropping header lines of csv file
        .drop(1)
        .map { fixtureText ->
            val fixtureData = fixtureText.split(",")

            val fixtureId = fixtureData[0].toInt()
            val homeTeam = teams.getOrPut(fixtureData[2]) { Team(fixtureData[2]) }
            val awayTeam = teams.getOrPut(fixtureData[4]) { Team(fixtureData[4]) }
            val (homeTeamScore, awayTeamScore) = fixtureData[3].trim()
                .split("-")
                .let { scores -> scores.first().toInt() to scores[1].toInt() }

            fixtureId to Match(homeTeam, awayTeam, homeTeamScore, awayTeamScore)
        }.fold(mutableMapOf<Int, List<Match>>()) { fixtureIdsToMatches, (fixtureId: Int, match: Match) ->
            val matches = fixtureIdsToMatches.getOrDefault(fixtureId, emptyList())

            // We always append a new patch to the current match list of the fixture
            fixtureIdsToMatches[fixtureId] = matches + match

            // The Same Map is always returned to aggregate all matches in a single data structure
            fixtureIdsToMatches
        }.entries.map { (fixtureId, matches) -> Fixture(fixtureId, matches) }
}

fun main() {
    val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
    val fixtures: List<Fixture> = parseFixtures(fixturesText)
    val teams = fixtures
        .flatMap { fixture ->
            fixture
                .matches
                .flatMap { match -> listOf(match.awayTeam, match.homeTeam) }
        }.distinct()

    // Create league object
    val league: LeagueApi = League(teams, fixtures)

    val numOfGoalsTeamScoredAgainst = league.numOfGoalsTeamScoredAgainst(teams.first(), teams[1])
    println("${teams.first().name} scored $numOfGoalsTeamScoredAgainst goals against ${teams[1].name}")

    val numOfGoalsTeamConcededAgainst = league.numOfGoalsTeamConcededAgainst(teams.first(), teams[1])
    println("${teams.first().name} conceded $numOfGoalsTeamConcededAgainst goals against ${teams[1].name}")

    league.displayLeagueTable()

    println()
    println()

    league.displayLeagueTableAtFixture(13)
}