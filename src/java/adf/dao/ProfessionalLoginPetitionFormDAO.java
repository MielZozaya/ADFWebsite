/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.dao;

import adf.model.ProfessionalLoginPetitionForm;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ProfessionalLoginPetitionFormDAO {

    public List<ProfessionalLoginPetitionForm> getProfessionalLoginPetitionForms();

    public void save(ProfessionalLoginPetitionForm petition);

    public ProfessionalLoginPetitionForm getProfessionalLoginPetitionForm(String petitionId);

    public void update(ProfessionalLoginPetitionForm petition);

    public void delete(ProfessionalLoginPetitionForm petition);

}
