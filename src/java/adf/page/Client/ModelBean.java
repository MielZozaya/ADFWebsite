/* This source code was taken from http://www.wicket-library.com/wicket-examples/ajax/tree/ */


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package adf.page.Client;

import adf.model.AdfUser;
import adf.model.ForumMessage;
import adf.page.Common.Home;
import java.io.Serializable;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Original:Matej Knopp; Edition: Miel Zozaya
 */
public class ModelBean implements Serializable{

    private Long messageId;
    private String title;
    private String nickName;
    private int replies;
    private Date date;

    /**
     * Creates the bean.
     *
     * @param forumMessage
     *            Class that will fill each property.
     */
    public ModelBean(ForumMessage forumMessage) {
        messageId = forumMessage.getId();

        title =  forumMessage.getTitle();

        AdfUser user = forumMessage.getSender();


        nickName= user.getNickname();


        replies = forumMessage.getChildren().size();

        date = forumMessage.getDate();
    }

    public ModelBean(String s){
        messageId = new Long(0);

        title =  s;

        nickName = s;

        replies = 0;

        date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }



    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getTitle() + ", " + getDate() + ", " + getNickName() + ", " + getReplies();
    }
}
