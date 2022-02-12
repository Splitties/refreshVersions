package de.fayard.refreshVersions.core.internal.failures

import de.fayard.refreshVersions.core.DependencyVersionsFetcher

internal fun DependencyVersionsFetcher.FailureCause.oneLineSummary(): String = when (this) {
    is DependencyVersionsFetcher.FailureCause.CommunicationIssue.HttpResponse -> {
        val message = exception?.message ?: ""
        "http status code $statusCode $message"
    }
    is DependencyVersionsFetcher.FailureCause.CommunicationIssue.NetworkIssue -> {
        val message = exception.message ?: exception.cause?.message ?: ""
        "network or server issue (${exception.javaClass} ${message})"
    }
    is DependencyVersionsFetcher.FailureCause.ParsingIssue -> {
        val message = exception.message ?: exception.cause?.message ?: ""
        "error while parsing metadata (${exception.javaClass} ${message})"
    }
}.replace('\n', ' ').replace("\r", "")
