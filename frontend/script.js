// API Configuration
const API_BASE_URL = '/api';

// Global state
let employees = [];
let orgUnits = [];

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    setupEventListeners();
    loadOrgUnits();
    loadEmployees();
});

// Event Listeners
function setupEventListeners() {
    document.getElementById('employeeForm').addEventListener('submit', handleEmployeeSubmit);
}

// Tab Management
function showTab(tabName) {
    // Hide all tab contents
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });
    
    // Remove active class from all tab buttons
    document.querySelectorAll('.tab-button').forEach(button => {
        button.classList.remove('active');
    });
    
    // Show selected tab
    document.getElementById(tabName).classList.add('active');
    
    // Add active class to clicked button
    event.target.classList.add('active');
}

// API Helper Functions
async function apiRequest(endpoint, options = {}) {
    showLoading(true);
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        });
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('API request failed:', error);
        showMessage(`Error: ${error.message}`, 'error');
        throw error;
    } finally {
        showLoading(false);
    }
}

// Employee Functions
async function loadEmployees() {
    try {
        employees = await apiRequest('/employees');
        displayEmployees(employees);
    } catch (error) {
        console.error('Failed to load employees:', error);
    }
}

async function createEmployee(employeeData) {
    try {
        const newEmployee = await apiRequest('/employees', {
            method: 'POST',
            body: JSON.stringify(employeeData)
        });
        showMessage('Employee created successfully!', 'success');
        loadEmployees();
        return newEmployee;
    } catch (error) {
        console.error('Failed to create employee:', error);
        showMessage('Failed to create employee. Please try again.', 'error');
    }
}

async function deleteEmployee(id) {
    if (!confirm('Are you sure you want to delete this employee?')) {
        return;
    }
    
    try {
        await apiRequest(`/employees/${id}`, {
            method: 'DELETE'
        });
        showMessage('Employee deleted successfully!', 'success');
        loadEmployees();
    } catch (error) {
        console.error('Failed to delete employee:', error);
        showMessage('Failed to delete employee. Please try again.', 'error');
    }
}

async function searchEmployeeByUserId(userId) {
    try {
        const employee = await apiRequest(`/employees/by-user-id/${userId}`);
        displayEmployees([employee]);
    } catch (error) {
        console.error('Failed to search employee:', error);
        displayEmployees([]);
    }
}

function searchEmployees() {
    const searchTerm = document.getElementById('searchEmployee').value.toLowerCase().trim();
    
    if (!searchTerm) {
        displayEmployees(employees);
        return;
    }
    
    // Try searching by user ID first
    if (searchTerm.length >= 3) {
        searchEmployeeByUserId(searchTerm);
    } else {
        // Local search by name
        const filteredEmployees = employees.filter(emp => 
            emp.firstName.toLowerCase().includes(searchTerm) ||
            emp.lastName.toLowerCase().includes(searchTerm) ||
            emp.userId.toLowerCase().includes(searchTerm) ||
            emp.position.toLowerCase().includes(searchTerm)
        );
        displayEmployees(filteredEmployees);
    }
}

function displayEmployees(employeeList) {
    const container = document.getElementById('employeesList');
    
    if (!employeeList || employeeList.length === 0) {
        container.innerHTML = '<p style="text-align: center; color: #7f8c8d; padding: 20px;">No employees found.</p>';
        return;
    }
    
    container.innerHTML = employeeList.map(employee => `
        <div class="employee-card">
            <div class="employee-header">
                <div class="employee-name">${employee.firstName} ${employee.lastName}</div>
                <button class="btn btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button>
            </div>
            <div class="employee-details">
                <div class="employee-detail">
                    <strong>User ID:</strong>
                    <span>${employee.userId}</span>
                </div>
                <div class="employee-detail">
                    <strong>Position:</strong>
                    <span>${employee.position}</span>
                </div>
                <div class="employee-detail">
                    <strong>Date of Birth:</strong>
                    <span>${formatDate(employee.dateOfBirth)}</span>
                </div>
                <div class="employee-detail">
                    <strong>Organization:</strong>
                    <span>${getOrgUnitName(employee.organizationalUnitId)}</span>
                </div>
            </div>
        </div>
    `).join('');
}

// Organization Unit Functions
async function loadOrgUnits() {
    try {
        orgUnits = await apiRequest('/org-units');
        populateOrgUnitSelect();
        displayOrgUnits();
    } catch (error) {
        console.error('Failed to load organizational units:', error);
        // Fallback for when org-units endpoint doesn't exist yet
        // orgUnits = [
        //     { id: 1, description: 'Company', parentId: null },
        //     { id: 2, description: 'Sales', parentId: 1 },
        //     { id: 3, description: 'Engineering', parentId: 1 },
        //     { id: 4, description: 'Human Resources', parentId: 1 },
        //     { id: 5, description: 'Inside Sales', parentId: 2 },
        //     { id: 6, description: 'Field Sales', parentId: 2 },
        //     { id: 7, description: 'Backend', parentId: 3 },
        //     { id: 8, description: 'Frontend', parentId: 3 },
        //     { id: 9, description: 'Recruiting', parentId: 4 },
        //     { id: 10, description: 'Payroll', parentId: 4 }
        // ];
        populateOrgUnitSelect();
        displayOrgUnits();
    }
}

function populateOrgUnitSelect() {
    const select = document.getElementById('orgUnit');
    select.innerHTML = '<option value="">Select Organization Unit</option>';
    
    orgUnits.forEach(unit => {
        const option = document.createElement('option');
        option.value = unit.id;
        option.textContent = unit.description;
        select.appendChild(option);
    });
}

function displayOrgUnits() {
    const container = document.getElementById('orgUnitsList');
    const hierarchy = buildOrgHierarchy();
    
    container.innerHTML = renderOrgHierarchy(hierarchy);
}

function buildOrgHierarchy() {
    const hierarchy = [];
    const unitMap = new Map();
    
    // Create a map for quick lookup
    orgUnits.forEach(unit => {
        unitMap.set(unit.id, { ...unit, children: [] });
    });
    
    // Build the hierarchy
    orgUnits.forEach(unit => {
        const unitWithChildren = unitMap.get(unit.id);
        if (unit.parentId === null) {
            hierarchy.push(unitWithChildren);
        } else {
            const parent = unitMap.get(unit.parentId);
            if (parent) {
                parent.children.push(unitWithChildren);
            }
        }
    });
    
    return hierarchy;
}

function renderOrgHierarchy(units, level = 0) {
    return units.map(unit => {
        const employeeCount = employees.filter(emp => emp.organizationalUnitId === unit.id).length;
        
        let html = `
            <div class="org-unit level-${level}">
                <div class="org-unit-name">${unit.description}</div>
                <div class="org-unit-info">${employeeCount} employees</div>
            </div>
        `;
        
        if (unit.children && unit.children.length > 0) {
            html += renderOrgHierarchy(unit.children, level + 1);
        }
        
        return html;
    }).join('');
}

// Form Handling
async function handleEmployeeSubmit(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const employeeData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        userId: document.getElementById('userId').value,
        position: document.getElementById('position').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        organizationalUnitId: parseInt(document.getElementById('orgUnit').value)
    };
    
    // Validate form
    if (!employeeData.firstName || !employeeData.lastName || !employeeData.userId || 
        !employeeData.position || !employeeData.dateOfBirth || !employeeData.organizationalUnitId) {
        showMessage('Please fill in all fields.', 'warning');
        return;
    }
    
    await createEmployee(employeeData);
    
    // Reset form
    document.getElementById('employeeForm').reset();
}

// Utility Functions
function getOrgUnitName(id) {
    const unit = orgUnits.find(unit => unit.id === id);
    return unit ? unit.description : 'Unknown';
}

function formatDate(dateString) {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString();
}

function showLoading(show) {
    const loading = document.getElementById('loading');
    if (show) {
        loading.classList.remove('hidden');
    } else {
        loading.classList.add('hidden');
    }
}

function showMessage(text, type = 'info') {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = text;
    messageDiv.className = `message ${type}`;
    messageDiv.classList.remove('hidden');
    
    // Auto-hide after 5 seconds
    setTimeout(() => {
        messageDiv.classList.add('hidden');
    }, 5000);
}

// Search functionality
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchEmployee');
    if (searchInput) {
        searchInput.addEventListener('keyup', function(event) {
            if (event.key === 'Enter') {
                searchEmployees();
            }
        });
    }
});