<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 12.07.15
  Time: 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form class="form-horizontal" role="form" id="newClientForm" action="/AdminUpdateServlet"
      method="post">
  <div class="form-group ">
    <label class="control-label col-sm-1" for="name">Name:</label>

    <div class="col-sm-4">

      <input type="text" class="form-control col-sm-offset-1" id="name" name="name"
             placeholder="Enter name" required>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-1" for="surname">Surname:</label>

    <div class="col-sm-4">
      <input type="text" class="form-control col-sm-offset-1" id="surname"
             name="surname" placeholder="Enter surname" required>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-1" for="date">Date:</label>

    <div class="col-sm-4">
      <input type="date" class="form-control col-sm-offset-1" id="date" name="date"
             placeholder="Enter Date" required>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1" for="passport">Passport:</label>

    <div class="col-sm-4">
      <input type="text" class="form-control col-sm-offset-1" id="passport"
             name="passport" placeholder="Enter Passport" required>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1" for="address">Address:</label>

    <div class="col-sm-4">
      <input type="text" class="form-control col-sm-offset-1" id="address"
             name="address" placeholder="Enter address" required>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1" for="email">Email:</label>

    <div class="col-sm-4">
      <input type="text" class="form-control col-sm-offset-1" id="email" name="email"
             placeholder="Enter email" required>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1" for="password">Password:</label>

    <div class="col-sm-4">
      <input type="password" class="form-control col-sm-offset-1" id="password"
             name="password" placeholder="Enter password" required>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1" for="number">Number:</label>

    <div class="col-sm-4">
      <input type="text" class="form-control col-sm-offset-1" id="number"
             name="number"
             placeholder="Enter phone number" required>
    </div>
  </div>
  <input type="hidden" name="type" value="addNewUser">

  <div class="form-group ">
    <label class="control-label col-sm-1" for="selectPlanNewUser">Plan:</label>
    <div class="col-sm-4">
      <select class="form-control col-sm-offset-1" id="selectPlanNewUser">
        <c:forEach var="plan" items="${planList}">
          <option value="${plan.name}">${plan.name}</option>
        </c:forEach>
      </select>
    </div>
  </div>
  <div class="form-group ">
    <label class="control-label col-sm-1">Options:</label>

    <div class="selectNewClientOptionWrapper">
      <div class="col-sm-4" id="selectNewClientOption">
        <c:forEach var="entry" items="${optionsForPlanMap}">
          <c:choose>
            <c:when test="${entry.value eq false}">
              <div class="checkbox col-sm-offset-1">
                <label><input type="checkbox" name="optionNewClientPlan"
                              onclick="selectOptionForPlanNewUser()"
                              value="${entry.key.name}">${entry.key.name}
                </label>
              </div>
            </c:when>
            <c:otherwise>
              <div class="checkbox disabled col-sm-offset-1">
                <label><input type="checkbox" name="optionNewClientPlan"
                              onclick="selectOptionForPlanNewUser()"
                              value="${entry.key.name}"
                              disabled>${entry.key.name}
                </label>
              </div>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-1 col-sm-4">
      <button type="submit" class="btn btn-default">Submit</button>
    </div>
  </div>
</form>
</html>