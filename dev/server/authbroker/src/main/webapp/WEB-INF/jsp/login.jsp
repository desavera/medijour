<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.growl.css"/>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="http://cdn.auth0.com/js/lock-9.min.js"></script>
    <script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <script type="text/javascript">
        $(function () {
            var error = ${error};
            if (error) {
                $.growl.error({message: "Please log in"});
            } else {
                $.growl({title: "Welcome!", message: "Please log in"});
            }
        });
        $(function () {
            var lock = new Auth0Lock('${clientId}', '${domain}');

	    // customize with own logo/badge
	    //lock.show({
  	     ////   icon: '/icons/medijour.png'
	    //});

            lock.showSignin({
                authParams: {
                    state: '${state}',
                    // change scopes to whatever you like
                    // claims are added to JWT id_token - openid profile gives everything
                    scope: 'openid roles user_id name nickname email picture'
                },
                responseType: 'code',
                popup: false,
                callbackURL: 'http://localhost:3099/callback'
            });
        });
    </script>
</div>
</body>
</html>
