package cn.sd.vrd.filter;

import cn.sd.vrd.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
                                     //urlPatterns：设置过滤规则
@WebFilter(filterName = "VrdFilter",
        urlPatterns = {"/send.html","/admin.html","/product/delete"})
public class VrdFilter implements Filter {
    /*过滤器初始化时执行的方法*/
    public void init(FilterConfig config) throws ServletException {
    }
    /*过滤器销魂时执行的方法*/
    public void destroy() {
    }
    /*执行controller方法之前和之后执行的方法*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //通过HttpServletRequest对象获得session，但ServletRequest是超类，强转一下
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        //得到会话对象Session
        HttpSession session = req.getSession();
        //得到登录的用户对象
        User u = (User)session.getAttribute("u");
        if(u!=null){//登陆了
            //执行此代码，就是放行
            chain.doFilter(request, response);
        }else{//没有登录，让客户端跳转到登录页面
            res.sendRedirect("/login.html");
        }
    }
}
