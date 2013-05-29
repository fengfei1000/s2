/**
 * @author 王铁堂 (Wang Tietang)/Howarste 
 * @email mylife1000@gmail.com
 * @blog http://blog.csdn.net/myloon and http://howarste.javaeye.com/
 * @googleCode http://code.google.com/u/mylife1000/ 
 * @date  2011-3-3 下午10:28:10
 * @comments Not the code written,is lonely!
 */
package hawthorstein.twitter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        System.out.println(request.getQueryString());
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return "index";
    }

    @RequestMapping("/reg")
    public String reg() {
        return "reg";
    }

    @RequestMapping("/{ownerId}")
    public String toTwitter(@PathVariable("ownerId") String ownerId) {
        return "reg";
    }

    @RequestMapping("/home")
    public String welcome() {
        return "home";
    }

    @RequestMapping("/home1")
    public String home1() {
        return "home1";
    }
}
