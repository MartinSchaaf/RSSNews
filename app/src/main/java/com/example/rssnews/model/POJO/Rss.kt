package com.example.rssnews.model.POJO

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
data class Rss @JvmOverloads constructor(
    @field:Attribute(name = "version")
    @param:Attribute(name = "version")
    var version: String? = null,

    @field:Element(name = "channel")
    @param:Element(name = "channel")
    var channel: Channel? = null
)