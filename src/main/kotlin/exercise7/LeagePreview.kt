package exercise7

import common.FileReader

internal fun parseFixtures(fixturesText: List<String>): List<Fixture> {
    val fixtureList : MutableList<Fixture> = emptyList<Fixture>().toMutableList()
    for(match in fixturesText.drop(1)) {
        val line = match.split(",")
        val score = line[3].split("-")
        val homeTeam = Team(line[2])
        val awayTeam = Team(line[4])
        val homeScore = score[0].toInt()
        val awayScore = score[1].toInt()

        val matchList = mutableListOf(Match(homeTeam, awayTeam, homeScore, awayScore))
        val fixId = line[0].toInt()
        fixtureList.add(Fixture(fixId, matchList))
    }
    return fixtureList
}

fun main() {
    val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
    val fixtures: List<Fixture> = parseFixtures(fixturesText)
    val teams = fixtures.flatMap { it.matches  }
        .map { it -> it.awayTeam }
        .toList<Team>().distinct()


    val league: LeagueApi = League(teams, fixtures)
    league.displayLeagueTable()

    league.displayLeagueTableAtFixture(13)

}