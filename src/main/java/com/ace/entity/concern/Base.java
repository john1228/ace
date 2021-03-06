package com.ace.entity.concern;


import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    String projectName;
}
