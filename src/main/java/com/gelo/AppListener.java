package com.gelo;

import com.gelo.controller.handler.router.Router;
import com.gelo.domain.Todo;
import com.gelo.util.JSON;
import com.gelo.util.StaticUtils;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebListener
public class AppListener implements ServletContextListener {

    private List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1L, "Get up", true),
            new Todo(2L, "Do work", false),
            new Todo(3L, "Go to sleep", false)
    ));

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        App app = new App(sce);
        Router router = app.router();

        app.serveStatic("/static");

        router.get("/", (req, res) -> res.sendFile(StaticUtils.getStaticFile("/static/index.html")));

        router.get("/app/todos", (req, res) -> res.json(todos));

        router.put("/app/todos", (req, res) -> todos = req.body(JSON.list(Todo.class)));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}