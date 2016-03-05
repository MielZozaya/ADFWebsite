package adf.service;

import adf.model.ADFProgramAnswers;
import adf.model.AdfUser;
import adf.model.ClientCategory;
import adf.model.ClientForumMessage;
import adf.model.ForumMessage;
import adf.model.InboxMessage;
import adf.model.ProfessionalLoginPetitionForm;
import adf.model.RegistrationInformation;
import adf.model.Tool;
import adf.model.ViewHistory;
import java.util.List;

public interface AdfService
{
    public boolean authenticate(String username, String password);

    public void acceptProfessionalLoginPetition(String petitionId, String url);

    public void addADFProgramAnswer(String username, Object answer, Class exerciseClass);

    public void addCategoryToClientForumMessage(String name, Long messageId);

    public void addClientCategory(String catName, String catDesc);

    public void addClientForumMessage(String title, String body, String username, String category);

    public void addClientForumMessageReply(String title, String body, String username, Long messageId);

    public boolean addInboxMessage(String message, String recieverName, String senderName);

    public boolean addProfessionalLoginPetitionForm(ProfessionalLoginPetitionForm petition);

    public void addTool(Tool tool);

    public void addViewHistory(ViewHistory viewHistory);

    public boolean changePassword(String oldPassword, String newPassword, String userName);

    public void declineProfessionalLoginPetition(String petitionId);

    public void deleteCategoryFromClientForumMessage(String name, Long messageId);

    public void deleteClientCategory(String name);

    public void deleteInboxMessage(Long id, String userName);

    public void deleteTool(Long id);

    public void editClientForumMessage(String title, String body, Long messageId);

    public ADFProgramAnswers getADFProgramAnswers(Class exerciseClass, String username);

    public ADFProgramAnswers getAllADFProgramAnswers(String username, String requester);

    public AdfUser getAdfUser(String userName);

    public ClientForumMessage getClientForumMessage(Long id);

    public List<ClientCategory> getClientCategories();

    public List<? extends ForumMessage> getClientForumMessagesByCategory(String catname);

    public List<AdfUser> getClientsForProfessional(String userName);

    public ProfessionalLoginPetitionForm getProfessionalLoginPetitionForm(String petitionId);

    public List<ProfessionalLoginPetitionForm> getProfessionalLoginPetitionForms();

    public List<InboxMessage> getInboxMessages(String username);

    public List<Tool> getTools();

    public List<ViewHistory> getViewHistories();

    public boolean isUserRegistered(String userName);

    public boolean registerClient(RegistrationInformation registrationInformation);

    public boolean registerProfessional(RegistrationInformation registrationInformation);

    public void sendInvitation(String userName, String email, String url);

    public void updateAdfUser(AdfUser user);


    public void dropDB();
    public void createDB();

    public void sendEmail(String to, String subject, String msg);




}

