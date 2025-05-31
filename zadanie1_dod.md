### Polecenie do budowania:
```
docker buildx build ^
--platform linux/amd64,linux/arm64 ^
--tag jakubdziem/weather-app:latest^ --push ^
--cache-from=type=registry,ref=jakubdziem/weather-app:cache ^
. 
```
### Wynik działania polecenia build:
``` 
[+] Building 9.6s (28/28) FINISHED                                                                                                                                                    docker-container:testbuilder
 => [internal] load build definition from Dockerfile                                                                                                                                                          0.0s
 => => transferring dockerfile: 1.20kB                                                                                                                                                                        0.0s
 => resolve image config for docker-image://docker.io/docker/dockerfile:1.4                                                                                                                                   1.2s
 => [auth] docker/dockerfile:pull token for registry-1.docker.io                                                                                                                                              0.0s
 => CACHED docker-image://docker.io/docker/dockerfile:1.4@sha256:9ba7531bd80fb0a858632727cf7a112fbfd19b17e94c4e84ced81e24ef1a0dbc                                                                             0.0s
 => => resolve docker.io/docker/dockerfile:1.4@sha256:9ba7531bd80fb0a858632727cf7a112fbfd19b17e94c4e84ced81e24ef1a0dbc                                                                                        0.0s
 => [internal] load .dockerignore                                                                                                                                                                             0.0s
 => => transferring context: 2B                                                                                                                                                                               0.0s
 => [linux/amd64 internal] load metadata for docker.io/library/eclipse-temurin:21-jre                                                                                                                         0.5s
 => [linux/amd64 internal] load metadata for docker.io/library/maven:3.9.6-eclipse-temurin-21                                                                                                                 0.6s
 => [linux/arm64 internal] load metadata for docker.io/library/eclipse-temurin:21-jre                                                                                                                         0.6s
 => [auth] library/eclipse-temurin:pull token for registry-1.docker.io                                                                                                                                        0.0s
 => [auth] library/maven:pull token for registry-1.docker.io                                                                                                                                                  0.0s
 => ERROR importing cache manifest from jakubdziem/weather-app:cache                                                                                                                                          0.7s
 => [linux/amd64 build 1/6] FROM docker.io/library/maven:3.9.6-eclipse-temurin-21@sha256:8d63d4c1902cb12d9e79a70671b18ebe26358cb592561af33ca1808f00d935cb                                                     0.0s
 => => resolve docker.io/library/maven:3.9.6-eclipse-temurin-21@sha256:8d63d4c1902cb12d9e79a70671b18ebe26358cb592561af33ca1808f00d935cb                                                                       0.0s
 => [linux/amd64 stage-1 1/3] FROM docker.io/library/eclipse-temurin:21-jre@sha256:3e08d54ec5a8780227a87ef2458a26c27c4b110e4443d25f055fbe2f96907139                                                           0.0s
 => => resolve docker.io/library/eclipse-temurin:21-jre@sha256:3e08d54ec5a8780227a87ef2458a26c27c4b110e4443d25f055fbe2f96907139                                                                               0.0s
 => [linux/arm64 stage-1 1/3] FROM docker.io/library/eclipse-temurin:21-jre@sha256:3e08d54ec5a8780227a87ef2458a26c27c4b110e4443d25f055fbe2f96907139                                                           0.0s
 => => resolve docker.io/library/eclipse-temurin:21-jre@sha256:3e08d54ec5a8780227a87ef2458a26c27c4b110e4443d25f055fbe2f96907139                                                                               0.0s
 => [internal] load build context                                                                                                                                                                             0.0s
 => => transferring context: 1.63kB                                                                                                                                                                           0.0s
 => [auth] jakubdziem/weather-app:pull token for registry-1.docker.io                                                                                                                                         0.0s
 => CACHED [linux/amd64 stage-1 2/3] WORKDIR /app                                                                                                                                                             0.0s
 => CACHED [linux/amd64 build 2/6] WORKDIR /app                                                                                                                                                               0.0s
 => CACHED [linux/amd64 build 3/6] COPY pom.xml .                                                                                                                                                             0.0s
 => CACHED [linux/amd64 build 4/6] RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline                                                                                                          0.0s
 => CACHED [linux/amd64 build 5/6] COPY src ./src                                                                                                                                                             0.0s
 => CACHED [linux/amd64 build 6/6] RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests                                                                                                      0.0s
 => CACHED [linux/amd64 stage-1 3/3] COPY --from=build /app/target/*.jar app.jar                                                                                                                              0.0s
 => CACHED [linux/arm64 stage-1 2/3] WORKDIR /app                                                                                                                                                             0.0s
 => CACHED [linux/arm64 stage-1 3/3] COPY --from=build /app/target/*.jar app.jar                                                                                                                              0.0s
 => exporting to image                                                                                                                                                                                        6.6s
 => => exporting layers                                                                                                                                                                                       0.0s
 => => exporting manifest sha256:5f6f58deceacf1719907879a1c4b14cc02d216386e8504b2cf88c2e8a4f22d81                                                                                                             0.0s
 => => exporting config sha256:b00126ea41d51f866e218808555bf33c9de237874cea13008668e4ce849b4c07                                                                                                               0.0s
 => => exporting attestation manifest sha256:c3821c591be0cd18a56a14d78fc6cb0e09bf5fca1125e2bd43a8186ff359b73b                                                                                                 0.0s
 => => exporting manifest sha256:473062f54bd50933f79ab217694f0eada20cd0853ee030e0d54552fd33ed7a60                                                                                                             0.0s
 => => exporting config sha256:e38cdc3e306048b405c718a905757347e791b4ad3355d2247b560b9528502850                                                                                                               0.0s
 => => exporting attestation manifest sha256:2e7332dc3df1d9003aad03622985d8db8ebc1a6da3cf987acac97154a900763b                                                                                                 0.0s
 => => exporting manifest list sha256:add09ffa4e23de331518fe6806580b3c8b3fd878f77df46ee0f932560030c6e3                                                                                                        0.0s
 => => pushing layers                                                                                                                                                                                         2.7s
 => => pushing manifest for docker.io/jakubdziem/weather-app:latest@sha256:add09ffa4e23de331518fe6806580b3c8b3fd878f77df46ee0f932560030c6e3                                                                   3.7s
 => [auth] jakubdziem/weather-app:pull,push token for registry-1.docker.io                                                                                                                                    0.0s
 => [auth] jakubdziem/my-java-app:pull jakubdziem/weather-app:pull,push token for registry-1.docker.io
```

### Obraz na docker hub:
``` https://hub.docker.com/r/jakubdziem/weather-app ```


### Informacja o manifeście (uzyskana za pomocą docker buildx imagetools inspect) :
``` 
Name:      docker.io/jakubdziem/weather-app:latest
MediaType: application/vnd.oci.image.index.v1+json
Digest:    sha256:add09ffa4e23de331518fe6806580b3c8b3fd878f77df46ee0f932560030c6e3

Manifests:
  Name:        docker.io/jakubdziem/weather-app:latest@sha256:5f6f58deceacf1719907879a1c4b14cc02d216386e8504b2cf88c2e8a4f22d81
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    linux/amd64

  Name:        docker.io/jakubdziem/weather-app:latest@sha256:473062f54bd50933f79ab217694f0eada20cd0853ee030e0d54552fd33ed7a60
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    linux/arm64

  Name:        docker.io/jakubdziem/weather-app:latest@sha256:c3821c591be0cd18a56a14d78fc6cb0e09bf5fca1125e2bd43a8186ff359b73b
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    unknown/unknown
  Annotations:
    vnd.docker.reference.type:   attestation-manifest
    vnd.docker.reference.digest: sha256:5f6f58deceacf1719907879a1c4b14cc02d216386e8504b2cf88c2e8a4f22d81

  Name:        docker.io/jakubdziem/weather-app:latest@sha256:2e7332dc3df1d9003aad03622985d8db8ebc1a6da3cf987acac97154a900763b
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    unknown/unknown
  Annotations:
    vnd.docker.reference.digest: sha256:473062f54bd50933f79ab217694f0eada20cd0853ee030e0d54552fd33ed7a60
    vnd.docker.reference.type:   attestation-manifest
```

### Część manifestu, gdzie są deklaracje platform sprzętowych:
``` 
  Name:        docker.io/jakubdziem/weather-app:latest@sha256:5f6f58deceacf1719907879a1c4b14cc02d216386e8504b2cf88c2e8a4f22d81
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    linux/amd64

  Name:        docker.io/jakubdziem/weather-app:latest@sha256:473062f54bd50933f79ab217694f0eada20cd0853ee030e0d54552fd33ed7a60
  MediaType:   application/vnd.oci.image.manifest.v1+json
  Platform:    linux/arm64
``` 
### Polecenie do analizy bezpieczeństwa (skanowanie Trivy)
```
trivy image jakubdziem/weather-app:latest
```
```
2025-05-12T21:49:40+02:00       INFO    [vulndb] Need to update DB
2025-05-12T21:49:40+02:00       INFO    [vulndb] Downloading vulnerability DB...
2025-05-12T21:49:40+02:00       INFO    [vulndb] Downloading artifact...        repo="mirror.gcr.io/aquasec/trivy-db:2"
63.60 MiB / 63.60 MiB [---------------------------------------------------------------------] 100.00% 7.82 MiB p/s 8.3s
2025-05-12T21:49:50+02:00       INFO    [vulndb] Artifact successfully downloaded       repo="mirror.gcr.io/aquasec/trivy-db:2"
2025-05-12T21:49:50+02:00       INFO    [vuln] Vulnerability scanning is enabled
2025-05-12T21:49:50+02:00       INFO    [secret] Secret scanning is enabled
2025-05-12T21:49:50+02:00       INFO    [secret] If your scanning is slow, please try '--scanners vuln' to disable secret scanning
2025-05-12T21:49:50+02:00       INFO    [secret] Please see also https://trivy.dev/v0.62/docs/scanner/secret#recommendation for faster secret detection
2025-05-12T21:49:57+02:00       INFO    [javadb] Downloading Java DB...
2025-05-12T21:49:57+02:00       INFO    [javadb] Downloading artifact...        repo="mirror.gcr.io/aquasec/trivy-java-db:1"
734.52 MiB / 734.52 MiB [-------------------------------------------------------------------] 100.00% 6.01 MiB p/s 2m2s
2025-05-12T21:52:01+02:00       INFO    [javadb] Artifact successfully downloaded       repo="mirror.gcr.io/aquasec/trivy-java-db:1"
2025-05-12T21:52:01+02:00       INFO    [javadb] Java DB is cached for 3 days. If you want to update the database more frequently, "trivy clean --java-db" command clears the DB cache.
2025-05-12T21:52:01+02:00       INFO    Detected OS     family="ubuntu" version="24.04"
2025-05-12T21:52:01+02:00       INFO    [ubuntu] Detecting vulnerabilities...   os_version="24.04" pkg_num=136
2025-05-12T21:52:01+02:00       INFO    Number of language-specific files       num=1
2025-05-12T21:52:01+02:00       INFO    [jar] Detecting vulnerabilities...

Report Summary

┌──────────────────────────────────────────────┬────────┬─────────────────┬─────────┐
│                    Target                    │  Type  │ Vulnerabilities │ Secrets │
├──────────────────────────────────────────────┼────────┼─────────────────┼─────────┤
│ jakubdziem/weather-app:latest (ubuntu 24.04) │ ubuntu │       36        │    -    │
├──────────────────────────────────────────────┼────────┼─────────────────┼─────────┤
│ app/app.jar                                  │  jar   │        0        │    -    │
└──────────────────────────────────────────────┴────────┴─────────────────┴─────────┘
Legend:
- '-': Not scanned
- '0': Clean (no security findings detected)


jakubdziem/weather-app:latest (ubuntu 24.04)
============================================
Total: 36 (UNKNOWN: 0, LOW: 20, MEDIUM: 16, HIGH: 0, CRITICAL: 0)
```
