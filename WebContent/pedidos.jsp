<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Pedidos</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	margin-bottom: 10px;
}

li {
	margin: 5px;
	padding: 5px;
	width: 150px;
}
</style>
<script>
	$(function() {
		dragdropinit();
	});
	function dragdropinit() {
		$("#cozinha li, #garcom li").draggable({
			revert : "invalid",
			containment : "document",
			helper : "clone",
			cursor : "crosshair",
			cancel : ".placeholder"
		});
		$("#garcom ol").droppable(
				{
					activeClass : "ui-state-default",
					hoverClass : "ui-state-hover",
					accept : "#cozinha li",
					greedy : true,
					drop : function(event, ui) {
						var id = ui.draggable.text().match(/ID\d/g);
						id = id[0].replace(/ID/g, "");
						$.ajax({
							url : "rest/pedido/atualizaColuna/" + id
									+ "/coluna/2",
							type : "PUT"
						});
						$('<li class="ui-state-highlight"></li>').text(
								ui.draggable.text()).appendTo(this);
						ui.draggable.remove();
					}
				}).sortable({
			items : "li:not(.placeholder)",
			sort : function() {
				$(this).removeClass("ui-state-default");
			}
		});
		$("#caixa ol").droppable(
				{
					activeClass : "ui-state-default",
					hoverClass : "ui-state-hover",
					accept : "#garcom li",
					greedy : true,
					drop : function(event, ui) {
						var id = ui.draggable.text().match(/ID\d/g);
						id = id[0].replace(/ID/g, "");
						$.ajax({
							url : "rest/pedido/atualizaColuna/" + id
									+ "/coluna/3",
							type : "PUT"
						});
						$('<li class="ui-state-highlight"></li>').text(
								ui.draggable.text()).appendTo(this);
						ui.draggable.remove();
					}
				}).sortable({
			items : "li:not(.placeholder)",
			sort : function() {
				$(this).removeClass("ui-state-default");
			}
		});
	}
</script>

<script type="text/javascript">
	jQuery(function($) {
		setInterval(function() {
			dragdropinit();
			$.getJSON('pedidos', function(events) {
				for ( var i in events) {
					var col = events[i].match(/Col\d/g);
					switch (col[0]) {
					case "Col1":
						$('#cozinha ol').append(
								'<li class="ui-state-highlight">' + events[i]
										+ '</li>');
						break;
					case "Col2":
						$('#garcom ol').append(
								'<li class="ui-state-highlight">' + events[i]
										+ '</li>');
						break;
					case "Col3":
						$('#caixa ol').append(
								'<li class="ui-state-highlight">' + events[i]
										+ '</li>');
						break;
					}
				}
			});
		}, 6000);
	});
</script>
</head>
<body>
	<div id="cozinha">
		<ol style="float: left; width: 30%">
			<ins class="placeholder">Fila Cozinha</ins>
			<c:forEach items="${pedidosInit}" var="pedidos">
				<c:if test="${pedidos.coluna == 1}">
					<c:forEach items="${pedidos.itens}" var="item">
						<li class="ui-state-highlight"><c:out value="${item}" />
							Mesa: <c:out value="${pedidos.mesa}" /> Col<c:out
								value="${pedidos.coluna}" /> ID<c:out
								value="${pedidos.idPedido}" /></li>
					</c:forEach>
				</c:if>
			</c:forEach>
		</ol>
	</div>
	<div id="garcom">
		<ol style="float: left; width: 30%">
			<ins class="placeholder">Fila Garçom</ins>
			<c:forEach items="${pedidosInit}" var="pedidos">
				<c:if test="${pedidos.coluna == 2}">
					<c:forEach items="${pedidos.itens}" var="item">
						<li class="ui-state-highlight"><c:out value="${item}" />
							Mesa: <c:out value="${pedidos.mesa}" /> Col<c:out
								value="${pedidos.coluna}" /> ID<c:out
								value="${pedidos.idPedido}" /></li>
					</c:forEach>
				</c:if>
			</c:forEach>
		</ol>
	</div>
	<div id="caixa">
		<ol style="float: left; width: 30%" class="sortable-class">
			<ins class="placeholder">Fila Caixa</ins>
			<c:forEach items="${pedidosInit}" var="pedidos">
				<c:if test="${pedidos.coluna == 3}">
					<c:forEach items="${pedidos.itens}" var="item">
						<li class="ui-state-highlight"><c:out value="${item}" />
							Mesa: <c:out value="${pedidos.mesa}" /> Col<c:out
								value="${pedidos.coluna}" /> ID<c:out
								value="${pedidos.idPedido}" /></li>
					</c:forEach>
				</c:if>
			</c:forEach>
		</ol>
	</div>
</body>
</html>