package com.mmolosay.resource

/**
 * [RuntimeException] the origin of which is unknown.
 *
 * @param cause the cause of occurring.
 */
class UnknownOriginException(cause: String) : RuntimeException(cause)