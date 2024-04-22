package exercise7

import common.FileReader

internal fun parseFixtures(fixturesText: List<String>): List<Fixture> {
    TODO("Parse fixtures text to list of Fixtures.")
}

fun main() {
    val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
    val fixtures: List<Fixture> = parseFixtures(fixturesText)
    val teams = TODO("Parse all teams from fixtures")

    // Create league object
    val league: LeagueApi = TODO("Instantiate league: League(teams, fixtures)")
    league.displayLeagueTable()

    league.displayLeagueTableAtFixture(13)
}