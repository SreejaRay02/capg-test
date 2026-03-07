# Customer Management System - Hibernate JPA Implementation

## Overview

This project demonstrates a complete Hibernate JPA implementation with **One-to-One mapping** between Customer and Order entities, including all CRUD operations.

## Implementation Details

### Question 1: Entity Classes with One-to-One Mapping ✅

#### Customer Entity

- **File**: `Customer.java`
- **Annotations**:
  - `@Entity` - Marks the class as a JPA entity
  - `@Table(name = "customers")` - Maps to the "customers" table
  - `@Id` and `@GeneratedValue` - Auto-generated primary key
  - `@OneToOne(mappedBy = "customer")` - One-to-One relationship (inverse side)
  - `CascadeType.ALL` - All operations cascade to Order
  - `FetchType.LAZY` - Lazy loading for better performance

#### Order Entity

- **File**: `Order.java`
- **Annotations**:
  - `@Entity` - Marks the class as a JPA entity
  - `@Table(name = "orders")` - Maps to the "orders" table
  - `@Id` and `@GeneratedValue` - Auto-generated primary key
  - `@OneToOne` - One-to-One relationship (owning side)
  - `@JoinColumn(name = "customer_id", unique = true)` - Foreign key column

### Question 2: DAO Interfaces ✅

#### CustomerDAO Interface

- **File**: `CustomerDAO.java`
- **Methods**:
  - `saveCustomer(Customer customer)` - Save new customer
  - `updateCustomer(Customer customer)` - Update existing customer
  - `deleteCustomerById(int id)` - Delete customer by ID
  - `getCustomerById(int id)` - Fetch customer by ID
  - `getAllCustomers()` - Fetch all customers
  - `getCustomerByEmail(String email)` - Fetch customer by email (JPQL)

#### OrderDAO Interface

- **File**: `OrderDAO.java`
- **Methods**:
  - `saveOrder(Order order)` - Save new order
  - `updateOrder(Order order)` - Update existing order
  - `deleteOrderById(int id)` - Delete order by ID
  - `getOrderById(int id)` - Fetch order by ID

### Question 3: DAO Implementation Classes ✅

#### CustomerDAOImpl

- **File**: `CustomerDAOImpl.java`
- Implements all methods from `CustomerDAO` interface
- Uses `EntityManager` for database operations
- Proper transaction management with rollback on errors
- Error handling with try-catch blocks

#### OrderDAOImpl

- **File**: `OrderDAOImpl.java`
- Implements all methods from `OrderDAO` interface
- Uses `EntityManager` for database operations
- Proper transaction management with rollback on errors
- Error handling with try-catch blocks

### Question 4: JPQL Query ✅

**Location**: `CustomerDAOImpl.java` - `getCustomerByEmail()` method

```java
@Override
public Customer getCustomerByEmail(String email) {
    EntityManager em = emf.createEntityManager();
    try {
        // JPQL Query to fetch customer by email
        TypedQuery<Customer> query = em.createQuery(
            "SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    } catch (Exception e) {
        System.err.println("Error fetching customer by email: " + e.getMessage());
        return null;
    } finally {
        em.close();
    }
}
```

## CRUD Operations Demonstrated

The `App.java` file demonstrates all required operations:

1. **INSERT** - Save new customer with order
2. **FETCH BY ID** - Retrieve customer by ID
3. **UPDATE** - Update customer details
4. **FETCH BY EMAIL** - JPQL query to fetch customer by email
5. **FETCH ALL** - Retrieve all customers
6. **UPDATE ORDER** - Update order details
7. **DELETE** - Delete customer by ID (cascades to order)

## Configuration

### Database Configuration

**File**: `src/main/resources/META-INF/persistance.xml`

Two persistence units are configured:

- **PU01**: PostgreSQL database
- **PU02**: MySQL database (currently used in DAO implementations)

### Maven Dependencies

**File**: `pom.xml`

Key dependencies added:

- `hibernate-core` (5.6.15.Final)
- `javax.persistence-api` (2.2)
- `mysql-connector-j` (8.0.33)
- `postgresql` (42.6.0)

## How to Run

1. **Update Database Configuration** in `persistance.xml`:
   - Set correct database URL, username, and password

2. **Build the Project**:

   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.capgemini.CustomerManagement.App"
   ```

## Key Features

✅ **One-to-One Bidirectional Mapping** between Customer and Order
✅ **Cascade Operations** - Deleting customer automatically deletes associated order
✅ **JPQL Query** - Custom query to fetch customer by email
✅ **Transaction Management** - Proper commit and rollback handling
✅ **Error Handling** - Try-catch blocks with meaningful error messages
✅ **Complete CRUD Operations** - All create, read, update, delete operations implemented

## Project Structure

```
src/main/java/com/capgemini/CustomerManagement/
├── App.java                  # Main application with demo operations
├── Customer.java             # Customer entity class
├── Order.java                # Order entity class
├── CustomerDAO.java          # Customer DAO interface
├── CustomerDAOImpl.java      # Customer DAO implementation
├── OrderDAO.java             # Order DAO interface
└── OrderDAOImpl.java         # Order DAO implementation
```

## Notes

- The application uses **Hibernate 5.6.15** with JPA 2.2
- **Cascade Type ALL** ensures that order operations are synchronized with customer
- **Lazy Loading** is enabled for the Order in Customer entity for performance
- Database schema is automatically created with `hibernate.hbm2ddl.auto=create`
- All SQL queries are logged to console with `hibernate.show_sql=true`
