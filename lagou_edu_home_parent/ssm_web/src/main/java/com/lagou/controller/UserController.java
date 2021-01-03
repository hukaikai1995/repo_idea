package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页及多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        //调用service
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "分页多条件查询成功", allUserByPage);
        return responseResult;
    }

    /*
        用户状态设置
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status) {

        //调用service
        userService.updateUserStatus(id,status);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "设置用户状态成功", status);

        return responseResult;

    }

    /*
        用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        //1.调用service
        User user1 = userService.login(user);

        if (user1 != null){

            //保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出来的的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id", user1.getId());

            //将查询出来的user对象，也存到map中，响应给前台，用于登出操作
            map.put("user",user1);

            return new ResponseResult(true, 1, "登录成功", map);

        } else{
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    /*
        分配角色(回显)
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){

        //调用service
        List<Role> roleList = userService.findUserRelationRoleById(id);

        //响应数据
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色回显成功", roleList);
        return responseResult;
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo ){

        //调用service
        userService.userContextRole(userVo);

        //响应数据
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色成功", null);
        return responseResult;
    }

    /*
        获取用户权限，进行菜单动态显示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token是否一致
        if (header_token.equalsIgnoreCase(session_token)) {

            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            //调用service
            ResponseResult responseResult = userService.getUserPermission(user_id);

            return responseResult;
        } else{
            ResponseResult responseResult = new ResponseResult(true, 400, "获取菜单信息失败", null);
            return responseResult;
        }

    }
}
