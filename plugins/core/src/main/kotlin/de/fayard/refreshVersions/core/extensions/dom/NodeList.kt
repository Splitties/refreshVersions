package de.fayard.refreshVersions.core.extensions.dom

import org.w3c.dom.Node
import org.w3c.dom.NodeList

internal fun NodeList.asList(): List<Node> = List(length) { item(it) }
