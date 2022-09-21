package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody//tells compiler this represents a web controller, should have methods to handle events
@RequestMapping("hello") //everything below lives at /hello now
public class HelloController {

    //Handles request at path/hello
//    @GetMapping("hello") //this method will handle get requests, parameter tells spring request lives here
//    @ResponseBody //will only use for next couple of classes until we get to templates
//    public String hello() {
//        return "Hello, Spring!";
//    }

    //lives at /hello/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    //lives at /hello/hello
    // Handles requests of the form /hello?name=LaunchCode
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name) { //query and method param need to be the same
        return "Hello, " + name + "!";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name){
        return "Hello, " + name + "!";
    }

    @RequestMapping(value="helloPost", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(@RequestParam String name, @RequestParam String language) {
        if (name == null) {
            name = "World";
        }

        return createMessage(name, language);
    }

    public static String createMessage(String name, String language){
        String greeting ="";
        if (language.equals("english")) {
            greeting = "Hello,";
        }
        else if (language.equals("french")) {
            greeting = "Bonjour,";
        }
        else if (language.equals("italian")) {
            greeting = "Bonjourno,";
        }
        else if (language.equals("spanish")) {
            greeting = "Hola,";
        }
        else if (language.equals("german")) {
            greeting = "Hallo,";
        }

        return greeting + " " + name + "!";
    }
    @GetMapping("form")
    public String helloForm(){
        return "<html>" +
                    "<body>" +
                        "<form action='/hello/helloPost' method='post'>" + //submit a request to /hello
                        "<input type='text' name='name'>" +
                        "<select name='language'>" +
                            "<option value='english'>English</option>" +
                            "<option value='spanish'>Spanish</option>" +
                            "<option value='french'>French</option>" +
                        "</select>" +
                        "<input type='submit' value='Greet me!'>" +
                        "</form>" +
                    "</body>" +
                "</html>";
    }
}
