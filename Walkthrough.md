# Benjamin Smull: Written Walkthrough
## 1. Project Overview: NASA Image Guesser
This Java project displays an image off of the NASA API. The user will select either a hard or easy difficulty. The hard difficulty will allow the user to pick a category of their choice, and they will have to guess the name of the randomly selected image that is displayed. If the easy difficulty is chosen, the user will have to guess the name of an image randomly selected from a pre created array of easy words. The user will keep guessing names of images until they get it wrong. The user loses the game by running out of guesses. The guesses are based on the length of the name of the image, and after each incorrect guess, one letter of the name is revealed to the user. 

## 2. Code Breakdown
### API.java
- fetches all of the data of the images and descriptions off of the NASA API, which is used in the program.
        Methods:
```java
getData(string endpoint)
```
- Makes an HTTP POST request to the API
- calls the API
- Connects the program to an API using an endpoint
- returns the JSON string for the user to use
```java
getWords(ArrayList<String> names)
```
- Checks if the size of the names ArrayList is greater than 1
- if the size is greater than 1 it will generate a random number from 0, to the size of the array
- it will then make a get request to the API to generate the data of the random element in names
- it will then access the "collection" JSON object, and the "items" JSON array in order to access the "links" and the "data" arrays
- another random number is generated to select a random JSON object from what the API returned
- the link and date created is accessed
- it returns an Image object with the name, url and date created
- if the size of "names" is not greater than 1, it will access the only word in names
- it will then make a get request to the API to generate the data of the one element in names
- it will then access the "collection" JSON object, and the "items" JSON array in order to access the "links" and the "data" arrays
- a random number is generated to select a random JSON object from what the API returned
- it will then access the keywords array in the JSON object
- it will check to see if the size of the array is empty. If not, it will iterate through the array of keywords
- if the keyword contains a space, doesn't contain any special characters, is longer than 2, isn't named Washington, doesn't contain planet, doesn't contain "space", "star", "EWTS", or "nasa", it will save the keyword in a String object
- it accesses the url and date of the image
-returns a new Image object that contains the keyword, the url and the date
```java
saveData(String data)
```
- saves the data into a text file
### App.java
- The runner class that will run the entire program
        Methods:
```java
main( String[] args )
```
- Generates a pre-created ArrayList of words, used in the "easy" mode of the program
- Creates a JFrame and will display the image
- Prints out the choice of difficulties and recieves the users answer
- if the user answered "easy", it will print out the image onto a GUI and run the game program
- after the game program is run, it will ask the user if they would like to keep playing, and it will recieve the answer
- the game program will keep running until the user answers "no"
- Once the user answers "no", their maximum streak will be displayed, and the program will terminate
- if the user enters "hard", the user will be prompted to enter a category, and the game program will run
- the image is then displayed of the randomly selected image from the category
- after the game program is run, it will ask the user if they would like to keep playing, and it will recieve the answer
- the game program will keep running until the user answers "no"
- Once the user answers "no", their maximum streak will be displayed, and the program will terminate
- if the user enters neither "easy" nor "hard" the user will be told to try again
### Game.java
- This class contains all of the game logic to the program
        Methods:
```java
Game(Image image)
```
- Constructs a game object using an image object
- this creates a new ArrayList and fill it with "_ "
- it will display the date of the picture, the url of the picture, and the name of the picture, for testing
```java
getMax()
```
- returns the max number of correct guesses in a row
```java
getNumCorrect()
```
- returns the number of correct guesses
```java
setNumCorrect()
```
- sets the numCorrect variable to a new value
```java
run()
```
- displays the statistics of the game, such as the average guesses per game, the remaining number of guesses, the user's streak, and the max streak.
- checks if the name of the image contains galaxy. If it does, it asks "what galaxy is this?", if not, it asks "what space structure is this?"
- prints out the contents of the array list, wordArray, which contains the spaces where each letter wwill be revealed
- prompts the user to guess, and recieves the input as a guess
- if the users guess matches the name, it will update the number of correct guesses the user has
- if the user guesses incorrectly, it will reveal one letter of the name of the image
- if the user runs out of guesses, it will display the user's max streak
```java
guess(String guess)
```
- increases the total number of guesses
- checks to see if the guess parameter matches the name of the image
- returns true if the two match, returns false otherwise
```java
average()
```
- returns the rounded average of the total number of guesses to the total number of games
### Images.java
- This method contains all of the necessary information for the images
        Methods:
```java
Images(String name, String url, String date)
```
- creates an image object
- sets the name, url and date to the corresponding instance variable
```java
getName()
```
- returns the name of the image
```java
getUrl()
```
- returns the url of the image
```java
getDate()
```
- returns the date of the image
### Features Implemented (Rubric Aligned)
✔ Base Project (88%)
- outputs 3 meaningful pieces of information (date, name and url)
- has more than 3 classes (4)
- connects to and calls an API
✔ Basic Statistics or Machine Learning (6%)
- calculates a stasticial method (average number of guesses)
✔ Filter/Sort Data (2%)
- I allowed the user to choose a category, and created a search using the input
✔ Written Walkthrough (+5%)
