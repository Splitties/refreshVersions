# Improving docs

## Docs are powered by MKDocs

The documentation of refreshVersions is published on a website accessible via this url: [{{link.site}}](../index.md).

It is generated with MKDocs from Markdown files.
The variant of Markdown used in MKDocs has useful additional features that we use:
- "Variables" interpolation, useful for long urls repeated in the doc.
- Automatic generation of table of contents based on headings.

## Where is the doc source

A bunch of things are defined in the `mkdocs.yml` file (located at the root of the repo):

- The structure of the documentation website is under the `nav` key.
- Under the `extra` key, we have common text snippets (example usage: `{{ '{{' }}link.github{{ '}}' }}`)
- Website metadata is defined at the top of the file (you probably don't need to touch it)
- Theme and MKDocs configuration

The content of the documentation itself is in the `docs` directory where you can find Markdown files and images in the `img` directory.

## Edit the doc

Before starting any work, **create a branch in your fork** that is **based on the `release` branch**.

Please, avoid non-improvements such as adding typos, or spelling errors.

Also, try to keep the language simple, non-ambiguous, and explicit enough, so all of our users can understand it. 🙏

## View your changes

Run one of the following commands, to ensure you have the tools to build the website locally:

- `pip3 install -r docs/requirements.txt`
- `pip install -r docs/requirements.txt`

_Note: on Linux and macOS, `pip3` is most likely already installed. On Windows, you might want to install anaconda/Python to get it._

Start the local server using the `mkdocs serve` command.

You'll quickly see a link appear in the console to view it in your default browser.

Any saved changes will refresh the page automatically if the right one is open in the browser.

Once you're done, you can close the tab, and quit the local server (<kbd>ctrl</kbd> + <kbd>C</kbd>).

## Submit your changes

Once you're done, please commit your changes with a clear title and message that mentions what changed in simple terms, then submit the pull request on GitHub.
