package com.example.rssnews.model.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
@Entity(tableName = "response")
data class Rss @JvmOverloads constructor(

    @PrimaryKey
    var id:Int?,

    @Ignore
    @field:Attribute(name = "version")
    @param:Attribute(name = "version")
    var version: String? = null,

    @Ignore
    @Embedded
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    var channel: Channel? = null
){
    constructor(v:Any) : this(null,null,null)
}