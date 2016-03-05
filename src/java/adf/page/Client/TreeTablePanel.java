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

import adf.model.ForumMessage;
import adf.page.Common.Home;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation;
import org.apache.wicket.extensions.markup.html.tree.table.IColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyRenderableColumn;
import org.apache.wicket.extensions.markup.html.tree.table.PropertyTreeColumn;
import org.apache.wicket.extensions.markup.html.tree.table.TreeTable;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Alignment;
import org.apache.wicket.extensions.markup.html.tree.table.ColumnLocation.Unit;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.tree.AbstractTree;
import org.apache.wicket.model.IModel;

/**
 * Page that shows a simple tree table.
 *
 * @author Matej Knopp
 */
public class TreeTablePanel extends Panel
{
    private TreeTable tree;

    /**
     * Page constructor.
     */
    public TreeTablePanel(String id, List<ForumMessage> messageList)
    {
        super(id);
        IColumn columns[] = new IColumn[] {
                new PropertyTreeColumn(new ColumnLocation(Alignment.MIDDLE, 8, Unit.PROPORTIONAL),
                        "Title", "userObject.title"),
                new PropertyRenderableColumn(new ColumnLocation(Alignment.LEFT, 5, Unit.EM), "Replies",
                        "userObject.replies"),
                new PropertyRenderableColumn(new ColumnLocation(Alignment.MIDDLE, 3,
                        Unit.PROPORTIONAL), "Date", "userObject.date"),
                new PropertyRenderableColumn(new ColumnLocation(Alignment.LEFT, 12,
                        Unit.EM), "User", "userObject.nickName") };

        

        tree = new TreeTable("treeTable",convertForumMessageToTreeModel(messageList), columns){

            @Override
            protected void onNodeLinkClicked(AjaxRequestTarget target, TreeNode node) {
                super.onNodeLinkClicked(target, node);
                ModelBean modelbean = (ModelBean)((DefaultMutableTreeNode) tree.getNodeComponent(node).getDefaultModel().getObject()).getUserObject();
                
                PageParameters param = new PageParameters();
                param.put("messageId", modelbean.getMessageId());
                setResponsePage(getPage().getPageClass(),param);
            }

        };
        tree.getTreeState().setAllowSelectMultiple(false);
        getTree().setRootLess(true);
        add(tree);
        tree.getTreeState().collapseAll();
    }

    protected AbstractTree getTree()
    {
        return tree;
    }

    protected TreeModel convertForumMessageToTreeModel(List<ForumMessage> list)
    {
        TreeModel model = null;
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ModelBean("ROOT"));
        add(rootNode, list);
        model = new DefaultTreeModel(rootNode);
        return model;
    }

    private void add(DefaultMutableTreeNode parent, List<ForumMessage> sub)
    {
        for (Iterator<ForumMessage> i = sub.iterator(); i.hasNext();)
        {
            ForumMessage o = i.next();
            if (!o.getChildren().isEmpty())
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(new ModelBean(
                    o));
                parent.add(child);
                add(child, o.getChildren());
            }
            else
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(new ModelBean(
                    o));
                parent.add(child);
            }
        }
    }
}