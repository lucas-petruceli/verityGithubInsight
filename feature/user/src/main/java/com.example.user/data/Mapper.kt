package com.example.user.data

import com.example.net.model.UserDetailsResponse

fun UserDetailsResponse.toUserDetails(): UserDetails {
    return UserDetails(
        id = this.id,
        name = this.login,
        avatarUrl = this.avatarUrl
    )
}