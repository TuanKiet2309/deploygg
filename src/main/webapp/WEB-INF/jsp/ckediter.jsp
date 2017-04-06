<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:webTemplate>
<script type="text/javascript">
	window.onload = function()
	{
		var editor;
		document.getElementById('textArea').style.display = "none";
	};
	function edit(){
	    editor = CKEDITOR.replace('editor1');
	};
	
	function save(){
		if (CKEDITOR.instances['editor1']) {
			
			var x = document.getElementById("editor1");
			
			document.getElementById('textArea').style.display = "block";
		    document.getElementById('textArea').value=editor.getData();
			
			CKEDITOR.instances['editor1'].destroy();
			
			
		}
	};
</script>
<body>
	<form method="post" action="/" enctype="multipart/form-data" id="divdata">
			
            <div name="editor1" id="editor1" rows="10" cols="80" var="editor1">
                ${editor1}
            </div>
            <input type="text" id="textArea" name="textArea">
            <input type="button" value="Edit" onclick="edit()">
            <input  type="submit" value="Save" onclick="save()">
            
                 
        </form>
</body>

</t:webTemplate>
