#bp-task



##things to improve:
* Better logging and error handling 
(currently parsing errors are just skipped/swallowed)
* parsing is very simple, if log/json doc will be of a more complex structure
then parsing should be written explicitly and not left to json4s
* not really sure how this would really scale when really running this app in multiple threads
at the very least synchronize the access to the wordOccur hashmap