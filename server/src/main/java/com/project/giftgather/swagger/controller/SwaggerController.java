package com.project.giftgather.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/swagger_test")
@RequiredArgsConstructor
@Tag(name = "Swagger", description = "Swagger Test API")
public class SwaggerController {

    @GetMapping("/basic")
    @Operation(summary = "Swagger basic", description = "스웨거 기본 사용")
    public ResponseEntity<String> swaggerBasic(){
        return new ResponseEntity<>("basic", HttpStatus.OK);
    }
}
