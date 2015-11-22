package quite.entity.rightMoudle;


import com.google.common.collect.Sets;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sys_resource")
public class SysResource implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;                           //ID
    @Column
    private String resourceName;            //资源名称
    @Column
    private String resourceUrl;             //资源地址
    @Column
    private Integer pid;               //上级目录ID
    @Column
    private Integer createUser;             //创建人
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;                //创建时间
    @Column
    private Integer updateUser;            //更新人
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;              //更新时间
    @Column
    private Long type;                    //资源类型，1，菜单类型，2，操作类型
    @Column
    private String permissionName;      //权限名称
    @Transient
    private Boolean tag;                  //标识位
    @Transient
    private List<SysResource> childList;//子资源
    @Transient
    private String pName;               //父资源名称
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_resource_role", joinColumns = { @JoinColumn(name = "role_id",referencedColumnName="ID") }, inverseJoinColumns = { @JoinColumn(name = "resource_id",referencedColumnName="ID") })
    private Set<SysRole> roles = Sets.newLinkedHashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public List<SysResource> getChildList() {
        return childList;
    }

    public void setChildList(List<SysResource> childList) {
        this.childList = childList;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
