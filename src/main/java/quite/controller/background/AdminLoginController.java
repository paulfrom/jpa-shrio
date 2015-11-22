package quite.controller.background;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import quite.core.credentials.RetryLimitHashedCredentialsMatcher;
import quite.entity.rightMoudle.SysResource;
import quite.entity.rightMoudle.SysUser;
import quite.service.AccountService;
import quite.utils.ConvertUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2015/1/12.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController{

    @Resource
    private AccountService accountService;


    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request){
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "�û���/�������";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "�û���/������� , ���������� "+ String.valueOf(5 - RetryLimitHashedCredentialsMatcher.passwordRetryCache.get(request.getParameter("username")).intValue() + " ��");
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "�˺��ѱ���������1Сʱ������";
        } else if(exceptionClassName != null) {
            error = "��������" + exceptionClassName;
        }
        model.addAttribute("error", error);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            WebUtils.saveRequest(request);
        }
        if(getCurrentUser()!=null){
            return "redirect:/admin/index.html";
        }
        return "background/login";

    }

    /**
     * ��¼�ɹ���ת��ҳ
     * @return
     * @throws IOException
     */
    @RequestMapping({"/home","/index"})
    public String home(HttpServletRequest request) throws IOException {
        SysUser user = getCurrentUser();
        request.getSession().setAttribute("user", user);
        if(request.getSession().getAttribute("resourceRoleList")==null && (user.getSysRoleList()!=null&&user.getSysRoleList().size()!=0)){
//            Set<Integer> ids = Sets.newLinkedHashSet();
//            for(SysRole i : user.getSysRoleList()){
//                ids.add(i.getId());
//            }
//            List<SysResource> resourceList=accountService.findPermissionResource(ids);
            List<SysResource> resourceList=accountService.findAllResource();
            List<SysResource> result=new ArrayList();
            if(resourceList!=null && resourceList.size()>0) {
                for(SysResource resource:resourceList){
                    if(resource.getPid()==0){
                        result.add(resource);
                        findChild(resource, resourceList);
                    }
                }
            }
            request.getSession().setAttribute("resourceRoleList",result);
        }
        return "background/index";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        SecurityUtils.getSubject().logout();
        return "background/login";
    }

    @RequestMapping("/editMyInfo")
    public String editMyInfo(Model model,HttpServletRequest request){
        model.addAttribute("info", request.getSession().getAttribute("user"));
        return "background/myinfo";
    }

    @RequestMapping("/updatePassword")
    public void  updatePassword(Model model,SysUser user) {
        try{
            Integer id = getCurrentUser().getId();
            if(StringUtils.isBlank(id.toString())) {
                model.addAttribute("flag",false);
                model.addAttribute("msg","�Ƿ�������");
            } else {
                user.setId(id);
                user.setPassword(ConvertUtil.encryptMD5(user.getNewpassword()));
                if(accountService.updateUser(user)){
                    model.addAttribute("flag",true);
                }else {
                    model.addAttribute("flag",false);
                    model.addAttribute("msg","û���ҵ��������");
                }
            }
        }catch (Exception e) {
            model.addAttribute("flag",false);
            model.addAttribute("msg",e.toString());
            e.printStackTrace();
        }
    }

    @RequestMapping("/checkPassword")
    @ResponseBody
    public String check(SysUser user) throws Exception{
        String password = getCurrentUser().getPassword();
        if(password.equals(ConvertUtil.encryptMD5(user.getPassword()))){
            return "true";
        }
        return "false";
    }

    public void findChild(SysResource resource, List<SysResource> sysResourceList) {
        List<SysResource> child=new ArrayList();
        if(sysResourceList!=null && sysResourceList.size()>0){
            for(SysResource childResource:sysResourceList){
                if(childResource.getPid()== Integer.parseInt(resource.getId().toString())){
                    child.add(childResource);
                    findChild(childResource,sysResourceList);
                }
            }
            resource.setChildList(child);
        }
        return;
    }
}
