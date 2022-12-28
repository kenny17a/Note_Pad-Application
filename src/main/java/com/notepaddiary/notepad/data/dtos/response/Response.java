package com.notepaddiary.notepad.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response {
    private String message;
}
