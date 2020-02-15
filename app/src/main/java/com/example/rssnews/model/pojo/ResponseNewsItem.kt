package com.example.rssnews.model.pojo

import org.simpleframework.xml.*


data class ResponseNewsItem @JvmOverloads constructor(


   @field:ElementList(name = "enclosure",inline = true, required = false)
   @param:ElementList(name = "enclosure",inline = true, required = false)
    val enclosure: List<Enclosure>? = null,

    @field:Element(name = "amplink")
    @param:Element(name = "amplink")
    var amplink: String? = null,

    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String? = null,

    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "category")
    @param:Element(name = "category")
    var category: String? = null,

    @field:Element(name = "pubDate")
    @param:Element(name = "pubDate")
    var pubDate: String? = null,

    @field:Element(name = "full-text")
    @param:Element(name = "full-text")
    var fullText: String? = null,

    @field:ElementList(entry = "group", inline = true, required = false)
    @param:ElementList(entry = "group", inline = true, required = false)
    var group: List<Group>? = null
)
