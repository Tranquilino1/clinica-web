const API = {
    auth: '/api/auth/login',
    patients: '/api/patients',
    appointments: '/api/appointments',
    history: '/api/medical-history'
};

let currentUser = null;

document.addEventListener('DOMContentLoaded', () => {
    initAuth();
    initNavigation();
});

// Authentication Logic
function initAuth() {
    const loginForm = document.getElementById('login-form');
    const storedUser = localStorage.getItem('user');

    if (storedUser) {
        loginSuccess(JSON.parse(storedUser));
    }

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const errorMsg = document.getElementById('login-error');

        try {
            const res = await fetch(API.auth, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            if (res.ok) {
                const user = await res.json();
                loginSuccess(user);
            } else {
                errorMsg.style.display = 'block';
            }
        } catch (err) {
            console.error('Auth error:', err);
            errorMsg.innerText = 'Error de conexión con el servidor cloud';
            errorMsg.style.display = 'block';
        }
    });

    document.getElementById('logout-btn').addEventListener('click', () => {
        localStorage.removeItem('user');
        window.location.reload();
    });
}

function loginSuccess(user) {
    currentUser = user;
    localStorage.setItem('user', JSON.stringify(user));
    document.getElementById('login-overlay').style.display = 'none';
    document.getElementById('main-app').style.display = 'flex';
    
    // Update Profile
    document.getElementById('user-name').innerText = user.fullName;
    document.getElementById('user-role').innerText = user.rol;
    document.getElementById('user-avatar').innerText = user.fullName.charAt(0);
    
    applyPermissions(user.rol);
    loadDashboardStats();
}

function applyPermissions(role) {
    const normalizedRole = role.toLowerCase();
    document.querySelectorAll('[class*="role-"]').forEach(el => {
        const allowed = Array.from(el.classList).some(cls => cls === `role-${normalizedRole}`);
        el.style.display = allowed ? 'flex' : 'none';
    });
}

// Navigation
function initNavigation() {
    const navLinks = document.querySelectorAll('#main-nav a');
    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const sectionId = link.getAttribute('data-section');
            
            navLinks.forEach(l => l.classList.remove('active'));
            link.classList.add('active');
            
            document.querySelectorAll('.app-section').forEach(s => s.style.display = 'none');
            document.getElementById(`section-${sectionId}`).style.display = 'block';
            
            loadSectionData(sectionId);
        });
    });
}

function loadSectionData(id) {
    if (id === 'patients') loadPatients();
    if (id === 'appointments') loadAppointments();
}

// Data Fetching
async function loadDashboardStats() {
    try {
        const [patients, appointments] = await Promise.all([
            fetch(API.patients).then(r => r.json()),
            fetch(API.appointments).then(r => r.json())
        ]);
        document.getElementById('count-patients').innerText = patients.length;
        document.getElementById('count-appointments').innerText = appointments.length;
    } catch (e) { console.error('Stats error:', e); }
}

// Patients Module
async function loadPatients() {
    const table = document.querySelector('#patients-table tbody');
    try {
        const data = await fetch(API.patients).then(r => r.json());
        table.innerHTML = data.map(p => `
            <tr onclick="editPatient(${p.id})">
                <td>${p.dni}</td>
                <td>${p.fullName}</td>
                <td>${p.telefono || '-'}</td>
                <td>
                    <button class="btn-primary btn-sm" onclick="viewHistory(${p.id})"><i class="fas fa-file-medical"></i></button>
                </td>
            </tr>
        `).join('');
    } catch (e) { console.error(e); }
}

async function savePatient(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const patient = Object.fromEntries(formData);
    
    await fetch(API.patients, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(patient)
    });
    hideModal('patient-modal');
    loadPatients();
}

// Billing Module
let currentInvoiceItems = [];

async function loadProducts() {
    const products = await fetch('/api/products').then(r => r.json());
    const container = document.getElementById('inventory-list');
    container.innerHTML = products.map(p => `
        <div class="product-item" onclick="addToInvoice('${p.name}', ${p.price})">
            <span>${p.name} - <b>${p.price} FCFA</b></span>
            <i class="fas fa-plus-circle"></i>
        </div>
    `).join('');
}

function addToInvoice(name, price) {
    currentInvoiceItems.push({ name, price, qty: 1 });
    renderInvoice();
}

function renderInvoice() {
    const list = document.getElementById('invoice-items');
    const subtotal = currentInvoiceItems.reduce((acc, item) => acc + item.price, 0);
    const tax = subtotal * 0.15;
    const total = subtotal + tax;

    list.innerHTML = currentInvoiceItems.map(item => `
        <div class="invoice-row">
            <span>${item.name}</span>
            <span>${item.price} FCFA</span>
        </div>
    `).join('');

    document.getElementById('lbl-subtotal').innerText = subtotal + ' FCFA';
    document.getElementById('lbl-tax').innerText = tax + ' FCFA';
    document.getElementById('lbl-total').innerText = total + ' FCFA';
}

// UI Helpers
function showModal(id) { document.getElementById(id).style.display = 'flex'; }
function hideModal(id) { document.getElementById(id).style.display = 'none'; }
