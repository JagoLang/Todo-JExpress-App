# JExpress Todo app
This is just a simple example how JExpress can be used.

```java
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
```

This is enough to serve static, create restful api and send html template file.
Simple and elegant solution.