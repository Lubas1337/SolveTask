# Solva Task

tasks:




# Deployment

## clone repo

```bash
    git clone https://github.com/Lubas1337/SolveTask
```

## run Docker-compose file

```bash
  sudo docker-compose up -d
```
This is to create a database

## run project 

```bash
  sudo mvn spring-boot:run
```

# Documentation

## RequestMapping URL: /api/rest/v0.1/


## Endpoints
# 1. Health Check

## URL: /check/health
Method: GET
Description: Check the health status of the application.
Response: Returns a JSON object indicating the status of the application.
status: String representing the status of the application. Possible values include:
"UP": Indicates that the application is running and healthy.

## Endpoints
# 1. Create New Expense Limit

## URL: /newLimit
Method: POST
Description: Create a new expense limit for an account.
Request Body:
ExpenseLimitDto: Data transfer object representing the details of the expense limit.
Response: Returns the created expense limit object.


# 2. Get Expense Limits with Transactions for an Account

## URL: /getLimitOnAccount/{accountNumber}
Method: GET
Description: Retrieve all expense limits with associated transactions for a specific account.
Path Parameters:
accountNumber: Integer representing the account number.
Response: Returns a list of expense limits with associated transactions.


# 3. Create New Account

## URL: /newAccount/{accountNumber}
Method: POST
Description: Create a new account with the provided account number.
Path Parameters:
accountNumber: Integer representing the account number.
Response: Returns the created account object.


# 4. Find Account by ID

## URL: /getByIdAccount/{id}
Method: GET
Description: Retrieve an account by its unique ID.
Path Parameters:
id: Long representing the unique ID of the account.
Response: Returns the account object if found.


# 5. Find Transaction by ID

## URL: /findTransaction/{id}
Method: GET
Description: Retrieve a transaction by its unique ID.
Path Parameters:
id: Long representing the unique ID of the transaction.
Response: Returns the transaction object if found.


# 6. Create New Transaction

## URL: /createTransaction
Method: POST
Description: Create a new transaction between accounts.
Request Body:
TransactionDto: Data transfer object representing the details of the transaction.
Response: Returns the created transaction object.




