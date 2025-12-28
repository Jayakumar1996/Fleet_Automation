#Feature: Automation practice
#
  #@BrokenImages
  #Scenario: Navigate to broken images page and verify broken images
    #When User clicks on the "Broken Images" section
    #Then User should be navigated to the broken images page
    #And User verifies all broken images on the page

    
Feature: PDF Download and Content Validation

  As a QA Automation Engineer
  I want to download a PDF file
  And validate its content
  So that document data is verified

  Scenario: Validate downloaded PDF content
    Given user is on the PDF download page
    When user downloads the PDF file
    Then PDF file should be downloaded successfully
    And PDF content should contain expected data

    