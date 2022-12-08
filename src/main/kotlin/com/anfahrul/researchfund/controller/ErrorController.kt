package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.exception.UnauthorizedException
import com.anfahrul.researchfund.model.WebResponseWithMessage
import com.anfahrul.researchfund.model.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unAuthorizedHandler(e: UnauthorizedException): WebResponseWithMessage {
        return WebResponseWithMessage(
            code = 401,
            status = "Unauthorized",
            message = e.message!!
        )
    }

    @ExceptionHandler(value = [BadRequestException::class])
    fun notFoundHandler(e: BadRequestException): WebResponseWithMessage {
        return WebResponseWithMessage(
            code = 400,
            status = "Bad Request",
            message = e.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(e: NotFoundException): WebResponseWithMessage {
        return WebResponseWithMessage(
            code = 404,
            status = "Not Found",
            message = e.message!!
        )
    }
}