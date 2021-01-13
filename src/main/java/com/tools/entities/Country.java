package com.tools.entities;

import com.tools.utils.MathUtils;

public class Country {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public Integer getNewCases() {
        return newCases;
    }

    public void setNewCases(Integer newCases) {
        this.newCases = newCases;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Integer getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(Integer activeCases) {
        this.activeCases = activeCases;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getTopCasesPerMilionPop() {
        return totalCasesPerMilionPop;
    }

    public void setTopCasesPerMilionPop(Integer topCasesPerMilionPop) {
        this.totalCasesPerMilionPop = topCasesPerMilionPop;
    }

    private String name;
    private int totalCases, newCases, totalDeaths, newDeaths, totalRecovered, newRecovered, activeCases, critical, totalCasesPerMilionPop, population;

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    private double overallDeathRate, newDeathsToActiveCasesPercentage, newDeathsToActiveCriticalCasesPercentage,
    percentageIncreaseOfTotalCases,
    recoveryPercentage, criticalActiveCasesPercentage, newRecoveredCasesPercentage;

    public double getNewRecoveredCasesPercentage() {
        return newRecoveredCasesPercentage;
    }

    public void setNewRecoveredCasesPercentage() {
        this.newRecoveredCasesPercentage = MathUtils.formatDoubleToTwoDecimals((double)this.getNewRecovered()
                / (double)(this.getActiveCases() + this.getNewRecovered() + this.getNewDeaths() - this.getNewCases())
                * 100);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getCriticalActiveCasesPercentage() {
        return criticalActiveCasesPercentage;
    }

    public void setCriticalActiveCasesPercentage() {
        this.criticalActiveCasesPercentage = MathUtils.formatDoubleToTwoDecimals((double)this.critical / (double)this.activeCases * 100);
    }

    public double getPercentageIncreaseOfTotalCases() {
        return this.percentageIncreaseOfTotalCases;
    }

    public void setPercentageIncreaseOfTotalCases() {
        this.percentageIncreaseOfTotalCases = MathUtils.formatDoubleToTwoDecimals((double)this.newCases / ((double)(this.totalCases - this.newCases)) * 100);
    }

    public double getRecoveryPercentage() {
        return recoveryPercentage;
    }

    public void setRecoveryPercentage() {
        this.recoveryPercentage = MathUtils.formatDoubleToTwoDecimals((double)this.totalRecovered / (double)this.totalCases * 100);
    }

    public void setNewDeathsToActiveCriticalCasesPercentage() {
        this.newDeathsToActiveCriticalCasesPercentage = MathUtils.formatDoubleToTwoDecimals((double)this.newDeaths / (double)this.critical * 100);
    }

    public double getNewDeathsToActiveCriticalCasesPercentage() {
        return this.newDeathsToActiveCriticalCasesPercentage;
    }

    public void setOverallDeathRate() {
        this.overallDeathRate = MathUtils.formatDoubleToTwoDecimals((double)this.totalDeaths / (double)this.totalCases * 100);
    }

    public double getOverallDeathRate() {
        return overallDeathRate;
    }

    public void setNewDeathsToActiveCasesPercentage() {
        this.newDeathsToActiveCasesPercentage = MathUtils.formatDoubleToTwoDecimals((double)this.newDeaths / (double)this.activeCases * 100);
    }

    public double getNewDeathsToActiveCasesPercentage() {
        return newDeathsToActiveCasesPercentage;
    }

    public int getTotalCasesPerMilionPop() {
        return totalCasesPerMilionPop;
    }

    public void setTotalCasesPerMilionPop(int totalCasesPerMilionPop) {
        this.totalCasesPerMilionPop = totalCasesPerMilionPop;
    }

    @Override
    public String toString() {
        return "Country [name=" + name + ", totalCases=" + totalCases + ", newCases=" + newCases + ", totalDeaths=" + totalDeaths + ", newDeaths=" + newDeaths
                + ", totalRecovered=" + totalRecovered + ", newRecovered=" + newRecovered + ", activeCases=" + activeCases + ", critical=" + critical
                + ", totalCasesPerMilionPop=" + totalCasesPerMilionPop + ", population=" + population + ", overallDeathRate=" + overallDeathRate
                + ", newDeathsToActiveCasesPercentage=" + newDeathsToActiveCasesPercentage + ", newDeathsToActiveCriticalCasesPercentage="
                + newDeathsToActiveCriticalCasesPercentage + ", percentageIncreaseOfTotalCases=" + percentageIncreaseOfTotalCases + ", recoveryPercentage="
                + recoveryPercentage + ", criticalActiveCasesPercentage=" + criticalActiveCasesPercentage + "]";
    }

    public int getPopulation() {
        return this.population;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void printStatistics() {
        System.out.println("SITUATION in " + this.getName() + " is:\n" + this.toString());
        System.out.println("Population: " + this.getPopulation() + " people");
        System.out.println(this.getName() + " has an OVERALL DEATH PERCENTAGE of " + this.getOverallDeathRate() + "%");
        System.out.println("NEW DEATHS represents " + this.getNewDeathsToActiveCasesPercentage() + "% of ACTIVE CASES!");
        System.out.println("NEW DEATHS represents " + this.getNewDeathsToActiveCriticalCasesPercentage() + "% of ACTIVE CRITICAL CASES!");
        System.out.println("TOTAL CASES INCREASED BY " + this.getPercentageIncreaseOfTotalCases() + "%");
        System.out.println("RECOVERY PERCENTAGE is " + this.getRecoveryPercentage() + "%");
        System.out.println("NEW RECOVERED CASES PERCENTAGE is " + this.getNewRecoveredCasesPercentage() + "%");
        System.out.println("CRITICAL ACTIVE CASES PERCENTAGE is " + this.getCriticalActiveCasesPercentage() + "%");
    }
}
