package com.ace.entity;

import lombok.*;

/**
 * @author john
 * @date 19-6-18 上午10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class RoomFreeOrg {
    private Long roomId;
    private String orgId;
    private String orgName;

    public RoomFreeOrg(String orgId, String orgName) {
        this.orgId = orgId;
        this.orgName = orgName;
    }

    public RoomFreeOrg(Long roomId, String orgId, String orgName) {
        this.roomId = roomId;
        this.orgId = orgId;
        this.orgName = orgName;
    }
}
