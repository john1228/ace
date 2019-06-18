package com.ace.entity;

import lombok.*;

/**
 * @author john
 * @date 19-6-18 上午10:52
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class RoomFreeOrg {
    @NonNull
    private Long roomId;
    @NonNull
    private String orgId;
    @NonNull
    private String orgName;
}
