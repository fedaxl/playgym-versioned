package controllers;

import play.*;
import play.mvc.*;

public class About extends Controller
{
  public static void index() {
    Logger.info("Rendering about page");
    render ("about.html");
  }
}
