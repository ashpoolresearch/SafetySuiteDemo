# HR API Frontend

A clean, responsive vanilla JavaScript frontend for testing the HR API endpoints.

## Features

- **Employee Management**: View, create, and delete employees
- **Organizational Structure**: Display hierarchical company structure
- **Search Functionality**: Search employees by name or user ID
- **Responsive Design**: Works on desktop and mobile devices
- **Real-time Updates**: Automatically refreshes data after operations

## Getting Started

1. **Start the HR API backend** (from the `hr-api/` directory):
   ```bash
   ./gradlew bootRun
   ```

2. **Open the frontend**:
   - Simply open `index.html` in your web browser
   - Or serve it using a local web server:
     ```bash
     # Using Python
     python -m http.server 8000
     
     # Using Node.js (if you have live-server installed)
     live-server
     ```

3. **Access the application**:
   - Direct file: `file:///path/to/frontend/index.html`
   - Local server: `http://localhost:8000`

## API Configuration

The frontend is configured to connect to the API at `http://localhost:8080/api`. If your API runs on a different port, update the `API_BASE_URL` in `script.js`.

## Functionality

### Employee Management
- **View All Employees**: Displays all employees with their details
- **Add Employee**: Form to create new employees with validation
- **Delete Employee**: Remove employees with confirmation
- **Search**: Find employees by name or user ID

### Organization Structure
- **Hierarchical Display**: Shows company structure with employee counts
- **Visual Levels**: Different colors for organization levels

## CORS Configuration

If you encounter CORS errors, add this configuration to your Spring Boot application:

```java
@CrossOrigin(origins = "*")
```

Or add a global CORS configuration:

```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
```

## Files

- `index.html` - Main HTML structure with tabs and forms
- `styles.css` - Modern CSS styling with responsive design
- `script.js` - JavaScript for API interaction and UI management
- `README.md` - This documentation file