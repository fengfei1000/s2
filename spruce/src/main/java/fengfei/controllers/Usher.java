package fengfei.controllers;

import japidviews.Application.Index;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;

public class Usher extends JapidController {

    public static void index() {
        throw new JapidResult(new Index().render());
    }

}