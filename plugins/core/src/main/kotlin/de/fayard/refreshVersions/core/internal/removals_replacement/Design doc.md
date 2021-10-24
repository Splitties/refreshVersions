# Dependency notations replacements and removals

Must be done at init time (in `settingsEvaluated { … }`) because it shall happen before build files are compiled and evaluated.

## Should the code be in refreshVersions or in refreshVersionsCore?

Before answering here are the things to check:

### Is the code parsing code all in the core module?

Yes

### Are there any objections to have it in the core module?

I don't think so.

## How to detect removal?

Tests checking for no removal would instead populate the new file for removed dependency notations.

### Where should these be put it given versioning constraints?

Do we need to parse our own version?

Or should keep something analogue to Android app's `versionCode`?
Can this be fully be automated?
If the VersionsPropertiesModel doesn't keep that value, can we use an internal map for the matching versions, when there's a match, in a way that would also work for snapshots, or disregard unexpected suffixes?

## How to detect

TK

## Implementation path

### Any migration to make?

In `bootstrapRefreshVersionsCore`, figure out efficiently if refreshVersions has just been updated.

### Doing the migration

#### Required data:

An ordered list (oldest to newest) with the following data for all removals since last upgrade:
- dependency notation
- corresponding dependency notation
- reason of removal
  - Usually deprecation, with direct replacement or not.
  - coordinates of replacement, if any
  - info url, if any
  - actionable info for a TODO comment?

#### Removed dependency notations storage/file-format

```markdown
## Revision 1

~~AndroidX.wear.watchFace.client~~
//
moved:[androidx.wear.watchface..watchface-client]
id:[androidx.wear..wear-watchface-client]

## Revision 2
~~SomeGroup.something~~
id:[com.somegroup..somegroup-something]
```

TK

#### Migration operation:

Keep in mind it shall:
- Honor the `withVersion(…)`, `withoutVersion()` and others when rewriting.
- Not be able to break the build.
- Let the user know about the rewrite via a warning log.

#### Example results

```kotlin
dependencies {
    // SomeGroup.something has been replaced,
    // following the deprecation of the com.somegroup:somegroup-something artifact
    //FIXME: Replace with the new dependency and remove these comments.
    // d
    api("com.somegroup:somegroup-something:_")
//moved:SomeGroup.somethingElse)
}
```
