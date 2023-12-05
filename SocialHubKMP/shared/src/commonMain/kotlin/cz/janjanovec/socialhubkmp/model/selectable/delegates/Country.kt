package cz.janjanovec.socialhubkmp.model.selectable.delegates

import cz.janjanovec.socialhubkmp.model.selectable.Selectable

data class Country(
    val isoCode: String,
    val name: String,
    val flag: String,
    val phonePrefix: String
): Selectable {
    override val id: String
        get() = isoCode
    override val listIcon: String
        get() = flag
    override val listTitle: String
        get() = name
    override val listDescription: String?
        get() = null
    override val onSelectedColor: String?
        get() = null
}

object CountryHelper {
    private val countries = listOf(
        // Europe
        Country("cz", "Czech Republic", "🇨🇿", "+420"),
        Country("sk", "Slovakia", "🇸🇰", "+421"),
        Country("de", "Germany", "🇩🇪", "+49"),
        Country("at", "Austria", "🇦🇹", "+43"),
        Country("pl", "Poland", "🇵🇱", "+48"),
        Country("hu", "Hungary", "🇭🇺", "+36"),
        Country("fr", "France", "🇫🇷", "+33"),
        Country("it", "Italy", "🇮🇹", "+39"),
        Country("es", "Spain", "🇪🇸", "+34"),
        Country("pt", "Portugal", "🇵🇹", "+351"),
        Country("gb", "United Kingdom", "🇬🇧", "+44"),
        Country("ie", "Ireland", "🇮🇪", "+353"),
        Country("be", "Belgium", "🇧🇪", "+32"),
        Country("nl", "Netherlands", "🇳🇱", "+31"),
        Country("dk", "Denmark", "🇩🇰", "+45"),
        Country("se", "Sweden", "🇸🇪", "+46"),
        Country("no", "Norway", "🇳🇴", "+47"),
        Country("fi", "Finland", "🇫🇮", "+358"),
        Country("ee", "Estonia", "🇪🇪", "+372"),
        Country("lv", "Latvia", "🇱🇻", "+371"),
        Country("lt", "Lithuania", "🇱🇹", "+370"),
        Country("by", "Belarus", "🇧🇾", "+375"),
        Country("ua", "Ukraine", "🇺🇦", "+380"),
        Country("ru", "Russia", "🇷🇺", "+7"),
        Country("md", "Moldova", "🇲🇩", "+373"),
        Country("ro", "Romania", "🇷🇴", "+40"),
        Country("bg", "Bulgaria", "🇧🇬", "+359"),
        Country("gr", "Greece", "🇬🇷", "+30"),
        Country("tr", "Turkey", "🇹🇷", "+90"),
        Country("hr", "Croatia", "🇭🇷", "+385"),
        Country("ba", "Bosnia and Herzegovina", "🇧🇦", "+387"),
        Country("rs", "Serbia", "🇷🇸", "+381"),
        Country("me", "Montenegro", "🇲🇪", "+382"),
        Country("al", "Albania", "🇦🇱", "+355"),
        Country("mk", "North Macedonia", "🇲🇰", "+389"),
        Country("si", "Slovenia", "🇸🇮", "+386"),
        Country("xk", "Kosovo", "🇽🇰", "+383"),
        Country("li", "Liechtenstein", "🇱🇮", "+423"),
        Country("ch", "Switzerland", "🇨🇭", "+41"),
        Country("lu", "Luxembourg", "🇱🇺", "+352"),
        Country("mt", "Malta", "🇲🇹", "+356"),
        Country("is", "Iceland", "🇮🇸", "+354"),
        Country("fo", "Faroe Islands", "🇫🇴", "+298"),
        Country("gl", "Greenland", "🇬🇱", "+299"),
        Country("gi", "Gibraltar", "🇬🇮", "+350"),
        Country("ad", "Andorra", "🇦🇩", "+376"),
    )

    fun getAll(): List<Country> = countries
    fun getCountryByIsoCode(isoCode: String): Country? = countries.find { it.isoCode == isoCode }
}
