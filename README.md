# RSA encryption & decryption

> Marc Veens - 500758673

This project was build in order to encrypt and decrypt a message based on **only** a public RSA key. This means that it is able to calculate the private key on it's own. 

The bottlenecks of finding p and q is that you have to loop over a large amount of numbers in order to find them. With the current values provided, it only tok 0.0005668 seconds but with bigger values, which are used for safe encryption, a regular laptop would probably take months or years in order to find the p and q. 

### Generate .jar
`mvn package`

This will execute a couple of tests and create 2 .jar files which you can now execute.

The tests that are executed are:
- `findPandQ`
- `findE`
- `findD`
- `rsaEncryption`
- `rsaDecryption`

## Encryption
An executable which requires a **pq** and a **message** as parameters. It returns the RSA encrypted message.

### Execution command:
`java -jar target\encrypt.jar 29747 "Trust because you are willing to accept the risk, not because it's safe or certain. ~Anonymous"`

This will encrypt the message based on the given **pq** and **message**. 

### Duration
It only takes the application 0.0025889 seconds to encrypt the given message.

## Decryption
An executable which requires a **pq** and a RSA encrypted **message** as parameters. It returns the RSA decrypted message. 

### Execution command:
`java -jar target\decrypt.jar 26219 "1818,4599,23536,1354,22073,4599,20331,23536,13351,4932,4599,593,20331,6872,14720,16661,20331,4932,21578,14720,4932,20331,12684,23536,4932,9462,5585,14720,4932,9462,23536,593,20331,20962,23536,4599,6872,593,4620,4932,20331,22073,14720,6872,4932,12731,20331,3526,4599,22073,22073,24933,20331,593,4599,9462,4932,21578,4599,13422,20331,20962,23536,4599,6872,20331,24185,14720,4932,21578,9462,593,9893,20331,25022,20331,4932,21578,14720,4932,4620,6872,20331,25340,21578,16661,20331,25340,4599,20331,13422,4599,7009,23536,12684,12684,4599,593,20962,20331,9462,4932,20331,20962,14720,9462,22073,16661,12731,20331,22093,14134,9462,9893,20331,14134,9462,9893,22073,14720,13422"`

This will decrypt the message based on the given **pq** and **message**. 


### Duration
It takes the application 0.7818371 seconds to decrypt the given message.