package adf.page.Common;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;


public class Home extends AdfWebPage {


    public Home() {
        AdfBorder border = new AdfBorder("adfBorder",0);
        add(border);
    }
}
