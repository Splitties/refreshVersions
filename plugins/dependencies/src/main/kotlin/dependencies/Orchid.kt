@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.internal.DependencyGroup
import org.gradle.api.Incubating

@Incubating
/**
 * Build and deploy beautiful documentation sites that grow with you.
 *
 * Official website: [orchid.run](https://orchid.run/)
 *
 * [Documentation](https://orchid.run/wiki/user-manual/getting-started)
 *
 * [Quick-Start](https://orchid.run/wiki/user-manual/getting-started/quickstart)
 *
 * [GitHub releases](https://github.com/orchidhq/Orchid/releases)
 *
 * GitHub page: [orchidhq/Orchid](https://github.com/orchidhq/Orchid)
 */
object Orchid : DependencyGroup(
    group = "io.github.javaeden.orchid",
    rawRule = """
        io.github.javaeden.orchid:*
                           ^^^^^^
    """.trimIndent()
) {

    private const val groupId = "io.github.javaeden.orchid"

    val core by module("OrchidCore")
    val test by module("OrchidTest")

    /**
     * Official webpage: [orchid.run/plugins](https://orchid.run/plugins)
     */
    val plugins = Plugins

    object Plugins : DependencyGroup(group = "io.github.javaeden.orchid") {
        val changelog by module("OrchidChangelog")
        val forms by module("OrchidForms")
        val groovydoc by module("OrchidGroovydoc")
        val javadoc by module("OrchidJavadoc")
        val kotlindoc by module("OrchidKotlindoc")
        val kss by module("OrchidKSS")
        val netlifyCMS by module("OrchidNetlifyCMS")
        val pages by module("OrchidPages")
        val pluginDocs by module("OrchidPluginDocs")
        val posts by module("OrchidPosts")
        val presentations by module("OrchidPresentations")
        val search by module("OrchidSearch")
        val sourceDoc by module("OrchidSourceDoc")
        val swagger by module("OrchidSwagger")
        val swiftdoc by module("OrchidSwiftdoc")
        val taxonomies by module("OrchidTaxonomies")
        val wiki by module("OrchidWiki")
        val asciidoc by module("OrchidAsciidoc")
        val bible by module("OrchidBible")
        val diagrams by module("OrchidDiagrams")
        val syntaxHighlighter by module("OrchidSyntaxHighlighter")
        val writersBlocks by module("OrchidWritersBlocks")
        val azure by module("OrchidAzure")
        val bitbucket by module("OrchidBitbucket")
        val github by module("OrchidGithub")
        val gitlab by module("OrchidGitlab")
        val netlify by module("OrchidNetlify")
    }

    /**
     * Official webpage: [orchid.run/themes](https://orchid.run/themes)
     */
    val themes = Themes

    object Themes : DependencyGroup(group = "io.github.javaeden.orchid") {
        val bsDoc by module("OrchidBsDoc")
        val copper by module("OrchidCopper")
        val editorial by module("OrchidEditorial")
        val futureImperfect by module("OrchidFutureImperfect")
    }

    val bundles = Bundles

    object Bundles : DependencyGroup(group = "io.github.javaeden.orchid") {
        val all by module("OrchidAll")
        val blog by module("OrchidBlog")
        val docs by module("OrchidDocs")
        val languagePack by module("OrchidLanguagePack")
    }
}
