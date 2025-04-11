<%@ page import="java.util.List, model.SectionEntity, org.hibernate.Session, org.hibernate.SessionFactory, org.hibernate.cfg.Configuration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Admin Panel</h2>
    <h4>Sections</h4>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Section ID</th>
            <th>Section Name</th>
        </tr>
        </thead>
        <tbody>
        <%
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            try (Session hibernateSession = sessionFactory.openSession()) {
                List<SectionEntity> sections = hibernateSession.createQuery("FROM SectionEntity", SectionEntity.class).list();
                for (SectionEntity section : sections) {
        %>
        <tr>
            <td><%= section.getIdSection() %></td>
            <td><%= section.getSectionName() %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
        </tbody>
    </table>
</div>

<div class="mt-3 text-center">
    <a href="index.jsp" class="btn btn-secondary">Log out</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>