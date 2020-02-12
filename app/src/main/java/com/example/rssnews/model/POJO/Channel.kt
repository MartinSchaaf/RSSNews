package com.example.rssnews.model.POJO

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList


data class Channel @JvmOverloads constructor(

	@field:ElementList(entry = "item", inline = true, required = false)
	@param:ElementList(entry = "item", inline = true, required = false)
	var item: List<ItemItem>? = null,

	@field:Element(name = "about")
	@param:Element(name = "about")
	var about: String? = null,

	@field:Element(name = "link")
	@param:Element(name = "link")
	var link: String? = null,

	@field:Element(name = "description")
	@param:Element(name = "description")
	var description: String? = null,

	@field:Element(name = "title")
	@param:Element(name = "title")
	var title: String? = null
)


