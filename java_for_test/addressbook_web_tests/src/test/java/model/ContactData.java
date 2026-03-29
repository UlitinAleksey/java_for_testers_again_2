package model;

public record ContactData(String firstname, String middlename, String lastname,String address) {

    public ContactData() {
        this ("","","","");
}

    public ContactData withfirstname(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname,this.address);
    }

    public ContactData withmiddlename(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname,this.address);
    }

    public ContactData withlastname(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname,this.address);
    }

    public ContactData withaddress(String address) {
        return new ContactData(this.firstname, this.middlename, this.lastname, address);
    }

}



