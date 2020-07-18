# Change log for refreshVersions

<!--## Version 0.9.5 (2020-07-13)-->
## [Unreleased]

This is a major release that brings surface-level and internal changes,
paving the way for the upcoming 1.0 release.

**The plugin setup/bootstrap has changed**, so check out the updated documentation in [Setting-up.adoc](docs/Setting-up.adoc).

### New features

- Support for `buildscript` dependencies. It now works just like regular dependencies.
- First class support for `buildSrc`
- Support maven repositories with basic authentication (aka. credentials with username and password)

### New dependency constants

- [COIL](https://coil-kt.github.io/coil/), a **Co**routine **I**mage **L**oader for Android.


### Changes
- Make the `refreshVersions` task cancellable during network requests.
- Now, only http 404 and 401 responses from repositories will be silent.
- Server errors (i.e. all but http 404 and 401 responses) are now reported.
- Network failures are now reported.
- There is now a connection timeout (10 seconds per request for now)
- An error is reported if a dependency wasn't found in any of the configured repositories.
- All the searched repositories are now listed if a dependency wasn't found in any of them.
- Only declared repositories are now looked up. (Before, refreshVersions would search all dependencies in all repositories of all modules and their buildscript, regardless of which module was declaring them.)

### Fixes
- Version sorting would crash if a version had a long number in it. This has now been fixed, any length of digit sequence is now supported in versions.
- Fix maven coordinates of several dependency constants

## Previous releases

[Change log](https://github.com/jmfayard/refreshVersions/blob/820a65589ce2e81124789f365a36ababc06bc9e3/CHANGELOG.md)
