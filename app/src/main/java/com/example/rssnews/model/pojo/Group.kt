package com.example.rssnews.model.pojo

import androidx.room.Embedded
import org.simpleframework.xml.Element

data class Group @JvmOverloads constructor(

	@Embedded
	@field:Element(name = "thumbnail")
	@param:Element(name = "thumbnail")
	val thumbnail: Thumbnail? = null,

	@Embedded
	@field:Element(name = "content")
	@param:Element(name = "content")
	val content: Content? = null
)
