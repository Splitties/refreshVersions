## See https://github.com/casey/just
## Install with $ brew install just
plugins:
    cd plugins && ./gradlew check publishToMavenLocal
run: plugins
    cd sample-kotlin && ./gradlew refreshVersions
    cd sample-groovy && ./gradlew refreshVersions
    cd sample-multi-modules  && ./gradlew refreshVersions
    cd sample-multi-modules  && ./gradlew buildSrcVersions
    cd sample-android && ./gradlew refreshVersions
cleanup: plugins
    cd sample-kotlin && ./gradlew refreshVersionsCleanup
    cd sample-groovy && ./gradlew refreshVersionsCleanup
    cd sample-multi-modules  && ./gradlew refreshVersionsCleanup
    cd sample-android && ./gradlew refreshVersionsCleanup
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
