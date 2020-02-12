package com.example.rssnews.model.POJO

import org.simpleframework.xml.Attribute

data class Enclosure @JvmOverloads constructor(
	@field:Attribute(name = "width")
	@param:Attribute(name = "width")
	var width: String? = null,

	@field:Attribute(name = "type")
	@param:Attribute(name = "type")
	var type: String? = null,

	@field:Attribute(name = "url")
	@param:Attribute(name = "url")
	var url: String? = null,

	@field:Attribute(name = "height")
	@param:Attribute(name = "height")
	var height: String? = null
)
