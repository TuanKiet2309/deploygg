<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:webTemplate>
<body>
	<form>
            <div name="editor1" id="editor1" rows="10" cols="80" var="editor1">
                ${editor1}
            </div>
            <INPUT TYPE="BUTTON" VALUE="Button 1" ONCLICK="button1()">
            <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                function button1()
            {
                	CKEDITOR.replace( 'editor1' );
            }
                
            </script>
        </form>
</body>
</t:webTemplate>
