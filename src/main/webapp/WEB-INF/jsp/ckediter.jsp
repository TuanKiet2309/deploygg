<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:webTemplate>
<script type="text/javascript">
	window.onload = function()
	{
		var editor;
	};
	function edit(){
	    editor = CKEDITOR.replace('editor1');
	};
	
	function save(){
		if (CKEDITOR.instances['editor1']) {
			var x = document.getElementById("editor1");
			x.innerHTML=editor.getData();
			alert(x.innerHTML);
			CKEDITOR.instances['editor1'].destroy();
			} 
	};
</script>
<body>
	<form method="POST"  action="/save">
			
            <div name="editor1" id="editor1" rows="10" cols="80" var="editor1">
                ${editor1}
            </div>
            <p>
            <input type="button" value="Edit" onclick="edit()">
            <input  type="button" value="Save" onclick="save()"></p>
                 
        </form>
</body>
</t:webTemplate>
