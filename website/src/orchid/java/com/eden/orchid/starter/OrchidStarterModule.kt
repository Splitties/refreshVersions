package com.eden.orchid.starter

import com.eden.orchid.api.compilers.TemplateFunction
import com.eden.orchid.api.compilers.TemplateTag
import com.eden.orchid.api.registration.OrchidModule
import com.eden.orchid.api.theme.components.OrchidComponent
import com.eden.orchid.utilities.addToSet

@Suppress("unused")
class OrchidStarterModule : OrchidModule() {

    override fun configure() {
        addToSet<TemplateTag, ReplaceTag>()
        addToSet<TemplateFunction, ReplaceFunction>()
        addToSet<OrchidComponent, ReplaceComponent>()
    }
}
