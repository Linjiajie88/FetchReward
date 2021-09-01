# FetchReward

Fetch Rewards Coding Exercise - Backend Software Engineering
## Function
● Add transactions for a specific payer and date.

● Spend points using the rules above and return a list of { "payer": <string>, "points": <integer> } for each call

● Return all payer point balances




## Usage
Windows:

run the springboot application with maven using command:
```bash
mvn spring-boot:run
```

## Test with curl
Reward Points:

```bash
POST/payer/transaction
curl -X POST --location "http://localhost:8080/payer/transaction" -H "Content-Type: application/json" -d "{ \"payer\": \"DANNON\", \"points\": 1000, \"timestamp\": \"2020-11-02T14:00:00Z\" }"

curl -X POST --location "http://localhost:8080/payer/transaction" -H "Content-Type: application/json" -d "{ \"payer\": \"UNILEVER\", \"points\": 200, \"timestamp\": \"2020-10-31T11:00:00Z\" }"

curl -X POST --location "http://localhost:8080/payer/transaction" -H "Content-Type: application/json" -d "{ \"payer\": \"DANNON\", \"points\": -200, \"timestamp\": \"2020-10-31T15:00:00Z\" }"

curl -X POST --location "http://localhost:8080/payer/transaction" -H "Content-Type: application/json" -d "{ \"payer\": \"MILLER COORS\", \"points\": 10000, \"timestamp\": \"2020-11-01T14:00:00Z\" }"

curl -X POST --location "http://localhost:8080/payer/transaction" -H "Content-Type: application/json" -d "{ \"payer\": \"DANNON\", \"points\": 300, \"timestamp\": \"2020-10-31T10:00:00Z\" }"
```

Spend Points
```bash
curl -X POST --location "http://localhost:8080/redeem" -H "Content-Type: application/json" -d "{\"points\": 5000}"
```

Get All Payers' Balances
```bash
curl -X GET --location "http://localhost:8080/payer/balances"
```

