package com.example.rssnews.model.POJO

import org.simpleframework.xml.Element

data class ItemItem(
	@Element(name = "yandex:full-text")
	val fullText: String? = null,
	@Element(name = "enclosure")
	val enclosure: Enclosure? = null,
	@Element(name = "amplink")
	val amplink: String? = null,

	val link: String? = null,
	val description: String? = null,
	val title: String? = null,
	val category: String? = null,
	val pubDate: String? = null,
	val group: Group? = null
)
