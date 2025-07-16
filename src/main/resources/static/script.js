const BASE_URL = '/api/bank';

// Create account
document.getElementById('accountForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const data = {
        accountHolderName: form.accountHolderName.value,
        balance: parseFloat(form.balance.value)
    };

    fetch(BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(() => {
            alert('Account created!');
            form.reset();
            getAccounts();
        })
        .catch(() => alert('Error creating account'));
});

// Get all accounts
function getAccounts() {
    fetch(BASE_URL)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById('accountList');
            list.innerHTML = '';
            data.forEach(account => {
                const item = document.createElement('li');
                item.textContent = `${account.id}: ${account.accountHolderName} — $${account.balance}`;
                list.appendChild(item);
            });
        })
        .catch(() => alert('Error loading accounts'));
}

// Get account by ID
document.getElementById('getByIdForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const id = e.target.id.value;

    fetch(`${BASE_URL}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(account => {
            document.getElementById('accountDetails').innerText =
                `ID: ${account.id}, Name: ${account.accountHolderName}, Balance: $${account.balance}`;
        })
        .catch(() => alert('Account not found'));
});

// Update account
document.getElementById('updateForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const id = form.id.value;

    const data = {
        accountHolderName: form.accountHolderName.value,
    };

    fetch(`${BASE_URL}/${id}/update`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert('Account updated!');
            getAccounts();
        })
        .catch(() => alert('Error updating account'));
});

// Delete account
document.getElementById('deleteForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const id = e.target.id.value;

    fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE'
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert('Account deleted!');
            getAccounts();
        })
        .catch(() => alert('Error deleting account'));
});

// Deposit amount
document.getElementById('depositForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const id = form.id.value;
    const balance = parseFloat(form.balance.value);

    fetch(`${BASE_URL}/${id}/deposit`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ balance: balance })
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert('Deposit successful!');
            getAccounts();
        })
        .catch(() => alert('Error depositing amount'));
});

// Withdraw amount
document.getElementById('withdrawForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const id = form.id.value;
    const balance = parseFloat(form.balance.value);

    fetch(`${BASE_URL}/${id}/withdraw`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ balance: balance })
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert('Withdrawal successful!');
            getAccounts();
        })
        .catch(() => alert('Error withdrawing amount'));
});

document.getElementById('sendMoney').addEventListener('submit',function(e){
 e.preventDefault();
 const form = e.target;
 const senderId = form.sender.value;
 const receiverId = form.receiver.value;
 const amount = parseFloat(form.amount.value);
 const data = { amount: amount}
 fetch(`${BASE_URL}/{senderId}/{receiverId}/sendMoney`,{
  method: 'PUT'
  headers: {'Content-type': 'application/json'},
  body : JSON.stringify(data)
 })
 .then(res =>){
 if(!res.ok) throw new Error();
  alert('Balance transfer successful!');
  getAccounts();
 }
 .catch(() => alert('Failed to transfer amount!! Try again'));
});

// Initial load
getAccounts();
