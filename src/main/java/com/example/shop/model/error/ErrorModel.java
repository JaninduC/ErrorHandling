package com.example.shop.model.error;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorModel {
    private int code;
    private String message;
}
