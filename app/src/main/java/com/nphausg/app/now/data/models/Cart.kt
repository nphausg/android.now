/*
 * Created by nphau on 11/19/22, 4:16 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 11/19/22, 3:58 PM
 */

package com.nphausg.app.now.data.models

import com.nphausg.app.now.data.Database
import java.util.*

class Cart(val id: String, val items: List<Fruit>) {

    companion object {
        fun sample(): Cart {
            return Cart(
                id = UUID.randomUUID().toString(),
                items = Database.FRUITS
            )
        }
    }
}