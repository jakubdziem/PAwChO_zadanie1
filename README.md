Polecenia do budowania:
### Polecenia do budowania:
``` docker build -t pogoda-app . ```
### Wynik działania polecenia build:
``` 
[+] Building 45.1s (16/16) FINISHED                                                                                                                                                           docker:desktop-linux
 => [internal] load build definition from Dockerfile                                                                                                                                                          0.1s
 => => transferring dockerfile: 1.03kB                                                                                                                                                                        0.1s
 => [internal] load metadata for docker.io/library/maven:3.9.6-eclipse-temurin-21                                                                                                                             1.3s
 => [internal] load metadata for docker.io/library/eclipse-temurin:21-jre                                                                                                                                     1.3s
 => [auth] library/maven:pull token for registry-1.docker.io                                                                                                                                                  0.0s
 => [auth] library/eclipse-temurin:pull token for registry-1.docker.io                                                                                                                                        0.0s
 => [internal] load .dockerignore                                                                                                                                                                             0.0s
 => => transferring context: 2B                                                                                                                                                                               0.0s
 => [build 1/5] FROM docker.io/library/maven:3.9.6-eclipse-temurin-21@sha256:8d63d4c1902cb12d9e79a70671b18ebe26358cb592561af33ca1808f00d935cb                                                                 0.0s
 => [stage-1 1/3] FROM docker.io/library/eclipse-temurin:21-jre@sha256:3e08d54ec5a8780227a87ef2458a26c27c4b110e4443d25f055fbe2f96907139                                                                       0.0s
 => [internal] load build context                                                                                                                                                                             0.1s
 => => transferring context: 2.89kB                                                                                                                                                                           0.0s
 => CACHED [build 2/5] WORKDIR /app                                                                                                                                                                           0.0s
 => CACHED [build 3/5] COPY pom.xml .                                                                                                                                                                         0.0s
 => [build 4/5] COPY src ./src                                                                                                                                                                                0.1s
 => [build 5/5] RUN mvn clean package -DskipTests                                                                                                                                                            41.5s
 => CACHED [stage-1 2/3] WORKDIR /app                                                                                                                                                                         0.0s
 => [stage-1 3/3] COPY --from=build /app/target/*.jar app.jar                                                                                                                                                 0.1s
 => exporting to image                                                                                                                                                                                        0.2s
 => => exporting layers                                                                                                                                                                                       0.1s
 => => writing image sha256:14df41ea081a52bcbe7799f4b881696f6d14c598392e5b6b17ecfb54ac4ac874                                                                                                                  0.0s
 => => naming to docker.io/library/pogoda-app
```
### Polecenie do uruchomienia:
``` docker run -e WEATHER_API_KEY=api_key -d -p 8080:8080 --name pogoda pogoda-app ```
### Polecenie potwierdzające działanie kontenera i poprawne funkcjonowanie opracowanej aplikacji
``` docker ps ```
```
CONTAINER ID   IMAGE                           COMMAND                  CREATED          STATUS                    PORTS                    NAMES
5a7ffc0f67fc   pogoda-app                      "java -jar app.jar"      28 seconds ago   Up 27 seconds (healthy)   0.0.0.0:8080->8080/tcp   pogoda
```
### Polecenie do wyświetlenia logów
``` docker logs pogoda ```
### Wycinek istotnych logów potwierdzających uruchomienie aplikacji:
```
2025-05-12T18:11:35.878Z  INFO 1 --- [WeatherAppChmura] [           main] c.d.w.WeatherAppChmuraApplication        : Czas startu: 2025-05-12T18:11:35.877911928
2025-05-12T18:11:35.878Z  INFO 1 --- [WeatherAppChmura] [           main] c.d.w.WeatherAppChmuraApplication        : Jakub Dziem
2025-05-12T18:11:35.878Z  INFO 1 --- [WeatherAppChmura] [           main] c.d.w.WeatherAppChmuraApplication        : Application is running on port: 8080
```
### Polecenie do sprawdzenia liczby warstw obrazu - tych warstw jest 7:
``` docker image inspect pogoda-app --format='{{.RootFS.Layers}}' ```
```
'[sha256:8901a649dd5a9284fa6206a08f3ba3b5a12fddbfd2f82c880e68cdb699d98bfb
sha256:c3f00a23a248e4f2c1dc00753e5923f9434370032a84bc54c492842a837de486
sha256:cb4585640237da55f9d185ffa81376637cdbb6ab9d12855f4db29802e666b5c7
sha256:8fb9457d1ca07ab4c72c32601738dd688c8bf3f351528471e7d4a789554716e6
sha256:34617a0900fee7aebdd4c65b862dd377afbdc6d741b47341548c40c0531b54f3
sha256:4b7c1a68f9ad4f16788af4031bb2daa5232a59a905950dddd29788b79d97843f
sha256:0af0ae3ef29b6d86c023311f55b1e41e293a30cd0f6259d8dde7c1618ce0b205]'
```
### Polecenie do sprawdzenia rozmiaru obrazu:
``` docker image ls pogoda-app ```
```
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
pogoda-app   latest    14df41ea081a   7 minutes ago   309MB
```
