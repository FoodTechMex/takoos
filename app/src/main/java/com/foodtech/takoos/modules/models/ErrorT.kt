package com.foodtech.takoos.modules.models

data class ErrorT(
    val msg: String,
    val causa: Throwable
) : Throwable(msg, causa)