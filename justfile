## See https://github.com/casey/just
## Install with $ brew install just
run:
    cd plugins && ./gradlew check publishToMavenLocal
    cd sample-kotlin && ./gradlew refreshVersions
    cd sample-groovy && ./gradlew refreshVersions
    cd sample-multi-modules  && ./gradlew refreshVersions
    cd sample-multi-modules  && ./gradlew buildSrcVersions
docs:
    mkdocs serve &
ngrok: docs
    which ngroke || echo "Error: install ngrok with $ brew install ngrok"
    ngrok http 80
site:
    open https://jmfayard.github.io/refreshVersions/
github:
    open https://github.com/jmfayard/refreshVersions/
todos:
    open https://github.com/jmfayard/refreshVersions/projects/4
issues:
    open https://github.com/jmfayard/refreshVersions/issues
prs:
    open https://github.com/jmfayard/refreshVersions/pulls
urls: site github todos issues prs
    echo "URLs opened"
