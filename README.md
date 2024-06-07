### testcontainers-sftp

Authenticate on a SFTP server with ssh keys using testcontainers-java.

Generate the keys on your machine:

```bash
ssh-keygen -t ed25519 -f ssh_host_ed25519_key
ssh-keygen -t rsa -b 4096 -f ssh_host_rsa_key
```

And run:
```bash
./gradlew test
```


Alternatively you can run the SFTP container on the docker:

```bash
docker compose up
```

And then execute the main function:

```bash
./gradlew bootRun
```
