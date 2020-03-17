Service provides API: 

HTTP GET:
/players - list of all players
/player/{id} - get information about player (including balance)
/player/{id}/transactions - get transaction history for player


HTTP POST:
/transaction - add transaction

Solution uses Spring Boot
Database: H2
Default DB data: resources/data.sql
