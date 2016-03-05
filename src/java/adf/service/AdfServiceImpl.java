package adf.service;

import adf.dao.*;
import adf.model.ADFProgramAnswers;
import adf.model.AdfUser;
import adf.model.ClientCategory;
import adf.model.ClientForumMessage;
import adf.model.ExerciseAnswers.*;
import adf.model.ForumMessage;
import adf.model.InboxMessage;
import adf.model.ProfessionalLoginPetitionForm;
import adf.model.RegistrationInformation;
import adf.model.Role;
import adf.model.Tool;
import adf.model.ViewHistory;

import com.Ostermiller.util.RandPass;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.springframework.mail.MailSender;


import org.apache.log4j.Logger;
import org.acegisecurity.DisabledException;
import org.hibernate.Hibernate;
import org.springframework.mail.SimpleMailMessage;

public class AdfServiceImpl implements AdfService {

    DatabaseDAO databaseDAO;
    ADFProgramAnswersDAO adfProgramAnswersDAO;
    ADFProgramFeedbackDAO adfProgramFeedbackDAO;
    RoleDAO roleDAO;
    AdfUserDAO adfUserDAO;
    CategoryDAO clientCategoryDAO;
    ForumMessageDAO clientForumMessageDAO;
    InboxMessageDAO inboxMessageDAO;
    WebsiteFeedbackDAO websiteFeedbackDAO;
    ViewHistoryDAO viewHistoryDAO;
    ProfessionalLoginPetitionFormDAO professionalLoginPetitionFormDAO;
    ToolDAO toolDAO;
    private MailSender mailSender;
    private RandPass randPass;
    private ProviderManager authenticationManager;
    private PasswordEncoder encoder;
    private static final Logger logger = Logger.getLogger(AdfServiceImpl.class.getCanonicalName());

    public AdfServiceImpl(
            DatabaseDAO databaseDAO,
            ADFProgramAnswersDAO adfProgramAnswersDAO,
            ADFProgramFeedbackDAO adfProgramFeedbackDAO,
            RoleDAO roleDAO,
            AdfUserDAO adfUserDAO,
            CategoryDAO clientCategoryDAO,
            ForumMessageDAO clientForumMessageDAO,
            InboxMessageDAO inboxMessageDAO,
            WebsiteFeedbackDAO websiteFeedbackDAO,
            ViewHistoryDAO viewHistoryDAO,
            ProfessionalLoginPetitionFormDAO professionalLoginPetitionFormDAO,
            ToolDAO toolDAO,
            MailSender mailSender,
            RandPass randPass,
            ProviderManager authenticationManager,
            PasswordEncoder encoder) {
        this.databaseDAO = databaseDAO;
        this.adfProgramAnswersDAO = adfProgramAnswersDAO;
        this.adfProgramFeedbackDAO = adfProgramFeedbackDAO;
        this.roleDAO = roleDAO;
        this.adfUserDAO = adfUserDAO;
        this.clientCategoryDAO = clientCategoryDAO;
        this.clientForumMessageDAO = clientForumMessageDAO;
        this.inboxMessageDAO = inboxMessageDAO;
        this.websiteFeedbackDAO = websiteFeedbackDAO;
        this.viewHistoryDAO = viewHistoryDAO;
        this.professionalLoginPetitionFormDAO = professionalLoginPetitionFormDAO;
        this.toolDAO = toolDAO;
        this.mailSender = mailSender;
        this.randPass = randPass;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }

    public boolean authenticate(String username, String password) {
        String u = username == null ? "" : username.trim();
        String p = password == null ? "" : password.trim();

        // Create an Acegi authentication request.
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(u, p);

        Object principal = authRequest.getPrincipal();
        logger.debug("Attempting to authenticate: " + principal);
        try {
            Authentication auth = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.info("Login by user '" + principal + "'.");
            return true;
        } catch (BadCredentialsException e) {
            logger.info("Failed login by user '" + principal + "' becaouse bad Credentials.");
            SecurityContextHolder.getContext().setAuthentication(null);
            return false;
        } catch (DisabledException e) {
            logger.info("Failed login by user '" + principal + "' because is disabled.");
            SecurityContextHolder.getContext().setAuthentication(null);
            return false;
        } catch (AuthenticationException e) {
            logger.warn("Could not authenticate a user");
            SecurityContextHolder.getContext().setAuthentication(null);
            return false;
        } catch (RuntimeException e) {
            logger.error("Unexpected exception while authenticating a user", e);
            SecurityContextHolder.getContext().setAuthentication(null);
            throw e;
        }
    }

    /**
     * Accepts a petition. Sends a acceptation email with a url that direct to the registration
     * @param petitionId
     *          Idenfitier of the petition
     * @param url
     *          Url to whom the user must be directed to compleate the registration
     */
    public void acceptProfessionalLoginPetition(String petitionId, String url) {
        ProfessionalLoginPetitionForm petition = professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForm(petitionId);
        if (petition != null) {
            petition.setAcceptedPetition(true);
            professionalLoginPetitionFormDAO.update(petition);
            sendEmail(petition.getEmail(), MailComposer.acceptPetitionSubject(), MailComposer.acceptPetitionBody(petition.getName(), url));
            logger.info("Professional Login Petition accepted. Email: "+petition.getId());
        }
    }

    public void addADFProgramAnswer(String username, Object answer, Class exerciseClass) {
        ADFProgramAnswers adfProgramAnswers = adfUserDAO.getAdfUser(username).getAdfProgramAnswers();
        switch (ADFProgramAnswers.getExerciseClassIndex(exerciseClass)) {
            case 0: {
                adfProgramAnswers.setExercise1Ans((Exercise1Ans) answer);
                break;
            }
            case 1: {
                adfProgramAnswers.addExercise2Ans((Exercise2Ans) answer);
                break;
            }
            case 2: {
                adfProgramAnswers.addExercise3Ans((Exercise3Ans) answer);
                break;
            }
            case 3: {
                adfProgramAnswers.addExercise4Ans((Exercise4Ans) answer);
                break;
            }
            case 4: {
                adfProgramAnswers.addExercise5aAns((Exercise5aAns) answer);
                break;
            }
            case 5: {
                adfProgramAnswers.addExercise5bAns((Exercise5bAns) answer);
                break;
            }
            case 6: {
                adfProgramAnswers.addExercise6Ans((Exercise6Ans) answer);
                break;
            }
            case 7: {
                adfProgramAnswers.addExercise7Ans((Exercise7Ans) answer);
                break;
            }
            case 8: {
                adfProgramAnswers.addExercise8Ans((Exercise8Ans) answer);
                break;
            }
            case 9: {
                adfProgramAnswers.addExercise9aAns((Exercise9aAns) answer);
                break;
            }
            case 10: {
                adfProgramAnswers.addExercise9bAns((Exercise9bAns) answer);
                break;
            }
            case 11: {
                adfProgramAnswers.addExercise9cAns((Exercise9cAns) answer);
                break;
            }
            case 12: {
                adfProgramAnswers.addExercise10Ans((Exercise10Ans) answer);
                break;
            }
            case 13: {
                adfProgramAnswers.addExercise11Ans((Exercise11Ans) answer);
                break;
            }
            case 14: {
                adfProgramAnswers.addExercise12Ans((Exercise12Ans) answer);
                break;
            }
        }
        adfProgramAnswersDAO.update(adfProgramAnswers);
        logger.info("Adf program answer added by: "+username);
    }

    /**
     * Adds a the ClientForumMessage to a ClientCategory
     * @param name
     *          ClientCategory name
     * @param messageId
     *          ClientCategory messageId
     */
    public void addCategoryToClientForumMessage(String name, Long messageId) {
        ClientCategory category = (ClientCategory) clientCategoryDAO.loadCategoryByName(name);
        ClientForumMessage forumMessage = (ClientForumMessage) clientForumMessageDAO.getForumMessage(messageId);
        forumMessage.addCategory(category);
        clientForumMessageDAO.update(forumMessage);

        /*Send Notifications*/
        for (String impliedUsername : getImpliedUsersInForumMessage(forumMessage)) {
            addInboxMessage("Message '" + forumMessage.getTitle() + "' has been added to category '" + category.getName() + "'", impliedUsername, null);
        }

        logger.info("Client Forum Message added to category: "+category.getName()+". ClientForumMessage: +"+forumMessage.toString());
    }

    /**
     * Adds a new category to the Clients Forum
     * @param catName
     *          Name of the category
     * @param catDesc
     *          Description of the category
     */
    public void addClientCategory(String catName, String catDesc) {
        clientCategoryDAO.save(new ClientCategory(catName, catDesc));
        logger.info("Client Category added. category: "+catName);
    }

    /**
     * Adds a Message in the Client forum for the mentioned user and category
     * @param title
     *          Title of the message
     * @param body
     *          Body of the message
     * @param username
     *          User sending the message
     * @param category
     *          Category where the message will be published
     */
    public void addClientForumMessage(String title, String body, String username, String category) {
        AdfUser user = adfUserDAO.getAdfUser(username);
        ClientCategory cat = (ClientCategory) clientCategoryDAO.loadCategoryByName(category);
        ClientForumMessage clientForumMessage = new ClientForumMessage(title, body, new Date(System.currentTimeMillis()));
        clientForumMessage.setSender(user);
        clientForumMessage.addCategory(cat);

        clientForumMessageDAO.save(clientForumMessage);

        logger.info("Client Forum Message added. "+clientForumMessage.toString());
    }

    /**
     * Adds a Message in the Client forum for the mentioned user and category
     * @param title
     *          Title of the message
     * @param body
     *          Body of the message
     * @param username
     *          User sending the message
     * @param parent_Id
     *          ID of the parent message replying to
     */
    public void addClientForumMessageReply(String title, String body, String username, Long messageId) {
        AdfUser user = adfUserDAO.getAdfUser(username);
        ClientForumMessage parentMessage = (ClientForumMessage) clientForumMessageDAO.getForumMessage(messageId);
        ClientForumMessage clientForumMessage = new ClientForumMessage(title, body, new Date(System.currentTimeMillis()));
        clientForumMessage.setSender(user);
        parentMessage.addChild(clientForumMessage);
        clientForumMessage.setCategories(parentMessage.getCategories());
        clientForumMessageDAO.save(clientForumMessage);

        /*Send Notifications*/
        for (String impliedUsername : getImpliedUsersInForumMessage(parentMessage)) {
            addInboxMessage("'" + user.getNickname() + "' has replied to message '" + parentMessage.getTitle() + "'", impliedUsername, null);
        }

        logger.info("Client Forum Message reply added. "+clientForumMessage.toString());
    }

    /**
     * Sends a InboxMessage for a user
     * @param message
     *          Message to be sent
     * @param recieverName
     *          Username of the reciever
     * @param senderName
     *          Username of the sender. If the sender is the 'system' set this to null
     */
    public boolean addInboxMessage(String message, String recieverName, String senderName) {
        AdfUser sender = senderName == null ? null : adfUserDAO.getAdfUser(senderName);
        AdfUser reciever = adfUserDAO.getAdfUser(recieverName);

        // Check if the sender has permissions to send messages to the reciever
        if (sender != null) {
            if (sender.isProfessionalLevel()) {
                Hibernate.initialize(sender.getClients());
                if (!sender.getClients().contains(reciever)) {
                    logger.info("Inbox Message sending trial to user: "+recieverName);
                    return false;
                }
            } else {
                if (!sender.getProfessional().getUsername().equals(reciever.getUsername())) {
                    logger.info("Inbox Message sending trial to user: "+recieverName);
                    return false;
                }
            }

        }

        InboxMessage newmessage = new InboxMessage(message, new Date(System.currentTimeMillis()));
        newmessage.addSender(sender);
        newmessage.addReceiver(reciever);
        inboxMessageDAO.save(newmessage);
        logger.info("Inbox Message added. Receiver: "+reciever.getUsername()+", Sender: "+senderName+" Message: "+message );
        return true;
    }

    /**
     * Adds a new professional login petition to the system
     * @param petition
     *      petition that will be added
     */
    public boolean addProfessionalLoginPetitionForm(ProfessionalLoginPetitionForm petition) {
        if(professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForm(petition.getId()) != null){
            return false;
        }
        professionalLoginPetitionFormDAO.save(petition);
        logger.info("New professional petition added. Id: "+petition.getId());
        return true;
    }

    /**
     * Saves the tool in the database
     * @param tool
     *      Tool object to be saved
     */
    public void addTool(Tool tool) {
        toolDAO.save(tool);
        logger.info("Tool saved. Name: "+tool.getName()+" Source: "+tool.getSrc()+" Desc: "+tool.getDescription());
    }

    /**
     * Adds a history entry on the database
     * @param viewHistory
     */
    public void addViewHistory(ViewHistory viewHistory) {
        viewHistoryDAO.save(viewHistory);
        logger.debug("ViewHistory added.");
    }

    /**
     * Changes the password of a user. Returns false if the old password is not correct
     * @param oldPassword
     *      Old password of the user
     * @param newPassword
     *      New password that is desired to set
     * @param userName
     *      Username of the user to set the password
     * @return
     */
    public boolean changePassword(String oldPassword, String newPassword, String userName) {
        AdfUser user = adfUserDAO.getAdfUser(userName);

        if (encoder.encodePassword(oldPassword, user.getUsername()).equals(user.getPassword())) {
            user.setPassword(encoder.encodePassword(newPassword, user.getUsername()));
            adfUserDAO.update(user);
            logger.info("AdfUser '" + user.getUsername() + "' changed its password");
            return true;
        }
        return false;
    }

    /**
     * Declines a petition. Sends a declination email and deletes the petition
     * @param petitionId
     *      Identifier of the petition to be declined
     */
    public void declineProfessionalLoginPetition(String petitionId) {
        ProfessionalLoginPetitionForm petition = professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForm(petitionId);
        if (petition != null) {
            sendEmail(petition.getEmail(), MailComposer.declinePetitionSubject(), MailComposer.declinePetitionBody(petition.getName()));
            logger.info("Professional petition declined. Email: "+petition.getEmail()+"; Name: "+petition.getName()+" "+petition.getMiddleName()+" "+petition.getSurname());
            professionalLoginPetitionFormDAO.delete(petition);
        }
    }

    /**
     * Deletes a category from the Clients Forum
     * @param catName
     *          Name of the category
     * @param catDesc
     *          Description of the category
     */
    public void deleteCategoryFromClientForumMessage(String name, Long messageId) {
        ClientCategory category = (ClientCategory) clientCategoryDAO.loadCategoryByName(name);
        if (!category.getName().equals("All")) {
            ClientForumMessage forumMessage = (ClientForumMessage) clientForumMessageDAO.getForumMessage(messageId);
            forumMessage.deleteCategory(category);
            clientForumMessageDAO.update(forumMessage);

            logger.info("Deleted category '"+category.getName()+"' from forum message with id '"+ forumMessage.getId()+"'");
            /*Send Notifications*/
            for (String impliedUsername : getImpliedUsersInForumMessage(forumMessage)) {
                addInboxMessage("Message '" + forumMessage.getTitle() + "' has been removed from category '" + category.getName() + "'", impliedUsername, null);
            }
        }
    }

    /**
     * Deletes a ClientCategory
     * @param name
     *          Name of the category to be deleted
     */
    public void deleteClientCategory(String name) {
        clientCategoryDAO.delete(clientCategoryDAO.loadCategoryByName(name));
        logger.info("Category '" + name + "' deleted.");
    }

    /**
     * Deletes an InboxMessage
     * @param id
     *          Id of the Message
     * @param userName
     *          Username of the user executing the action
     */
    public void deleteInboxMessage(Long id, String userName) {
        AdfUser user = adfUserDAO.getAdfUser(userName);
        InboxMessage message = inboxMessageDAO.getInboxMessageById(id);

        if (user.getUsername().equals(message.getReceiver().getUsername())) {
            logger.debug("Deleting" + message);
            inboxMessageDAO.delete(message);
        }
    }

    /**
     * Deletes the given tool
     * @param id
     *      Identifier of the tool to be deleted
     */
    public void deleteTool(Long id) {
        Tool tool = toolDAO.getTool(id);
        logger.info("Deleting Tool with id: "+id+". File referenced: "+tool.getSrc());
        toolDAO.delete(tool);
    }

    /**
     * Edits a message on the Clients Forum
     * @param title
     * @param body
     * @param messageId
     */
    public void editClientForumMessage(String title, String body, Long messageId) {
        ClientForumMessage forumMessage = (ClientForumMessage) clientForumMessageDAO.getForumMessage(messageId);
        forumMessage.setTitle(title);
        forumMessage.setMessage(body);
        clientForumMessageDAO.update(forumMessage);
        logger.info("ClientForumMessage with id '" + messageId + "' was edited.");
    }

    /**
     * Call that returns all the ADF program answers of the user with the username
     * specified in the parameter. 
     * @param username
     *      Username of the user from which the answers will be getted
     * @param requester
     *      Username of the user that is requesting the information
     * @return
     *      ADFProgramAnswers object with all the fields initialized. If the requester
     *          has no permissions a null object will be returned.
     */
    public ADFProgramAnswers getAllADFProgramAnswers(String username, String requester) {
        // Check if the requester is allowed to get the information
        AdfUser user = adfUserDAO.getAdfUser(username);
        if (!user.getUsername().equals(requester)) {
            AdfUser professional = user.getProfessional();
            if (professional == null || !professional.getUsername().equals(requester)) {
                logger.error("Access tried to '"+username+"' answers without permissions");
                return null;
            }
        }

        // Get the Object
        ADFProgramAnswers adfProgramAnswers = user.getAdfProgramAnswers();
        Hibernate.initialize(adfProgramAnswers.getExercise1Ans());
        Hibernate.initialize(adfProgramAnswers.getExercise2AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise3AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise4AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise5aAnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise5bAnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise6AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise7AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise8AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise9aAnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise9bAnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise9cAnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise10AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise11AnsList());
        Hibernate.initialize(adfProgramAnswers.getExercise12AnsList());
        return adfProgramAnswers;
    }

    /**
     * This method gets a ADFProgramAnswers object for a user. This answers will
     *  only contain the exercises type corresponding to the exercise class
     *  given as an argument. The other exercise fields of the object will not be
     *  initialised.
     * @param exerciseClass
     *          Class corresponding to the exercise that we want to get.
     * @param username
     *          Username of the user from which to load the object.
     * @return
     *          ADFProgramAnswers type object.
     */
    public ADFProgramAnswers getADFProgramAnswers(Class exerciseClass, String username) {
        ADFProgramAnswers adfProgramAnswers = adfUserDAO.getAdfUser(username).getAdfProgramAnswers();
        switch (ADFProgramAnswers.getExerciseClassIndex(exerciseClass)) {
            case 0: {
                Hibernate.initialize(adfProgramAnswers.getExercise1Ans());
                break;
            }
            case 1: {
                Hibernate.initialize(adfProgramAnswers.getExercise2AnsList());
                break;
            }
            case 2: {
                Hibernate.initialize(adfProgramAnswers.getExercise3AnsList());
                break;
            }
            case 3: {
                Hibernate.initialize(adfProgramAnswers.getExercise4AnsList());
                break;
            }
            case 4: {
                Hibernate.initialize(adfProgramAnswers.getExercise5aAnsList());
                break;
            }
            case 5: {
                Hibernate.initialize(adfProgramAnswers.getExercise5bAnsList());
                break;
            }
            case 6: {
                Hibernate.initialize(adfProgramAnswers.getExercise6AnsList());
                break;
            }
            case 7: {
                Hibernate.initialize(adfProgramAnswers.getExercise7AnsList());
                break;
            }
            case 8: {
                Hibernate.initialize(adfProgramAnswers.getExercise8AnsList());
                break;
            }
            case 9: {
                Hibernate.initialize(adfProgramAnswers.getExercise9aAnsList());
                break;
            }
            case 10: {
                Hibernate.initialize(adfProgramAnswers.getExercise9bAnsList());
                break;
            }
            case 11: {
                Hibernate.initialize(adfProgramAnswers.getExercise9cAnsList());
                break;
            }
            case 12: {
                Hibernate.initialize(adfProgramAnswers.getExercise10AnsList());
                break;
            }
            case 13: {
                Hibernate.initialize(adfProgramAnswers.getExercise11AnsList());
                break;
            }
            case 14: {
                Hibernate.initialize(adfProgramAnswers.getExercise12AnsList());
                break;
            }
        }
        return adfProgramAnswers;
    }

    /**
     * This method returns an AdfUser object related with the username passed as an argument.
     * @param userName
     *      Username of the user
     * @return
     */
    public AdfUser getAdfUser(String userName) {
        AdfUser user = adfUserDAO.getAdfUser(userName);
        Hibernate.initialize(user.getProfessional());
        return user;
    }

    /**
     * Loads the list of categories for clients
     * @return
     */
    public List<ClientCategory> getClientCategories() {
        logger.debug("Accessing list of Client Categories");
        List<ClientCategory> clientCatList = new ArrayList<ClientCategory>();
        clientCatList.addAll((Collection) clientCategoryDAO.getCategory());
        return clientCatList;
    }

    public ClientForumMessage getClientForumMessage(Long messageId) {
        return (ClientForumMessage) clientForumMessageDAO.getForumMessage(messageId);
    }

    /**
     * Loads the list of messges from the client forum given a certain category
     * @param catname
     * @return
     */
    public List<? extends ForumMessage> getClientForumMessagesByCategory(String catname) {
        logger.debug("Accessing Client Forum Messages for category '" + catname + "'");
        ClientCategory category = (ClientCategory) clientCategoryDAO.loadCategoryByName(catname);
        List<ClientForumMessage> clientMessages = new ArrayList<ClientForumMessage>();
        clientMessages.addAll((Collection) clientForumMessageDAO.getForumMessagesByCategory(category));
        return clientMessages;
    }

    /**
     * Returns the list of clients that the professional has
     * @param userName
     *      Username of the professional
     * @return
     */
    public List<AdfUser> getClientsForProfessional(String userName) {
        AdfUser user = adfUserDAO.getAdfUser(userName);
        if (user.isProfessionalLevel()) {
            Hibernate.initialize(user.getClients());
            List<AdfUser> clientList = new ArrayList<AdfUser>();
            clientList.addAll((Collection) user.getClients());
            return clientList;
        } else {
            logger.error("Access tried to getClientsForProfessional with not professional username");
            return new ArrayList<AdfUser>();
        }
    }

    /**
     * Loads InboxMessages for a user
     * @param username
     *          Name of the user
     * @return
     *          List of InboxMessages
     */
    public List<InboxMessage> getInboxMessages(String username) {
        return inboxMessageDAO.getInboxMessageForUser(username);
    }

    /**
     * Returns an specific professional login petition form
     * @param petitionId
     *      Identifier of the petition that is wanted
     * @return
     */
    public ProfessionalLoginPetitionForm getProfessionalLoginPetitionForm(String petitionId) {
        return professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForm(petitionId);
    }

    /**
     * Returns a list containing all the professional login petitions
     * @return
     */
    public List<ProfessionalLoginPetitionForm> getProfessionalLoginPetitionForms() {
        return professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForms();
    }

    /**
     * Returns the list of Tool objects in the database
     * @return
     */
    public List<Tool> getTools() {
        return toolDAO.getTools();
    }

    /**
     * Gets all the recorded user activity
     * @return
     */
    public List<ViewHistory> getViewHistories() {
        return viewHistoryDAO.getViewHistories();
    }

    /**
     * Checks if the user with the stated username exists
     * @param username
     *      Username of the user.
     * @return
     */
    public boolean isUserRegistered(String username) {
        return adfUserDAO.isAdfUserRegistered(username);
    }

    /**
     * Registers a new client and sends the client its password to login
     * @param regInfo
     *      RegistrationInformation ofbject.
     * @return
     */
    public boolean registerClient(RegistrationInformation regInfo) {
        String password;
        try {
            // Check if the username is not already in use
            if (isUserRegistered(regInfo.getUsername())) {
                return false;
            }

            AdfUser client = new AdfUser(regInfo.getUsername(), false, regInfo.getEmail(), regInfo.getTelephone(), new Date(System.currentTimeMillis()));
            password = randPass.getPass(10);
            client.setPassword(encoder.encodePassword(password, client.getUsername()));
            client.setRole(roleDAO.get("ROLE_client"));
            client.setProfessionalLevel(false);
            client.setNickname(regInfo.getUsername());
            adfUserDAO.save(client);
            client.setDemographics(regInfo.getDemographics());



            if (regInfo.getProfessionalUsername() != null) {
                AdfUser professional = adfUserDAO.getAdfUser(regInfo.getProfessionalUsername());
                if (professional.isProfessionalLevel()) {
                    professional.addClient(client);
                    adfUserDAO.update(professional);
                    addInboxMessage("You have a new client", professional.getUsername(), null);
                }
            }

            addInboxMessage("WELCOME", client.getUsername(), null);

            sendEmail(regInfo.getEmail(), MailComposer.registrationSubject(), MailComposer.registrationBody(regInfo.getUsername(), password));

            logger.info("Registrated new client with username: " + client.getUsername());

        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    /**
     * Registers a new professional and sends the professional its password to login
     * @param regInfo
     *      RegistrationInformation ofbject.
     * @return
     */
    public boolean registerProfessional(RegistrationInformation regInfo) {
        String password;
        try {
            if (isUserRegistered(regInfo.getUsername())) {
                return false;
            }

            if (regInfo.getProfessionalPetitionId() == null) {
                return false;
            }

            ProfessionalLoginPetitionForm petition = professionalLoginPetitionFormDAO.getProfessionalLoginPetitionForm(regInfo.getProfessionalPetitionId());

            AdfUser professional = new AdfUser(regInfo.getUsername(), true, regInfo.getEmail(), regInfo.getTelephone(), new Date(System.currentTimeMillis()));
            password = randPass.getPass(10);
            professional.setPassword(encoder.encodePassword(password, professional.getUsername()));
            professional.setRole(roleDAO.get("ROLE_professional"));
            professional.setProfessionalLevel(true);
            professional.setNickname(regInfo.getUsername());
            adfUserDAO.save(professional);
            professional.setDemographics(regInfo.getDemographics());
            professional.setProfessionalLoginPetitionForm(petition);

            sendEmail(regInfo.getEmail(), MailComposer.registrationSubject(), MailComposer.registrationBody(regInfo.getUsername(), password));

            addInboxMessage("WELCOME", professional.getUsername(), null);

            logger.info("Registrated new professional with username: " + professional.getUsername());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    /**
     * Sends a invitation to the registration to the email stated.
     * @param userName
     *      Username of the professional sending the invitation
     * @param email
     *      Email of the client to receive the invitation
     * @param url
     *      Url to procide with the registration that will link the professional and the client
     */
    public void sendInvitation(String userName, String email, String url) {
        AdfUser professional = adfUserDAO.getAdfUser(userName);
        Hibernate.initialize(professional.getProfessionalLoginPetitionForm());
        String professionalName;

        if (professional.getProfessionalLoginPetitionForm() == null) {
            professionalName = "A Psychology professional";
        } else {
            professionalName = professional.getProfessionalLoginPetitionForm().getName()
                    + " " + professional.getProfessionalLoginPetitionForm().getMiddleName()
                    + " " + professional.getProfessionalLoginPetitionForm().getSurname();
        }

        sendEmail(email, MailComposer.sendInvitationSubject(), MailComposer.sendInvitationBody(professionalName, url));

        logger.info("Invitation sent from '" + professional.getUsername() + "' to: " + email);
    }

    /**
     * Updates a user
     * @param user
     *      User element to be updated
     */
    public void updateAdfUser(AdfUser user) {
        AdfUser loadeduser = adfUserDAO.getAdfUser(user.getUsername());

        // Change values (that are directly changeable for the users)
        loadeduser.setDenyProfessionalContact(user.isDenyProfessionalContact());
        loadeduser.setDenyProfessionalSupervising(user.isDenyProfessionalSupervising());
        loadeduser.setNickname(user.getNickname());

        logger.info("AdfUser '" + user.getUsername() + "' changed account information");
        adfUserDAO.update(loadeduser);
    }

    public void populateDB() {
        Role clientRole = new Role("ROLE_client", "client role");
        Role professionalRole = new Role("ROLE_professional", "professional role");
        Role administratorRole = new Role("ROLE_admin", "administrator role");

        roleDAO.save(clientRole);
        roleDAO.save(professionalRole);
        roleDAO.save(administratorRole);

        AdfUser clientA = new AdfUser("clientA", false, "clientA@gmial.com", "0777888999", new Date(System.currentTimeMillis()));
        clientA.setPassword(encoder.encodePassword("clientApass", clientA.getUsername()));
        clientA.setNickname("clientAnickname");
        clientA.setRole(clientRole);
        adfUserDAO.save(clientA);

        AdfUser clientB = new AdfUser("clientB", false, "clientB@gmial.com", "0777888999", new Date(System.currentTimeMillis()));
        clientB.setPassword(encoder.encodePassword("clientBpass", clientB.getUsername()));
        clientB.setNickname("clientBnickname");
        clientB.setRole(clientRole);
        adfUserDAO.save(clientB);


        AdfUser profA = new AdfUser("profA", true, "profA@gmial.com", "0777888999", new Date(System.currentTimeMillis()));
        profA.setRole(professionalRole);
        profA.setNickname("profAnickname");
        profA.setPassword(encoder.encodePassword("profApass", profA.getUsername()));
        adfUserDAO.save(profA);
        profA.addClient(clientA);
        profA.addClient(clientB);
        adfUserDAO.update(profA);

        AdfUser profB = new AdfUser("profB", true, "profB@gmial.com", "0777888999", new Date(System.currentTimeMillis()));
        profB.setRole(professionalRole);
        profB.setNickname("profBnickname");
        profB.setPassword(encoder.encodePassword("profBpass", profB.getUsername()));
        adfUserDAO.save(profB);

        AdfUser superadmin = new AdfUser("superadmin", true, "superadmin@gmial.com", "0777888999", new Date(System.currentTimeMillis()));
        superadmin.setRole(administratorRole);
        superadmin.setNickname("Admin");
        superadmin.setPassword(encoder.encodePassword("superadminpass", superadmin.getUsername()));
        adfUserDAO.save(superadmin);

        ClientCategory problems = new ClientCategory("Problems", "Problems category");
        ClientCategory behaviour = new ClientCategory("Help with behaviours", "behaviours category");
        ClientCategory support = new ClientCategory("Support", "Support category");
        ClientCategory all = new ClientCategory("All", "all messages category");
        clientCategoryDAO.save(problems);
        clientCategoryDAO.save(behaviour);
        clientCategoryDAO.save(support);

        ClientForumMessage m1 = new ClientForumMessage("title1", "message1", new Date(System.currentTimeMillis() - 100000));
        m1.setSender(clientA);
        ClientForumMessage m11 = new ClientForumMessage("title1.1", "message1.1", new Date(System.currentTimeMillis() - 90000));
        m11.setSender(clientB);
        ClientForumMessage m111 = new ClientForumMessage("title1.1.1", "message1.1.1", new Date(System.currentTimeMillis() - 80000));
        m111.setSender(clientA);
        ClientForumMessage m12 = new ClientForumMessage("title1.2", "message1.2", new Date(System.currentTimeMillis() - 70000));
        m12.setSender(clientB);
        ClientForumMessage m112 = new ClientForumMessage("title1.1.2", "message1.1.2", new Date(System.currentTimeMillis() - 60000));
        m112.setSender(clientB);
        ClientForumMessage m13 = new ClientForumMessage("title1.3", "message1.3", new Date(System.currentTimeMillis() - 50000));
        m13.setSender(clientA);
        ClientForumMessage m131 = new ClientForumMessage("title1.3.1", "message1.3.1", new Date(System.currentTimeMillis() - 40000));
        m131.setSender(clientB);
        ClientForumMessage m2 = new ClientForumMessage("title2", "message2", new Date(System.currentTimeMillis() - 30000));
        m2.setSender(clientB);
        ClientForumMessage m3 = new ClientForumMessage("title3", "message3", new Date(System.currentTimeMillis() - 20000));
        m3.setSender(clientB);
        ClientForumMessage m31 = new ClientForumMessage("title3.1", "message3.1", new Date(System.currentTimeMillis() - 100000));
        m31.setSender(clientA);

        m11.addChild(m111);
        m11.addChild(m112);
        m13.addChild(m131);
        m1.addChild(m11);
        m1.addChild(m12);
        m1.addChild(m13);
        m3.addChild(m31);

        m1.addCategory(problems);
        m1.addCategory(support);
        m2.addCategory(behaviour);
        m3.addCategory(problems);
        m3.addCategory(support);
        m3.addCategory(behaviour);

        m1.addCategory(all);
        m2.addCategory(all);
        m3.addCategory(all);

        clientForumMessageDAO.save(m1);
        clientForumMessageDAO.save(m2);
        clientForumMessageDAO.save(m3);


    }

    public void createDB() {
        databaseDAO.createDB();
        populateDB();
    }

    public void dropDB() {
        databaseDAO.destroyDB();
    }

    /**
     * Returns a List of users implied in a ForumMessage discussion
     * @param parentMessage
     *      Parent Mensage in which the users are replied
     * @return
     *      List of usernames
     */
    private List<String> getImpliedUsersInForumMessage(ForumMessage parentMessage) {
        List<String> usernames = new ArrayList<String>();
        usernames.add(parentMessage.getSender().getUsername());
        for (ForumMessage message : parentMessage.getChildren()) {
            usernames.add(message.getSender().getUsername());
        }
        return usernames;
    }

    // from: http://cluff7.php0h.com/javamailsenderimpl-sample.html
    // edited by: Miel Zozaya Garcia
    public void sendEmail(String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(null);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}


