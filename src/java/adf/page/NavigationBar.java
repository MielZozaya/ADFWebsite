package adf.page;

import java.util.List;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author Jeff
 * source taken from http://jeff-schwartz.blogspot.com/2009/03/adventure-with-wicket.html at 20 July 2010
 */
public abstract class NavigationBar extends Panel{

    abstract public List<MenuItem> getMenuItems();
    private final String panelId;

    public String getPanelId(){
        return panelId;
    }

    private int selectedTab;

    public int getSelectedTab(){
        return selectedTab;
    }

    private final String normalTabStyle;

    public String getNormalTabStyle(){
        return normalTabStyle;
    }

    private final String selectedTabStyle;

    public String getSelectedTabStyle(){
        return selectedTabStyle;
    }

    public NavigationBar(String id, int currentTab,
            String normalTabStyle, String selectedTabStyle) {
        super(id);

        this.panelId = id;
        this.selectedTab = currentTab;
        this.normalTabStyle = normalTabStyle;
        this.selectedTabStyle = selectedTabStyle;

        IModel model = new LoadableDetachableModel() {

            protected Object load() {
                List<MenuItem> menuItems = getMenuItems();
                if(selectedTab >=0 && selectedTab<menuItems.size())
                    menuItems.get(selectedTab).setSelected(true);
                return menuItems;
            }
        };

        ListView NavigationBarListView =
                new ListView("tabitem", model) {

            @Override
            protected void populateItem(ListItem item) {
                // get the menuItem associated with thiw ListItem
                MenuItem menuItem = (MenuItem) item.getModelObject();

                // create a link using the menuItem
                // and add it to the ListItem
                if (menuItem.getHref() == null) {
                    ExternalLink el = new ExternalLink("tablink", "#");
                    el.add(new Label ("tabdescription",
                            menuItem.getDescription()));
                    item.add(el);

                } else {
//                    Link pl = new BookmarkablePageLink("tablink",
//                            menuItem.getHref());
                    Link pl = menuItem.getHref();
                    pl.addOrReplace(new Label ("tabdescription",
                            menuItem.getDescription()));
                    item.add(pl);
                }

                // set the css class attribute of the ListItem by
                // checking to see if it is selected or not
                String cssClassName = menuItem.isSelected()?
                    getSelectedTabStyle() : getNormalTabStyle();
                item.add(new SimpleAttributeModifier("class",cssClassName));
                item.add(new SimpleAttributeModifier("class",cssClassName));
            }


        };

        add(new SimpleAttributeModifier("id", id));
        add(NavigationBarListView);
    }
}
