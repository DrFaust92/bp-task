#bp-task


##Usage:

Main takes 3 String paramaters:
* host url
* port
* log generator path (ive used the windows one,
 havent tested the other ones)
 
 ###Getting List of all distinct words:
 {host}:{port}/bp-task/ (GET request) - will return json array of words
 
 ###Getting Count for specific words:
 {host}:{port}/bp-task/{word} (GET request) - will an a count for specific word
 



##things to improve:
* Better logging and error handling 
(currently parsing errors are just skipped/swallowed)
* parsing is very simple, if log/json doc will be of a more complex structure
then parsing should be written explicitly and not left to json4s
* not really sure how this would really scale when really running this app in multiple threads
at the very least synchronize the access to the wordOccur hashmap