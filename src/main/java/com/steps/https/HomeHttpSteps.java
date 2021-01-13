package com.steps.https;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tools.constants.Constants;
import com.tools.entities.CountriesCollection;
import com.tools.entities.Country;
import com.tools.utils.MathUtils;
import com.tools.utils.SerenitySessionUtils;

public class HomeHttpSteps extends AbstractHttpsSteps {
    private static final long serialVersionUID = 1L;
    @Steps()
    private CountryHttpsSteps countryHttpSteps;

    @Step
    public void printTopCountriesNames(String dayTab, int numberOfCountries) {
        CountriesCollection countries = new CountriesCollection();
        countries.setCountries(getCountriesRecords(dayTab));
        Constants.COUNTRY_ALL_PERCENTAGE_VALUES_FIELDS_LIST.forEach(fieldName -> {
            countries.printTopCountriesNames(fieldName, numberOfCountries);
            countries.printTopFromTheLastCountries(fieldName, numberOfCountries);
            System.out.println("\n");
        });
    }

    @Step
    public void printTopAbsoluteNumbersCountries(String dayTab, int numberOfCountries) {
        CountriesCollection countries = new CountriesCollection();
        countries.setCountries(getCountriesRecords(dayTab));
        Constants.COUNTRY_ALL_ABSOLUTE_VALUES_FIELDS_LIST.forEach(fieldName -> {
            countries.printTopCountriesNames(fieldName, numberOfCountries);
            countries.printTopFromTheLastCountries(fieldName, numberOfCountries);
            System.out.println("\n");
        });
    }

    @Step
    public void printSpecificCountryStatistics(String dayTab, String name) {
        Country country = getCountryRecord(dayTab, name);
        country.printStatistics();
    }

    public void compareCountriesPercentage(String statisticName, Double percentageToCompare, Double referencePercentage) {
        System.out.println(statisticName + ": COMPARED TO " + referencePercentage + "%, "
                + percentageToCompare
                + "% REPRESENTS "
                + MathUtils.formatDoubleToTwoDecimals(((percentageToCompare / referencePercentage) * 100)) + "%");
    }

    public void compareCountriesInformationByField(Country countryToCompare, Country referenceCountry, String fieldName) throws IllegalArgumentException,
    IllegalAccessException, NoSuchFieldException, SecurityException {
        Double countryToCompareFieldValue = MathUtils.getObjectDoubleFieldValue(countryToCompare, fieldName);
        Double referenceCountryFieldValue = MathUtils.getObjectDoubleFieldValue(referenceCountry, fieldName);
        System.out.println(fieldName.replaceAll("([^_])([A-Z])", "$1 $2").toUpperCase() + ": COMPARED TO " + referenceCountryFieldValue + "% OF "
                + referenceCountry.getName() + ", " + countryToCompareFieldValue + "% OF " + countryToCompare.getName() + " REPRESENTS "
                + MathUtils.formatDoubleToTwoDecimals((countryToCompareFieldValue / referenceCountryFieldValue) * 100) + "%");
    }

    @Step
    public void printSpecificCountriesStatisticsComparisons(String dayTab, String targetCountry, String referenceCountry) throws IllegalArgumentException,
    SecurityException {
        Country country = getCountryRecord(dayTab, targetCountry);
        Country reference = getCountryRecord(dayTab, referenceCountry);
        System.out.println(country.getName() + " COMPARED TO " + reference.getName() + "\n");
        Constants.COUNTRY_ALL_PERCENTAGE_VALUES_FIELDS_LIST.parallelStream().forEach(fieldName -> {
            try {
                compareCountriesInformationByField(country, reference, fieldName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("\n");
    }

    public void printCountriesComparisons(String dayTab, String countryToCompare, String fieldName) throws IllegalArgumentException, IllegalAccessException,
    NoSuchFieldException, SecurityException {
        CountriesCollection countries = new CountriesCollection();
        countries.setCountries(getCountriesRecords(dayTab));
        Country country = getCountryRecord(dayTab, countryToCompare);

        Country maxValueCountry = countries.getCountryByFieldMaxValue(fieldName);
        Country minValueCountry = countries.getCountryByFieldMinValue(fieldName);

        compareCountriesInformationByField(country, maxValueCountry, fieldName);
        compareCountriesInformationByField(country, minValueCountry, fieldName);
        System.out.println("\n");
    }

    @Step
    public void printSpecificCountryComparisonToBestAndWorstTopCountries(String dayTab, String countryToCompare) throws IllegalArgumentException,
    SecurityException {
        Country country = getCountryRecord(dayTab, countryToCompare);
        System.out.println(country.getName() + " COMPARED TO BEST AND WORST TOP COUNTRIES");
        System.out.println("POPULATION: " + country.getPopulation() + " PEOPLE\n");

        Constants.COUNTRY_ALL_PERCENTAGE_VALUES_FIELDS_LIST.parallelStream().forEach(fieldName -> {
            try {
                printCountriesComparisons(dayTab, countryToCompare, fieldName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public int getCellNumberValue(Element countryRecordRow, int columnNumber) {
        String cellValue = countryRecordRow.select("td:nth-child(" + columnNumber + ")").text();
        if (cellValue != null) {
            try {
                return Integer.parseInt(cellValue.replace(",", ""));
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public String getCellStringValue(Element countryRecordRow, int columnNumber) {
        String cellValue = countryRecordRow.select("td:nth-child(" + columnNumber + ")").text();
        if (cellValue != null) {
            return cellValue;
        }
        return null;
    }

    public Country getCountryDetail(Element countryRecordRow) {
        Country country = new Country();
        country.setName(getCellStringValue(countryRecordRow, 2));
        country.setTotalCases(getCellNumberValue(countryRecordRow, 3));
        country.setNewCases(getCellNumberValue(countryRecordRow, 4));
        country.setTotalDeaths(getCellNumberValue(countryRecordRow, 5));
        country.setNewDeaths(getCellNumberValue(countryRecordRow, 6));
        country.setTotalRecovered(getCellNumberValue(countryRecordRow, 7));
        country.setNewRecovered(getCellNumberValue(countryRecordRow, 8));
        country.setActiveCases(getCellNumberValue(countryRecordRow, 9));
        country.setCritical(getCellNumberValue(countryRecordRow, 10));
        country.setTotalCasesPerMilionPop(getCellNumberValue(countryRecordRow, 11));
        country.setOverallDeathRate();
        country.setNewDeathsToActiveCasesPercentage();
        country.setNewDeathsToActiveCriticalCasesPercentage();
        country.setPercentageIncreaseOfTotalCases();
        country.setRecoveryPercentage();
        country.setCriticalActiveCasesPercentage();
        country.setNewRecoveredCasesPercentage();
        country.setPopulation(getCellNumberValue(countryRecordRow, 15));
        return country;
    }

    public List<Country> getCountriesRecords(String dayTab) {
        if (SerenitySessionUtils.getFromSession(dayTab) == null) {
            List<Country> countries = getCountriesRecordsFromResponse(
                    dayTab,
                    c -> (c.getNewRecovered() > 0) && (c.getNewCases() > 0) && (c.getNewDeaths() > 0) && (c.getActiveCases() > 0) && (c.getCritical() > 0)
                    && (c.getNewDeaths() < c.getCritical()) && !c.getName().equals("Total:") && !c.getName().equals("World"));
            SerenitySessionUtils.putOnSession(dayTab, countries);
            return countries;
        }
        return SerenitySessionUtils.getFromSession(dayTab);
    }

    public Country getCountryRecord(String dayTab, String name) {
        return getCountriesRecordsFromResponse(dayTab, c -> c.getName().equalsIgnoreCase(name)).get(0);
    }

    @Step
    public void printCountryUpdatesSinceYesterday(String name) {
        Country countryOfYesterday = getCountryRecord(Constants.TAB_YESTERDAY, name);
        Country countryOfNow = getCountryRecord(Constants.TAB_NOW, name);
        System.out.println(countryOfNow.getName() + "'S CURRENT SITUATION:");
        System.out.println("NEW RECOVERED: " + (countryOfNow.getNewRecovered()));
        System.out.println("NEW ACTIVE CASES: " + (countryOfNow.getActiveCases() - countryOfYesterday.getActiveCases()));
        System.out.println("NEW CRITICAL: " + (countryOfNow.getCritical() - countryOfYesterday.getCritical()));
        SerenitySessionUtils.removeFromSession(Constants.TAB_YESTERDAY);
        SerenitySessionUtils.removeFromSession(Constants.TAB_NOW);
    }

    public List<Country> getCountriesRecordsFromResponse(String dayTab, Predicate<Country> predicate) {
        ArrayList<Country> countries = new ArrayList<Country>();
        String countriesHtml = getRequest("").asString();
        Document page = Jsoup.parse(countriesHtml);
        Elements countriesRowRecords = page.select("#main_table_countries_" + dayTab + " tbody tr:not(.row_continent)");
        countriesRowRecords.parallelStream().forEach(countriesRowRecord -> {
            if (predicate.test(getCountryDetail(countriesRowRecord))) {
                countries.add(getCountryDetail(countriesRowRecord));
            }
        });
        return countries;
    }

}