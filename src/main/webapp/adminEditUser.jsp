<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 12.07.15
  Time: 6:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<div class="editUserSearchWrapper">
  <h4><label for="inputNumber">Enter Phone:</label></h4>
  <input type="text" class="form-control" id="inputNumber">
  <input type="submit" id="searchNumber" value="Search" onclick="searchClient(this)"/>
</div>

<hr>

<br>
<br>

<div class="editUserWrapper">
  <form class="form-horizontal" role="form" id="editAdminClientForm" action="/AdminUpdateServlet"
        method="post" onsubmit="saveEditUser(this);return false;">
    <div class="form-group ">
      <label class="control-label col-sm-1" for="name" >Name:</label>

      <div class="col-sm-4">

        <input type="text" class="form-control col-sm-offset-1" id="name" name="name"
               placeholder="Enter name" value="${adminEditUser.name}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-1" for="surname">Surname:</label>

      <div class="col-sm-4">
        <input type="text" class="form-control col-sm-offset-1" id="surname"
               name="surname" placeholder="Enter surname" value="${adminEditUser.surname}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-1" for="date">Date:</label>

      <div class="col-sm-4">
        <input type="date" class="form-control col-sm-offset-1" id="date" name="date"
               placeholder="Enter Date" <%--value="<fmt:formatDate pattern="yyyy-MM-dd" value="${adminEditUser.dateOfBirth}"/>"--%> required>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1" for="passport">Passport:</label>

      <div class="col-sm-4">
        <input type="text" class="form-control col-sm-offset-1" id="passport"
               name="passport" placeholder="Enter Passport"  value="${adminEditUser.passportData}"required>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1" for="address">Address:</label>

      <div class="col-sm-4">
        <input type="text" class="form-control col-sm-offset-1" id="address"
               name="address" placeholder="Enter address" value="${adminEditUser.address}" required>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1" for="email">Email:</label>

      <div class="col-sm-4">
        <input type="text" class="form-control col-sm-offset-1" id="email" name="email"
               placeholder="Enter email" value="${adminEditUser.email}" required>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1" for="password">Password:</label>

      <div class="col-sm-4">
        <input type="password" class="form-control col-sm-offset-1" id="password"
               name="password" placeholder="Enter password" value="${adminEditUser.password}" required>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1" for="number">Number:</label>

      <div class="col-sm-4">
        <input type="text" class="form-control col-sm-offset-1" id="number"
               name="number" value="${adminEditUserNumber}"
               placeholder="Enter phone number" required>
      </div>
    </div>
    <input type="hidden" name="type" value="editAdminUser">

    <div class="form-group ">
      <label class="control-label col-sm-1" for="selectPlanEditUser">Plan:</label>
      <div class="col-sm-4">
        <select class="form-control col-sm-offset-1" id="selectPlanEditUser" onchange="selectNewPlanEditUser(this)">
          <c:forEach var="plan" items="${allPlanList}">
            <c:choose>
              <c:when test="${plan.name eq adminEditUserNumber}">
                <option value="${plan.name}"  selected >${plan.name}</option>
              </c:when>
              <c:otherwise>
                <option value="${plan.name}">${plan.name}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group ">
      <label class="control-label col-sm-1">Options:</label>

      <div class="selectEditClientOptionWrapper">
        <div class="col-sm-4" id="selectEditClientOption">
          <c:forEach var="entry" items="${optionsEditUser}">
            <c:choose>
              <c:when test="${entry.value eq false}">
                <div class="checkbox col-sm-offset-1">
                  <label><input type="checkbox" name="optionEditClientPlan"
                                onclick="selectOptionAdminEditUser()"
                                value="${entry.key.name}_edit">${entry.key.name}
                  </label>
                </div>
              </c:when>
              <c:otherwise>
                <div class="checkbox disabled col-sm-offset-1">
                  <label><input type="checkbox" name="optionEditClientPlan"
                                onclick="selectOptionAdminEditUser()"
                                value="${entry.key.name}_edit"
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
</div>
</html>
