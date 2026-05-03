package model;

public record ContactData(String id,
                          String firstname,
                          String lastname,
                          String address,
                          String home,
                          String mobile,
                          String work,
                          String secondary) {

    public ContactData() {
        this("", "", "", "", "", "", "", "");
    }

    public ContactData(String firstname, String lastname) {
        this("", firstname, lastname, "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address,  this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address,  this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address,  this.home, this.mobile, this.work, this.secondary);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address,  home, this.mobile, this.work, this.secondary);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address,  this.home, mobile, this.work, this.secondary);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address,  this.home, this.mobile, work, this.secondary);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address,  this.home, this.mobile, this.work, secondary);
    }
}