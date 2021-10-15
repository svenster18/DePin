package com.mohamadrizki.depin.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("UserResponse")
	val userResponse: List<UserResponseItem>
)

data class UserResponseItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("no_tlp")
	val noTlp: String,

	@field:SerializedName("no_identitas")
	val noIdentitas: String,

	@field:SerializedName("email")
	val email: String
)
