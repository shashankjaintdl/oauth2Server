package com.ics.icsoauth2server.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    private String uuid;

    @Column(
            name = "first_name",
            columnDefinition = "TEXT",
            length = 50,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "TEXT",
            length = 50,
            nullable = false
    )
    private String lastName;

    @NotNull(message = USERNAME_NOT_NULL)
    @NotBlank(message = USERNAME_NOT_BLANK)
    @NotEmpty(message = USERNAME_NOT_EMPTY)
    @Column(
            name = "username",
            length = 50,
            updatable = false,
            nullable = false,
            unique = true
    )
    private String username;

    @NotNull(message = PASSWORD_NOT_NULL)
    @NotBlank(message = PASSWORD_NOT_BLANK)
    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Email(message = EMAIL_NOT_VALID)
    @NotNull(message = EMAIL_NOT_NULL)
    @NotBlank(message = EMAIL_NOT_BLANK)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @Column(
            name = "email_id",
            unique = true,
            length = 100,
            updatable = false,
            nullable = false
    )
    private String emailId;

    @Length(
            min = 10,
            max = 13,
            message = MOBILE_NO_NOT_EXCEED
    )
    @Column(
            name = "mobile_no",
            length = 13
    )
    private String mobileNo;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credential_expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER,cascade = ALL)
    @JoinTable(
            name = "role_user",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    private Set<Roles> roles;

    @OneToMany(
            fetch = EAGER,
            mappedBy = "user"
    )
    private Set<UserAddress> userAddresses;

    @Transient
    private Collection< ? extends GrantedAuthority> authorities;


    public User(String uuid,String firstName, String lastName, String username, String emailId, String password){
        this.uuid = uuid;
        this.setUpdatedDate(new Date());
        this.setCreatedDate(new Date());
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
    }

    public User(String uuid,String firstName, String lastName, String username, String emailId, String password,Set<Roles> roles){
        this(uuid,firstName,lastName,username,emailId,password);
        if(roles.isEmpty() || roles == null)
            throw new IllegalArgumentException("Roles must not be null");
        this.roles = roles;
    }

    public User(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
