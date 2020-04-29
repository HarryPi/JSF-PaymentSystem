package entity;


import dto.SystemUserDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String userpassword;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @OneToOne(mappedBy = "systemUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Account account;

    @OneToMany(
            mappedBy = "transactionOwner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SystemTransaction> systemTransactions;

    /**
     * The User of the system
     */
    public SystemUser() {
    }

    /**
     * The user of the system
     *
     * @param username     The user email
     * @param userpassword Password
     * @param name         Users first name
     * @param surname      Users last name
     * @param account      Users active {@link Account} only one can exist
     */
    public SystemUser(
            @NotNull String username,
            @NotNull String userpassword,
            @NotNull String name,
            @NotNull String surname,
            Account account
    ) {
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.account = account;
    }

    public SystemUserDto toDto() {
        return new SystemUserDto(
                this.id,
                this.username,
                this.userpassword,
                this.name,
                this.surname,
                this.account,
                this.systemTransactions
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<SystemTransaction> getSystemTransactions() {
        return systemTransactions;
    }

    public void setSystemTransactions(List<SystemTransaction> systemTransactions) {
        this.systemTransactions = systemTransactions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.userpassword);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userpassword, other.userpassword)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.surname, other.surname);
    }

}
