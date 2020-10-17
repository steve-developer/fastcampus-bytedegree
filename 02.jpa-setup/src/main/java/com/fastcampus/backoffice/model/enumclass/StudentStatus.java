package com.fastcampus.backoffice.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum StudentStatus {

    REGISTERED(1,"등록상태"),
    UNREGISTERED(2,"해지상태")
    ;

    private Integer id;
    private String title;

}
