/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.dao.hibernate;

import adf.dao.ProfessionalLoginPetitionFormDAO;
import adf.model.ProfessionalLoginPetitionForm;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ProfessionalLoginPetitionFormDAOImpl extends HibernateDaoSupport implements ProfessionalLoginPetitionFormDAO{

    public List<ProfessionalLoginPetitionForm> getProfessionalLoginPetitionForms() {
        return getHibernateTemplate().find("from ProfessionalLoginPetitionForm");
    }

    public void save(ProfessionalLoginPetitionForm petition) {
        getHibernateTemplate().save(petition);
    }

    public ProfessionalLoginPetitionForm getProfessionalLoginPetitionForm(String petitionId) {
        return (ProfessionalLoginPetitionForm) getHibernateTemplate().get(ProfessionalLoginPetitionForm.class, petitionId);
    }

    public void update(ProfessionalLoginPetitionForm petition) {
        getHibernateTemplate().update(petition);
    }

    public void delete(ProfessionalLoginPetitionForm petition) {
        getHibernateTemplate().delete(petition);
    }

}
