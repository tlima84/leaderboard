package com.tlima84.leaderboards.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.*

@ControllerAdvice(annotations = [RestController::class])
class ApplicationExceptionHandler : ApplicationContextAware {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun validationException(exception: MethodArgumentNotValidException): FieldValidationError {
        val result = exception.bindingResult
        val mapErrors = result.fieldErrors.associate { it.field to (it.defaultMessage ?: String()) }

        return FieldValidationError(
            timestamp = Date().time,
            status = 400,
            error = HttpStatus.BAD_REQUEST.name,
            message = "input.invalid",
            fieldErrors = mapErrors
        )
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) { }

    data class FieldValidationError(

        val timestamp: Long,

        val status: Int,

        val error: String,

        val message: String,

        val fieldErrors: Map<String, String>
    )
}