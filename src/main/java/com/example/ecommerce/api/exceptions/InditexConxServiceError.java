package com.example.ecommerce.api.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter

public class InditexConxServiceError {
    private final Map<String, String[]> parametrosError;
    private final String error;
}
