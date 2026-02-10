// auth-interceptor.js
// This script automatically injects the JWT Token into every API call

const originalFetch = window.fetch;

window.fetch = async (...args) => {
    let [resource, config] = args;
    
    // 1. Get the User object from Local Storage
    const userStr = localStorage.getItem('user');
    
    if (userStr) {
        const user = JSON.parse(userStr);
        
        // 2. If we have a token, attach it to the headers
        if (user.token) {
            config = config || {};
            config.headers = config.headers || {};
            config.headers['Authorization'] = 'Bearer ' + user.token;
        }
    }
    
    try {
        // 3. Make the actual network request
        const response = await originalFetch(resource, config);

        // 4. Global Error Handling
        if (response.status === 401) {
            // 401 means "Unauthorized" (Token expired or invalid)
            console.warn("Session expired. Logging out...");
            localStorage.removeItem('user');
            window.location.href = 'login.html';
        }

        return response;
    } catch (error) {
        return Promise.reject(error);
    }
};