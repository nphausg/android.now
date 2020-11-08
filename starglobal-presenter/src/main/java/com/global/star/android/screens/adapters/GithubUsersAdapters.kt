package com.global.star.android.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.global.star.android.R
import com.global.star.android.databinding.RowUserBinding
import com.global.star.android.domain.entities.GithubUser
import com.global.star.android.shared.screens.adapters.CorePagingDataAdapter
import com.global.star.android.shared.screens.adapters.DataBindingViewHolder

class GithubUsersAdapters : CorePagingDataAdapter<GithubUser>(COMPARE) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(parent)
    }

    class UserViewHolder(binding: RowUserBinding) :
        DataBindingViewHolder<GithubUser>(binding) {

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                return UserViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.row_user,
                        parent,
                        false
                    )
                )
            }
        }

        override fun bind(item: GithubUser?) {
            (binding as RowUserBinding).item = item
            super.bind(item)
        }
    }

    companion object {
        private val COMPARE = object : DiffUtil.ItemCallback<GithubUser>() {
            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem == newItem
            }
        }
    }

}