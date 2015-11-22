package quite.entity.rightMoudle;


import com.google.common.collect.Sets;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class SysRole{

    @Id
    @GeneratedValue
    private Integer id;                 //ID
    private String roleName;         //角色名称
    private String roleDescription;  //角色描述
    private Integer createUser;      //创建人
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;         //创建时间
    private Integer updateUser;      //更新人
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;         //更新时间
    private String resourceId;        //资源Id
    private Integer Pid;
    private Boolean tag;              //是否是自己的节点
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "sysRoleList", fetch = FetchType.EAGER)
    private Set<SysUser> userList = Sets.newLinkedHashSet();
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<SysResource> sysResourceSet = Sets.newLinkedHashSet();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getPid() {
        return Pid;
    }

    public void setPid(Integer pid) {
        Pid = pid;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Set<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(Set<SysUser> userList) {
        this.userList = userList;
    }

    public Set<SysResource> getSysResourceSet() {
        return sysResourceSet;
    }

    public void setSysResourceSet(Set<SysResource> sysResourceSet) {
        this.sysResourceSet = sysResourceSet;
    }
}
