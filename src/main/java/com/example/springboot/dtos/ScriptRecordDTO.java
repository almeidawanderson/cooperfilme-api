package com.example.springboot.dtos;

import com.example.springboot.enums.ScriptStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScriptRecordDTO(
        @NotBlank(message = "Você precisa adicionar um nome ao Roteiro")
        String script_name,

        @NotNull
        ScriptStatus script_status
) {
}
