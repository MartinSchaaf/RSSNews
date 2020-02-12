package com.example.rssnews.model.POJO

import org.simpleframework.xml.Attribute

data class Content @JvmOverloads constructor(

	@field:Attribute(name = "url")
	@param:Attribute(name = "url")
	val url: String? = null,

	@field:Attribute(name = "type")
	@param:Attribute(name = "type")
	val type:String? = null
)
