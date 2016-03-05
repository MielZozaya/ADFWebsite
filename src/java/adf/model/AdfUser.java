package adf.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 *
 * @author miel
 */
public class AdfUser implements UserDetails {

    private String username;
    private String nickname;
    private boolean professionalLevel;
    private int version;
    private String email;
    private String password;
    private String phoneNum;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private Role role;
    private Address address;
    private Set<InboxMessage> recievedMessages = new HashSet<InboxMessage>();
    private Set<InboxMessage> sentMessages = new HashSet<InboxMessage>();
    private Set<AdfUser> clients = new HashSet<AdfUser>();
    private AdfUser professional;
    private ADFProgramAnswers adfProgramAnswers = new ADFProgramAnswers();
    private boolean denyProfessionalContact = false;
    private boolean denyProfessionalSupervising = false;
    private boolean postContactable = false;
    private Demographics demographics;
    private ProfessionalLoginPetitionForm professionalLoginPetitionForm;
    private Date creationDate;

    public AdfUser(String username, boolean professional, String email, String phoneNum, Date creationDate) {
        this.username = username;
        this.professionalLevel = professional;
        this.email = email;
        this.phoneNum = phoneNum;
        this.creationDate = creationDate;
        this.address = null;
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.denyProfessionalContact = false;
        this.denyProfessionalSupervising = false;
        this.postContactable = false;
    }

    public AdfUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public GrantedAuthority[] getAuthorities() {
        Role[] roles = new Role[1];
        roles[0] = role;
        return roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<InboxMessage> getRecievedMessages() {
        return recievedMessages;
    }

    public void setRecievedMessages(Set<InboxMessage> recievedMessages) {
        this.recievedMessages = recievedMessages;
    }

    public void addRecievedMessage(InboxMessage recievedMessage) {
        this.recievedMessages.add(recievedMessage);
    }

    public Set<InboxMessage> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<InboxMessage> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public void addSentMessage(InboxMessage sentMessage) {
        this.sentMessages.add(sentMessage);
    }

    public Set<AdfUser> getClients() {
        return clients;
    }

    public void setClients(Set<AdfUser> clients) {
        this.clients = clients;
    }

    public void addClient(AdfUser client) {
        this.clients.add(client);
        client.setProfessional(this);
    }

    public AdfUser getProfessional() {
        return professional;
    }

    public void setProfessional(AdfUser professional) {
        this.professional = professional;
    }

    public boolean isProfessionalLevel() {
        return professionalLevel;
    }

    public void setProfessionalLevel(boolean professionalLevel) {
        this.professionalLevel = professionalLevel;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ADFProgramAnswers getAdfProgramAnswers() {
        return adfProgramAnswers;
    }

    public void setAdfProgramAnswers(ADFProgramAnswers adfProgramAnswers) {
        this.adfProgramAnswers = adfProgramAnswers;
    }

    public boolean isDenyProfessionalContact() {
        return denyProfessionalContact;
    }

    public void setDenyProfessionalContact(boolean denyProfessionalContact) {
        this.denyProfessionalContact = denyProfessionalContact;
    }

    public boolean isDenyProfessionalSupervising() {
        return denyProfessionalSupervising;
    }

    public void setDenyProfessionalSupervising(boolean denyProfessionalSupervising) {
        this.denyProfessionalSupervising = denyProfessionalSupervising;
    }

    public boolean isPostContactable() {
        return postContactable;
    }

    public void setPostContactable(boolean postContactable) {
        this.postContactable = postContactable;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    public ProfessionalLoginPetitionForm getProfessionalLoginPetitionForm() {
        return professionalLoginPetitionForm;
    }

    public void setProfessionalLoginPetitionForm(ProfessionalLoginPetitionForm professionalLoginPetitionForm) {
        this.professionalLoginPetitionForm = professionalLoginPetitionForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdfUser)) {
            return false;
        }

        AdfUser user = ((AdfUser) o);

        if (!username.equals(user.username)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AdfUser(");

        sb.append("username=");
        sb.append(username);
        sb.append(", professional=");
        sb.append(professionalLevel);
        sb.append(", version=");
        sb.append(version);
        sb.append(", email=");
        sb.append(email);
        sb.append(", phone number=");
        sb.append(phoneNum);
        sb.append(", password=");
        sb.append(password);
        sb.append(", enabled=");
        sb.append(enabled);
        sb.append(", accountNonExpired=");
        sb.append(accountNonExpired);
        sb.append(", credentialsNonExpired=");
        sb.append(credentialsNonExpired);
        sb.append(", accountNonLocked=");
        sb.append(accountNonLocked);

        if (this.getAuthorities() != null) {
            sb.append(", authorities= ");
            for (int i = 0; i < this.getAuthorities().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.getAuthorities()[i].toString());
            }
        } else {
            sb.append(", No authorities");
        }

        if (this.address != null) {
            sb.append(", address=");
            sb.append(address.toString());
        }

        sb.append(")");
        return sb.toString();
    }
}
