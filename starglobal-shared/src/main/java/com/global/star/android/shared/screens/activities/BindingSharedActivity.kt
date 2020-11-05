package com.global.star.android.shared.screens.activities

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.global.star.android.shared.common.extensions.activityBinding

open class BindingSharedActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    SharedActivity() {

    protected val binding: B by activityBinding(layoutId)

    override fun onSyncViews(savedInstanceState: Bundle?) {
        super.onSyncViews(savedInstanceState)
        binding.lifecycleOwner = this
    }
}