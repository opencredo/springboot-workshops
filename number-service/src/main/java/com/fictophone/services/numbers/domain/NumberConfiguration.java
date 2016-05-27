package com.fictophone.services.numbers.domain;

public final class NumberConfiguration {

    public static NumberConfiguration empty() {
        return of("{}");
    }

    public static NumberConfiguration of(String configuration) {
        return new NumberConfiguration(configuration);
    }

    private final String configuration;

    private NumberConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getConfiguration() {
        return configuration;
    }

    @Override
    public String toString() {
        return configuration;
    }
}
