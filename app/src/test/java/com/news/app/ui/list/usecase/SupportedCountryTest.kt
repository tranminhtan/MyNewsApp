package com.news.app.ui.list.usecase

import com.news.app.ui.list.support.SupportedCountry
import org.junit.Assert
import org.junit.Test
import java.util.Locale

class SupportedCountryTest {
    private val supportedCountry = SupportedCountry()

    @Test
    fun getCountryCode_emptyLocale_returnUS() {
        Locale.setDefault(Locale.ROOT)
        Assert.assertEquals("us", supportedCountry.getCountryCode())
    }

    @Test
    fun getCountryCode_validLocale_returnIt() {
        var testLocale: Locale
        supportedCountry.supportedCountry.forEach { code ->
            testLocale = Locale(code, code)
            Locale.setDefault(testLocale)
            Assert.assertEquals(code, supportedCountry.getCountryCode())
        }
    }
}