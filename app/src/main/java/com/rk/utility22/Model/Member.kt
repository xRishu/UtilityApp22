package com.rk.utility22.Model

data class Member(
    val alpha_two_code: Int,
    val domains: domains,
    val country: String?="",
    val stateProvince: String?="",
    val webPages: web_pages,
    val name: String?="",

)