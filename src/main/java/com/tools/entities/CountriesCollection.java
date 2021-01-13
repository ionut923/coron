package com.tools.entities;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joor.Reflect;

public class CountriesCollection {
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void sortCountriesByFieldDescending(String fieldName) {
        this.countries.sort(Comparator.comparing(country -> Reflect.on(country).field(fieldName).get()).reversed());
    }

    public Country getCountryByFieldMaxValue(String fieldName) {
        this.countries.sort(Comparator.comparing(country -> Reflect.on(country).field(fieldName).get()).reversed());
        return this.countries.get(0);
    }

    public Country getCountryByFieldMinValue(String fieldName) {
        this.countries.sort(Comparator.comparing(country -> Reflect.on(country).field(fieldName).get()));
        return this.countries.get(0);
    }

    public void sortCountriesByFieldAscending(String fieldName) {
        this.countries.sort(Comparator.comparing(country -> Reflect.on(country).field(fieldName).get()));
    }

    public void printTopCountriesNames(String fieldName, int numberOfCountries) {
        sortCountriesByFieldDescending(fieldName);
        System.out.println("HIGHEST "
                + StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(fieldName), StringUtils.SPACE).toUpperCase()));
        for (Country country : this.countries.subList(0, numberOfCountries)) {
            System.out.println("- " + country.getName().toUpperCase() + " with "
                    + Reflect.on(country).field(fieldName).get().toString());
        }
    }

    public void printTopFromTheLastCountries(String fieldName, int numberOfCountries) {
        sortCountriesByFieldAscending(fieldName);
        System.out.println("LOWEST "
                + StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(fieldName), StringUtils.SPACE).toUpperCase()));
        for (Country country : this.countries.subList(0, numberOfCountries)) {
            System.out.println("- " + country.getName().toUpperCase() + " with "
                    + Reflect.on(country).field(fieldName).get().toString());
        }
    }
}
