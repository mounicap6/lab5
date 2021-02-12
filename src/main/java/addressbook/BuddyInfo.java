package addressbook;

import javax.persistence.*;


@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer age;
    private String name;
    private String phoneNumber;
    private String address;

    @ManyToOne
    //@JoinColumn(name = "addressbookId")
    private AddressBook addressBook;

    protected BuddyInfo(){
        this(null, null,null,null);
    }

    public BuddyInfo(String name, String phoneNumber, String address, Integer age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
    }

    /**
     * Create a buddy without a phone number
     * @param name Name of the buddy
     */

    public BuddyInfo(String name){
        this(name, null,null,null);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age == 0) {
            this.age = 1;
        } else {
            this.age = Math.abs(age);
        }
    }

   /* @Override
        public String toString() {
        //return "Name: " + this.name + " ; Phone number: " + this.phoneNumber + " ; Address: " + this.address + " ; Age: " + this.age;
        return "ID: " + this.id + " ; Name: " + this.name + " ; Phone number: " + this.phoneNumber + " ; Address: " + this.address + " ; Age: " + this.age;
    }*/

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
