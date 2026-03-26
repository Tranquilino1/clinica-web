document.addEventListener('DOMContentLoaded', () => {
    console.log('Clínica Aauca Web Initialized');
    loadPatients();

    // Mock patient load if API fails or for demo
    function loadPatients() {
        const tableBody = document.querySelector('#patients-table tbody');
        
        fetch('/api/patients')
            .then(res => res.json())
            .then(data => {
                if (data.length === 0) {
                    renderMockData(tableBody);
                } else {
                    renderData(tableBody, data);
                }
            })
            .catch(err => {
                console.warn('Using mock data due to API error:', err);
                renderMockData(tableBody);
            });
    }

    function renderData(container, data) {
        container.innerHTML = data.map(p => `
            <tr>
                <td>${p.dni}</td>
                <td>${p.fullName}</td>
                <td>${p.direccion || 'No especificada'}</td>
                <td><span class="badge ok">Activo</span></td>
                <td><button class="btn-primary" style="padding: 5px 10px; font-size: 0.8rem;">Ver</button></td>
            </tr>
        `).join('');
    }

    function renderMockData(container) {
        const mock = [
            { dni: 'DIP-001234', fullName: 'Santiago Obiang', direccion: 'Malabo II' },
            { dni: 'DIP-005678', fullName: 'Consuelo Ayecaba', direccion: 'Buena Esperanza' }
        ];
        renderData(container, mock);
    }
});
