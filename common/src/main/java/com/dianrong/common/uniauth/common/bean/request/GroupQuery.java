package com.dianrong.common.uniauth.common.bean.request;

import java.util.List;

/**
 * Created by Arc on 3/3/2016.
 */
public class GroupQuery extends PageParam  {

	private static final long serialVersionUID = -6174713703363466941L;
	private Integer id;
    private List<Integer> groupIds;
    private String code;
    private String name;
    private String description;
    private Byte status;
    private Byte userGroupType;
    private Long userId;
    private Integer roleId;
    private Integer tagId;
    private Boolean needTag;
    private Boolean needUser;
    private Boolean needParentId;
    private Boolean includeOwner;
    
    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public GroupQuery setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public Integer getTagId() {
        return tagId;
    }

    public GroupQuery setTagId(Integer tagId) {
        this.tagId = tagId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public GroupQuery setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public GroupQuery setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public GroupQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GroupQuery setDescription(String description) {
        this.description = description;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public GroupQuery setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public Byte getUserGroupType() {
        return userGroupType;
    }

    public GroupQuery setUserGroupType(Byte userGroupType) {
        this.userGroupType = userGroupType;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public GroupQuery setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getNeedTag() {
        return needTag;
    }

    public GroupQuery setNeedTag(Boolean needTag) {
        this.needTag = needTag;
        return this;
    }

    public Boolean getNeedUser() {
        return needUser;
    }

    public GroupQuery setNeedUser(Boolean needUser) {
        this.needUser = needUser;
        return this;
    }

	public Integer getRoleId() {
		return roleId;
	}

	public GroupQuery setRoleId(Integer roleId) {
		this.roleId = roleId;
		return this;
	}

    public Boolean getNeedParentId() {
        return needParentId;
    }

    public GroupQuery setNeedParentId(Boolean needParentId) {
        this.needParentId = needParentId;
        return this;
    }

	public Boolean getIncludeOwner() {
		return includeOwner;
	}

	public GroupQuery setIncludeOwner(Boolean includeOwner) {
		this.includeOwner = includeOwner;
		 return this;
	}

	@Override
	public String toString() {
		return "GroupQuery [id=" + id + ", groupIds=" + groupIds + ", code=" + code + ", name=" + name
				+ ", description=" + description + ", status=" + status + ", userGroupType=" + userGroupType
				+ ", userId=" + userId + ", roleId=" + roleId + ", tagId=" + tagId + ", needTag=" + needTag
				+ ", needUser=" + needUser + ", needParentId=" + needParentId + ", includeOwner=" + includeOwner + "]";
	}
}
