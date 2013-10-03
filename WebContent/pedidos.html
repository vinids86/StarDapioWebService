<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
function dragdropinit (){
		$("#cozinha li, #garcom li").draggable({
			revert : "invalid",
			containment : "document",
			helper : "clone",
			cursor : "crosshair",
			cancel : ".placeholder"
		});
		$("#garcom ol").droppable({
			activeClass : "ui-state-default",
			hoverClass : "ui-state-hover",
			accept : "#cozinha li",
			greedy : true,
			drop : function(event, ui) {
				
				$('<li class="ui-state-highlight"></li>').text(ui.draggable.text()).appendTo(this);
				ui.draggable.remove();
			}
		}).sortable({
			items : "li:not(.placeholder)",
			sort : function() {
				$(this).removeClass("ui-state-default");
			}
		});
		$("#caixa ol").droppable({
			activeClass : "ui-state-default",
			hoverClass : "ui-state-hover",
			accept : "#garcom li",
			greedy : true,
			drop : function(event, ui) {
				
				$('<li class="ui-state-highlight"></li>').text(ui.draggable.text()).appendTo(this);
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
					$('#cozinha ol').append(
							'<li class="ui-state-highlight">' + events[i]
									+ '</li>');
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
		</ol>
	</div>
	<div id="garcom">
		<ol style="float: left; width: 30%">
			<ins class="placeholder">Fila Garçom</ins>
		</ol>
	</div>
	<div id="caixa">
		<ol style="float: left; width: 30%" class="sortable-class">
			<ins class="placeholder">Fila Caixa</ins>
		</ol>
	</div>
</body>
</html>