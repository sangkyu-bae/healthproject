
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript" charset="UTF-8">
	
    var msg = `${msg}`;
    var url = `${url}`;
    var path=`${pageContext.request.contextPath}`
    console.log(msg);
    console.log(path+url);
    
    alert(msg);
    location.href = path+url;
    
</script>