# Before you contribute

Please, in order to not waste your efforts, your time, and our time, before working on any contribution, take a little time to read the relevant parts of the contributing guide.

## Code of Conduct

First, we have a very simple code of conduct that you need to follow while interacting on this repo:

Whenever you are about to post or commit, ask yourself "Would an idiot do that?", and if they would, do not do that thing.

https://youtu.be/KFwUcEwD4l4

[![](http://www.theofficequotes.com/screenshots/84521e6a7a4ea0cf2057070b8fa200ba.jpg)](https://youtu.be/KFwUcEwD4l4)

---

Use this Code of Conduct as you like in your own projects and organizations.

### Q & A

Q: What about breathing?

A: The behavior must be exclusive to idiocy.

## General considerations

_Note: If you only want to improve the docs, you can skip the rest of this page and jump right on [the documentation specific contributing guide](improving-docs.md)._

### Reliability

refreshVersions is used by a lot of different projects, and it shall be as reliable as possible, especially since it takes part in the build process of these projects and could break it in case of a bug.

As a consequence, we strive to keep its quality as high as we can, and we actively avoid regressions and unreliable code.

Sometimes, that might mean not adding a feature because of its impact, or the risks for end projects.

Please, when submitting a feature request or a contribution, take some time to think about the implications on projects other than yours.

### Compatibility

Also, refreshVersions being a long term project, we do our best to not break the builds of our users on upgrade, so for any new feature or change, we shall have way to introduce it in a compatible way.

Note that as a Gradle plugin, we have the ability to automate migration tasks if needed, but it cannot always apply.

## Submitting issues

If you want to report an issue or submit a feature request, see [this guide](submitting-issues.md) to help us address it as quickly as possible.

## Submitting PRs

_Unless it's the special case of minor documentation improvement, please, do not start working on a PR before you're assigned via the corresponding issue. Not doing so might lead to overlapping efforts, and it's less likely we would accept it._

If you have been assigned an issue, if you are a maintainer of this project or if you just want to play with the project on your own, first, see how to [set up the dev environment properly](submitting-prs/dev-env.md), as the IDE will not get it right on its own.

Once you've ensured this is set up correctly, you'll need to follow [our development process](submitting-prs/dev-process.md). It has the information you need to make a successful contribution, regardless of the type of change.
