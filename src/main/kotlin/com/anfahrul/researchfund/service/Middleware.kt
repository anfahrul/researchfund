package com.anfahrul.researchfund.service

interface Middleware {

    fun researcherMiddleware(jwt: String?)

    fun funderMiddleware(jwt: String?)
}