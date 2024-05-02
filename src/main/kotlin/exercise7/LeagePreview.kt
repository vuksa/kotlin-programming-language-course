package exercise7

import common.FileReader

internal fun parseFixtures(fixturesText: List<String>): List<Fixture> {
   val matchList : MutableList<Match> = emptyList<Match>().toMutableList()
    val fixtureList : MutableList<Fixture> = emptyList<Fixture>().toMutableList()
    for(match in fixturesText.drop(1)) {
        val line = match.split(",")
        val score = line[3].split("-")
        matchList.add(Match(Team(line[2]), Team(line[4]), Integer.parseInt(score[0]), Integer.parseInt(score[1])))
        for(i in line) {
            fixtureList.add(Fixture(Integer.parseInt(line[0]), matchList))
        }
    }
    return fixtureList
}

fun main() {
    val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
    val fixtures: List<Fixture> = parseFixtures(fixturesText)
    val teams = fixtures.flatMap { it.matches  }
        .map { it -> it.awayTeam }
        .toList<Team>().distinct()


    // Create league object
    val league: LeagueApi = League(teams, fixtures)
    println(league.displayLeagueTable())
    //league.displayLeagueTable()

    //league.displayLeagueTableAtFixture(13)
}