# KEY-VALUE DATA STORE

#It is a Java code that store key and value in the Json file and perform CRD operation.

1. Get key and value from the user and store into the hashtable. (we use hashtable becase it is thread-safe)

2. Convert the hashtable into JSON object and write the JSON object to  the file.

3. For Time-To-Live property we get the time limit from the user.

4. If the Time-To-Live property for the key is expired it no longer available for read and delete operation.

5. The write operation happens if the file size is less than 1GB.

Thus, the key and value will write in the file as a json object and read and delete operation perform if the key is alive.


#Code compelexity

If the number od input is 'n', then the code runs in O(n).

NOTE: The JSON object work when we use JSON.simple jar. we need to include this jar file in our external libraries.
NOTE: we can download the jar using the link: https://repo.maven.apache.org/maven2/com/googlecode/json-simple/json-simple/1.1/json-simple-1.1.jar
