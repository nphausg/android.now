package com.global.star.android.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.global.star.android.data.entities.GithubUserEntities
import org.hamcrest.CoreMatchers.`is`

@RunWith(AndroidJUnit4::class)
class GithubUserDaoTest : LocalDbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {

        val user = createUser("nphau")

        db.userDao().insert(user)

        val loaded = db.userDao().findByLogin(user.login).blockingFirst()
        MatcherAssert.assertThat(loaded.login, `is`("nphau"))

        val replacement = createUser("nphau01")
        db.userDao().insert(replacement)

        val loadedReplacement = db.userDao().findByLogin(replacement.login).blockingFirst()
        MatcherAssert.assertThat(loadedReplacement.login, `is`("nphau02"))
    }

    companion object TestUtil {

        fun createUser(login: String) = GithubUserEntities(
            login = login,
            name = "$login name",
        )

    }
}