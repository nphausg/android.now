package com.global.star.android.shared.libs.imageloader

import android.os.Process
import java.util.concurrent.ThreadFactory

class PriorityThreadFactory(private val threadName: String) : ThreadFactory {
    override fun newThread(runnable: Runnable): Thread {
        return Thread(runnable).apply {
            name = threadName
            priority = Process.THREAD_PRIORITY_BACKGROUND
        }
    }
}