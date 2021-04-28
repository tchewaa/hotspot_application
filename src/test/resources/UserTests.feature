@UserTests
  Feature: User End Points Tests

    Scenario : client makes request for all USERS
      When The client sends a request for all users
      Then Assert that there is a result

    Scenario Outline: client makes request for specific USERS by an email
      When The client sends a request for a specific user by email "<email>"
      Then Assert that there is a result

      Examples:
      | email      |
      | user@1.com |
      | user@2.com |
      | user@3.com |
      | user@4.com |

    Scenario Outline: client request for specific USERS by an ID
      When The client sends a request for a specific user by id "<id>"
      And I assert the email is "<email>"

      Examples:
        | id | email            |
        | 1  | wrongemail@a.com |
        | 2  | user@2.com       |
        | 3  | user@3.com       |
        | 4  | user@4.com       |