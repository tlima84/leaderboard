package com.tlima84.leaderboards.config

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import java.util.*
import kotlin.collections.HashMap

@ControllerAdvice(annotations = [RestController::class])
class ApplicationExceptionHandler {

    /*
    * handles field validation exception except when non-null fields are not sent
    * */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE], headers = ["Accept=application/json"])
    fun validationException(exception: MethodArgumentNotValidException): FieldValidationError {
        //get BindingResult to add into a Map of errors
        val result = exception.bindingResult
        //create a map containing field errors
        val mapErrors = result.fieldErrors.associate { it.field to (it.defaultMessage ?: String()) }
        //create object with invalid field
        return FieldValidationError(
            timestamp = Date().time,
            status = 400,
            error = HttpStatus.BAD_REQUEST.name,
            message = "Invalid input",
            fieldErrors = mapErrors
        )
    }

    /*
    * Handles exception when non-null field are sent in body request
    * */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE], headers = ["Accept=application/json"])
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException, request: WebRequest): FieldValidationError {

        //get cause as MissingKotlinParameterException to use to build exception body
        val cause = exception.cause as? MissingKotlinParameterException
        if (cause != null) {
            //get field name from cause to add into error message
            val fieldName = cause.path[0].fieldName
            //add fieldName into the message
            val message = "The field '$fieldName' must not be null."
            //create object with invalid field
            return FieldValidationError(
                timestamp = Date().time,
                status = 400,
                error = HttpStatus.BAD_REQUEST.name,
                message = "Invalid input",
                fieldErrors = hashMapOf(fieldName to message)
            )
        }
        //if cause type is different from MissingKotlinParameterException return generic field validation error.
        return FieldValidationError(
            timestamp = Date().time,
            status = 400,
            error = HttpStatus.BAD_REQUEST.name,
            message = "HttpMessageNotReadableException error: ${exception.message}",
            fieldErrors = HashMap()
        )
    }

    data class FieldValidationError(

        val timestamp: Long,

        val status: Int,

        val error: String,

        val message: String,

        val fieldErrors: Map<String, String>
    )
}