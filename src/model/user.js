export default class User{
    constructor(email, password, firstname, lastname, birthday, address, description, gender, role) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
        this.description = description;
        this.gender = gender;
        this.role = role;
    }
    //
    // constructor (username, password, address) {
    //     this.username = username;
    //     this.password = password;
    //     this.address = address;
    // }
}