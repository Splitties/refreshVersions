# Dependency notations replacements and removals

Must be done at init time (in `settingsEvaluated { … }`) because it shall happen before build files are compiled and evaluated.

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

#### ✅🧐 Required data:

An ordered list (oldest to newest) with the following data for all removals since last upgrade:
- dependency notation
- corresponding dependency notation
- reason of removal
  - Usually deprecation, with direct replacement or not.
  - coordinates of replacement, if any
  - info url, if any
  - actionable info for a TODO comment?

#### ✅ Removed dependency notations storage/file-format

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

#### Tests update

✅ Removed dependency notations shall be removed from the `dependencies-mapping-validated.txt` file to avoid clash when the
maven coordinates stay the same, but the dependency notation moves.

✅ The list of removed dependency notations should be prefilled in the WIP section.

✅ We need a test that fails when there's a WIP section.

✅ We need to force tests to run (and pass) before any publishing task happens.

✅ Fail the build if there's a non snapshot release without an entry in the release to revision mapping

#### Updating the removals revisions

✅ Fill `version-to-removals-revision-mapping.txt` for releases.
✅ Write (SNAPSHOT) or remove (release) a generated resource for the removals revision number.
✅ Edit (all) version parsing AND writing for `versions.properties` to handle removals revision for snapshots.

#### Migration operation:

Keep in mind it shall:
- ✅ Honor the `withVersion(…)`, `withoutVersion()` and others when rewriting.
- 🙏 Not be able to break the build.
- ⏳ Let the user know about the rewrite via a warning log.

1. ✅ Find where the updating code should be.
2. ✅ Find what the `bootstrapRefreshVersionsCore` function will need to support replacement of removed dependency notations.
3. ✅ Get the last removals revision from `versions.properties` if it was a snapshot, or from the mapping if it was a mapped release, or default to 0.
4. ✅ Find all `build.gradle[.kts]` files efficiently. Don't rely on include because it might be dynamic, and we don't want to track migration on a per-module basis as it'd increase the complexity on user-side.
5. ✅ Update the version in `versions.properties`, including the revision if current version is a snapshot.

#### ✅ Example results

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
