package fengfei.controllers;

import japidviews.Application.Index;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;

public class Store extends JapidController {

    public static void best(String id) {
        throw new JapidResult(new Index().render());
    }

    public static void bestSellers(String id) {
        throw new JapidResult(new Index().render());
    }

    public static void random(String id) {
        throw new JapidResult(new Index().render());
    }

    public static void catalog(String id) {
        throw new JapidResult(new Index().render());
    }

    public static void showNew(String id) {
        throw new JapidResult(new Index().render());
    }

}
