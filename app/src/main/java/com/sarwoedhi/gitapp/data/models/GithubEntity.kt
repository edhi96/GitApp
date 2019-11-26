package com.sarwoedhi.gitapp.data.models

import com.google.gson.annotations.SerializedName

data class GithubEntity(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val ownersName: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("type") val typeUser: String?
)