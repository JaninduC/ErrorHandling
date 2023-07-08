package com.example.shop.model.error;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorModel {
    private int code;
    private String message;
//    private Object result;
}
