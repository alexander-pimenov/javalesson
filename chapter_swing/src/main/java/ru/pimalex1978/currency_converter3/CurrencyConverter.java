package ru.pimalex1978.currency_converter3;

import ru.pimalex1978.currency_converter3.service.CurrencyConvertService;

/**
 * Класс CurrencyConverter позволяет в реальном времени получать обменный курс для двух валют.
 * https://github.com/posadskiy/currency-converter
 */

public class CurrencyConverter {

    /**
     * API Key for <url>currencyconverterapi.com</url>
     */
    private String apiKey;

    private CurrencyConvertService currencyConvertService = new CurrencyConvertService();

    private CurrencyConverter() {};

    public CurrencyConverter(String apiKey) {
        this.apiKey = apiKey;
    }
    /**
     * Receives exchange rate
     * Получаем обменный курс
     *
     * @param from The currency to be exchanged
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rate(Currency from, Currency to) {
        return currencyConvertService.rate(this.apiKey, from, to);
    }

    /**
     * Receives exchange rate from USD
     * Получает обменный курс от доллара США
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromUsd(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.USD, to);
    }

    /**
     * Receives exchange rate from USD to Euro
     * Получает обменный курс от доллара США к евро
     *
     * @return rate
     */
    public Double rateFromUsdToEuro() {
        return currencyConvertService.rate(this.apiKey, Currency.USD, Currency.EUR);
    }

    /**
     * Receives exchange rate from Euro to USD
     * Получает обменный курс евро к доллару США
     *
     * @return rate
     */
    public Double rateFromEuroToUsd() {
        return currencyConvertService.rate(this.apiKey, Currency.EUR, Currency.USD);
    }

    /**
     * Receives exchange rate to USD
     * Получает обменный курс к доллару США
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToUsd(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.USD);
    }

    /**
     * Receives exchange rate from Euro
     * Получает обменный курс от евро
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromEuro(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.EUR, to);
    }

    /**
     * Receives exchange rate to Euro
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToEuro(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.EUR);
    }

    /**
     * Receives exchange rate from UAH
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromUah(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.UAH, to);
    }

    /**
     * Receives exchange rate to UAH
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToUah(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.UAH);
    }

    /**
     * Receives exchange rate from Russian Rubble
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromRub(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.RUB, to);
    }

    /**
     * Receives exchange rate to Russian Rubble
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToRub(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.RUB);
    }

    /**
     * Receives exchange rate from New Belorussian Rubble (BYN)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromByn(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.BYN, to);
    }

    /**
     * Receives exchange rate to New Belorussian Rubble (BYN)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToByn(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.BYN);
    }

    /**
     * Receives exchange rate to Kuwaiti Dinar (KWD)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToKwd(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.KWD);
    }

    /**
     * Receives exchange rate from Kuwaiti Dinar (KWD)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromKuwaitiDinar(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.KWD, to);
    }

    /**
     * Receives exchange rate to Bahraini Dinar (BHD)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToKuwaitiDinar(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.BHD);
    }

    /**
     * Receives exchange rate from Bahraini Dinar (BHD)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromBahrainiDinar(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.BHD, to);
    }

    /**
     * Receives exchange rate to Oman Rial (OMR)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToOmanRial(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.OMR);
    }

    /**
     * Receives exchange rate from Oman Rial (OMR)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromOmanRial(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.OMR, to);
    }

    /**
     * Receives exchange rate to Jordan Dinar (JOD)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToJordanDinar(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.JOD);
    }

    /**
     * Receives exchange rate from Jordan Dinar (JOD)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromJordanDinar(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.JOD, to);
    }

    /**
     * Receives exchange rate to British Pound Sterling (GBP)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToBritishPoundSterling(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.GBP);
    }

    /**
     * Receives exchange rate from British Pound Sterling (GBP)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromBritishPoundSterling(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.GBP, to);
    }

    /**
     * Receives exchange rate to Cayman Islands Dollar (KYD)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToCaymanIslandsDollar(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.KYD);
    }

    /**
     * Receives exchange rate from Cayman Islands Dollar (KYD)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromCaymanIslandsDollar(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.KYD, to);
    }

    /**
     * Receives exchange rate to Swiss Franc (CHF)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToSwissFranc(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.CHF);
    }

    /**
     * Receives exchange rate from Swiss Franc (CHF)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromSwissFranc(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.CHF, to);
    }

    /**
     * Receives exchange rate to Canadian Dollar (CAD)
     *
     * @param from The currency to be exchanged
     * @return rate
     */
    public Double rateToCanadianDollar(Currency from) {
        return currencyConvertService.rate(this.apiKey, from, Currency.CAD);
    }

    /**
     * Receives exchange rate from Canadian Dollar (CAD)
     *
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rateFromCanadianDollar(Currency to) {
        return currencyConvertService.rate(this.apiKey, Currency.CAD, to);
    }

    /**
     * Receives exchange rate
     *
     * @param from The currency to be exchanged
     * @param to The currency in which the exchange
     * @return rate
     */
    public Double rate(String from, String to) {
        return currencyConvertService.rate(this.apiKey, Currency.valueOf(from), Currency.valueOf(to));
    }
}
