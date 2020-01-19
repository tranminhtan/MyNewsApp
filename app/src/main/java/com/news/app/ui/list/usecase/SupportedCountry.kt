package com.news.app.ui.list.usecase

import androidx.annotation.VisibleForTesting
import java.util.Locale
import javax.inject.Inject

private const val DEFAULT_COUNTRY = "us"

class SupportedCountry @Inject constructor() {
    @VisibleForTesting
    val supportedCountry = arrayOf(
        "ae",
        "ar",
        "at",
        "au",
        "be",
        "bg",
        "br",
        "ca",
        "ch",
        "cn",
        "co",
        "cu",
        "cz",
        "de",
        "eg",
        "fr",
        "gb",
        "gr",
        "hk",
        "hu",
        "id",
        "ie",
        "il",
        "in",
        "it",
        "jp",
        "kr",
        "lt",
        "lv",
        "ma",
        "mx",
        "my",
        "ng",
        "nl",
        "no",
        "nzv",
        "ph",
        "pl",
        "pt",
        "ro",
        "rs",
        "ru",
        "sa",
        "se",
        "sg",
        "si",
        "sk",
        "th",
        "tr",
        "tw",
        "ua",
        "us",
        "ve",
        "za"
    )

    fun getCountryCode(): String {
        return Locale.getDefault().country.toLowerCase(Locale.getDefault()).let {
            if (supportedCountry.contains(it)) {
                it
            } else {
                DEFAULT_COUNTRY
            }
        }
    }
}