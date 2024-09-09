package com.crypto.dashboardweb.model.enums;

/**
 * Exchanges Enum
 * stores the app supported exchanges
 */
public enum Exchanges {
    OKX,
    KUCOIN,
    GATEIO;

    // Method to get supported exchanges as an array
    public static String[] getSupportedExchanges() {
        return java.util.Arrays.stream(Exchanges.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }
}
