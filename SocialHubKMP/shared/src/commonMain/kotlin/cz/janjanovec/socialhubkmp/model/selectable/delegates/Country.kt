package cz.janjanovec.socialhubkmp.model.selectable.delegates

import cz.janjanovec.socialhubkmp.model.selectable.Selectable

enum class Country(
    val isoCode: String,
    val countryName: String,
    val flag: String,
    val phonePrefix: String
): Selectable {
    CZ("cz", "Czech Republic", "🇨🇿", "+420"),
    SK("sk", "Slovakia", "🇸🇰", "+421"),
    DE("de", "Germany", "🇩🇪", "+49"),
    AT("at", "Austria", "🇦🇹", "+43"),
    PL("pl", "Poland", "🇵🇱", "+48"),
    HU("hu", "Hungary", "🇭🇺", "+36"),
    FR("fr", "France", "🇫🇷", "+33"),
    IT("it", "Italy", "🇮🇹", "+39"),
    ES("es", "Spain", "🇪🇸", "+34"),
    PT("pt", "Portugal", "🇵🇹", "+351"),
    GB("gb", "United Kingdom", "🇬🇧", "+44"),
    IE("ie", "Ireland", "🇮🇪", "+353"),
    BE("be", "Belgium", "🇧🇪", "+32"),
    NL("nl", "Netherlands", "🇳🇱", "+31"),
    DK("dk", "Denmark", "🇩🇰", "+45"),
    SE("se", "Sweden", "🇸🇪", "+46"),
    NO("no", "Norway", "🇳🇴", "+47"),
    FI("fi", "Finland", "🇫🇮", "+358"),
    EE("ee", "Estonia", "🇪🇪", "+372"),
    LV("lv", "Latvia", "🇱🇻", "+371"),
    LT("lt", "Lithuania", "🇱🇹", "+370"),
    BY("by", "Belarus", "🇧🇾", "+375"),
    UA("ua", "Ukraine", "🇺🇦", "+380"),
    RU("ru", "Russia", "🇷🇺", "+7"),
    MD("md", "Moldova", "🇲🇩", "+373"),
    RO("ro", "Romania", "🇷🇴", "+40"),
    BG("bg", "Bulgaria", "🇧🇬", "+359"),
    GR("gr", "Greece", "🇬🇷", "+30"),
    TR("tr", "Turkey", "🇹🇷", "+90"),
    HR("hr", "Croatia", "🇭🇷", "+385"),
    BA("ba", "Bosnia and Herzegovina", "🇧🇦", "+387"),
    RS("rs", "Serbia", "🇷🇸", "+381"),
    ME("me", "Montenegro", "🇲🇪", "+382"),
    AL("al", "Albania", "🇦🇱", "+355"),
    MK("mk", "North Macedonia", "🇲🇰", "+389"),
    SI("si", "Slovenia", "🇸🇮", "+386"),
    XK("xk", "Kosovo", "🇽🇰", "+383"),
    LI("li", "Liechtenstein", "🇱🇮", "+423"),
    CH("ch", "Switzerland", "🇨🇭", "+41"),
    LU("lu", "Luxembourg", "🇱🇺", "+352"),
    MT("mt", "Malta", "🇲🇹", "+356"),
    IS("is", "Iceland", "🇮🇸", "+354"),
    FO("fo", "Faroe Islands", "🇫🇴", "+298"),
    GL("gl", "Greenland", "🇬🇱", "+299"),
    GI("gi", "Gibraltar", "🇬🇮", "+350"),
    AD("ad", "Andorra", "🇦🇩", "+376");
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
    fun getAll(): List<Country> = Country.values().toList()
    fun getCountryByIsoCode(isoCode: String): Country? = Country.values().toList().find { it.isoCode == isoCode }
}
