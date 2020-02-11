package com.example.rssnews.model.POJO

import org.simpleframework.xml.Element

data class Channel(
	@Element(name = "item")
	val item: List<ItemItem?>? = null,
	@Element(name = "about")
	val about: String? = null,
	@Element(name = "link")
	val link: String? = null,
	@Element(name = "description")
	val description: String? = null,
	@Element(name = "title")
	val title: String? = null
)
