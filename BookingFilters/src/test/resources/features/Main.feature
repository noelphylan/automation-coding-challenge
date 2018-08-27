Feature: Test Booking.com new filter functionality

@local
Scenario: Basic Search on booking.com with a 5 star filter appplied
Given I am on Booking Home Page 
When I enter "Limerick" in the Destination input field
 And I click on the CheckIn icon
 And I select a check in date 3 months from today for 1 night
 And I click on the Search button
 And I click on the 5 star filter
Then The results page has these hotels
 | Hotel Name            | Is Listed |
 | The Savoy Hotel       | true      |
 | George Limerick Hotel | false     |
 
@local
Scenario: Basic Search on booking.com with a Sauna filter appplied
Given I am on Booking Home Page 
When I enter "Limerick" in the Destination input field
 And I click on the CheckIn icon
 And I select a check in date 3 months from today for 1 night
 And I click on the Search button
 And I click on the Sauna filter
Then The results page has these hotels
 | Hotel Name            | Sauna |
 | Limerick Strand Hotel | true  |
 | The Savoy Hotel       | false | 
 
@local
Scenario: if 5 star filter and Sauna filter are not applied, then hotel results size should be at least 1
Given I am on Booking Home Page 
When I enter "Limerick" in the Destination input field
 And I click on the CheckIn icon
 And I select a check in date 3 months from today for 1 night
 And I click on the Search button
Then The results page has at least 1 hotel

@local
Scenario: if 5 star filter is applied, then unapplied the hotel results should be unfiltered
Given I am on Booking Home Page 
When I enter "Limerick" in the Destination input field
 And I click on the CheckIn icon
 And I select a check in date 3 months from today for 1 night
 And I click on the Search button
 And I click on the 5 star filter
 And I click to remove the Applied filter 
Then The results page has the unfiltered hotel list

@local
Scenario: if Sauna filter is applied, then unapplied the hotel results should be unfiltered
Given I am on Booking Home Page 
When I enter "Limerick" in the Destination input field
 And I click on the CheckIn icon
 And I select a check in date 3 months from today for 1 night
 And I click on the Search button
 And I click on the Sauna filter
 And I click to remove the Applied filter 
Then The results page has the unfiltered hotel list 
 