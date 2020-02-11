package com.example.rssnews.model.POJO

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
data class Response @JvmOverloads constructor(
	@field:Element(name = "version")
	var version: String? = null,

	@field:Element(name = "channel")
	val channel: Channel? = null
)
