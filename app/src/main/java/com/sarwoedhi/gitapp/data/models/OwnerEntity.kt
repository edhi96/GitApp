package com.sarwoedhi.gitapp.data.models

import com.google.gson.annotations.SerializedName


class OwnerEntity(@SerializedName("login") val login: String?, @SerializedName("html_url") val html_url: String?)