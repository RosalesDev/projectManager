package com.st.project_manager.dto;

public class UserPersonHasRoleDTO extends AuditDTO {

  private Integer id;
  private Integer userPersonId;
  private Integer roleId;
  private Boolean enable;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserPersonId() {
    return userPersonId;
  }

  public void setUserPersonId(Integer userPersonId) {
    this.userPersonId = userPersonId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

}
