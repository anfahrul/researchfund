package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Role
import com.anfahrul.researchfund.exception.UnauthorizedException
import com.anfahrul.researchfund.service.Middleware
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class MiddlewareImpl: Middleware {
    override fun researcherMiddleware(jwt: String?) {
        val token = jwt?.substring(7, jwt.length)
        val body = Jwts.parser().setSigningKey("secret-key").parseClaimsJws(token).body

        if (body["role"].toString() != Role.RESEARCHER.toString()) {
            throw UnauthorizedException("Akses tidak diizinkan")
        }
    }

    override fun funderMiddleware(jwt: String?) {
        val token = jwt?.substring(7, jwt.length)
        val body = Jwts.parser().setSigningKey("secret-key").parseClaimsJws(token).body

        if (body["role"].toString() != Role.FUNDER.toString()) {
            throw UnauthorizedException("Akses tidak diizinkan")
        }
    }
}