package com.global.star.android.data.transform

import com.global.star.android.data.entities.GithubUserEntities
import com.global.star.android.domain.entities.GithubUser

object EntitiesTransformer {

    fun fromGithubUserEntitiesToGithubUser(entity: GithubUserEntities): GithubUser {
        return GithubUser(
            id = entity.id,
            bio = entity.bio,
            login = entity.login,
            name = entity.name,
            avatarUrl = entity.avatarUrl,
            htmlUrl = entity.htmlUrl,
            company = entity.bio,
            followers = entity.followers,
            location = entity.location,
            following = entity.following
        )
    }
}