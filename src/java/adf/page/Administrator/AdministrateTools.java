/*
 * Sources from: http://www.wicket-library.com/wicket-examples/upload/?wicket:interface=:0:3:::
 * Edited by: Miel Zozaya Garcia
 */
package adf.page.Administrator;

import adf.model.Tool;
import adf.page.AdfApplication;
import adf.page.Professional.ToolListView;
import java.io.File;
import java.util.List;
import org.apache.wicket.Application;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Folder;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Administrator/AdministrateTools")
@AuthorizeInstantiation({"ROLE_admin"})
public class AdministrateTools extends AdministrationBasePage {

    private Model nameModel = new Model();
    private Model descriptionModel = new Model();
    private Model srcModel = new Model();

    public AdministrateTools() {
        super(AdministrateTools.class);

        final List<Tool> toolList = getAdfService().getTools();

        // add the list of tools already in the system
        border.add(new ToolListView("tools", toolList));

        border.add(new Label("dir", getUploadFolder().getAbsolutePath()));

        // add the form to upload a new tool
        Form fileUploadForm = new Form("uploadForm") {

            @Override
            protected void onSubmit() {
                final FileUpload upload =  (FileUpload) srcModel.getObject();
                if (upload != null) {
                    // Create a new file
                    File newFile = new File(getUploadFolder(), upload.getClientFileName());

                    // Check new file, promt error if it does
                    if(!checkFileExists(newFile, toolList)) return;

                    try {
                        // Save to new file
                        newFile.createNewFile();
                        upload.writeTo(newFile);

//                        UploadPage.this.info("saved file: " + upload.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Unable to write file");
                    }

                    // Safe information to database
                    Tool tool = new Tool();
                    tool.setName((String)nameModel.getObject());
                    tool.setDescription((String)descriptionModel.getObject());
                    tool.setSrc((String)upload.getClientFileName());

                    getAdfService().addTool(tool);
                    setResponsePage(AdministrateTools.class);
                }
            }

            private boolean checkFileExists(File newFile, List<Tool> toolList) {
                if (newFile.exists()) {
                    // Try to delete the file
                    error("File already uploaded!");
                    return false;
                }
                for(Tool tool: toolList){
                    if(newFile.getName().equals(tool.getSrc())){
                        error("File name already in the database!");
                        return false;
                    }
                }
                return true;
            }
        };
        fileUploadForm.setMultiPart(true);
        fileUploadForm.add(new UploadProgressBar("progress", fileUploadForm));
        border.add(fileUploadForm);

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        fileUploadForm.add(feedback);

        TextField name = new TextField("name", nameModel);
        name.setRequired(true);
        fileUploadForm.add(name);

        TextArea description = new TextArea("description", descriptionModel);
        description.setRequired(true);
        fileUploadForm.add(description);

        FileUploadField fileUploadField = new FileUploadField("fileInput", srcModel);
        fileUploadField.setRequired(true);
        fileUploadForm.add(fileUploadField);
    }

    private Folder getUploadFolder() {
        return ((AdfApplication) Application.get()).getUploadFolder();
    }
}
