# RSA encryption & decription

> Marc Veens - 500758673

## Encryption project
An executable which requires a **pq** and a **message** as parameters. It returns the RSA encrypted message. 

### Generate .jar
`mvn package`

This will execute a couple of tests and create a .jar file which you can now execute.

### Execution command:
`java -jar target\Encrypt-1.0.jar 29747 "Trust because you are willing to accept the risk, not because it's safe or certain. ~Anonymous"`

This will encrypt the message based on the given **pq** and **message**. 

### Duration
It only takes the application 0.0025889 seconds to find **p** and **q**.