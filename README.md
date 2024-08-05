# Event Ticketing Service
## Description

An event ticketing system that has the below functionalities:
- User can register their email and name with the system
- Admin users can create events
- Users can view events
- Users can book tickets for events that aren't full
- User can view all their unexpired tickets

## API Endpoints
Tickets:
- POST "/v1/ticket"
  - Books a ticket for a user for an event
  - Request Body Example:
    - ```
      {
          "event_name": "test1",
          "user_name": "test1",
          "email": "test1@test.com"
      }
- GET "/v1/ticket?email={email}"
  - Gets all the unexpired tickets that a user owns
  
Users:
- POST "/v1/user"
  - Registers a user with the system
  - Request Body Example:
    - ```
      {
          "name": "test1",
          "email": "test1@test.com"
      }

- GET "/v1/user/{email}"
  - Gets the user with the email address
  
Events:
- POST "/v1/event"
  - Creates an event with non-duplicate names
    - Request Body Example:
      - ```
        {
            "name": "test1",
            "start_time": "2025-02-25T12:01:03Z",
            "end_time": "2025-03-01T12:01:03Z",
            "max_capacity": 200
        }

- GET "/v1/event"
  - Gets all unexpired events
- GET "/v1/event/{name}"
  - Gets the unexpired event with name

## Run
1. Create a MySQL database locally
2. Replace the "url", "username", and "password" under "datasource" in application.yml file with your local MySQL database details.
3. Run the application, default port is 8085
