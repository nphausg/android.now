package com.global.star.android.shared.common.delegates

import android.os.Bundle
import androidx.fragment.app.Fragment
import vn.futagroup.android.shared.common.extensions.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentBundleDelegate<T : Any> : ReadWriteProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        return thisRef.arguments
            ?.get(key) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        args.put(key, value)
    }

}
