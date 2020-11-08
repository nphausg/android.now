package com.global.star.android.domain.entities

interface IGithubUserModel {
    val id: Long
    val bio: String?
    val name: String?
    val blog: String?
    val followers: Int
    val following: Int
    val email: String?
    val login: String?
    val company: String?
    val htmlUrl: String?
    val avatarUrl: String?
    val createdAt: String?
    val location: String?
}