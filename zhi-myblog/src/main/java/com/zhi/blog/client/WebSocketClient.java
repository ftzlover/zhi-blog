package com.zhi.blog.client;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/3/9 15:24
 */
import javax.websocket.Session;

public class WebSocketClient {

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //连接的uri
    private String uri;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
