package org.jetbrains.exercise2.task4

/**
 * Represents a country with its name, capital city, population, languages spoken, and total area in square kilometers.
 *
 * @property name The name of the country.
 * @property capitalCity The capital city of the country.
 * @property population The population of the country.
 * @property languages The list of languages spoken in the country.
 * @property totalAreaInSquareKilometers The total area of the country in square kilometers.
 */
internal data class Country(
    val name: String,
    val capitalCity: String,
    val population: Long,
    val languages: List<String>,
    val totalAreaInSquareKilometers: Double
)


/**
 * List of 20 countries with the data about their capitol city, official language, population, and total area.
 */
internal val countries = listOf(
    Country("Serbia", "Belgrade", 6_869_504, listOf("Serbian"), 88_499.0),
    Country("United States", "Washington, D.C.", 339_996_563, listOf("English"), 9_525_067.0),
    Country("Canada", "Ottawa", 36_991_981, listOf("English", "French"), 9_984_670.0),
    Country("United Kingdom", "London", 67_026_292, listOf("English"), 244_376.0),
    Country("Germany", "Berlin", 84_607_016, listOf("German"), 357_581.0),
    Country("France", "Paris", 68_373_433, listOf("French"), 551_695.0),
    Country("Italy", "Rome", 58_853_482, listOf("Italian"), 302_068.0),
    Country("Spain", "Madrid", 48_592_909, listOf("Spanish"), 505_370.0),
    Country("China", "Beijing", 1_425_671_352, listOf("Mandarin"), 9_596_960.0),
    Country("Japan", "Tokyo", 123_294_513, listOf("Japanese"), 377_195.0),
    Country("India", "New Delhi", 1_428_627_663, listOf("Hindi", "English"), 3_287_263.0),
    Country("Brazil", "Brasília", 216_422_446, listOf("Portuguese"), 8_510_346.0),
    Country("Australia", "Canberra", 26_439_111, listOf("English"), 7_741_220.0),
    Country("Russia", "Moscow", 144_444_359, listOf("Russian"), 17_098_246.0),
    Country(
        "South Africa", "Pretoria, Bloemfontein, Cape Town", 59308690,
        listOf(
            "Afrikaans", "English", "Zulu", "Xhosa", "Southern Sotho",
            "Tswana", "Northern Sotho", "Venda", "Tsonga", "Swati", "Ndebele"
        ), 1_219_090.0
    ),
    Country("Argentina", "Buenos Aires", 45195774, listOf("Spanish"), 2_780_400.0),
    Country("Mexico", "Mexico City", 128_455_567, listOf("Spanish"), 1_964_375.0),
    Country("Egypt", "Cairo", 112_716_598, listOf("Arabic"), 1_010_408.0),
    Country("Nigeria", "Abuja", 223_804_632, listOf("English"), 923_768.0),
    Country("Indonesia", "Jakarta", 277_534_122, listOf("Indonesian"), 1_904_569.0)
)

/**
 * Task 4:
 *
 * Implement functions that process countries' data and provide analyzed results.
 * Implement them using functional approach, by applying a map, flatmap, filter, maxBy, minBy and other useful functions
 * from Kotlin standard lib.
 */

internal fun List<Country>.findCountryWithBiggestTotalArea(): Country {
    return this.maxBy { it.totalAreaInSquareKilometers }
}

internal fun List<Country>.findCountryWithBiggestPopulation(): Country {
    return this.maxBy { it.population }
}

internal fun List<Country>.findCountryWithHighestPopulationDensity(): Country {
    return this.maxBy { it.population / it.totalAreaInSquareKilometers }
}

internal fun List<Country>.findCountryWithLowestPopulationDensity(): Country {
    return this.minBy { it.population / it.totalAreaInSquareKilometers }
}

internal fun List<Country>.findLanguageSpokenInMostCountries(): String {
    return this.flatMap { it.languages }
        .groupingBy { it }
//        .also { println("all groupings $it") }
        .eachCount()
        .also { println("catalog: $it") }
        .maxBy { it.value }
        .key
}

internal fun List<Country>.filterCountriesThatSpeakLanguage(language: String): List<Country> {
    return this.filter { it.languages.contains(language) }

}


fun main() {
    println("Country with a biggest total area is a ${countries.findCountryWithBiggestTotalArea().name}")
    println("Country with a biggest population is a ${countries.findCountryWithBiggestPopulation().name}")
    println("Country with a biggest population density is a ${countries.findCountryWithHighestPopulationDensity().name}")
    println("Country with a lowest population density is a ${countries.findCountryWithLowestPopulationDensity().name}")
    println("Language spoken in most countries is a ${countries.findLanguageSpokenInMostCountries()}")
    val countriesThatSpeakEnglish = countries.filterCountriesThatSpeakLanguage("English")
    println("Countries that speak English language are ${countriesThatSpeakEnglish.joinToString { it.name }}")
}
