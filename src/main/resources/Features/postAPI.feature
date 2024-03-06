#Author: prakgautam@gmail.com
Feature: Get request for Google API.

  @test
  Scenario Outline: User hits the Google API to fetch the places
    Given User tries to add a place "<Name>" "<Phone Number>" "<address>" "<Website>" "<Language>"
    When User tries to get the address added "<Name>" "<Phone Number>" "<address>" "<Website>" "<Language>"
    #And User update the address due to correction
    #Then User deletes the added address.
    Examples: 
      | Name        | Phone Number | address                            | Website              | Language    |
      | HIRA        |  04426549278 | No 24, Vivekanandar kurukku Street | www.vandumurugan.com | English -IN |
      | Casa Grande |  04422334455 | No 30, Egattur, OMR                | www.naisekar.com     | French -EN  |
