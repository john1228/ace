package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class UserPermission implements Serializable{
	private Integer id;
	private Integer userId;
	private String permissionId;
}
