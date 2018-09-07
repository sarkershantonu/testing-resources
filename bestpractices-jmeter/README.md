# Jmeter - Best Practices

# Property i use 
-user.properties

# Get 10 digit time stamp 
${__javaScript(new Date/1000 | 0,)}

# Regular Expression in http request default is different
- http://example\.com/.*

https://jmeter.apache.org/usermanual/component_reference.html#HTTP_Request_Defaults
