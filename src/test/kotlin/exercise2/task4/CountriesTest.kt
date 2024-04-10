package exercise2.task4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CountriesTest {
    @Test
    fun `test - find country with a biggest total area`() {
        val actualCountry = countries.findCountryWithBiggestTotalArea()

        assertEquals("Russia", actualCountry.name)
        assertEquals(17_098_246.0, actualCountry.totalAreaInSquareKilometers)
    }

    @Test
    fun `test - find country with a biggest population`() {
        val actualCountry = countries.findCountryWithBiggestPopulation()

        assertEquals("India", actualCountry.name)
        assertEquals(1_428_627_663, actualCountry.population)
    }

    @Test
    fun `test - find country with a biggest population density`() {
        val actualCountry = countries.findCountryWithHighestPopulationDensity()

        assertEquals("India", actualCountry.name)
    }

    @Test
    fun `test - find country with a lowest population density`() {
        val actualCountry = countries.findCountryWithLowestPopulationDensity()

        assertEquals("Australia", actualCountry.name)
    }

    @Test
    fun `test - find language spoken in the most countries`() {
        val actualLanguage = countries.findLanguageSpokenInMostCountries()

        assertEquals("English", actualLanguage)
    }

    @Test
    fun `test - find countries that speak specific language`() {
        languageCountryCatalog.forEach { (language: String, expectedCountries: List<String>) ->
            val actualCountries = countries.filterCountriesThatSpeakLanguage(language)
                .map(Country::name).sorted()
            val sortedExpectedCountries = expectedCountries.sorted()
            assertEquals(sortedExpectedCountries, actualCountries) {
                "List of countries that speaks $language should be ${expectedCountries.joinToString()}, but it was ${actualCountries.joinToString()}."
            }
        }
    }

    private val languageCountryCatalog: Map<String, List<String>> = mapOf(
        "Serbian" to listOf("Serbia"),
        "English" to listOf(
            "United States",
            "Canada",
            "United Kingdom",
            "India",
            "South Africa",
            "Australia",
            "Nigeria"
        ),
        "German" to listOf("Germany"),
        "French" to listOf("France", "Canada"),
        "Italian" to listOf("Italy"),
        "Spanish" to listOf("Spain", "Argentina", "Mexico"),
        "Mandarin" to listOf("China"),
        "Japanese" to listOf("Japan"),
        "Hindi" to listOf("India"),
        "Portuguese" to listOf("Brazil"),
        "Russian" to listOf("Russia"),
        "Afrikaans" to listOf("South Africa"),
        "Zulu" to listOf("South Africa"),
        "Xhosa" to listOf("South Africa"),
        "Southern Sotho" to listOf("South Africa"),
        "Tswana" to listOf("South Africa"),
        "Northern Sotho" to listOf("South Africa"),
        "Venda" to listOf("South Africa"),
        "Tsonga" to listOf("South Africa"),
        "Swati" to listOf("South Africa"),
        "Ndebele" to listOf("South Africa"),
        "Arabic" to listOf("Egypt"),
        "Indonesian" to listOf("Indonesia")
    )

}
