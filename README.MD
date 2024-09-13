# words-api
A simple RESTful API project that can be used for word games or any programs based on the use of the Polish language dictionary.
## Features

- Words of the given length
- Words containing the given sequence
- Words beginning with a given sequence
- Words ending in a given sequence
- Generate any number of words (as long as that many words meet the conditions specified in the request)

## API
#### `GET /api/words/get?length=5`
Returns a word of the length equal to 5.

###### Example Response:
```json
{
  "id": 1,
  "words": [
    "kotek"
  ]
}
```
#### `GET /api/words/get?containing=ad`
Returns a word containing the given sequence.

###### Example response:
 ```json
{
  "id": 2,
  "words": [
    "pozgadywaliśmy"
  ]
}
 ```
#### `GET /api/words/get?starting=piesk`
Returns a word beginning in a given sequence.

###### Example response:
 ```json
{
  "id": 3,
  "words": [
    "pieskowego"
  ]
}
 ```
#### `GET /api/words/get?ending=ami`
Returns a word ending in a given sequence.

###### Example response:
 ```json
{
  "id": 4,
  "words": [
    "wiatrakami"
  ]
}
 ```
#### `GET /api/words/get?amount=3`
Returns multiple words.

###### Example response:
 ```json
{
  "id": 5,
  "words": [
    "niestłoczonego",
    "uderzyła",
    "multimiliarder"
  ]
}
 ```

## Multiple parameters
In order to receive more precise response, multiple parameters can be included in a single request.

### Example requests:
###### 
##### Example request:
```
GET http://localhost:8080/api/words/get?start=a&length=10&amount=4
```
###### Example response:
 ```json
{
  "id": 6,
  "words": [
    "alembikowy",
    "złotolisty",
    "zakończasz",
    "pokołatało"
  ]
}
```
---
##### Example request:
```
GET http://localhost:8080/api/words/get?starting=ko&ending=ami&amount=2
```
###### Example response:
 ```json
{
  "id": 7,
  "words": [
    "kolcobrzuchami",
    "konsultantami"
  ]
}
 ```