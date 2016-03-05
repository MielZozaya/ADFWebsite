/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Trash;

import adf.service.AdfService;
import adf.service.MailComposer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author miel
 */
public class TestMailSender {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdfService adfService = (AdfService) context.getBean("adfService");
        adfService.sendEmail("xxxxxx@gmail.com" , MailComposer.sendInvitationSubject(), MailComposer.sendInvitationBody("Miel Zozaya", "http://www.google.com"));
    }
}
