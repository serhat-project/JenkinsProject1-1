Feature: OrangeHrm Login

  Scenario: Login Search Jenkins

    Given User is on Opencart website "http://opencart.abstracta.us/"
    When user searches for "Mac"
    And stores the name of the last product in System.setProperty
    And user switches to a new Tab "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"
    And user clicks on Login
    And inputs the stored product name in username
    Then user takes a screen shot