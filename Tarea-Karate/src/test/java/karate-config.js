function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    apiUrl: 'https://api.realworld.io/api/'
   
  }
  if (env == 'dev') {
    config.userEmail= "charlyrosariofuentes@"
    config.userPassword = "12345678" 
  } else if (env == 'e2e') {
    // customize
  }
  return config;
}