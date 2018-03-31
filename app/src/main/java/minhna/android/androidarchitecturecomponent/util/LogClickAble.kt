package minhna.android.androidarchitecturecomponent.util

import java.util.regex.Pattern

/**
 * Created by Minh on 3/31/2018.
 */
class LogClickAble {
    private val CALL_STACK_INDEX = 1
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

    fun prependCallLocation(message: String): String {
        var message = message
        // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
        // because Robolectric runs them on the JVM but on Android the elements are different.
        val stackTrace = Throwable().stackTrace
        if (stackTrace.size <= CALL_STACK_INDEX) {
            throw IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?")
        }
        val clazz = extractClassName(stackTrace[CALL_STACK_INDEX])
        val lineNumber = stackTrace[CALL_STACK_INDEX].lineNumber
        message = ".($clazz.kt:$lineNumber) - $message"
        return message
    }

    /**
     * Extract the class name without any anonymous class suffixes (e.g., `Foo$1`
     * becomes `Foo`).
     */
    private fun extractClassName(element: StackTraceElement): String {
        var tag = element.className
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        return tag.substring(tag.lastIndexOf('.') + 1)
    }
}