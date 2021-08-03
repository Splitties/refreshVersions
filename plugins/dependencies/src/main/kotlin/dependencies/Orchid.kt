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

    val core get() = module("OrchidCore")
    val test get() = module("OrchidTest")

    /**
     * Official webpage: [orchid.run/plugins](https://orchid.run/plugins)
     */
    val plugins = Plugins

    object Plugins : DependencyGroup(group = "io.github.javaeden.orchid") {
        val changelog get() = module("OrchidChangelog")
        val forms get() = module("OrchidForms")
        val groovydoc get() = module("OrchidGroovydoc")
        val javadoc get() = module("OrchidJavadoc")
        val kotlindoc get() = module("OrchidKotlindoc")
        val kss get() = module("OrchidKSS")
        val netlifyCMS get() = module("OrchidNetlifyCMS")
        val pages get() = module("OrchidPages")
        val pluginDocs get() = module("OrchidPluginDocs")
        val posts get() = module("OrchidPosts")
        val presentations get() = module("OrchidPresentations")
        val search get() = module("OrchidSearch")
        val sourceDoc get() = module("OrchidSourceDoc")
        val swagger get() = module("OrchidSwagger")
        val swiftdoc get() = module("OrchidSwiftdoc")
        val taxonomies get() = module("OrchidTaxonomies")
        val wiki get() = module("OrchidWiki")
        val asciidoc get() = module("OrchidAsciidoc")
        val bible get() = module("OrchidBible")
        val diagrams get() = module("OrchidDiagrams")
        val syntaxHighlighter get() = module("OrchidSyntaxHighlighter")
        val writersBlocks get() = module("OrchidWritersBlocks")
        val azure get() = module("OrchidAzure")
        val bitbucket get() = module("OrchidBitbucket")
        val github get() = module("OrchidGithub")
        val gitlab get() = module("OrchidGitlab")
        val netlify get() = module("OrchidNetlify")
    }

    /**
     * Official webpage: [orchid.run/themes](https://orchid.run/themes)
     */
    val themes = Themes

    object Themes : DependencyGroup(group = "io.github.javaeden.orchid") {
        val bsDoc get() = module("OrchidBsDoc")
        val copper get() = module("OrchidCopper")
        val editorial get() = module("OrchidEditorial")
        val futureImperfect get() = module("OrchidFutureImperfect")
    }

    val bundles = Bundles

    object Bundles : DependencyGroup(group = "io.github.javaeden.orchid") {
        val all get() = module("OrchidAll")
        val blog get() = module("OrchidBlog")
        val docs get() = module("OrchidDocs")
        val languagePack get() = module("OrchidLanguagePack")
    }
}
