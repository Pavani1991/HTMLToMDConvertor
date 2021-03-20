# HTMLToMDConvertor
## About the application
This application takes a .html file as input, parses `table` content and generates a .md file in the given path by the user.
this application also supports italic, bold and italic-bold formats.

## Dependencies:
Maven and Java has to be installed

## instructions to run the application
Run below command to generate the jar:
### `mvn clean install`

To parse the file from HTML to MD run below command: 
### `java -jar target/HTMLToMIDConvertor-0.0.1-SNAPSHOT.jar src/main/resources resource.html`

This will generate the new file in resource.md in src/main/resources

To run only Test files run command:
### `mvn surefire:test`
