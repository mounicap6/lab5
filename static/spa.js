var table = document.getElementById("buddiesTable");
var submitbutton = document.getElementById("submit");
var addressBookBuddiesLink = null;
var submitAB = document.getElementById("submitAB");


createNewAddressBook();

function createNewAddressBook(){
    fetch('http://localhost:8080/addressbook', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    }).then(function (value) {
        value.json().then(function (addressbook) {
            addressBookBuddiesLink = addressbook._links.self.href;
        });
    });
};

function addNewBuddy(e) {
    e.preventDefault();
    var name = document.getElementById("buddyName").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var address = document.getElementById("buddyAddress").value;

    fetch('http://localhost:8080/buddy', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "name": name,
            "phoneNumber": phoneNumber,
            "address" : address
        })
    }).then(function (value) {
        value.json().then(function (buddy) {
            var buddyAddressBookUrl = buddy._links.addressBook.href;

            fetch(buddyAddressBookUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'text/uri-list'
                },
                body: addressBookBuddiesLink
            }).then(function (value1) {
                attachBuddyToUI(buddy.name, buddy.phoneNumber, buddy.address);
            })
        });
    });
}

function attachBuddyToUI(name, phoneNumber,address) {
    var buddyRow =  document.createElement('tr')
    buddyRow.innerHTML = "<td>"+name+"</td><td>"+phoneNumber+"</td><td>"+address+"</td>";

    table.tBodies[0].insertBefore(buddyRow,table.rows[table.rows.length-1])
}

submitbutton.addEventListener("click", addNewBuddy);