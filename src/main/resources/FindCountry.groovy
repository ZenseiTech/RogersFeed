package com.zensei.compare

/**
 * Created by rodrijor on 4/21/2017.
 */

def countries = ["Cuba", "CANADA", "RUSSIA", "CHILE", "turKia", "dascw"]

def countriesToFind = [
        "BXDCUBAAOXT",
        "CANADAACIOU",
        "ZRUSSIAIZTR",
        "PELIHCWNQQK",
        "PELxHCWNQQI",
        "TELtHCWNQQA"
]

findCountries(countries, countriesToFind)
findCountries(countries, verticalToHorizontal(countriesToFind))
findCountries(countries, acrossToHorizontal(countriesToFind))


def findCountries(countries, countriesToFind) {
    for(String countryToFind : countriesToFind) {
        search(countryToFind, countries)
    }
}


def search(countryToFind, countries) {
    for(String country : countries) {
        if(findCountry(countryToFind, country) || findCountryReverse(countryToFind, country)) {
            println("found country: " + country)
        }
    }
}


def boolean findCountry(String lineInput, String countryToFind) {
    return lineInput.toUpperCase().contains(countryToFind.toUpperCase())
}


def boolean findCountryReverse(String lineInput, String countryToFind) {
    return lineInput.reverse().toUpperCase().contains(countryToFind.toUpperCase())
}

def verticalToHorizontal(countriesToFind) {

    def newCountriesToFind = []
    StringBuilder sb = new StringBuilder()

    def size = countriesToFind[0].size()

    for(int i = 1; i < size; ++i) {
        for(String countryToFind : countriesToFind) {
            sb.append(countryToFind[i])
        }
        newCountriesToFind.add(sb.toString())
        sb = new StringBuilder()
    }

    return newCountriesToFind
}

def acrossToHorizontal(countriesToFind) {

    def newCountriesToFind = []
    StringBuilder sb = new StringBuilder()

    def position = 0
    def size = countriesToFind[0].size()

    for(int n = 0; n < countriesToFind.size(); n++) {

        for(int i = 1; i < size; ++i) {

            for(int z = n; z < countriesToFind.size(); ++z) {
                if(position < size) {
                    sb.append(countriesToFind[z][position++])
                }
            }
            newCountriesToFind.add(sb.toString())
            sb = new StringBuilder()
            position = i
        }
    }

    println(newCountriesToFind)
    return newCountriesToFind
}