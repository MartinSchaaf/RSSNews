package com.example.rssnews.model.POJO

import org.simpleframework.xml.Element


class Response {

	var rss: Rss? = null

	override fun toString(): String {
		return "ClassPojo [rss = $rss]"
	}
}