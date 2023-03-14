package org.jetic.web.chat;

/**
 * Title:        经天网络
 * Description:
 * Copyright:    Copyright (c) 2000<br>
 * Company:      www.jetic.com  经天<br>
 * @author hover
 * @version 1.0
 */

public class Protocol {
    private String userid;

    public Protocol(String userid) {
        this.userid = userid;
    }

    public String processInput(String input) {
        return (userid + ": " + input);
    }
}