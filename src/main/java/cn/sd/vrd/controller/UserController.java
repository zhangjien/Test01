package cn.sd.vrd.controller;

import cn.sd.vrd.mapper.UserMapper;
import cn.sd.vrd.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired(required = false)
    private UserMapper mapper;

    @RequestMapping("/login")
    public int login(User user, HttpSession session, String rem, HttpServletResponse response){
        System.out.println("rem="+rem);
        User u = mapper.selectByUsername(user.getUsername());
        if(u!=null){
            if(u.getPassword().equals(user.getPassword())){
                session.setAttribute("u",u);
                if(rem!=null){//记住用户名和密码
                    //创建cookie并把用户名和密码保存进Cookie里面
                    Cookie c1 = new Cookie("un", u.getUsername());
                    Cookie c2 = new Cookie("pw",u.getPassword());
                    //设置cookie保存时间
                    c1.setMaxAge(60*60*24*30);
                    c2.setMaxAge(60*60*24*30);
                    //通过响应对象将cookie带给浏览器
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                return 1;//登录成功
            }
            return 3;//用户名错误
        }
        return 2;//用户名不存在
    }

    @RequestMapping("/currentUser")
    public User currentUser(HttpSession session){
        return (User)session.getAttribute("u");
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("u");
    }

}
