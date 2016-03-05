/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.Administrator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Administrator/DatabaseInformation")
@AuthorizeInstantiation({"ROLE_admin"})
public class DatabaseInformation extends AdministrationBasePage {

    public DatabaseInformation() {
        super(DatabaseInformation.class);

        Link downloadViewHistory = new Link("downloadViewHistory") {

            @Override
            public void onClick() {
                try {
                    final String fileName = "View History.xls";
                    final File file = File.createTempFile(fileName, null);


                    ConvertToXls.convertViewHistory(file, getAdfService().getViewHistories());
                    IResourceStream resourceStream = new FileResourceStream(file) {

                        @Override
                        public void close() throws IOException {
                            super.close();
                            Files.remove(file);
                        }
                    };
                    getRequestCycle().setRequestTarget(
                            new ResourceStreamRequestTarget(resourceStream) {

                                @Override
                                public String getFileName() {
                                    return fileName;
                                }
                            });
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseInformation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        border.add(downloadViewHistory);
    }
}
