package ge.wololo.androidversionchecker

import android.os.Build

object ApiCompatHandler {

    inline fun on(apiLevel: Int, runnable: () -> Unit): ApiCompatHandler? {
        return executeOrContinue({ apiLevel == Build.VERSION.SDK_INT }, runnable)
    }

    inline fun aboveOrEqual(apiLevel: Int, runnable: () -> Unit): ApiCompatHandler? {
        return executeOrContinue({ apiLevel >= Build.VERSION.SDK_INT }, runnable)
    }

    inline fun above(apiLevel: Int, runnable: () -> Unit): ApiCompatHandler? {
        return executeOrContinue({ apiLevel > Build.VERSION.SDK_INT }, runnable)
    }

    inline fun below(apiLevel: Int, runnable: () -> Unit): ApiCompatHandler? {
        return executeOrContinue({ apiLevel < Build.VERSION.SDK_INT }, runnable)
    }

    inline fun belowOrEqual(apiLevel: Int, runnable: () -> Unit): ApiCompatHandler? {
        return executeOrContinue({ apiLevel <= Build.VERSION.SDK_INT }, runnable)
    }

    inline fun last(runnable: () -> Unit) {
        runnable()
    }

    // Hide this call, but publish it so it's still available for inline
    @PublishedApi
    internal inline fun executeOrContinue(shouldExecute: () -> Boolean, runnable: () -> Unit): ApiCompatHandler? {
        return if (shouldExecute()) {
            runnable()
            null
        } else {
            ApiCompatHandler
        }
    }
}
