package com.example.rssnews.model.pojo

import org.simpleframework.xml.Element

data class Group @JvmOverloads constructor(
	@field:Element(name = "thumbnail")
	@param:Element(name = "thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:Element(name = "content")
	@param:Element(name = "content")
	val content: Content? = null
)
