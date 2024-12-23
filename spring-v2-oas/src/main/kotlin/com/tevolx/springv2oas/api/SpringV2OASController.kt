package com.tevolx.springv2oas.api

import com.tevolx.springv2oas.model.CreateRequest
import com.tevolx.springv2oas.model.Response
import com.tevolx.springv2oas.model.UpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/spring-v2"])
class SpringV2OASController {

    @GetMapping("/get-mapping/{pathVariable}")
    fun getMappingFun(
        @RequestParam(required = false) nonRequiredStringParam: String? = null,
        @RequestParam requiredStringParam: String,
        @RequestParam requiredIntParam: Int,
        @PathVariable pathVariable: String,
    ): ResponseEntity<Response> {
        return ResponseEntity.ok().body(Response("", null, 0, 0L, true))
    }

    @PostMapping("/post-mapping")
    fun postMappingFun(
        @RequestBody requestBody: CreateRequest,
    ): ResponseEntity<Response> {
        return ResponseEntity.ok().body(Response("", null, 0, 0L, true))
    }

    @PutMapping("/put-mapping/{pathVariable}")
    fun putMappingFun(
        @RequestBody requestBody: UpdateRequest,
        @PathVariable pathVariable: String,
    ): ResponseEntity<Response> {
        return ResponseEntity.ok().body(Response("", null, 0, 0L, true))
    }

    @PatchMapping("/patch-mapping/{pathVariable}")
    fun patchMappingFun(
        @RequestBody requestBody: UpdateRequest,
        @PathVariable pathVariable: String,
    ): ResponseEntity<Response> {
        return ResponseEntity.ok().body(Response("", null, 0, 0L, true))
    }

    @DeleteMapping("/delete-mapping/{pathVariable}")
    fun deleteMappingFun(
        @PathVariable pathVariable: Long,
    ): ResponseEntity<Response> {
        return ResponseEntity.ok().body(Response("", null, 0, 0L, true))
    }
}