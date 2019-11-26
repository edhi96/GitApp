package com.sarwoedhi.gitapp.data.models


import com.google.gson.annotations.SerializedName

data class SearchEntity(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("owner") val owner: OwnerEntity?

)
