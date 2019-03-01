package com.ace.entity.concern;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Base {
    @NonNull
    private Integer staffId;
}
