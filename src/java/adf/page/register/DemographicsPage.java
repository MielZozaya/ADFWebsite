/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.register;

import adf.model.RegistrationInformation;
import adf.model.Demographics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath ( path = "Register/Demographics")
public class DemographicsPage extends RegistrationBasePage {

    public DemographicsPage() {

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        border.add(feedback);

        Form form = new Form("form"){

            @Override
            protected void onSubmit() {
                Demographics demographics = new Demographics();
                demographics.setGender((String) genderModel.getObject());
                demographics.setAge(((Integer) ageModel.getObject()).intValue());
                demographics.setOccupation((String) occupationModel.getObject());
                demographics.setEthnicity((String)ethnicityModel.getObject());
                demographics.setReligion((String)religionModel.getObject());
                demographics.setCountry((String)religionModel.getObject());
                demographics.setProblemDuration((String)problemDurationModel.getObject());
                demographics.setTypeOfProblem((String)typeOfProblemModel.getObject());
                demographics.setIsRelativeMisUsing((String)isRelativeMisUsingModel.getObject());
                demographics.setRelativesGender((String)relativesGenderModel.getObject());
                demographics.setConvivienceDuration((String)convivienceDurationModel.getObject());
                demographics.setRelationWithAdict((String)relationWithAdictModel.getObject());
                demographics.setRelativesAge(((Integer)relativesAgeModel.getObject()).intValue());
                demographics.setAdictionChangeIn3Months((String)adictionChangeIn3MonthsModel.getObject());

                RegistrationInformation registrationInformation = getAdfSession().getRegistrationInformation();
                if(registrationInformation != null){
                    registrationInformation.setDemographics(demographics);
                    getAdfSession().setRegistrationInformation(registrationInformation);
                    setResponsePage(RegistrationComplete.class);
                } else {
                    setResponsePage(RegistrationError.class);
                }

            }

        };
        border.add(form);

        DropDownChoice gender = new DropDownChoice("gender", genderModel,genderList);
        gender.setRequired(true);
        form.add(gender);

        ageList = new ArrayList();
        for(int i = 16; i<100 ; i++) ageList.add(i);
        DropDownChoice age = new DropDownChoice("age", ageModel,ageList);
        age.setRequired(true);
        form.add(age);

        DropDownChoice occupation = new DropDownChoice("occupation", occupationModel,occupationList);
        occupation.setRequired(true);
        form.add(occupation);

        DropDownChoice ethnicity = new DropDownChoice("ethnicity", ethnicityModel,ethnicityList);
        ethnicity.setRequired(true);
        form.add(ethnicity);

        DropDownChoice religion = new DropDownChoice("religion", religionModel,religionList);
        religion.setRequired(true);
        form.add(religion);

        DropDownChoice country = new DropDownChoice("country", countryModel,countryList);
        country.setRequired(true);
        form.add(country);

        DropDownChoice problemDuration = new DropDownChoice("problemDuration", problemDurationModel,problemDurationList);
        problemDuration.setRequired(true);
        form.add(problemDuration);

        DropDownChoice typeOfProblem = new DropDownChoice("typeOfProblem", typeOfProblemModel,typeOfProblemList);
        typeOfProblem.setRequired(true);
        form.add(typeOfProblem);

        DropDownChoice isRelativeMisUsing = new DropDownChoice("isRelativeMisUsing", isRelativeMisUsingModel,isRelativeMisUsingList);
        isRelativeMisUsing.setRequired(true);
        form.add(isRelativeMisUsing);

        DropDownChoice relativesGender = new DropDownChoice("relativesGender", relativesGenderModel,relativesGenderList);
        relativesGender.setRequired(true);
        form.add(relativesGender);

        DropDownChoice convivienceDuration = new DropDownChoice("convivienceDuration", convivienceDurationModel,convivienceDurationList);
        convivienceDuration.setRequired(true);
        form.add(convivienceDuration);

        DropDownChoice relationWithAdict = new DropDownChoice("relationWithAdict", relationWithAdictModel,relationWithAdictList);
        relationWithAdict.setRequired(true);
        form.add(relationWithAdict);

        relativesAgeList = new ArrayList();
        for(int i = 10; i<100 ; i++) relativesAgeList.add(i);
        DropDownChoice relativesAge = new DropDownChoice("relativesAge", relativesAgeModel,relativesAgeList);
        relativesAge.setRequired(true);
        form.add(relativesAge);

        DropDownChoice adictionChangeIn3Months = new DropDownChoice("adictionChangeIn3Months", adictionChangeIn3MonthsModel,adictionChangeIn3MonthsList);
        adictionChangeIn3Months.setRequired(true);
        form.add(adictionChangeIn3Months);
    }

    private Model genderModel = new Model();
    private Model ageModel = new Model();
    private Model occupationModel = new Model();
    private Model ethnicityModel = new Model();
    private Model religionModel = new Model();
    private Model countryModel = new Model();
    private Model problemDurationModel = new Model();
    private Model typeOfProblemModel = new Model();
    private Model isRelativeMisUsingModel = new Model();
    private Model relativesGenderModel = new Model();
    private Model convivienceDurationModel = new Model();
    private Model relationWithAdictModel = new Model();
    private Model relativesAgeModel = new Model();
    private Model adictionChangeIn3MonthsModel = new Model();

    private List<String> genderList = Arrays.asList("Male", "Female");
    private List<Integer> ageList; // From 16 to 99
    private List<Integer> relativesAgeList; // From 10 to 99
    private List<String> occupationList = Arrays.asList("Full time employment",
            "Part time employment", "Retired", "Student", "Looking after home/family",
            "Permanently sick or disabled", "None of the above");
    private List<String> ethnicityList = Arrays.asList("White British",
            "White Irish", "Other White", "White and Black Caribbean", "White and Black African",
            "White and Asian", "Other mixed background", "Indian", "Pakistani",
            "Bangladeshi", "Any other Asian", "Caribbean", "African",
            "Any other black background", "Chinese", "Any Other");
    private List<String> religionList = Arrays.asList("None",
            "Christian (all denominations)", "Buddhist", "Hindu", "Jewish",
            "Muslim", "Sikh", "Other religion");
    private List<String> problemDurationList = Arrays.asList("1 to 6 months",
            "7 to 11 months", "1 to 2 years", "3 to 5 years", "6 to 9 years", "10 or more years");
    private List<String> typeOfProblemList = Arrays.asList("Drinking problem",
            "Drug problem", "Drinking and drug problem");
    private List<String> isRelativeMisUsingList = Arrays.asList("Yes", "No");
    private List<String> relativesGenderList = Arrays.asList("Male", "Female");
    private List<String> convivienceDurationList = Arrays.asList("Less than six months",
            "Six months to one year", "One to two years", "More than two years",
            "Just recently moved and not living together",
            "Do not live with him/her but I see them once or more each week",
            "Do not live with him/her but I see them 1-3 times a month",
            "Do not live with him/her but I see them every 3-5 months",
            "Do not live with him/her but I see them once every 6 months",
            "Do not live with him/her but I see them once a year");
    private List<String> relationWithAdictList = Arrays.asList("Father/Mother",
            "Husband/Wife/Partner", "Brother/Sister", "Son/Daughter", "Other");
    private List<String> adictionChangeIn3MonthsList = Arrays.asList("Much better",
            "Somewhat better","The same or unsure","Somewhat worse","Much worse");
    private List<String> countryList = Arrays.asList("Afghanistan",
            "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps",
            "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana",
            "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon",
            "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia",
            "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia",
            "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
            "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador",
            "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji",
            "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
            "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau",
            "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel",
            "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
            "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan",
            "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
            "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
            "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro",
            "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru",
            "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea",
            "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar",
            "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis",
            "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino",
            "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
            "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
            "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Suriname",
            "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan",
            "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia",
            "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
            "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
            "Yemen", "Zambia", "Zimbabwe");
}
