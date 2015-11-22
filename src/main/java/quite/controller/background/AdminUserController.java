package quite.controller.background;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import quite.entity.rightMoudle.SysUser;
import quite.service.UserService;
import quite.utils.ConvertUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pual
 * Date: 2015/9/11
 * Desc:
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController{

    @Resource
    UserService userService;
//    @Resource
//    RoleService roleService;

    @RequestMapping("/manager")
    public String userManager(Model model,SysUser sysUser ,@PageableDefault(size = 10) Pageable pageable) throws IOException {
        Page<SysUser> page = userService.queryUserPage(sysUser, pageable);
        model.addAttribute("page",page);
//        model.addAttribute("roles",roleService.getAllRoles());
        return "background/system/sysUserList";
    }

    @RequestMapping("/add")
    public void addSysUser(Model model, SysUser sysUser, HttpServletRequest request) throws Exception {
        sysUser.setCreateUser(getCurrentUser().getId());
        boolean flag=false;
        if (sysUser.getStatus()!=null&&sysUser.getStatus().equals("on")) {
            sysUser.setStatus("1");
        } else {
            sysUser.setStatus("0");
        }
        if(StringUtils.isEmpty(sysUser.getId())) {
            if (sysUser.getPassword() == null || sysUser.getPassword() == "") {
                model.addAttribute("flag", false);
                return;
            }
            sysUser.setPassword(ConvertUtil.encryptMD5(sysUser.getPassword()));
            flag=userService.addSysUser(sysUser);
        }else {
            flag=userService.updateUser(sysUser);
        }
    }

    @RequestMapping("/delete")
    public void deleteUser(Model model, Integer userId) throws IOException {
        boolean flag=userService.deleteSysUser(userId);
        model.addAttribute("flag",flag);
    }

    @RequestMapping("/checkloginName")
    public void checkloginName(String loginName,String id,Model model) throws IOException {
        int count = userService.countLoginName(loginName,id);
        model.addAttribute(count==0);
    }

    @RequestMapping("/editMyInfo")
    public void editMyInfo(Model model, SysUser sysUser, HttpServletRequest request) throws Exception {
        boolean flag = userService.updateUser(sysUser);
        if(flag){
            if(sysUser.getId()==getCurrentUser().getId()){
                SysUser sessionUser= (SysUser) request.getSession().getAttribute("user");
                sessionUser.setUserName(sysUser.getUserName());
                sessionUser.setPhone(sysUser.getPhone());
                sessionUser.setQq(sysUser.getQq());
                sessionUser.setEmail(sysUser.getEmail());
            }
            model.addAttribute("flag",flag);
        }else {
            model.addAttribute("flag",flag);
            model.addAttribute("msg","没有修改内容");
        }
    }

    @RequestMapping("/uploadImage")
    public void uploadImage(Model model, HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("filename");
//        String path= FileUtil.fileUpload(file, "image");
        SysUser sysUser=new SysUser();
        sysUser.setHeadPath("");
        sysUser.setId(getCurrentUser().getId());
        boolean flag = userService.updateUser(sysUser);
        if(flag){
            getCurrentUser().setHeadPath("");
            model.addAttribute("flag",flag);
        }else {
            model.addAttribute("flag",flag);
            model.addAttribute("msg","没有找到相应数据");
        }

    }
}
