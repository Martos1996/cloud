<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto ASR new...</title>
</head>
<body>
<script>

function statusChangeCallback(response) {
	 console.log('statusChangeCallback');
	 console.log(response);
	 console.log(response.authResponse.accessToken);
	 //alert(response.authResponse.accessToken);
	 if (response.status === 'connected') {
	 window.location.href='Sign_in_Controller.jsp?access_token='+response.authResponse.accessToken; 
	 } else {
	 // The person is not logged into your app or we are unable to tell.
	 document.getElementById('status').innerHTML = 'Please log ' +
	 'into this app.';
	 }
	 }


  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '553509792090700',
      cookie     : true,   // Enable cookies to allow the server to access the session.
	  secret     :'ff961a31f937c8db001ea75ae27e518f',
      xfbml      : true,                     // Parse social plugins on this webpage.
      version    : 'v5.0'           // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };

  
  (function(d, s, id) {                      // Load the SDK asynchronously
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

 
  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.access_token);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + ' con un id de ' + response.id + '!';
    });
  }

</script>

<fb:login-button scope="public_profile,email,user_photos,user_posts,user_link,user_friends" onlogin="checkLoginState();">
</fb:login-button>
<h1>Si, al iniciar sesion le da error recargue esta pagina</h1>
<div id="status">
</div>

</body>
</html>