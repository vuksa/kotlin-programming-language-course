package exercise7

/**
 * Represents a team participating in a league.
 *
 * @property name The name of the team.
 */
internal data class Team(val name: String)

/**
 * Represents a match between two teams.
 *
 * @property homeTeam The home team.
 * @property awayTeam The away team.
 * @property homeTeamScore The score of the home team.
 * @property awayTeamScore The score of the away team.
 */
internal data class Match(
    val homeTeam: Team,
    val awayTeam: Team,
    val homeTeamScore: Int,
    val awayTeamScore: Int
)

/**
 * Represents a fixture which contains a fixture ID and a list of matches.
 *
 * @property fixtureId The ID of the fixture.
 * @property matches The list of matches in the fixture.
 */
internal data class Fixture(
    val fixtureId: Int,
    val matches: List<Match>
)

/**
 * Represents a team's entry in a league table.
 *
 * @param team The team participating in the league.
 * @param totalGamesPlayed The total number of games played by the team.
 * @param wins The number of wins by the team.
 * @param loses The number of loses by the team.
 * @param draws The number of draws by the team.
 * @param totalScoredGoals The total number of goals scored by the team.
 * @param totalConcededGoals The total number of goals conceded by the team.
 */
internal data class LeagueTableEntry(
    val team: Team,
    val totalGamesPlayed: Int,
    val wins: Int,
    val loses: Int,
    val draws: Int,
    val totalScoredGoals: Int,
    val totalConcededGoals: Int,
) {
    /**
     * The total number of points earned by a team.
     * The Team earns 3 points for every win, 1 point for a draw, and zero points for a loss.
     */
    val totalPoints: Int get() = 3 * wins + draws
}
