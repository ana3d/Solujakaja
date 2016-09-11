
<%
	if (request.getAttribute("lasku_data") == null) {
		response.sendRedirect("SoluServlet");
		return;
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solukirjanpito</title>
</head>
<link rel="stylesheet" type="text/css" href="main.css">
<script src="main.js"></script>
<body>

	<div class="div" id="addNew">
		<form action="AddServlet" method="post" id="add">
			<table class="table-add">
				<thead>
					<tr>
						<th colspan="2">Lisää uusi lasku</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Lasku:</td>
						<td><input type="text" name="tapahtuma"></td>
					</tr>
					<tr>
						<td>Summa:</td>

						<td><input type="number" step="0.01" min="0" name="summa"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"
							onclick="return IsEmpty();" value="Lisää uusi lasku"
							class="button"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
<div id="wrapper">
	
	<c:forEach var="data" items="${lasku_data}">
		<div class="div">
			<table>
				<thead>
					<tr>
						<th>Laskunumero: <c:out value="${data.lasku_id}" /> / <c:out
								value="${data.tapahtuma}" /></th>
						<th>Maksettu?</th>
					</tr>
				</thead>
				<tr>
					<c:if
						test="${data.huone_1_velka > 0 && data.huone_1_maksettu == 'False'}">
						<td>Huone 1: <font color="red">
						<c:out value="${data.huone_1_velka}" />&#8364;</font>
						</td>
						<td>
							<form method="post" name="velka" action="HandlePayment">
								<input type="hidden" name="room" value="1"> <input
									type="hidden" name="lasku_id" value="${data.lasku_id}">
								<input type="hidden" name="sum" value="${data.huone_1_velka}">
								<button class="button">Kuittaa maksetuksi</button>
							</form>
						</td>
					</c:if>

					<c:if
						test="${data.huone_1_velka == 0 && data.huone_1_maksettu == 'True'}">
						<td colspan="2">Huone 1 kuitattu maksetuksi: <font
							color="green"> <c:out value="${data.huone_1_timestamp}" />
						</font></td>
					</c:if>
				</tr>
				<tr>
					<c:if
						test="${data.huone_2_velka > 0 && data.huone_2_maksettu == 'False'}">
						<td>Huone 2: <font color="red">
						<c:out value="${data.huone_2_velka}" />&#8364;</font>
						</td>
						<td>
							<form method="post" name="velka" action="HandlePayment">
								<input type="hidden" name="room" value="2"> <input
									type="hidden" name="lasku_id" value="${data.lasku_id}">
								<input type="hidden" name="sum" value="${data.huone_2_velka}">
								<button class="button">Kuittaa maksetuksi</button>
							</form>
						</td>
					</c:if>

					<c:if
						test="${data.huone_2_velka == 0 && data.huone_2_maksettu == 'True'}">
						<td colspan="2">Huone 2 kuitattu maksetuksi: <font
							color="green"> <c:out value="${data.huone_2_timestamp}" />
						</font></td>
					</c:if>
				<tr>
					<c:if
						test="${data.huone_3_velka > 0 && data.huone_3_maksettu == 'False'}">
						<td>Huone 3: <font color="red"> 
						<c:out value="${data.huone_3_velka}" />&#8364;
						</font>
						</td>
						<td>
							<form method="post" name="velka" action="HandlePayment">
								<input type="hidden" name="room" value="3"> <input
									type="hidden" name="lasku_id" value="${data.lasku_id}">
								<input type="hidden" name="sum" value="${data.huone_3_velka}">
								<button class="button">Kuittaa maksetuksi</button>
							</form>
						</td>
					</c:if>

					<c:if
						test="${data.huone_3_velka == 0 && data.huone_3_maksettu == 'True'}">
						<td colspan="2">Huone 3 kuitattu maksetuksi: <font
							color="green"> <c:out value="${data.huone_3_timestamp}" />
						</font></td>
					</c:if>
				</tr>
				<tr>
					<c:if
						test="${data.huone_4_velka > 0 && data.huone_4_maksettu == 'False'}">
						<td>Huone 4: <font color="red">
						<c:out value="${data.huone_4_velka}" />&#8364;</font>
						</td>
						<td>
							<form method="post" name="velka" action="HandlePayment">
								<input type="hidden" name="room" value="4"> <input
									type="hidden" name="lasku_id" value="${data.lasku_id}">
								<input type="hidden" name="sum" value="${data.huone_4_velka}">
								<button class="button">Kuittaa maksetuksi</button>
							</form>
						</td>
					</c:if>

					<c:if
						test="${data.huone_4_velka == 0 && data.huone_4_maksettu == 'True'}">
						<td colspan="2">Huone 4 kuitattu maksetuksi: <font
							color="green"> <c:out value="${data.huone_4_timestamp}" />
						</font></td>
					</c:if>
				</tr>

				<tr>

					<c:if test="${data.velkaa_jaljella == 0}">
					
						<td>
						<font color="Green">Kaikki maksaneet</font>
							
								
								
								
						</td>	
						<td>
						<form method="post" name="del" action="Delete">
						<input type="hidden" name="lasku_id" value="${data.lasku_id}">
						<button class="button">Poista maksettu lasku</button>
						</form>
						</td>
						
					</c:if>

					<c:if test="${data.velkaa_jaljella > 0}">
						<td><b>Maksamatta: <c:out value="${data.velkaa_jaljella}" />&#8364;
						</b></td>
						<td><b>Yhteensä <c:out value="${data.total}" />&#8364;</b></td>						
					</c:if>
					

				</tr>
			</table>
		</div>
	</c:forEach>
</div>
</body>
</html>