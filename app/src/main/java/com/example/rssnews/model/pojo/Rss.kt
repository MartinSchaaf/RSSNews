package com.example.rssnews.model.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
class Rss @JvmOverloads constructor(

    @field:Attribute(name = "version")
    @param:Attribute(name = "version")
    var version: String? = null,



    @Embedded
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    var channel: Channel? = null
)