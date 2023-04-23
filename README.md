# Purpose
Automates the end to end scenario for the web application 'magento.softwaretestingboard'
# Selenium_Automation
This automation com is developed using selenium, cucumber, java and maven.

## Installation

Pre-requisite: The following softwares are needed -

1) Install JDK 1.8 and set path
2) Install Maven and set path
3) Intellij
4) Intellij Plugins: Maven , Cucumber and Gherkin

Git link : https://github.com/Divya1702/Selenium_Automation.git

## Project structure
1) src/test/resources/Features/AddtoCart.Feature - Cucumber Feature File
2) src/main/java/com/pages - All page object classes
3) src/main/java/com/steps - Cucumber step definition class
4) src/main/java/com/config - Configuration properties
5) src/main/java/com/infrastructure - Utility classes

## Config Options
Main class =cucumber.api.cli.Main
glue=com.steps
VM options = -Dcucumber.options="--format html:target/cucumber-reports-html" (To generate HTML report)
