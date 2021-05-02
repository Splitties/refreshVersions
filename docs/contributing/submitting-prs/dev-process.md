# Development process

_Note: If the contribution you want to make is updating, adding or removing dependency notations, you can skip the rest of this document and go directly to [the specific guide](dependency-notations-updates.md)._

## Better safe than sorry

### Fixing bugs

Please, make sure your bug fix doesn't introduce new bugs before submitting.

![We find the bug. (Hey!) We fix the bug. (Yay!) Now we have two bugs. (What?) Now we have three bugs. (Oh no!)](https://i.redd.it/b1b640z9b3c21.jpg)

### Changing behavior of existing features

Behavior changes might introduce bugs, and these might not be caught until users face them.

Consequently, whenever possible, we keep the old implementation and add the new one side by side, with a feature flag controlling which one is being used.

That allows our users to switch-off changes that might interfere with their project setup.

For changes that break the API or setup of some or all projects, we provide automatic or semi-automatic migration on upgrade. If it's needed, we will let you know in the corresponding issue.

### New features

Unless impossible, we add every new feature behind a feature-flag, so our users can disable it if it causes any problem in their project.

## Send a draft early

![I don't always submit PRs, but when I do, I start by submitting shitty drafts.](../../img/i-dont-always-submit-prs.jpg)

We do not want you to spend a lot of time on a change only to realize it didn't do in the right direction for us to be able to integrate it in the project.

Consequently, we are asking you, especially for new features, to send us an early draft PR that we can give early feedback on before you complete your contribution.

## Check nothing is broken

Before committing anything, please check nothing is broken. 🙏

Ways to do it are running the `check` task against the `plugins` project, as well as ensuring the sample projects still build, and behave properly in the IDE with your changes.

## Use proper commit messages

If you don't, we will squash your PR into one commit on merge.

## You can reach for help

Feel free to ask for any help in the PR comments or in the corresponding issue.

## Submitting the PR

In the text of the PR, make sure to reference the corresponding issue properly.

### Examples:

For bug fixes:
> Fixes #456

For new features or changes:
> Resolves #123
