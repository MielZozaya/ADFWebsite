/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.service;

/**
 *
 * @author miel
 */
public class MailComposer {

    public static String sendInvitationSubject(){
        return "Invitation to join Alcohol, Drugs & Families!";
    }

    public static String sendInvitationBody(String professionalName, String url){
        return "Dear, \n" +
                "\n" +
                professionalName+" has invited you to join Alcohol, Drugs and Families website. Here you will be" +
                "able to find a many facilities that will help you. To register click the following link: " +
                url+".\n" +
                "If the link does not redirect you copy the link and paste it on your web browser.\n\n" +
                "Best regards,\n\n" +
                "Alcohol, Drugs and Families Team.";
    }

    public static String registrationSubject(){
        return "Registration compleate";
    }

    public static String registrationBody(String username, String password){
        return "PLEASE DO NOT REPLY TO THIS E-MAIL AS YOUR MESSAGE WILL GO NOWHERE!\n\n"+

"Thank you for registering at ADF, you can now log in with the following Username: "+username+" and password: "+password+"\n"+
 "We recommend that you change your password when you first log in, you can log in at www.alcoholdrugsandfamilies.nhs.uk";
    }

    public static String declinePetitionSubject(){
        return "Professional Login Petition";
    }

    public static String declinePetitionBody(String name){
        return "Dear "+name+",\n\n" +
                "We are sorry to inform you that your petition to join Alcohol, Drugs and Families website as a professional has been declined.\n\n" +
                "For any further enquiry please contact us,\n\n" +
                "Alcohol, Drugs and Families Team";
    }

    public static String acceptPetitionSubject(){
        return "Professional Login Petition";
    }

    public static String acceptPetitionBody(String name, String url){
        return "Dear "+name+",\n\n" +
                "We are glad to inform you that your petition to join Alcohol, Drugs and Families website as a professional has been accepted.\n\n" +
                "To continue with the registration please click the following link: "+url+"\n\n"+
                "For any further enquiry please contact us,\n\n" +
                "Alcohol, Drugs and Families Team";
    }


}
