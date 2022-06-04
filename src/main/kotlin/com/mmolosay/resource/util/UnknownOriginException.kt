package com.mmolosay.resource.util

/**
 * [RuntimeException] the origin of which is unknown.
 *
 * @param cause the detail message.
 */
class UnknownOriginException(cause: String) : RuntimeException(cause)