@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

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
object Orchid {

    private const val groupId = "io.github.javaeden.orchid"

    const val core = "$groupId:OrchidCore:_"
    const val test = "$groupId:OrchidTest:_"

    /**
     * Official webpage: [orchid.run/plugins](https://orchid.run/plugins)
     */
    val plugins = Plugins
    object Plugins : IsNotADependency {
        const val changelog         = "$groupId:OrchidChangelog:_"
        const val forms             = "$groupId:OrchidForms:_"
        const val groovydoc         = "$groupId:OrchidGroovydoc:_"
        const val javadoc           = "$groupId:OrchidJavadoc:_"
        const val kotlindoc         = "$groupId:OrchidKotlindoc:_"
        const val kss               = "$groupId:OrchidKSS:_"
        const val netlifyCMS        = "$groupId:OrchidNetlifyCMS:_"
        const val pages             = "$groupId:OrchidPages:_"
        const val pluginDocs        = "$groupId:OrchidPluginDocs:_"
        const val posts             = "$groupId:OrchidPosts:_"
        const val presentations     = "$groupId:OrchidPresentations:_"
        const val search            = "$groupId:OrchidSearch:_"
        const val sourceDoc         = "$groupId:OrchidSourceDoc:_"
        const val swagger           = "$groupId:OrchidSwagger:_"
        const val swiftdoc          = "$groupId:OrchidSwiftdoc:_"
        const val taxonomies        = "$groupId:OrchidTaxonomies:_"
        const val wiki              = "$groupId:OrchidWiki:_"
        const val asciidoc          = "$groupId:OrchidAsciidoc:_"
        const val bible             = "$groupId:OrchidBible:_"
        const val diagrams          = "$groupId:OrchidDiagrams:_"
        const val syntaxHighlighter = "$groupId:OrchidSyntaxHighlighter:_"
        const val writersBlocks     = "$groupId:OrchidWritersBlocks:_"
        const val azure             = "$groupId:OrchidAzure:_"
        const val bitbucket         = "$groupId:OrchidBitbucket:_"
        const val github            = "$groupId:OrchidGithub:_"
        const val gitlab            = "$groupId:OrchidGitlab:_"
        const val netlify           = "$groupId:OrchidNetlify:_"
    }

    /**
     * Official webpage: [orchid.run/themes](https://orchid.run/themes)
     */
    val themes = Themes
    object Themes : IsNotADependency {
        const val bsDoc           = "$groupId:OrchidBsDoc:_"
        const val copper          = "$groupId:OrchidCopper:_"
        const val editorial       = "$groupId:OrchidEditorial:_"
        const val futureImperfect = "$groupId:OrchidFutureImperfect:_"
    }

    val bundles = Bundles
    object Bundles : IsNotADependency {
        const val all          = "$groupId:OrchidAll:_"
        const val blog         = "$groupId:OrchidBlog:_"
        const val docs         = "$groupId:OrchidDocs:_"
        const val languagePack = "$groupId:OrchidLanguagePack:_"
    }
}
