## Sprawozdanie zadanie 2
### Opis poszczególnych kroków (jobs):

### Workflow jest uruchamiany:

* automatycznie po wypchnięciu nowego **tagu wersji** w formacie `v*`, np. `v1.0.0`,
* manualnie za pomocą `workflow_dispatch`.

### Checkout code

```yaml
- name: Checkout code
  uses: actions/checkout@v4
```

Pobiera aktualną wersję kodu źródłowego repozytorium.

---

### Set up QEMU for multi-architecture support

```yaml
- name: Set up QEMU for multi-architecture support
  uses: docker/setup-qemu-action@v3
```

Pozwala na emulację różnych architektur procesorów (np. ARM64) podczas budowania obrazów.

---

### Set up Docker Buildx

```yaml
- name: Set up Docker Buildx
  uses: docker/setup-buildx-action@v3
```

Włącza `buildx`, potrzebny do budowy wieloarchitekturnej.

---

### Extract Docker metadata

```yaml
- name: Extract Docker metadata
  id: meta
  uses: docker/metadata-action@v5
  with:
    images: ghcr.io/${{ github.repository_owner }}/pawcho_zadanie1
    flavor: latest=false
    tags: |
      type=semver,pattern={{version}}
      type=sha,format=short,prefix=sha-
```

Ustala tagi dla obrazu:

* `1.0.0` (na podstawie semver),
* `sha-abc1234` (na podstawie SHA commita).

**Uzasadnienie:** tag semantyczny `semver` zapewnia odniesienie do wersji, `sha` jest odniesieniem do konkretnego commita.

---

### Log in to GitHub Container Registry

```yaml
- name: Log in to GitHub Container Registry
  uses: docker/login-action@v3
```

Logowanie do GHCR w celu publikacji obrazu.

---

### Log in to Docker Hub

```yaml
- name: Log in to Docker Hub
  uses: docker/login-action@v3
```

Dodatkowe logowanie – używane do cache'owania.

---

### Build image for CVE scanning

```yaml
- name: Build image for CVE scanning
  uses: docker/build-push-action@v5
  with:
    context: .
    platforms: linux/amd64
    load: true
    tags: ${{ steps.meta.outputs.tags }}
```

Buduje obraz lokalnie dla `linux/amd64` – ten wariant obrazu zostanie zeksanowany pod kątem podatności.

---

### Push multi-architecture image to GHCR

```yaml
- name: Push multi-architecture image to GHCR
  uses: docker/build-push-action@v5
  with:
    context: .
    platforms: linux/amd64,linux/arm64
    push: true
    cache-from: type=registry,ref=${{ vars.DOCKERHUB_USERNAME }}/pawcho_zadanie1:cache
    cache-to: type=registry,ref=${{ vars.DOCKERHUB_USERNAME }}/pawcho_zadanie1:cache,mode=max
    tags: ${{ steps.meta.outputs.tags }}
```

Buduje obraz na dwie platformy i wypycha go do GHCR z pełnym cache.

---

### Perform CVE scan using Docker Scout

```yaml
- name: Perform CVE scan using Docker Scout
  uses: docker/scout-action@v1
  with:
    command: cves
    image: ghcr.io/${{ github.repository_owner }}/pawcho_zadanie1:${{ steps.meta.outputs.version }}
    only-severities: critical,high
    exit-code: true
```

Przeprowadza analizę bezpieczeństwa obrazu kontenera. Skupia się wyłącznie na podatnościach o wysokim i krytycznym poziomie zagrożenia. W przypadku ich wykrycia, workflow zakończy się błędem.

## Uzasadnienie użycia Docker Scout

Wybrano **Docker Scout**, ponieważ:

* to oficjalne narzędzie Docker Inc. do skanowania CVE,
* można go łatwo zintegrować z GitHub Actions i CLI Dockera,
* umożliwia filtrowanie podatności po poziomie zagrożenia (`critical`, `high`),
* (`exit-code: true`) pozwala zablokować wypchnięcie obrazu z lukami bezpieczeństwa.

---

[Obowiązkowe sprawozdanie zadanie 1](https://github.com/jakubdziem/PAwChO_zadanie1/blob/master/zadanie1.md)
<br>
[Nieobowiązkowe sprawozdanie zadanie 1](https://github.com/jakubdziem/PAwChO_zadanie1/blob/master/zadanie1_dod.md)
<br>
