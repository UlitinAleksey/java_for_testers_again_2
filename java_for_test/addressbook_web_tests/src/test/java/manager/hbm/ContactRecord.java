package manager.hbm;

import jakarta.persistence.*;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middlename")
    public String middlename = "";

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "nickname")
    public String nickname = "";

    @Column(name = "address")
    public String address;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = "";
        this.lastname = lastname;
        this.nickname = "";
        this.address = address;
    }
}