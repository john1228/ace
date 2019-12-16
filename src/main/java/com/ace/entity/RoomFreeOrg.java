package com.ace.entity;

import lombok.*;

/**
 * 会议室免费组织
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
