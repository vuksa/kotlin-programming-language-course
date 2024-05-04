package exercise7

import common.FileReader

internal fun parseFixtures(fixturesText: List<String>): List<Fixture> {
    val roundToMatchesMap: Map<String, List<String>> = fixturesText
        .drop(1)
        .groupBy { it.split(',').first() }

    val fixtures: MutableList<Fixture> = mutableListOf()

    roundToMatchesMap.forEach { (round, matchStrList) ->
        matchStrList.forEach { matchStr ->
            val matchList: List<Match> = List(matchStrList.size) {
                val (_, _, homeTeamName, resultStr, awayTeamName) = matchStr.trim().split(',')
                val resultSplit = resultStr.trim().split('-')
                Match(Team(homeTeamName), Team(awayTeamName), resultSplit.first().toInt(), resultSplit.last().toInt())
            }
            fixtures.add(Fixture(round.toInt(), matchList))
        }
    }

    return fixtures
}

internal fun parseTeamsFromFixtures(fixtures: List<Fixture>): List<Team> {
    val result: MutableList<Team> = mutableListOf()
    fixtures.forEach { fixture ->
        fixture.matches.forEach { match ->
            result.add(match.homeTeam)
            result.add(match.awayTeam)
        }
    }
    return result.distinct()
}

fun main() {
    val fixturesText = FileReader.readFileInResources("exercise7/fixtures.csv")
    val fixtures: List<Fixture> = parseFixtures(fixturesText)
//    fixtures.forEach { println(it) }
    val teams = parseTeamsFromFixtures(fixtures)

    // Create league object
    val league: LeagueApi = League(teams, fixtures)
    league.displayLeagueTable()
//
//    league.displayLeagueTableAtFixture(13)
}