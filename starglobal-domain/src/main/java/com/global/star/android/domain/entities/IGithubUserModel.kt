package com.global.star.android.domain.entities

interface IGithubUserModel {
    val avatarUrl: String?
    val htmlUrl: String?
    val login: String?
    val name: String?
    val bio: String?
    val blog: String?
    val company: String?
    val createdAt: String?
    val location: String?
    val email: String?
    val followers: Int
    val following: Int
}